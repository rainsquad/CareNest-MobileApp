package com.example.pregapplication;

//import static com.example.tranquilapplication.Services.Constants.PREFERENCE_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.pregapplication.QuestionsandAnswers.QuestionAnswersClass;
//import com.example.tranquilapplication.QuestionsandAnswers.QuestionAnswersClass;
//import com.example.tranquilapplication.ResponseModels.QuestionnaireResponseModel;
//import com.example.tranquilapplication.Services.Constants;
//import com.example.tranquilapplication.Services.NetworkClient;
//import com.example.tranquilapplication.Services.NetworkService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuestionnaireActivity extends AppCompatActivity implements View.OnClickListener {

    TextView userid, questionTextView, depressiontype;
    ImageView imgBack;
    Button ansA, ansB, ansC, ansD, submitBtn, btn_restart, SeeResults;
    int totalQuestion = QuestionAnswersClass.question.length;
    int currentQuestionIndex, score = 0;
    SharedPreferences sharedPreferences;
    private static final String KEY_ID = "id";

    private static final String KEY_DEPRESSION_TYPE = "depressionType";
    String todaydate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    RelativeLayout layout;
    String selectedAnswer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire1);
        userid = findViewById(R.id.userid);
        imgBack = findViewById(R.id.imgBack);
        btn_restart = findViewById(R.id.btn_restart);
        depressiontype = findViewById(R.id.Depressiontype);
        SeeResults = findViewById(R.id.btnSeeResults);
        SeeResults.setVisibility(View.INVISIBLE);


        /* Get user details using shared preferances*/
//        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
//        String id = sharedPreferences.getString(KEY_ID, null);
//        String Depressiontype = sharedPreferences.getString(KEY_DEPRESSION_TYPE, null);
//        userid.setText(id);
//        depressiontype.setText(Depressiontype);


        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.answerA);
        ansB = findViewById(R.id.answerB);
        ansC = findViewById(R.id.answerC);
        ansD = findViewById(R.id.answerD);
        submitBtn = findViewById(R.id.submit_btn);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        SeeResults.setOnClickListener(this);
        btn_restart.setBackground(ContextCompat.getDrawable(QuestionnaireActivity.this, R.drawable.button_round));


        loadNewQuestion();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuestionnaireActivity.super.onBackPressed();
            }
        });
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartQuiz();
                btn_restart.setBackground(ContextCompat.getDrawable(QuestionnaireActivity.this, R.drawable.button_round_grey));
            }
        });

        SeeResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionnaireActivity.this, ScreenTestResutsActivity.class);

                //get total marks and type of depression from previous activities
                intent.putExtra("message", score);
                intent.putExtra("depressionType", depressiontype.getText().toString());
                startActivity(intent);
                finish();
            }
        });


    }


    @Override
    public void onClick(View view) {


        ansA.setBackground(ContextCompat.getDrawable(this, R.drawable.button_round_grey));
        ansB.setBackground(ContextCompat.getDrawable(this, R.drawable.button_round_grey));
        ansC.setBackground(ContextCompat.getDrawable(this, R.drawable.button_round_grey));
        ansD.setBackground(ContextCompat.getDrawable(this, R.drawable.button_round_grey));

        //Validating answer selection ( User should select an answer before pressing submit button, and also user should answer all 10 questions)

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {

            if (currentQuestionIndex == 0 || currentQuestionIndex == 1 || currentQuestionIndex == 3) {

                if (selectedAnswer.equals(QuestionAnswersClass.Answer1[currentQuestionIndex])) {
                    score = new Integer(score + 0);
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else if (selectedAnswer.equals(QuestionAnswersClass.Answer2[currentQuestionIndex])) {
                    score = new Integer(score + 1);
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else if (selectedAnswer.equals(QuestionAnswersClass.Answer3[currentQuestionIndex])) {
                    score = new Integer(score + 2);
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else if (selectedAnswer.equals(QuestionAnswersClass.Answer4[currentQuestionIndex])) {
                    score = new Integer(score + 3);
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else  {
                    Toast.makeText(QuestionnaireActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();

                }


            }

            else if(currentQuestionIndex==2 ||currentQuestionIndex == 4 ||currentQuestionIndex==5 ||currentQuestionIndex == 6 ||currentQuestionIndex==7 ||currentQuestionIndex == 8 ||currentQuestionIndex==9 ) {
                if (selectedAnswer.equals(QuestionAnswersClass.Answer1[currentQuestionIndex])) {
                    score = new Integer(score + 3);
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else if (selectedAnswer.equals(QuestionAnswersClass.Answer2[currentQuestionIndex])) {
                    score = new Integer(score + 2);
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else if (selectedAnswer.equals(QuestionAnswersClass.Answer3[currentQuestionIndex])) {
                    score = new Integer(score + 1);
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else if (selectedAnswer.equals(QuestionAnswersClass.Answer4[currentQuestionIndex])) {
                    score = new Integer(score + 0);
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else {
                    Toast.makeText(QuestionnaireActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();

                }
            }


        } else {
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            // clickedButton.setBackgroundColor(Color.MAGENTA);
            clickedButton.setBackground(ContextCompat.getDrawable(this, R.drawable.button_round));

        }

    }


    //Loading the next question from data set
    void loadNewQuestion() {

        if (currentQuestionIndex == totalQuestion) {

            finishQuiz();
            submitBtn.setVisibility(View.INVISIBLE);

            //   CreatepopUpwindow();

            return;
        }

        questionTextView.setText(QuestionAnswersClass.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswersClass.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswersClass.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswersClass.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswersClass.choices[currentQuestionIndex][3]);

    }

    void finishQuiz() {

        questionTextView.setText("Test completed.");
        ansA.setVisibility(View.INVISIBLE);
        ansB.setVisibility(View.INVISIBLE);
        ansC.setVisibility(View.INVISIBLE);
        ansD.setVisibility(View.INVISIBLE);
        btn_restart.setVisibility(View.INVISIBLE);
        SeeResults.setVisibility(View.VISIBLE);
//        uploadDataset();

    }


    // upload the test marks, date, user id server database
//    void uploadDataset() {
//        HashMap<String, String> params = new HashMap<>();
//        params.put("testmarks", String.valueOf(score));
//        params.put("date", String.valueOf(todaydate));
//        params.put("userid", userid.getText().toString());
//        upload(params);
//    }

////    public void upload(HashMap<String, String> params) {
////
////
////        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
////        Call<QuestionnaireResponseModel> login = networkService.upload(params);
////        login.enqueue(new Callback<QuestionnaireResponseModel>() {
////            @Override
////            public void onResponse(@NonNull Call<QuestionnaireResponseModel> call, @NonNull Response<QuestionnaireResponseModel> response) {
////                QuestionnaireResponseModel responseBody = response.body();
////                if (responseBody != null) {
////                    if (responseBody.getSuccess().equals("1")) {
////                        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
////                        //  Toast.makeText(QuestionnaireActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
////                        SeeResults.setVisibility(View.VISIBLE);
////
////                    } else {
////                        Toast.makeText(QuestionnaireActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
////                    }
////                }
////
////            }
////
////            @Override
////            public void onFailure(@NonNull Call<QuestionnaireResponseModel> call, @NonNull Throwable t) {
////
////            }
////        });
//    }

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

}