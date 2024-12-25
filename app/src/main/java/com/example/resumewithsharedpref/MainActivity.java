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

    // UI Components
    private RadioGroup radioGroup;
    private RadioButton radioBtnYes, radioBtnNo;
    TextView applyingPositionText;
    private LinearLayout workExperienceExample;

    // Input fields
    private EditText name, dateOfBirth, phoneNumber, applyingPosition, email;
    private EditText honsDegree, honsDepartment, honsInstitution, honsYear, honsCGPA;
    private EditText hscDegree, hscDepartment, hscInstitution, hscYear, hscCGPA;
    private EditText sscDegree, sscDepartment, sscInstitution, sscYear, sscCGPA;

    // Buttons
    private Button saveData, resumeActivity;

    // SharedPreferences
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all components
        initialize();

        // Handle radio group selection changes
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> radioBtnIsSelected());

        // Save data to SharedPreferences
        saveData.setOnClickListener(v -> saveFormData());

        // Navigate to the Resume activity
        resumeActivity.setOnClickListener(v -> navigateToResumeActivity());
    }

    /**
     * Handles the radio button selection logic.
     * Displays a dialog for work experience if "Yes" is selected.
     */
    @SuppressLint("SetTextI18n")
    private void radioBtnIsSelected() {
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId == radioBtnYes.getId()) {
            // Show a dialog for work experience
            showWorkExperienceDialog();
        } else if (selectedId == radioBtnNo.getId()) {
            Toast.makeText(this, "No work experience selected.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Displays a dialog to collect work experience details.
     */
    private void showWorkExperienceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.work_experience, null);

        // Find dialog fields
        EditText startingDate = dialogView.findViewById(R.id.startingDate);
        EditText endingDate = dialogView.findViewById(R.id.endingDate);
        EditText position = dialogView.findViewById(R.id.position);
        EditText jobResponsibility = dialogView.findViewById(R.id.jobResponsibility);
        Button btnSaveExperience = dialogView.findViewById(R.id.btnSaveExperience);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Handle save button click
        btnSaveExperience.setOnClickListener(v -> {
            String startingDateValue = startingDate.getText().toString();
            String endingDateValue = endingDate.getText().toString();
            String positionValue = position.getText().toString();
            String jobResponsibilityValue = jobResponsibility.getText().toString();

            // Validate input fields
            if (startingDateValue.isEmpty() || endingDateValue.isEmpty() || positionValue.isEmpty() || jobResponsibilityValue.isEmpty()) {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save data to SharedPreferences
            editor.putString("startingDate", startingDateValue);
            editor.putString("endingDate", endingDateValue);
            editor.putString("position", positionValue);
            editor.putString("jobResponsibility", jobResponsibilityValue);
            editor.apply();

            // Update UI dynamically with saved data
            updateWorkExperienceUI(startingDateValue, endingDateValue, positionValue, jobResponsibilityValue);
            dialog.dismiss();
        });
    }

    /**
     * Dynamically updates the work experience UI with saved details.
     */
    @SuppressLint("SetTextI18n")
    private void updateWorkExperienceUI(String startingDate, String endingDate, String position, String responsibility) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View savedExperienceView = layoutInflater.inflate(R.layout.saved_experience, null);

        TextView savedStartingDate = savedExperienceView.findViewById(R.id.savedStartingDate);
        TextView savedEndingDate = savedExperienceView.findViewById(R.id.savedEndingDate);
        TextView savedPosition = savedExperienceView.findViewById(R.id.savedPosition);
        TextView savedJobResponsibility = savedExperienceView.findViewById(R.id.savedJobResponsibility);

        savedStartingDate.setText("Starting Date: " + startingDate);
        savedEndingDate.setText("Ending Date: " + endingDate);
        savedPosition.setText("Position: " + position);
        savedJobResponsibility.setText("Responsibilities: " + responsibility);

        workExperienceExample.removeAllViews();
        workExperienceExample.addView(savedExperienceView);
    }

    /**
     * Saves form data into SharedPreferences.
     */
    private void saveFormData() {
        try {
            editor.putString("name", name.getText().toString());
            editor.putString("dateOfBirth", dateOfBirth.getText().toString());
            editor.putString("phoneNumber", phoneNumber.getText().toString());
            editor.putString("applyingPosition", applyingPosition.getText().toString());
            editor.putString("email", email.getText().toString());
            editor.putString("honsDegree", honsDegree.getText().toString());
            editor.putString("honsDepartment", honsDepartment.getText().toString());
            editor.putString("honsInstitution", honsInstitution.getText().toString());
            editor.putString("honsYear", honsYear.getText().toString());
            editor.putString("honsCGPA", honsCGPA.getText().toString());
            editor.putString("hscDegree", hscDegree.getText().toString());
            editor.putString("hscDepartment", hscDepartment.getText().toString());
            editor.putString("hscInstitution", hscInstitution.getText().toString());
            editor.putString("hscYear", hscYear.getText().toString());
            editor.putString("hscCGPA", hscCGPA.getText().toString());
            editor.putString("sscDegree", sscDegree.getText().toString());
            editor.putString("sscDepartment", sscDepartment.getText().toString());
            editor.putString("sscInstitution", sscInstitution.getText().toString());
            editor.putString("sscYear", sscYear.getText().toString());
            editor.putString("sscCGPA", sscCGPA.getText().toString());
            editor.apply();

            Toast.makeText(this, "Data saved successfully.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Invalid input.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Navigates to the Resume activity.
     */
    private void navigateToResumeActivity() {
        Intent intent = new Intent(MainActivity.this, Resume.class);
        startActivity(intent);
    }

    /**
     * Initializes UI components and SharedPreferences.
     */
    private void initialize() {
        radioGroup = findViewById(R.id.radioGroup);
        radioBtnYes = findViewById(R.id.radioBtnYes);
        radioBtnNo = findViewById(R.id.radioBtnNo);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        phoneNumber = findViewById(R.id.phoneNumber);
        saveData = findViewById(R.id.saveData);
        resumeActivity = findViewById(R.id.resumeActivity);
        applyingPositionText = findViewById(R.id.applyingPositionText);
        workExperienceExample = findViewById(R.id.workExperienceExample);
        applyingPosition = findViewById(R.id.applyingPosition);
        name = findViewById(R.id.name);
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

        preferences = getSharedPreferences("Resume", MODE_PRIVATE);
        editor = preferences.edit();
    }
}
