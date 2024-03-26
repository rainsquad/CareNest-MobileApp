package com.example.pregapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

public class FetalGrowthFragment extends Fragment {
        Spinner spinner;
        TextView growthTracker, healthTracker;

        EditText etBPD, etHC,etAC,etFL;
        LinearLayout layout;

        Button btnSubmit;
        FetalDevelopmentAndSuggestionFragment fetalDevelopmentAndSuggestionFragment = new FetalDevelopmentAndSuggestionFragment();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fetal_growth_fragment, container, false);
        etBPD = v.findViewById(R.id.etBPD);
        etHC = v.findViewById(R.id.etHC);
        etAC = v.findViewById(R.id.etAC);
        etFL = v.findViewById(R.id.etFL);
        btnSubmit = v.findViewById(R.id.submit);
        spinner = v.findViewById(R.id.spinner);
        layout = v.findViewById(R.id.linLayout);
        growthTracker = v.findViewById(R.id.txtFetalGrowth);
        healthTracker = v.findViewById(R.id.txtFetalHealth);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.spinner_items,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        growthTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        healthTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreatepopUpwindow();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateFetalWeight();
            }
        });


        return v;
    }


    private void calculateFetalWeight() {
        double bpd = Double.parseDouble(etBPD.getText().toString());
        double hc = Double.parseDouble(etHC.getText().toString());
        double ac = Double.parseDouble(etAC.getText().toString());
        double fl = Double.parseDouble(etFL.getText().toString());

        // Calculate fetal movement using formulas
        double weight = calculateFetaWeight(ac,fl,bpd,hc);
        System.out.println("Estimated fetal weight: " + weight + " grams");
        // You can display the result or perform further actions with it
        CreatepopUpwindowWeight(weight);

    }


        public static double calculateFetaWeight(double ac, double fl, double bpd, double headCircumference) {
            double efw = Math.pow(10, 1.335) - 0.0034 * ac * fl + 0.0316 * bpd + 0.0457 * headCircumference + 0.1623 * fl;
            return efw;
        }

        public static void main(String[] args) {
            // Sample fetal measurements
            double ac = 20.5; // Abdominal circumference in cm
            double fl = 5.7;  // Femur length in cm
            double bpd = 3.2; // Biparietal diameter in cm
            double headCircumference = 15.4; // Head circumference in cm

            // Calculate estimated fetal weight
            double estimatedFetalWeight = calculateFetaWeight(ac, fl, bpd, headCircumference);

            Log.d("FetalWeightCalculator", "Estimated Fetal Weight: " + estimatedFetalWeight + " grams");
        }

    private void CreatepopUpwindowWeight(Double weight) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.popup_window_weight, null);


        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;

        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);


        layout.post(new Runnable() {
            @Override
            public void run() {


                popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);


            }
        });


        TextView Gotit,result,iconclose;


        Gotit = popUpView.findViewById(R.id.Gotit);
        result = popUpView.findViewById(R.id.txtResult);
        iconclose = popUpView.findViewById(R.id.iconclose);
        result.setText(String.valueOf(weight)+" grams");
        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                popupWindow.dismiss();



            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();


            }
        });
//         and if you want to close popup when touch Screen
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                popupWindow.dismiss();
                return true;

            }

        });




    }
    private void CreatepopUpwindow() {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_feature_popup, null);


        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;

        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);


        layout.post(new Runnable() {
            @Override
            public void run() {


                popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);


            }
        });


        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                popupWindow.dismiss();
                PremiumPaymentPopupWindow();


            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();


            }
        });
//         and if you want to close popup when touch Screen
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                popupWindow.dismiss();
                return true;

            }

        });




    }






    //premium payment popup window
    private void PremiumPaymentPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_feature_paymentgate_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;


        boolean focusable = true;

        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {

                popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);


            }
        });

        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconClosepayementGate);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
                PremiumPaymentCardDetails();


            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                popupWindow.dismiss();
                return true;
            }
        });

    }



    //Premium payment enter card details popup

    private void PremiumPaymentCardDetails() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_payment_details_enter_popup, null);


        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {

                popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);


            }
        });

        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
                PremiumPaymentDone();


            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                popupWindow.dismiss();
                return true;
            }
        });

    }
    private void PremiumPaymentDone() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_payment_approved_popup, null);


        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;

        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {

                popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);


            }
        });
        TextView Gotit;
        ImageView iconclose;
        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fetalDevelopmentAndSuggestionFragment)
                        .addToBackStack(null)  // Add to back stack if needed
                        .commit();
            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                popupWindow.dismiss();
                return true;
            }
        });

    }
}