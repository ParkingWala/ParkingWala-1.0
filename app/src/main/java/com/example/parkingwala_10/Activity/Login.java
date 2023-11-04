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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText LoginUsername,LoginPassword;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        Button loginButton = findViewById(R.id.login_button);
        LoginUsername = findViewById(R.id.login_username);
        LoginPassword = findViewById(R.id.login_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email, password;
                email = LoginUsername.getText().toString();
                password = LoginPassword.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "No Empty Field allowed", Toast.LENGTH_SHORT).show();

                }else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String user_id=mAuth.getCurrentUser().getEmail();
                                Toast.makeText(Login.this, "Id: " + user_id, Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(Login.this,Signup.class);
                                startActivity(intent);
                                Log.d("TAG", "onComplete: " + mAuth.getCurrentUser().getEmail());
                            } else {
                                Toast.makeText(Login.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        }


                    });
                }


            }
        });


    }

}