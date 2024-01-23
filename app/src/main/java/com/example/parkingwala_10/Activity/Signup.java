package com.example.parkingwala_10.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.parkingwala_10.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    private Button loginbtn, signupbtn;
    private EditText reg_firstname,reg_lastname,reg_phonenumber,reg_username,reg_password;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_signup);
        loginbtn=findViewById(R.id.login_button);
        signupbtn=findViewById(R.id.signup_button);
        reg_firstname=findViewById(R.id.first_name);
        reg_lastname=findViewById(R.id.last_name);
        reg_phonenumber=findViewById(R.id.phone_number);
        reg_username=findViewById(R.id.registration_username);
        reg_password=findViewById(R.id.registration_password);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname,lname ,phone,username,pass;
                fname=reg_firstname.getText().toString().trim();
                lname=reg_lastname.getText().toString().trim();
                phone=reg_phonenumber.getText().toString().trim();
                username=reg_username.getText().toString().trim();
                pass=reg_password.getText().toString().trim();


                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pass)){
                    Toast.makeText(Signup.this, "User name or password should not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(username,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Signup.this, "Registration is Successfull :"+mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Signup.this, "Registration is failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }






            }
        });









    }
}