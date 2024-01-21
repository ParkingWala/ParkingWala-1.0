package com.example.parkingwala_10.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parkingwala_10.Adapter.ParikingLocationAdapter;
import com.example.parkingwala_10.R;
import com.example.parkingwala_10.model.Parking;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeOwnerApp extends AppCompatActivity {
    RecyclerView myRecyclerView;
    DatabaseReference database;
    ParikingLocationAdapter parikingLocationAdapter;
    ArrayList<Parking> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_app);

        TextView addItemBTN;

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
        database= FirebaseDatabase.getInstance().getReference("Parking_locations");
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        parikingLocationAdapter = new ParikingLocationAdapter(this,list);
        myRecyclerView.setAdapter(parikingLocationAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Parking p=dataSnapshot.getValue(Parking.class);
                    list.add(p);
                }
                parikingLocationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        addItemBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeOwnerApp.this, RegisterParkingLocation.class);
                startActivity(intent);
            }
        });
    }
}