package com.example.pregapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.camera.core.ImageCaptureException;
import androidx.camera.view.PreviewView;

import androidx.annotation.NonNull;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.pregapplication.classes.Services;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EmotionDetectionFragment extends Fragment {
    // Get the URL of the flask server
    String serverUrl = Services.ipAddress + "/getEmotion";

    // Declare the global variables
    private Button analyzeButton;
    FloatingActionButton cameraChangeBtn;
    Boolean isAnalyzing = false;
    TextView textView;
    private Handler handler;
    PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private static final int REQUEST_CAMERA_PERMISSION = 100;
    private ImageCapture frameCapture;

    ArrayList<File> list;
    int lenseFace = CameraSelector.LENS_FACING_BACK;

    private static final long TIMEOUT_MILLIS = 800; // Timeout duration in milliseconds

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.emotion_detection, container, false);

        handler = new Handler();
        textView = v.findViewById(R.id.textview);
        previewView = v.findViewById(R.id.cameraPreviewView);
        cameraChangeBtn = v.findViewById(R.id.camera_change_btn);
        cameraChangeBtn.setOnClickListener(view -> changeCamera());

        cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext());

        // Initialize the frameCapture use case
        frameCapture = new ImageCapture.Builder().build();

        // Bind the camera when permission is granted
        requestCameraPermission();

        analyzeButton = v.findViewById(R.id.analyzeButton);
        analyzeButton.setOnClickListener(view -> recordVideo());

        return v;
    }

    private Runnable captureTask = new Runnable() {
        @Override
        public void run() {
            if (isAnalyzing) {
                captureFrame();
                handler.postDelayed(this, TIMEOUT_MILLIS); // Schedule next capture
            }
        }
    };

    private void startCapturing() {
        handler.postDelayed(captureTask, TIMEOUT_MILLIS); // Start capturing immediately
    }

    private void stopCapturing() {
        handler.removeCallbacks(captureTask); // Stop capturing
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            bindCamera();
        }
    }

    private void changeCamera() {
        if (lenseFace == CameraSelector.LENS_FACING_BACK) {
            lenseFace = CameraSelector.LENS_FACING_FRONT;
        } else {
            lenseFace = CameraSelector.LENS_FACING_BACK;
        }
        bindCamera();
    }

    private void bindPreview(ProcessCameraProvider cameraProvider) {

        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(lenseFace)
                .build();

        // Unbind the use cases before rebinding
        cameraProvider.unbindAll();

        // Bind the camera and frameCapture use case
        cameraProvider.bindToLifecycle(this, cameraSelector, preview, frameCapture);
        preview.setSurfaceProvider(previewView.getSurfaceProvider());
    }

    private void bindCamera() {
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                // Handle the exception appropriately
            }
        }, ContextCompat.getMainExecutor(requireContext()));
    }

    private File getOutputDirectory(Context context) {
        // Get the external files directory
        File mediaDir = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES);

        // Create a subdirectory for your app's videos if it doesn't exist
        File appDir = new File(mediaDir, "PregApplication");
        appDir.mkdirs();

        return appDir;
    }

    private void captureFrame() {
        // Set up output file to save the captured frame
        File outputDirectory = getOutputDirectory(requireContext());
        File frameFile = new File(outputDirectory, System.currentTimeMillis() + ".jpg");

        // Create the output options for the ImageCapture use case
        ImageCapture.OutputFileOptions outputFileOptions =
                new ImageCapture.OutputFileOptions.Builder(frameFile).build();

        // Capture the frame
        frameCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(requireContext()),
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        list.add(frameFile);
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        // Image capture failure
                        exception.printStackTrace();
                    }
                });
    }

    public void uploadToBackend() {
        OkHttpClient client = new OkHttpClient();
        // Initialize MultipartBody.Builder
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        if (list.isEmpty()){
            return;
        }

        // Iterate over the ArrayList and add each file as a part in the builder
        for (File file : list) {
            // Add each file as a part with a unique name
            builder.addFormDataPart("frames", file.getName(), RequestBody.create(file, MediaType.parse("image/jpeg")));
        }

        // Build the request body
        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(serverUrl)
                .post(requestBody)
                .build();

        // Execute the request and handle the response
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                // Handle the failure
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                // Handle the success
                if (response.isSuccessful()) {

                    // Get the response body as a string
                    String responseBody = response.body().string();

                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(responseBody);
                        }
                    });
                }
            }
        });
    }

    // Method to start the frame recording
    public void startRecording() {
        list = new ArrayList<>();
            startCapturing();
    }

    // Method to stop the frame recording
    public void stopRecording() {
        stopCapturing();
    }

    // Method to handle the button click
    public void recordVideo() {
        // Check if the recording is already in progress or not
        if (!isAnalyzing) {
            // Start the recording and change the button text
            startRecording();
            analyzeButton.setText("Stop");
            isAnalyzing = true;
        } else {
            // Stop the recording, upload the frames, and change the button text
            stopRecording();
            uploadToBackend();
            analyzeButton.setText("Analyze");
            isAnalyzing = false;
        }
    }
}