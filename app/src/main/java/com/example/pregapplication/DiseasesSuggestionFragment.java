package com.example.pregapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pregapplication.classes.Services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DiseasesSuggestionFragment extends Fragment {
    // Get the URL of the flask server
    String serverUrl = Services.ipAddress + "/getDiseases";

    // Declare the global variables
    Button diseasesAnalyzeButton;
    String diseasesSuggestion;

    // Initialize an ArrayList to hold the checkbox values
    ArrayList<Integer> checkboxValues = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.diseases_suggestion, container, false);

        diseasesAnalyzeButton = v.findViewById(R.id.analyze_button);

        CheckBox checkBox1 = v.findViewById(R.id.checkbox_1);
        CheckBox checkBox2 = v.findViewById(R.id.checkbox_2);
        CheckBox checkBox3 = v.findViewById(R.id.checkbox_3);
        CheckBox checkBox4 = v.findViewById(R.id.checkbox_4);
        CheckBox checkBox5 = v.findViewById(R.id.checkbox_5);
        CheckBox checkBox6 = v.findViewById(R.id.checkbox_6);
        CheckBox checkBox7 = v.findViewById(R.id.checkbox_7);
        CheckBox checkBox8 = v.findViewById(R.id.checkbox_8);
        CheckBox checkBox9 = v.findViewById(R.id.checkbox_9);
        CheckBox checkBox10 = v.findViewById(R.id.checkbox_10);
        CheckBox checkBox11 = v.findViewById(R.id.checkbox_11);
        CheckBox checkBox12 = v.findViewById(R.id.checkbox_12);
        CheckBox checkBox13 = v.findViewById(R.id.checkbox_13);
        CheckBox checkBox14 = v.findViewById(R.id.checkbox_14);
        CheckBox checkBox15 = v.findViewById(R.id.checkbox_15);
        CheckBox checkBox16 = v.findViewById(R.id.checkbox_16);
        CheckBox checkBox17 = v.findViewById(R.id.checkbox_17);
        CheckBox checkBox18 = v.findViewById(R.id.checkbox_18);
        CheckBox checkBox19 = v.findViewById(R.id.checkbox_19);
        CheckBox checkBox20 = v.findViewById(R.id.checkbox_20);
        CheckBox checkBox21 = v.findViewById(R.id.checkbox_21);
        CheckBox checkBox22 = v.findViewById(R.id.checkbox_22);
        CheckBox checkBox23 = v.findViewById(R.id.checkbox_23);
        CheckBox checkBox24 = v.findViewById(R.id.checkbox_24);
        CheckBox checkBox25 = v.findViewById(R.id.checkbox_25);
        CheckBox checkBox26 = v.findViewById(R.id.checkbox_26);
        CheckBox checkBox27 = v.findViewById(R.id.checkbox_27);
        CheckBox checkBox28 = v.findViewById(R.id.checkbox_28);
        CheckBox checkBox29 = v.findViewById(R.id.checkbox_29);
        CheckBox checkBox30 = v.findViewById(R.id.checkbox_30);
        CheckBox checkBox31 = v.findViewById(R.id.checkbox_31);
        CheckBox checkBox32 = v.findViewById(R.id.checkbox_32);
        CheckBox checkBox33 = v.findViewById(R.id.checkbox_33);
        CheckBox checkBox34 = v.findViewById(R.id.checkbox_34);
        CheckBox checkBox35 = v.findViewById(R.id.checkbox_35);
        CheckBox checkBox36 = v.findViewById(R.id.checkbox_36);
        CheckBox checkBox37 = v.findViewById(R.id.checkbox_37);
        CheckBox checkBox38 = v.findViewById(R.id.checkbox_38);
        CheckBox checkBox39 = v.findViewById(R.id.checkbox_39);
        CheckBox checkBox40 = v.findViewById(R.id.checkbox_40);
        CheckBox checkBox41 = v.findViewById(R.id.checkbox_41);
        CheckBox checkBox42 = v.findViewById(R.id.checkbox_42);
        CheckBox checkBox43 = v.findViewById(R.id.checkbox_43);
        CheckBox checkBox44 = v.findViewById(R.id.checkbox_44);
        CheckBox checkBox45 = v.findViewById(R.id.checkbox_45);
        CheckBox checkBox46 = v.findViewById(R.id.checkbox_46);
        CheckBox checkBox47 = v.findViewById(R.id.checkbox_47);
        CheckBox checkBox48 = v.findViewById(R.id.checkbox_48);
        CheckBox checkBox49 = v.findViewById(R.id.checkbox_49);
        CheckBox checkBox50 = v.findViewById(R.id.checkbox_50);
        CheckBox checkBox51 = v.findViewById(R.id.checkbox_51);
        CheckBox checkBox52 = v.findViewById(R.id.checkbox_52);
        CheckBox checkBox53 = v.findViewById(R.id.checkbox_53);
        CheckBox checkBox54 = v.findViewById(R.id.checkbox_54);
        CheckBox checkBox55 = v.findViewById(R.id.checkbox_55);
        CheckBox checkBox56 = v.findViewById(R.id.checkbox_56);
        CheckBox checkBox57 = v.findViewById(R.id.checkbox_57);
        CheckBox checkBox58 = v.findViewById(R.id.checkbox_58);
        CheckBox checkBox59 = v.findViewById(R.id.checkbox_59);
        CheckBox checkBox60 = v.findViewById(R.id.checkbox_60);
        CheckBox checkBox61 = v.findViewById(R.id.checkbox_61);
        CheckBox checkBox62 = v.findViewById(R.id.checkbox_62);
        CheckBox checkBox63 = v.findViewById(R.id.checkbox_63);
        CheckBox checkBox64 = v.findViewById(R.id.checkbox_64);
        CheckBox checkBox65 = v.findViewById(R.id.checkbox_65);
        CheckBox checkBox66 = v.findViewById(R.id.checkbox_66);
        CheckBox checkBox67 = v.findViewById(R.id.checkbox_67);
        CheckBox checkBox68 = v.findViewById(R.id.checkbox_68);
        CheckBox checkBox69 = v.findViewById(R.id.checkbox_69);
        CheckBox checkBox70 = v.findViewById(R.id.checkbox_70);
        CheckBox checkBox71 = v.findViewById(R.id.checkbox_71);
        CheckBox checkBox72 = v.findViewById(R.id.checkbox_72);
        CheckBox checkBox73 = v.findViewById(R.id.checkbox_73);
        CheckBox checkBox74 = v.findViewById(R.id.checkbox_74);
        CheckBox checkBox75 = v.findViewById(R.id.checkbox_75);
        CheckBox checkBox76 = v.findViewById(R.id.checkbox_76);
        CheckBox checkBox77 = v.findViewById(R.id.checkbox_77);
        CheckBox checkBox78 = v.findViewById(R.id.checkbox_78);
        CheckBox checkBox79 = v.findViewById(R.id.checkbox_79);
        CheckBox checkBox80 = v.findViewById(R.id.checkbox_80);
        CheckBox checkBox81 = v.findViewById(R.id.checkbox_81);
        CheckBox checkBox82 = v.findViewById(R.id.checkbox_82);
        CheckBox checkBox83 = v.findViewById(R.id.checkbox_83);
        CheckBox checkBox84 = v.findViewById(R.id.checkbox_84);
        CheckBox checkBox85 = v.findViewById(R.id.checkbox_85);
        CheckBox checkBox86 = v.findViewById(R.id.checkbox_86);
        CheckBox checkBox87 = v.findViewById(R.id.checkbox_87);
        CheckBox checkBox88 = v.findViewById(R.id.checkbox_88);
        CheckBox checkBox89 = v.findViewById(R.id.checkbox_89);
        CheckBox checkBox90 = v.findViewById(R.id.checkbox_90);
        CheckBox checkBox91 = v.findViewById(R.id.checkbox_91);
        CheckBox checkBox92 = v.findViewById(R.id.checkbox_92);
        CheckBox checkBox93 = v.findViewById(R.id.checkbox_93);
        CheckBox checkBox94 = v.findViewById(R.id.checkbox_94);
        CheckBox checkBox95 = v.findViewById(R.id.checkbox_95);
        CheckBox checkBox96 = v.findViewById(R.id.checkbox_96);
        CheckBox checkBox97 = v.findViewById(R.id.checkbox_97);
        CheckBox checkBox98 = v.findViewById(R.id.checkbox_98);
        CheckBox checkBox99 = v.findViewById(R.id.checkbox_99);
        CheckBox checkBox100 = v.findViewById(R.id.checkbox_100);
        CheckBox checkBox101 = v.findViewById(R.id.checkbox_101);
        CheckBox checkBox102 = v.findViewById(R.id.checkbox_102);
        CheckBox checkBox103 = v.findViewById(R.id.checkbox_103);
        CheckBox checkBox104 = v.findViewById(R.id.checkbox_104);
        CheckBox checkBox105 = v.findViewById(R.id.checkbox_105);
        CheckBox checkBox106 = v.findViewById(R.id.checkbox_106);
        CheckBox checkBox107 = v.findViewById(R.id.checkbox_107);
        CheckBox checkBox108 = v.findViewById(R.id.checkbox_108);
        CheckBox checkBox109 = v.findViewById(R.id.checkbox_109);
        CheckBox checkBox110 = v.findViewById(R.id.checkbox_110);
        CheckBox checkBox111 = v.findViewById(R.id.checkbox_111);
        CheckBox checkBox112 = v.findViewById(R.id.checkbox_112);
        CheckBox checkBox113 = v.findViewById(R.id.checkbox_113);
        CheckBox checkBox114 = v.findViewById(R.id.checkbox_114);
        CheckBox checkBox115 = v.findViewById(R.id.checkbox_115);
        CheckBox checkBox116 = v.findViewById(R.id.checkbox_116);
        CheckBox checkBox117 = v.findViewById(R.id.checkbox_117);
        CheckBox checkBox118 = v.findViewById(R.id.checkbox_118);
        CheckBox checkBox119 = v.findViewById(R.id.checkbox_119);
        CheckBox checkBox120 = v.findViewById(R.id.checkbox_120);
        CheckBox checkBox121 = v.findViewById(R.id.checkbox_121);
        CheckBox checkBox122 = v.findViewById(R.id.checkbox_122);
        CheckBox checkBox123 = v.findViewById(R.id.checkbox_123);
        CheckBox checkBox124 = v.findViewById(R.id.checkbox_124);
        CheckBox checkBox125 = v.findViewById(R.id.checkbox_125);
        CheckBox checkBox126 = v.findViewById(R.id.checkbox_126);
        CheckBox checkBox127 = v.findViewById(R.id.checkbox_127);
        CheckBox checkBox128 = v.findViewById(R.id.checkbox_128);
        CheckBox checkBox129 = v.findViewById(R.id.checkbox_129);
        CheckBox checkBox130 = v.findViewById(R.id.checkbox_130);
        CheckBox checkBox131 = v.findViewById(R.id.checkbox_131);
        CheckBox checkBox132 = v.findViewById(R.id.checkbox_132);

        // Common checked change listener
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                // Update the ArrayList based on the checkbox state
                int index = Integer.parseInt(buttonView.getTag().toString()) - 1; //
                checkboxValues.set(index, isChecked ? 1 : 0);
            }
        };

        // Set the listener to all checkboxes and initialize the ArrayList with default values (0)
        checkBox1.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox1

        checkBox2.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox2

        checkBox3.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox3

        checkBox4.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox4

        checkBox5.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox5

        checkBox6.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox6

        checkBox7.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox7

        checkBox8.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox8

        checkBox9.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox9

        checkBox10.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox10

        checkBox11.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox11

        checkBox12.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox12

        checkBox13.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox13

        checkBox14.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox14

        checkBox15.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox15

        checkBox16.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox16

        checkBox17.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox17

        checkBox18.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox18

        checkBox19.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox19

        checkBox20.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox20

        checkBox21.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox21

        checkBox22.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox22

        checkBox23.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox23

        checkBox24.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox24

        checkBox25.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox25

        checkBox26.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox26

        checkBox27.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox27

        checkBox28.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox28

        checkBox29.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox29

        checkBox30.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox30

        checkBox31.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox31

        checkBox32.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox32

        checkBox33.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox33

        checkBox34.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox34

        checkBox35.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox35

        checkBox36.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox36

        checkBox37.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox37

        checkBox38.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox38

        checkBox39.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox39

        checkBox40.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox40

        checkBox41.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox41

        checkBox42.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox42

        checkBox43.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox43

        checkBox44.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox44

        checkBox45.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox45

        checkBox46.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox46

        checkBox47.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox47

        checkBox48.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox48

        checkBox49.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox49

        checkBox50.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox50

        checkBox51.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox51

        checkBox52.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox52

        checkBox53.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox53

        checkBox54.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox54

        checkBox55.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox55

        checkBox56.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox56

        checkBox57.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox57

        checkBox58.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox58

        checkBox59.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox59

        checkBox60.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox60

        checkBox61.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox61

        checkBox62.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox62

        checkBox63.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox63

        checkBox64.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox64

        checkBox65.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox65

        checkBox66.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox66

        checkBox67.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox67

        checkBox68.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox68

        checkBox69.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox69

        checkBox70.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox70

        checkBox71.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox71

        checkBox72.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox72

        checkBox73.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox73

        checkBox74.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox74

        checkBox75.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox75

        checkBox76.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox76

        checkBox77.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox77

        checkBox78.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox78

        checkBox79.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox79

        checkBox80.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox80

        checkBox81.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox81

        checkBox82.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox82

        checkBox83.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox83

        checkBox84.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox84

        checkBox85.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox85

        checkBox86.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox86

        checkBox87.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox87

        checkBox88.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox88

        checkBox89.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox89

        checkBox90.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox90

        checkBox91.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox91

        checkBox92.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox92

        checkBox93.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox93

        checkBox94.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox94

        checkBox95.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox95

        checkBox96.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox96

        checkBox97.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox97

        checkBox98.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox98

        checkBox99.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox99

        checkBox100.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox100

        checkBox101.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox101

        checkBox102.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox102

        checkBox103.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox103

        checkBox104.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox104

        checkBox105.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox105

        checkBox106.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox106

        checkBox107.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox107

        checkBox108.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox108

        checkBox109.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox109

        checkBox110.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox110

        checkBox111.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox111

        checkBox112.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox112

        checkBox113.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox113

        checkBox114.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox114

        checkBox115.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox115

        checkBox116.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox116

        checkBox117.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox117

        checkBox118.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox118

        checkBox119.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox119

        checkBox120.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox120

        checkBox121.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox121

        checkBox122.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox122

        checkBox123.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox123

        checkBox124.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox124

        checkBox125.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox125

        checkBox126.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox126

        checkBox127.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox127

        checkBox128.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox128

        checkBox129.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox129

        checkBox130.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox130

        checkBox131.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox131

        checkBox132.setOnCheckedChangeListener(listener);
        checkboxValues.add(0); // Default value for checkBox132

        diseasesAnalyzeButton.setOnClickListener(view -> {

            JSONObject json = new JSONObject();
            try {
                json.put("symptoms", new JSONArray(checkboxValues));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            OkHttpClient okHttpClient = new OkHttpClient();

            RequestBody body = RequestBody.create(
                    MediaType.parse("application/json; charset=utf-8"),
                    json.toString()
            );

            Request request = new Request.Builder()
                    .url(serverUrl)
                    .post(body)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "Network not found!", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    diseasesSuggestion = response.body().string();
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = v.findViewById(R.id.output);
                            textView.setText(diseasesSuggestion);
                        }
                    });
                }
            });
        });
        return v;
    }
}