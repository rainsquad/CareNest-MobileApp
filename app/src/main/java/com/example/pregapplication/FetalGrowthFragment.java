package com.example.pregapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
        double weight = calculateFetaWeight(bpd, hc, ac, fl);
        System.out.println("Estimated fetal weight: " + weight + " grams");
        // You can display the result or perform further actions with it
        CreatepopUpwindowWeight(weight);

    }
    private double calculateFetaWeight(double bpd, double hc, double ac, double fl) {
        // Hadlock formula constants
        double a = 1.07;
        double b = -0.015;
        double c = -0.007;
        double d = 0.0006;

        // Calculate estimated fetal weight using Hadlock formula
        double weight = a * Math.pow(ac, 2) * hc - b * Math.pow(ac, 2) * fl + c * Math.pow(ac, 3) - d * Math.pow(fl, 2) * bpd;

        return weight;
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