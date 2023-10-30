package com.example.parkingwala_10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private EditText LoginUsername,LoginPassword;
    private Button LoginButton;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        LoginButton = findViewById(R.id.login_button);
        LoginUsername = findViewById(R.id.login_username);
        LoginPassword = findViewById(R.id.login_password);

        LoginPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email, password;
                email = LoginUsername.getText().toString();
                password = LoginPassword.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this, "Id: " + Objects.requireNonNull(mAuth.getCurrentUser()).getUid(), Toast.LENGTH_SHORT).show();
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