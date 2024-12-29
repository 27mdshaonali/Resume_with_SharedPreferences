package com.example.resumewithsharedpref;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Resume extends AppCompatActivity {

    public static String APPLYING_POSITION = "";
    public static String NAME = "";
    public static String EMAIL = "";
    public static String PHONE_NUMBER = "";
    public static String DATE_OF_BIRTH = "";
    public static String HONS_DEGREE = "";
    public static String HONS_DEPARTMENT = "";
    public static String HONS_INSTITUTION = "";
    public static String HONS_YEAR = "";
    public static String HONS_CGPA = "";
    public static String HSC_DEGREE = "";
    public static String HSC_DEPARTMENT = "";
    public static String HSC_INSTITUTION = "";
    public static String HSC_YEAR = "";
    public static String HSC_CGPA = "";
    public static String SSC_DEGREE = "";
    public static String SSC_DEPARTMENT = "";
    public static String SSC_INSTITUTION = "";
    public static String SSC_YEAR = "";
    public static String SSC_CGPA = "";

    public static String STARTING_DATE = "";
    public static String ENDING_DATE = "";

    public static String COMPANY_NAME = "";
    public static String COMPANY_LOCATION = "";
    public static String JOB_TITLE = "";
    public static String JOB_RESPONSIBILITY = "";

    boolean isEditMode = false;
    LinearLayout linearLayoutEducationDetails;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private TextView userName, applicantName, applyingPosition, userEmail, userPhoneNumber, userDateOfBirth, userEducation, userWorkExperience;
    private EditText editUserName, editApplicantName, editApplyingPosition, editEmail, editPhoneNumber, editDateOfBirth, editEducation, editWorkExperience;
    private Button editDataButton, saveDataButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        initializingElements();
        setDataFromPreviousActivity();


        // Set initial visibility for edit fields
        toggleEditMode(false);

        // Set up button click listeners
        editDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleEditMode(true);
            }
        });

        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                toggleEditMode(false);
            }
        });
    }

    /**
     * Toggles between edit and view mode.
     *
     * @param enableEdit If true, switches to edit mode; otherwise, switches to view mode.
     */
    private void toggleEditMode(boolean enableEdit) {
        isEditMode = enableEdit;

        // Toggle visibility of TextViews and EditTexts
        userName.setVisibility(enableEdit ? View.GONE : View.VISIBLE);
        applicantName.setVisibility(enableEdit ? View.GONE : View.VISIBLE);
        applyingPosition.setVisibility(enableEdit ? View.GONE : View.VISIBLE);
        userEmail.setVisibility(enableEdit ? View.GONE : View.VISIBLE);
        userPhoneNumber.setVisibility(enableEdit ? View.GONE : View.VISIBLE);
        userDateOfBirth.setVisibility(enableEdit ? View.GONE : View.VISIBLE);

        editUserName.setVisibility(enableEdit ? View.VISIBLE : View.GONE);
        editApplicantName.setVisibility(enableEdit ? View.VISIBLE : View.GONE);
        editApplyingPosition.setVisibility(enableEdit ? View.VISIBLE : View.GONE);
        editEmail.setVisibility(enableEdit ? View.VISIBLE : View.GONE);
        editPhoneNumber.setVisibility(enableEdit ? View.VISIBLE : View.GONE);
        editDateOfBirth.setVisibility(enableEdit ? View.VISIBLE : View.GONE);

        userEducation.setVisibility(enableEdit ? View.GONE : View.VISIBLE);
        editEducation.setVisibility(enableEdit ? View.VISIBLE : View.GONE);

        userWorkExperience.setVisibility(enableEdit ? View.GONE : View.VISIBLE);
        editWorkExperience.setVisibility(enableEdit ? View.VISIBLE : View.GONE);

        // Update button visibility
        editDataButton.setVisibility(enableEdit ? View.GONE : View.VISIBLE);
        saveDataButton.setVisibility(enableEdit ? View.VISIBLE : View.GONE);

        if (enableEdit) {

            // Populate EditText fields with existing data
            editUserName.setText(applicantName.getText().toString());
            editApplicantName.setText(applicantName.getText().toString());
            editApplyingPosition.setText(applyingPosition.getText().toString());
            editEmail.setText(userEmail.getText().toString());
            editPhoneNumber.setText(userPhoneNumber.getText().toString());
            editDateOfBirth.setText(userDateOfBirth.getText().toString());
            editEducation.setText(userEducation.getText().toString());
            editWorkExperience.setText(userWorkExperience.getText().toString());

        }
    }

    /**
     * Saves data from EditText fields into TextViews.
     */
    private void saveData() {

        // Save the data into SharedPreferences
        editor.putString("name", editApplicantName.getText().toString().trim());
        editor.putString("applyingPosition", editApplyingPosition.getText().toString().trim());
        editor.putString("email", editEmail.getText().toString().trim());
        editor.putString("phoneNumber", editPhoneNumber.getText().toString().trim());
        editor.putString("dateOfBirth", editDateOfBirth.getText().toString().trim());
        editor.putString("education", editEducation.getText().toString().trim());
        editor.putString("workExperience", editWorkExperience.getText().toString().trim());
        editor.apply();

        // Update TextViews with saved data
        applicantName.setText(preferences.getString("name", ""));
        applyingPosition.setText(preferences.getString("applyingPosition", ""));
        userEmail.setText(preferences.getString("email", ""));
        userPhoneNumber.setText(preferences.getString("phoneNumber", ""));
        userDateOfBirth.setText(preferences.getString("dateOfBirth", ""));
        userEducation.setText(preferences.getString("education", ""));
        userWorkExperience.setText(preferences.getString("workExperience", ""));


    }

    public void visibilityEducationDetails() {
        if (!HONS_DEGREE.isEmpty()) {
            linearLayoutEducationDetails.setVisibility(View.GONE);
        }
        if (!HSC_DEGREE.isEmpty()) {
            linearLayoutEducationDetails.setVisibility(View.GONE);
        }
        if (!SSC_DEGREE.isEmpty()) {
            linearLayoutEducationDetails.setVisibility(View.GONE);
        } else {
            linearLayoutEducationDetails.setVisibility(View.VISIBLE);
        }
    }

    public void visibilityWorkExperienceDetails() {
        if (!STARTING_DATE.isEmpty()) {
            linearLayoutEducationDetails.setVisibility(View.VISIBLE);
        } else {
            linearLayoutEducationDetails.setVisibility(View.GONE);
        }
    }

    public void initializingElements() {
        // Initialize Views
        userName = findViewById(R.id.userName);
        applicantName = findViewById(R.id.applicantName);
        applyingPosition = findViewById(R.id.applyingPosition);
        userEmail = findViewById(R.id.userEmail);
        userPhoneNumber = findViewById(R.id.userPhoneNumber);
        userDateOfBirth = findViewById(R.id.userDateOfBirth);

        editUserName = findViewById(R.id.editUserName);
        editApplicantName = findViewById(R.id.editApplicantName);
        editApplyingPosition = findViewById(R.id.editApplyingPosition);
        editEmail = findViewById(R.id.editEmail);
        editPhoneNumber = findViewById(R.id.editPhoneNumber);
        editDateOfBirth = findViewById(R.id.editDateOfBirth);

        editDataButton = findViewById(R.id.editData);
        saveDataButton = findViewById(R.id.saveData);

        userEducation = findViewById(R.id.userEducation);
        editEducation = findViewById(R.id.editEducation);

        userWorkExperience = findViewById(R.id.userWorkExperience);
        editWorkExperience = findViewById(R.id.editWorkExperience);

        preferences = getSharedPreferences("ResumeData", MODE_PRIVATE);
        editor = preferences.edit();

        linearLayoutEducationDetails = findViewById(R.id.linearLayoutEducationDetails);
    }

    @SuppressLint("SetTextI18n")
    public void setDataFromPreviousActivity() {
        // Setting Data from Previous Activity
        userName.setText(NAME);
        applicantName.setText(NAME);
        applyingPosition.setText(APPLYING_POSITION);
        userEmail.setText(EMAIL);
        userPhoneNumber.setText(PHONE_NUMBER);
        userDateOfBirth.setText(DATE_OF_BIRTH);

        userEducation.setText(HONS_DEGREE + "   " + HONS_DEPARTMENT + "   " + HONS_INSTITUTION + "   " + HONS_YEAR + "   " + HONS_CGPA + "\n");
        userEducation.append(HSC_DEGREE + "   " + HSC_DEPARTMENT + "   " + HSC_INSTITUTION + "   " + HSC_YEAR + "   " + HSC_CGPA + "\n");
        userEducation.append(SSC_DEGREE + "   " + SSC_DEPARTMENT + "   " + SSC_INSTITUTION + "   " + SSC_YEAR + "   " + SSC_CGPA);

        visibilityEducationDetails();

        userWorkExperience.setText("Starting Date: " + STARTING_DATE + "\n" + "Ending Date: " + ENDING_DATE + "\n" + "Company Name: " + COMPANY_NAME + "\n" + "Job Title: " + JOB_TITLE + "\n" + "Job Responsibility: " + JOB_RESPONSIBILITY + "\n" + "Company Location: " + COMPANY_LOCATION);
    }


}
