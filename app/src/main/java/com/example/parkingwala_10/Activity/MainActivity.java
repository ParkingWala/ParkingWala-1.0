package com.example.parkingwala_10.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.parkingwala_10.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button textView;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        initializeActivity();
        onClick();
    }

    private void initializeActivity() {
        textView = findViewById(R.id.buttonDemo);
    }

    private void onClick() {
        textView.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, R.string.parkingwala, Toast.LENGTH_LONG).show();
            Intent intent =new Intent(this, Login.class);
            startActivity(intent);
        });
    }

}