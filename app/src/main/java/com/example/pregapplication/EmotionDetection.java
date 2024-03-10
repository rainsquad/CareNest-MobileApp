package com.example.pregapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EmotionDetection extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emotion_detection);
    }

    // Declare a constant for the request code
    private static final int REQUEST_IMAGE = 1;

    // Declare a variable for the image URI
    private Uri imageUri;

    // Declare a variable for the server URL
    //private String serverUrl = "http://192.168.8.100:5000/getEmotion";
    String serverUrl = "http://10.0.2.2:5000/getEmotion";

    // Create an Intent to capture or select an image
    public void chooseImage (View view) {
        Intent intent = new Intent ();
        intent.setType ("image/*");
        intent.setAction (Intent.ACTION_GET_CONTENT);
        startActivityForResult (Intent.createChooser (intent, "Select Image"), REQUEST_IMAGE);
    }

    // Handle the result of the Intent
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null && data.getData () != null) {

            // Get the image URI from the data
            imageUri = data.getData ();

            // Upload the image to the server
            uploadImage();
        }
    }

    // Create a method to upload the image to the server
    public void uploadImage() {
        // Check if the image URI is not null

        if (imageUri != null) {
            // Get the image file from the URI
            File imageFile = new File(getPath(imageUri));

            // Create a multipart request body
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", imageFile.getName(), RequestBody.create(MediaType.parse("image/jpeg"), imageFile))
                    .addFormDataPart("userID", "123") // add any other parameters you need
                    .build();

            // Create a request object
            Request request = new Request.Builder()
                    .url(serverUrl)
                    .post(requestBody)
                    .build();

            // Create an OkHttp client object
            OkHttpClient client = new OkHttpClient();

            // Execute the request and handle the response
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    // Handle the failure
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    // Handle the success
                    if (response.isSuccessful()) {

                        TextView textView = findViewById(R.id.textview);

                        // Get the response body as a string
                        String responseBody = response.body().string();

                        // Display the response in a Toast
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(responseBody);
                            }
                        });
                    }
                }
            });
        }
    }

    // Create a helper method to get the file path from the URI
    public String getPath (Uri uri) {
        String result = null;
        String [] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver ().query (uri, projection, null, null, null);

        if (cursor != null && cursor.moveToFirst ()) {
            int column_index = cursor.getColumnIndexOrThrow (MediaStore.Images.Media.DATA);
            result = cursor.getString (column_index);
            cursor.close ();
        }
        return result;
    }
}