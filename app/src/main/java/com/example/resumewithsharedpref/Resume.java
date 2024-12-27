package com.example.resumewithsharedpref;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Resume extends AppCompatActivity {

    public static String APPLYING_POSITION = "";
    public static String NAME = "";
    public static String EMAIL = "";
    public static String PHONE_NUMBER = "";
    public static String DATE_OF_BIRTH = "";
    public static String POSITION = "";
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


    boolean isEditMode = false;
    private TextView userName, applicantName, applyingPosition, userEmail, userPhoneNumber, userDateOfBirth;
    private EditText editUserName, editApplicantName, editApplyingPosition, editEmail, editPhoneNumber, editDateOfBirth;
    private Button editDataButton, saveDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

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


        //Setting Date from Previous Activity

        userName.setText(NAME);
        applicantName.setText(NAME);
        applyingPosition.setText(APPLYING_POSITION);
        userEmail.setText(EMAIL);
        userPhoneNumber.setText(PHONE_NUMBER);
        userDateOfBirth.setText(DATE_OF_BIRTH);


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
        }
    }

    /**
     * Saves data from EditText fields into TextViews.
     */
    private void saveData() {
        // Update TextViews with data from EditTexts

        applicantName.setText(editApplicantName.getText().toString());

        userName.setText(applicantName.getText().toString());

        applyingPosition.setText(editApplyingPosition.getText().toString());
        userEmail.setText(editEmail.getText().toString());
        userPhoneNumber.setText(editPhoneNumber.getText().toString());
        userDateOfBirth.setText(editDateOfBirth.getText().toString());
    }
}
