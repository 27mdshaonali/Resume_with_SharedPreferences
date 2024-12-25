package com.example.resumewithsharedpref;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioBtnYes, radioBtnNo;
    TextView workExperence, applyingPositionText, education, educationExample;
    LinearLayout workExperienceExample;
    private EditText name, dateOfBirth, phoneNumber;
    private Button saveData, resumeActivity;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioGroup);
        radioBtnYes = findViewById(R.id.radioBtnYes);
        radioBtnNo = findViewById(R.id.radioBtnNo);

        name = findViewById(R.id.name);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        phoneNumber = findViewById(R.id.phoneNumber);

        saveData = findViewById(R.id.saveData);
        resumeActivity = findViewById(R.id.resumeActivity);

        applyingPositionText = findViewById(R.id.applyingPositionText);


        workExperienceExample = findViewById(R.id.workExperienceExample);


        preferences = getSharedPreferences("Resume", MODE_PRIVATE);
        editor = preferences.edit();

//        radioGroup.setOnCheckedChangeListener((group, checkedId) -> radioBtnIsSelected());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                radioBtnIsSelected();

            }
        });


        saveData.setOnClickListener(View -> {

            String nameValue = name.getText().toString();
            String dateOfBirthValue = dateOfBirth.getText().toString();
            String phoneNumberValue = phoneNumber.getText().toString();


            try {

                int phoneNumberValueInt = Integer.parseInt(phoneNumberValue);

                editor.putString("name", nameValue);
                editor.putString("dateOfBirth", dateOfBirthValue);
                editor.putInt("phoneNumber", phoneNumberValueInt);
                editor.apply();


            } catch (Exception e) {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
            }


        });


        resumeActivity.setOnClickListener(View -> {

            String savedName = preferences.getString("name", "");
            String savedDateOfBirth = preferences.getString("dateOfBirth", "");
            int savedPhoneNumber = preferences.getInt("phoneNumber", 0);

            Resume.NAME = savedName;
            Resume.DATE_OF_BIRTH = savedDateOfBirth;
            Resume.PHONE_NUMBER = savedPhoneNumber;

            startActivity(new Intent(MainActivity.this, Resume.class));

        });

    }

    @SuppressLint("SetTextI18n")
    public void radioBtnIsSelected() {

        int radioBtnId = radioGroup.getCheckedRadioButtonId();

        if (radioBtnId == radioBtnYes.getId()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            LayoutInflater inflater;
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.work_experence, null);

            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText startingDate = view.findViewById(R.id.startingDate);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText endingDate = view.findViewById(R.id.endingDate);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText position = view.findViewById(R.id.position);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText jobResponsibility = view.findViewById(R.id.jobResponsibility);

            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button btnSaveExperience = view.findViewById(R.id.btnSaveExperience);

            builder.setView(view);
            AlertDialog dialog = builder.create();
            dialog.show();

            btnSaveExperience.setOnClickListener(View -> {

                String startingDateValue = startingDate.getText().toString();
                String endingDateValue = endingDate.getText().toString();
                String positionValue = position.getText().toString();
                String jobResponsibilityValue = jobResponsibility.getText().toString();

                if (startingDateValue.isEmpty() || endingDateValue.isEmpty() || positionValue.isEmpty() || jobResponsibilityValue.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                editor.putString("startingDate", startingDateValue);
                editor.putString("endingDate", endingDateValue);
                editor.putString("position", positionValue);
                editor.putString("jobResponsibility", jobResponsibilityValue);
                editor.apply();

                // Inflate the layout dynamically
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                View savedExperienceView = layoutInflater.inflate(R.layout.saved_experience, null);


                // Populate the fields
                TextView savedStartingDate = savedExperienceView.findViewById(R.id.savedStartingDate);
                TextView savedEndingDate = savedExperienceView.findViewById(R.id.savedEndingDate);
                TextView savedPosition = savedExperienceView.findViewById(R.id.savedPosition);
                TextView savedJobResponsibility = savedExperienceView.findViewById(R.id.savedJobResponsibility);

                savedStartingDate.setText("Starting Date: " + startingDateValue);
                savedEndingDate.setText("Ending Date: " + endingDateValue);
                savedPosition.setText("Position: " + positionValue);
                savedJobResponsibility.setText("Responsibilities: " + jobResponsibilityValue);


                // Replace the existing view with this new layout
                workExperienceExample.removeAllViews(); // Clear the current content
                workExperienceExample.addView(savedExperienceView); // Add the updated table layout

                Resume.STARTING_DATE = startingDateValue;
                Resume.ENDING_DATE = endingDateValue;
                Resume.POSITION = positionValue;
                Resume.JOB_RESPONSIBILITY = jobResponsibilityValue;

                dialog.dismiss();
            });


        } else if (radioBtnId == radioBtnNo.getId()) {

            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();

        }


    }

}
