package com.example.resumewithsharedpref;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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

import soup.neumorphism.NeumorphButton;

public class MainActivity extends AppCompatActivity {

    private EditText name, dateOfBirth, phoneNumber, applyingPosition, email;
    private EditText honsDegree, honsDepartment, honsInstitution, honsYear, honsCGPA;
    private EditText hscDegree, hscDepartment, hscInstitution, hscYear, hscCGPA;
    private EditText sscDegree, sscDepartment, sscInstitution, sscYear, sscCGPA;
    private Button saveData, resumeActivity;
    private RadioGroup radioGroup;
    RadioButton radioBtnYes, radioBtnNo;
    LinearLayout workExperienceExample;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize components
        initializeComponents();

        // Set up listeners
        setupListeners();
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
        preferences = getSharedPreferences("Resume", MODE_PRIVATE);
        editor = preferences.edit();

        // Pre-fill fields with existing data
        prefillFields();
    }

    private void prefillFields() {
        name.setText(preferences.getString("name", ""));
        dateOfBirth.setText(preferences.getString("dateOfBirth", ""));
        phoneNumber.setText(preferences.getString("phoneNumber", ""));
        applyingPosition.setText(preferences.getString("applyingPosition", ""));
        email.setText(preferences.getString("email", ""));
        honsDegree.setText(preferences.getString("honsDegree", ""));
        honsDepartment.setText(preferences.getString("honsDepartment", ""));
        honsInstitution.setText(preferences.getString("honsInstitution", ""));
        honsYear.setText(preferences.getString("honsYear", ""));
        honsCGPA.setText(preferences.getString("honsCGPA", ""));
        hscDegree.setText(preferences.getString("hscDegree", ""));
        hscDepartment.setText(preferences.getString("hscDepartment", ""));
        hscInstitution.setText(preferences.getString("hscInstitution", ""));
        hscYear.setText(preferences.getString("hscYear", ""));
        hscCGPA.setText(preferences.getString("hscCGPA", ""));
        sscDegree.setText(preferences.getString("sscDegree", ""));
        sscDepartment.setText(preferences.getString("sscDepartment", ""));
        sscInstitution.setText(preferences.getString("sscInstitution", ""));
        sscYear.setText(preferences.getString("sscYear", ""));
        sscCGPA.setText(preferences.getString("sscCGPA", ""));
    }

    private void setupListeners() {
        saveData.setOnClickListener(v -> saveFormData());
        resumeActivity.setOnClickListener(v -> navigateToResumeActivity());

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioBtnYes) {
                Toast.makeText(this, "Work experience selected.", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.radioBtnNo) {
                Toast.makeText(this, "No work experience selected.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveFormData() {
        updateFieldIfChanged("name", name);
        updateFieldIfChanged("dateOfBirth", dateOfBirth);
        updateFieldIfChanged("phoneNumber", phoneNumber);
        updateFieldIfChanged("applyingPosition", applyingPosition);
        updateFieldIfChanged("email", email);
        updateFieldIfChanged("honsDegree", honsDegree);
        updateFieldIfChanged("honsDepartment", honsDepartment);
        updateFieldIfChanged("honsInstitution", honsInstitution);
        updateFieldIfChanged("honsYear", honsYear);
        updateFieldIfChanged("honsCGPA", honsCGPA);
        updateFieldIfChanged("hscDegree", hscDegree);
        updateFieldIfChanged("hscDepartment", hscDepartment);
        updateFieldIfChanged("hscInstitution", hscInstitution);
        updateFieldIfChanged("hscYear", hscYear);
        updateFieldIfChanged("hscCGPA", hscCGPA);
        updateFieldIfChanged("sscDegree", sscDegree);
        updateFieldIfChanged("sscDepartment", sscDepartment);
        updateFieldIfChanged("sscInstitution", sscInstitution);
        updateFieldIfChanged("sscYear", sscYear);
        updateFieldIfChanged("sscCGPA", sscCGPA);

        editor.apply();

        Toast.makeText(this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
    }

    private void updateFieldIfChanged(String key, EditText field) {
        String currentValue = preferences.getString(key, "");
        String newValue = field.getText().toString();

        if (!TextUtils.isEmpty(newValue) && !newValue.equals(currentValue)) {
            editor.putString(key, newValue);
        }
    }

    private void navigateToResumeActivity() {
        Intent intent = new Intent(MainActivity.this, Resume.class);
        startActivity(intent);
    }
}
