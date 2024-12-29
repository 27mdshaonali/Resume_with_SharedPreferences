package com.example.resumewithsharedpref;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

    RadioButton radioBtnYes, radioBtnNo;
    LinearLayout workExperienceExample;
    Button saveData, resumeActivity;
    RadioGroup radioGroup;
    EditText name, dateOfBirth, phoneNumber, applyingPosition, email;
    EditText honsDegree, honsDepartment, honsInstitution, honsYear, honsCGPA;
    EditText hscDegree, hscDepartment, hscInstitution, hscYear, hscCGPA;
    EditText sscDegree, sscDepartment, sscInstitution, sscYear, sscCGPA;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize components
        initializeComponents();

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> radioGroupOnClick());

        // Set click listeners
        saveData.setOnClickListener(view -> saveDataToSharedPreferences());

        resumeActivity.setOnClickListener(view -> {
            shareDataToResumeActivity();
            startActivity(new Intent(MainActivity.this, Resume.class));
        });
    }

    private void initializeComponents() {
        // Initialize EditText fields
        name = findViewById(R.id.name);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        phoneNumber = findViewById(R.id.phoneNumber);
        applyingPosition = findViewById(R.id.applyingPosition);
        email = findViewById(R.id.email);
        honsDegree = findViewById(R.id.honsDegree);
        honsDepartment = findViewById(R.id.honsDepartment);
        honsInstitution = findViewById(R.id.honsInstitution);
        honsYear = findViewById(R.id.honsYear);
        honsCGPA = findViewById(R.id.honsCGPA);
        hscDegree = findViewById(R.id.hscDegree);
        hscDepartment = findViewById(R.id.hscDepartment);
        hscInstitution = findViewById(R.id.hscInstitution);
        hscYear = findViewById(R.id.hscYear);
        hscCGPA = findViewById(R.id.hscCGPA);
        sscDegree = findViewById(R.id.sscDegree);
        sscDepartment = findViewById(R.id.sscDepartment);
        sscInstitution = findViewById(R.id.sscInstitution);
        sscYear = findViewById(R.id.sscYear);
        sscCGPA = findViewById(R.id.sscCGPA);

        // Initialize buttons
        saveData = findViewById(R.id.saveData);
        resumeActivity = findViewById(R.id.resumeActivity);

        // Initialize radio group
        radioGroup = findViewById(R.id.radioGroup);
        radioBtnYes = findViewById(R.id.radioBtnYes);
        radioBtnNo = findViewById(R.id.radioBtnNo);

        // Initialize other UI components
        workExperienceExample = findViewById(R.id.workExperienceExample);

        // Initialize SharedPreferences
        preferences = getSharedPreferences("ResumeData", MODE_PRIVATE);
        editor = preferences.edit();
    }

    private void saveDataToSharedPreferences() {
        // Save data to SharedPreferences only if fields are not empty or changed
        saveIfChanged("name", name);
        saveIfChanged("dateOfBirth", dateOfBirth);
        saveIfChanged("phoneNumber", phoneNumber);
        saveIfChanged("applyingPosition", applyingPosition);
        saveIfChanged("email", email);
        saveIfChanged("honsDegree", honsDegree);
        saveIfChanged("honsDepartment", honsDepartment);
        saveIfChanged("honsInstitution", honsInstitution);
        saveIfChanged("honsYear", honsYear);
        saveIfChanged("honsCGPA", honsCGPA);
        saveIfChanged("hscDegree", hscDegree);
        saveIfChanged("hscDepartment", hscDepartment);
        saveIfChanged("hscInstitution", hscInstitution);
        saveIfChanged("hscYear", hscYear);
        saveIfChanged("hscCGPA", hscCGPA);
        saveIfChanged("sscDegree", sscDegree);
        saveIfChanged("sscDepartment", sscDepartment);
        saveIfChanged("sscInstitution", sscInstitution);
        saveIfChanged("sscYear", sscYear);
        saveIfChanged("sscCGPA", sscCGPA);

        editor.apply();
    }

    private void saveIfChanged(String key, EditText field) {
        String currentValue = preferences.getString(key, "");  // Get existing value
        String newValue = field.getText().toString().trim();   // Get new value

        // Only update if new value is not empty and different from the existing value
        if (!TextUtils.isEmpty(newValue) && !newValue.equals(currentValue)) {
            editor.putString(key, newValue); // Save only if changed
        }
    }

    private void shareDataToResumeActivity() {
        Resume.NAME = preferences.getString("name", "");
        Resume.DATE_OF_BIRTH = preferences.getString("dateOfBirth", "");
        Resume.PHONE_NUMBER = preferences.getString("phoneNumber", "");
        Resume.APPLYING_POSITION = preferences.getString("applyingPosition", "");
        Resume.EMAIL = preferences.getString("email", "");
        Resume.HONS_DEGREE = preferences.getString("honsDegree", "");
        Resume.HONS_DEPARTMENT = preferences.getString("honsDepartment", "");
        Resume.HONS_INSTITUTION = preferences.getString("honsInstitution", "");
        Resume.HONS_YEAR = preferences.getString("honsYear", "");
        Resume.HONS_CGPA = preferences.getString("honsCGPA", "");
        Resume.HSC_DEGREE = preferences.getString("hscDegree", "");
        Resume.HSC_DEPARTMENT = preferences.getString("hscDepartment", "");
        Resume.HSC_INSTITUTION = preferences.getString("hscInstitution", "");
        Resume.HSC_YEAR = preferences.getString("hscYear", "");
        Resume.HSC_CGPA = preferences.getString("hscCGPA", "");
        Resume.SSC_DEGREE = preferences.getString("sscDegree", "");
        Resume.SSC_DEPARTMENT = preferences.getString("sscDepartment", "");
        Resume.SSC_INSTITUTION = preferences.getString("sscInstitution", "");
        Resume.SSC_YEAR = preferences.getString("sscYear", "");
        Resume.SSC_CGPA = preferences.getString("sscCGPA", "");
    }

    @SuppressLint("SetTextI18n")
    private void radioGroupOnClick() {
        if (radioBtnYes.isChecked()) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.work_experience, null);

            EditText startingDate = view.findViewById(R.id.startingDate);
            EditText endingDate = view.findViewById(R.id.endingDate);
            EditText companyName = view.findViewById(R.id.companyName);
            EditText position = view.findViewById(R.id.position);
            EditText jobResponsibility = view.findViewById(R.id.jobResponsibility);
            EditText location = view.findViewById(R.id.location);
            Button saveExperience = view.findViewById(R.id.saveExperience);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view);
            AlertDialog dialog = builder.create();
            dialog.show();

            saveExperience.setOnClickListener(view1 -> {
                try {
                    String startingDateValue = startingDate.getText().toString().trim();
                    String endingDateValue = endingDate.getText().toString().trim();
                    String companyNameValue = companyName.getText().toString().trim();
                    String positionValue = position.getText().toString().trim();
                    String jobResponsibilityValue = jobResponsibility.getText().toString().trim();
                    String locationValue = location.getText().toString().trim();

                    if (!startingDateValue.isEmpty() && !endingDateValue.isEmpty() && !positionValue.isEmpty() && !jobResponsibilityValue.isEmpty()) {
                        editor.putString("startingDate", startingDateValue);
                        editor.putString("endingDate", endingDateValue);
                        editor.putString("companyName", companyNameValue);
                        editor.putString("position", positionValue);
                        editor.putString("jobResponsibility", jobResponsibilityValue);
                        editor.putString("location", locationValue);

                        editor.apply();

                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View viewSaved = inflater.inflate(R.layout.saved_experience, null);

                        TextView savedStartingDate = viewSaved.findViewById(R.id.savedStartingDate);
                        TextView savedEndingDate = viewSaved.findViewById(R.id.savedEndingDate);
                        TextView savedCompanyName = viewSaved.findViewById(R.id.savedCompanyName);
                        TextView savedPosition = viewSaved.findViewById(R.id.savedPosition);
                        TextView savedJobResponsibility = viewSaved.findViewById(R.id.savedJobResponsibility);
                        TextView savedLocation = viewSaved.findViewById(R.id.savedLocation);

                        Resume.STARTING_DATE = preferences.getString("startingDate", "");
                        Resume.ENDING_DATE = preferences.getString("endingDate", "");
                        Resume.COMPANY_NAME = preferences.getString("companyName", "");
                        Resume.JOB_TITLE = preferences.getString("position", "");
                        Resume.JOB_RESPONSIBILITY = preferences.getString("jobResponsibility", "");
                        Resume.COMPANY_LOCATION = preferences.getString("location", "");


                        dialog.dismiss();
                    } else {
                        Toast.makeText(this, "Please fill all input fields!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("MainActivity", "Error: ", e);
                    Toast.makeText(this, "Enter Valid Values", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
                }
            });

            workExperienceExample.setVisibility(View.VISIBLE);
        } else {
            workExperienceExample.setVisibility(View.GONE);
        }
    }
}
