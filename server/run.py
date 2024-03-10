import os
import cv2
import numpy as np
import joblib
import shutil
import matplotlib.pyplot as plt
import base64
import io

from flask import Flask, request, jsonify
from keras.models import load_model
from matplotlib.ticker import MaxNLocator, PercentFormatter


# Flask app object
app = Flask(__name__)

# Import models

# Load Emotion Detection Model
EmotionDetectionModel = load_model(os.path.join('models', 'emotion_detection', 'EmotionDetection.h5'))
# Load Food Suggestion Model
FoodSuggestionModel = joblib.load(os.path.join('models', 'food_suggestion', 'FoodSuggestion.pkl'))
# Load Diseases Suggestion Model
DiseasesSuggestionModel = joblib.load(os.path.join('models', 'diseases_suggestion', 'DiseasesSuggestion.sav'))
# Load Fetal Development and Suggestion Model
FetalDevelopmentAndSuggestionModel = joblib.load(os.path.join('models', 'fetal_development_and_suggestion', 'FetalDevelopmentAndSuggestion.sav'))


# Route to handle the emotion detection
@app.route ('/getEmotion', methods=['POST'])
def emotion_detection():

    # List of emotions
    emotions = ["Angry", "Disgust", "Fear", "Happy", "Neutral", "Sad", "Surprise"]

    # Check if the request has a file part
    if 'frames' not in request.files:
        return 'Try Again'
    
    # Get the frames from the request
    frames_data = request.files.getlist('frames')

    # Create a folder to save frames
    folder = 'uploads'
    if not os.path.exists(folder):
        os.makedirs(folder)
    
    for frame in frames_data:
        # Save frames to the server
        frame_location = os.path.join(folder, frame.filename)
        frame.save(frame_location)

    # Load pre-trained face detector model
    faceCascade = cv2.CascadeClassifier('models/emotion_detection/face_detector/haarcascade_frontalface.xml')

    frames = []

    for frame in os.listdir(folder):
        try:
            frame_array = cv2.imread(os.path.join(folder, frame))
            frames.append(frame_array)
        except Exception as e:
            pass

    # Remove the uploaded frames
    shutil.rmtree(folder)

    # Create an empty list to store the emotion predictions
    predictions = []

    # Loop through the frames
    for frame in frames:
        # Convert the frame to grayscale
        grayscale = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

        # Detect faces in the frame
        faces = faceCascade.detectMultiScale(grayscale, 1.1, 4)

        # Iterate through detected faces
        for x, y, w, h in faces:
            roi_gray = grayscale[y:y+h, x:x+w]
            roi_color = frame[y:y+h, x:x+w]
            cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 250, 0), 2)
            facess = faceCascade.detectMultiScale(roi_gray)

            # Check if face is detected
            if len(facess) == 0:
                return 'Face data error!'
            else:
                for (ex, ey, ew, eh) in facess:
                    face_roi = roi_color[ey: ey+eh, ex:ex + ew]

        # Resize face data for prediction
        face_data = cv2.resize(face_roi, (224, 224))
        face_data = np.expand_dims(face_data, axis=0)
        face_data = face_data / 255.0

        # Predict emotion
        EmotionSuggestion = EmotionDetectionModel.predict(face_data)

        # Append the emotion prediction to the predictions list
        predictions.append(emotions[np.argmax(EmotionSuggestion)])

    # Print the predicted emotion suggestions
    print(predictions)

    # Count the frequency of each emotion prediction
    counts = {}
    for prediction in predictions:
        counts[prediction] = counts.get(prediction, 0) + 1

    # Get the emotion with the highest count
    most_voted = max(counts, key=counts.get)

    print(most_voted)

    # Return the most voted emotion
    return most_voted


# Route to handle the food suggestion
@app.route ('/getFood', methods=['POST', 'GET'])
def food_suggestion ():

    # Extract the values from the data
    weight = float(request.form['weight']) # in kilograms
    height = float(request.form['height']) # in centimeters
    age = float(request.form['age']) # in years
    weeks = float(request.form['weeks']) # current week of pregnancy
    activity = (str(request.form['activity'])).lower()
    gain = (str(request.form['gain'])).lower()

    # Calculate BMR using the Mifflin-St Jeor equation
    bmr = 10 * weight + 6.25 * height - 5 * age - 161

    # Convert weight to meters
    height = height / 100 # 1 m = 100 cm

    # Calculate BMI using the formula
    bmi = weight / (height ** 2)

    # Multiply BMR by an activity factor
    if activity == "sedentary":
        factor = 1.2
    elif activity == "lightly active":
        factor = 1.375
    elif activity == "moderately active":
        factor = 1.55
    elif activity == "very active":
        factor = 1.725
    else:
        factor = 1.9

    # Calculate TDEE by multiplying BMR by the factor
    tdee = bmr * factor

    # Determine the trimester based on the number of weeks
    if weeks <= 13:
        trimester = 1
    elif weeks <= 27:
        trimester = 2
    else:
        trimester = 3

    # Add extra calories based on the trimester and the rate of weight gain
    if trimester == 1:
        extra = 0
    elif trimester == 2:
        extra = 340
    else:
        extra = 452

    # Adjust the extra calories based on the rate of weight gain
    if gain == "low":
        extra = extra * 0.8
    elif gain == "high":
        extra = extra * 1.2
    
    # Calculate the total calories needed per day
    calories = tdee + extra

    # Compare BMI with the BMI ranges for pregnant women
    if bmi < 18.5:
        tag = 7 # Underweight
    elif bmi < 25:
        tag = 8 # Normal
    elif bmi < 30:
        tag = 9 # Overweight
    else:
        tag = 10 # Obese

    print("Weight: ", weight)
    print("Height: ", height)
    print("Age: ", age)
    print("Weeks: ", weeks)
    print("trimester: ", trimester)
    print("activity: ", activity)
    print("gain: ", gain)
    print("BMI is: ", bmi)
    print("Calories to maintain weight are: ", calories)
    print("BMI tag is: ", tag)

    # Predict food suggestions using the FoodSuggestionModel based on input features
    foodSuggestions = FoodSuggestionModel.predict([[age, weight, height, bmi, calories, tag]])

    # Print the predicted food suggestions
    print(foodSuggestions)

    # Split the food suggestions string into a list
    foodSuggestions = foodSuggestions[0].split(",")

    # Join the first 7 food suggestions from the list into a single string
    foodSuggestions = ", ".join(foodSuggestions[:7])

    # Return the first 7 food suggestions
    return foodSuggestions


