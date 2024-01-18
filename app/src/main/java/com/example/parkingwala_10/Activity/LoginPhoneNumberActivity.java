package com.example.parkingwala_10.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.parkingwala_10.R;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class LoginPhoneNumberActivity extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    EditText phoneInput;
    TextView sendOtpBtn, signup_btn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone_number);

        countryCodePicker = findViewById(R.id.login_countrycode);
        phoneInput = findViewById(R.id.login_mobile_number);
        sendOtpBtn = findViewById(R.id.send_otp_btn);
        progressBar = findViewById(R.id.login_progress_bar);
        signup_btn = findViewById(R.id.create_new_account);

        progressBar.setVisibility(View.GONE);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        TextView toolbar_title = findViewById(R.id.toolbar_title);
//        toolbar_title.setText("Login");
//        TextView welcom = findViewById(R.id.welcom);
//        welcom.setVisibility(View.GONE);
//        ImageView back_btn = findViewById(R.id.toolbar_backBtn);
//        back_btn.setOnClickListener(view -> onBackPressed());

        countryCodePicker.registerCarrierNumberEditText(phoneInput);
        sendOtpBtn.setOnClickListener((v)->{
            if (phoneInput.length() == 10) {
//                Toast.makeText(this, "length if is " + phoneInput.length(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "total number is " + countryCodePicker.getFullNumberWithPlus(), Toast.LENGTH_LONG).show();
                if (!countryCodePicker.isValidFullNumber()) {
                    phoneInput.setError("Phone number not valid");
                    return;
                }
                Intent intent = new Intent(LoginPhoneNumberActivity.this, LoginOtpActivity.class);
                intent.putExtra("phone", countryCodePicker.getFullNumberWithPlus());
                startActivity(intent);
            }else {
                Toast.makeText(this, "length else is " + phoneInput.length(), Toast.LENGTH_SHORT).show();
            }
        });

        signup_btn.setOnClickListener((v)->{
            Intent intent2 = new Intent(LoginPhoneNumberActivity.this, Signup.class);
            startActivity(intent2);
        });

    }


}