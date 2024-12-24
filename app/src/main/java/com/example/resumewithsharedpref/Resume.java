package com.example.resumewithsharedpref;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Resume extends AppCompatActivity {

    public static String NAME = "";
    public static String DATE_OF_BIRTH = "";
    public static int PHONE_NUMBER = 0;

    public static String STARTING_DATE = "";
    public static String ENDING_DATE = "";
    public static String POSITION = "";
    public static String JOB_RESPONSIBILITY = "";

    TextView userNameText, userName, userDateOfBirth, userPhoneNumber;

    Button editData,saveData, downloadResume;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resume);

        userNameText = findViewById(R.id.userNameText);
        userName = findViewById(R.id.userName);
        userDateOfBirth = findViewById(R.id.userDateOfBirth);
        userPhoneNumber = findViewById(R.id.userPhoneNumber);

        editData = findViewById(R.id.editData);
        saveData = findViewById(R.id.saveData);
        downloadResume = findViewById(R.id.downloadResume);

        preferences = getSharedPreferences("Resume", MODE_PRIVATE);
        editor = preferences.edit();

        userNameText.setText(NAME);
        userName.setText(NAME);
        userDateOfBirth.setText(DATE_OF_BIRTH);
        userPhoneNumber.setText(String.valueOf(PHONE_NUMBER));



    }
}