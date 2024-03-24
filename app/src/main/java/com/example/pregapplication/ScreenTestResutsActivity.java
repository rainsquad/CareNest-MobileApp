package com.example.pregapplication;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ScreenTestResutsActivity extends AppCompatActivity {

    TextView txtView, txtNextStep, txtResultsSuggest;

    Button btnNextStep;

    LinearLayout popupbg, button;

    Dialog mDialog;
    RelativeLayout layout;
    ImageView imgBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_test_resuts);
        txtView = findViewById(R.id.txtScore);
        txtNextStep = findViewById(R.id.txtNextStep);
        btnNextStep = findViewById(R.id.btnNextStep);
        btnNextStep.setVisibility(View.INVISIBLE);
        mDialog = new Dialog(this);
        layout = findViewById(R.id.testpopup);
        button = findViewById(R.id.button);
        imgBack = findViewById(R.id.imgBack);


       // createNotificationChannel();


        //Get the score value from previous activity
        Intent myintent = getIntent();
        int score = myintent.getIntExtra("message", 0);
        String depressionType = myintent.getStringExtra("depressionType");

        txtView.setText("YOUR SCORE IS : " + score + "/30  ");


        txtResultsSuggest = findViewById(R.id.txtResultsSuggest);
        if (score <= 8) {
            txtResultsSuggest.setText("YOUR TEST SCORE OF " + score + "/30 SUGGESTS THAT YOU HAVE NO RISK OF " + depressionType + " DEPRESSION AT THE TIME. WHAT YOU ARE EXPERIENCING COULD BE A MILD DISCOMFORT.");
            txtNextStep.setText("IN CASE YOU CONTINUE EXPERIENCING ANY DISCOMFORTING SYMPTOMS IT IS RECOMMENDED THAT YOU CONDUCT ANOTHER SCREENING TEST IN 2 WEEKS TIME.");
            //notifypatient();
        } else {
            txtResultsSuggest.setText("YOUR TEST SCORE OF " + score + "/30 SUGGESTS THAT YOU MAY BE HAVING A RISK OF " + depressionType + " DEPRESSION. YOU ABSOLUTELY HAVE NOTHING TO WORRY ABOUT SINCE " + depressionType + " DEPRESSION IS A COMPLETELY NORMAL AND TREATABLE CONDITION!.");
            txtNextStep.setText("SINCE YOUR TEST SCORE REFLECTS A RISK FOR " + depressionType + " DEPRESSION, WE SUGGEST THAT YOU PROCEED TO CLINICAL DIAGNOSIS WITH A PSYCHIATRIST.");
            btnNextStep.setText("CLICK HERE");
            btnNextStep.setVisibility(View.VISIBLE);
        }
        btnNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//               Intent intentmy = new Intent(ScreenTestResutsActivity.this,Testpopupwindow.class);
//               startActivity(intentmy);
                CreatepopUpwindow(score);
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
//    public void notifypatient()
//    {
//        Toast.makeText(this, "notification set1", Toast.LENGTH_SHORT).show();
//
//
//        Intent intent  = new Intent(ScreenTestResutsActivity.this, NotificationReminderforEPDStestBroadcadst.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(ScreenTestResutsActivity.this,0,intent,0);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//        long timeAtButtonCLick   = System.currentTimeMillis();
//
//        long tenSeconds =  1000*10;
//
//        alarmManager.set(AlarmManager.RTC_WAKEUP,
//                timeAtButtonCLick + tenSeconds,pendingIntent);
//
//    }


//    private  void createNotificationChannel()
//    {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "Tranquil notifications 2";
//            String descripion = "Tranquil Appointment Manager 2 ";
//
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel channel2 = new NotificationChannel("notifyTest", name, importance);
//            channel2.setDescription(descripion);
//
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel2);
//        }
//    }


//    private void createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "Tranquil notifications";
//            String descripion = "Tranquil Appointment Manager";
//
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel channel = new NotificationChannel("notifypatient", name, importance);
//            channel.setDescription(descripion);
//
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }

//    public void notifypatient() {
//        Toast.makeText(this, "notification set", Toast.LENGTH_SHORT).show();
//
//
//        Intent intent = new Intent(ScreenTestResutsActivity.this, NotificationReminderBroadcadst.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(ScreenTestResutsActivity.this, 0, intent, 0);
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//        long timeAtButtonCLick = System.currentTimeMillis();
//
//        long tenSeconds = 1000 * 10;
//
//        alarmManager.set(AlarmManager.RTC_WAKEUP,
//                timeAtButtonCLick + tenSeconds, pendingIntent);
//
//    }
    //pop up window

    private void CreatepopUpwindow(int score) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.mainpopup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

            }
        });

        TextView Gotit;

        Gotit = popUpView.findViewById(R.id.Gotit);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent doc = new Intent(ScreenTestResutsActivity.this, DoctorDetailsActivity.class);
//
//                doc.putExtra("testMarks", score);
//                startActivity(doc);


            }
        });
        // and if you want to close popup when touch Screen
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }


}