# Route to handle the diseases suggestion
@app.route ('/getDiseases', methods=['POST', 'GET'])
def diseases_suggestion ():

    data = request.get_json()
    symptoms = data['symptoms']

    print(symptoms)

    # # Predict diseases suggestions using the DiseasesSuggestionModel based on input data
    diseasesSuggestion = DiseasesSuggestionModel.predict([symptoms])

    # return the response
    return diseasesSuggestion[0]


# Route to handle the fetal development and suggestion
@app.route ('/getFetalDevelopment', methods=['POST', 'GET'])
def fetal_development_and_suggestion ():

    # List of fetal status
    fetal_status = ["Normal", "Suspect", "Pathological"]
    
    # Extract the values from the data
    baseline_value = float(request.form['baseline_value'])
    accelerations = float(request.form['accelerations'])
    fetal_movement = int(request.form['fetal_movement'])
    uterine_contractions = int(request.form['uterine_contractions'])
    light_decelerations = int(request.form['light_decelerations'])
    severe_decelerations = int(request.form['severe_decelerations'])
    prolongued_decelerations = int(request.form['prolongued_decelerations'])
    abnormal_short_term_variability = int(request.form['abnormal_short_term_variability'])
    mean_value_of_short_term_variability = int(request.form['mean_value_of_short_term_variability'])
    percentage_of_time_with_abnormal_long_term_variability = int(request.form['percentage_of_time_with_abnormal_long_term_variability'])
    mean_value_of_long_term_variability = int(request.form['mean_value_of_long_term_variability'])
    histogram_width = int(request.form['histogram_width'])
    histogram_min = int(request.form['histogram_min'])
    histogram_max = int(request.form['histogram_max'])
    histogram_number_of_peaks = int(request.form['histogram_number_of_peaks'])
    histogram_number_of_zeroes = int(request.form['histogram_number_of_zeroes'])
    histogram_mode = int(request.form['histogram_mode'])
    histogram_mean = int(request.form['histogram_mean'])
    histogram_median = int(request.form['histogram_median'])
    histogram_variance = int(request.form['histogram_variance'])
    histogram_tendency = int(request.form['histogram_tendency'])

    FetalDevelopmentSuggestions = FetalDevelopmentAndSuggestionModel.predict_proba(
        [[baseline_value, accelerations, fetal_movement, uterine_contractions, light_decelerations, severe_decelerations,
        prolongued_decelerations, abnormal_short_term_variability, mean_value_of_short_term_variability,
        percentage_of_time_with_abnormal_long_term_variability, mean_value_of_long_term_variability, histogram_width,
        histogram_min, histogram_max, histogram_number_of_peaks, histogram_number_of_zeroes, histogram_mode, histogram_mean,
        histogram_median, histogram_variance, histogram_tendency]]
    )

    print(FetalDevelopmentSuggestions)

    # Apply a style
    plt.style.use('ggplot')

    # Convert probabilities to percentages
    percentages = [p * 100 for p in FetalDevelopmentSuggestions[0].tolist()]

    print(percentages)

    # Define colors for each bar
    colors = ['green', 'orange', 'red']

    # Creating the bar chart with different colors and reduced DPI for performance
    fig, ax = plt.subplots(dpi=80)
    bars = ax.bar(fetal_status, percentages, color=colors)

    # Adding title and labels with increased font size for readability
    ax.set_title('Fetal Status', fontsize=12)
    ax.set_ylabel('Probability (%)', fontsize=12)

    # Set the x-ticks before setting the x-tick labels
    ax.set_xticks(range(len(fetal_status)))
    ax.set_xticklabels(fetal_status, fontsize=15)

    # Optimize performance by limiting the number of y-ticks
    ax.yaxis.set_major_locator(MaxNLocator(integer=True))

    # Use PercentFormatter to convert to percentage format
    ax.yaxis.set_major_formatter(PercentFormatter())

    # Display percentages inside the bars
    for bar in bars:
        height = bar.get_height()
        ax.text(bar.get_x() + bar.get_width() / 2, height / 2, f'{height:.0f}%', ha='center', va='center', color='white', fontsize=20)

    #plt.show()

    # Save the plot to a BytesIO object and convert to base64
    buf = io.BytesIO()
    plt.savefig(buf, format='png')
    buf.seek(0)
    b64_string = base64.b64encode(buf.getvalue()).decode()

    # Close the figure to free memory
    plt.close(fig)

    # b64_string variable contains a plot as a base64 encoded string
    #return b64_string

    # Return the JSON object
    return jsonify(b64_plot = b64_string)


if __name__ == "__main__":
    app.run(host = '0.0.0.0', port = 5000, debug = True)