package com.example.parkingwala_10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeActivity();
        onClick();
    }

    private void initializeActivity() {
        textView = findViewById(R.id.buttonDemo);
    }

    private void onClick() {
        textView.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, R.string.parkingwala, Toast.LENGTH_LONG).show();
        });
    }

}