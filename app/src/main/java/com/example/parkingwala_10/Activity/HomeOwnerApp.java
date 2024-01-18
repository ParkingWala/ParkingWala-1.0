package com.example.parkingwala_10.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parkingwala_10.R;

public class HomeOwnerApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_app);

        TextView addItemBTN;
        RecyclerView myRecyclerView;

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
//        }        TextView toolbar_title = findViewById(R.id.toolbar_title);
//        toolbar_title.setText("User");
//        ImageView back_btn = findViewById(R.id.toolbar_backBtn);
//        back_btn.setOnClickListener(view -> onBackPressed());
//        TextView welcome = findViewById(R.id.welcom);
//        welcome.setVisibility(View.VISIBLE);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
//        TextView toolbar_title = findViewById(R.id.toolbar_title);
//        toolbar_title.setText("User");
//        ImageView back_btn = findViewById(R.id.toolbar_backBtn);
//        back_btn.setOnClickListener(view -> onBackPressed());
//        TextView welcome = findViewById(R.id.welcom);
//        welcome.setVisibility(View.VISIBLE);

        myRecyclerView = findViewById(R.id.my_parking_location_recycler_v);
        addItemBTN = findViewById(R.id.add_parking_location_btn);



        addItemBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeOwnerApp.this, RegisterParkingLocation.class);
                startActivity(intent);
            }
        });
    }
}