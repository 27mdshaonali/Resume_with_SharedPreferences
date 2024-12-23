package com.example.resumewithsharedpref;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioBtnYes, radioBtnNo;
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
            dateOfBirth.setText("");
            phoneNumber.setText("01711111111");
        } else if (radioBtnId == radioBtnNo.getId()) {
            phoneNumber.setText("");
            dateOfBirth.setText("27/01/2004");

        }


    }

}
