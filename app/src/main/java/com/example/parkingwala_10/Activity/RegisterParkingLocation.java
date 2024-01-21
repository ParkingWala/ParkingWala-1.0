package com.example.parkingwala_10.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.parkingwala_10.R;
import com.example.parkingwala_10.commen.BaseActivity;
import com.example.parkingwala_10.model.Parking;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterParkingLocation extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    TextView currentLocation, TandC_text, btn_submit;
    EditText amount, capacity, contact_no, address, parking_name;
    TimePicker fromTime, toTime;
    CheckBox TandC_box;
    double latitude, longitude;
    int from_Hr, from_Min, to_Hr, to_Min;

    private Boolean getLocationFlag = false;

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_parking_location);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        TextView toolbar_title = findViewById(R.id.toolbar_title);
//        toolbar_title.setText("User");
//        TextView welcom = findViewById(R.id.welcom);
//        welcom.setVisibility(View.VISIBLE);
//        ImageView back_btn = findViewById(R.id.toolbar_backBtn);
//        back_btn.setOnClickListener(view -> onBackPressed());


        InitializeActivity();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        BaseActivity.SingleBtnShowAlertDialog(RegisterParkingLocation.this, "Current Location", "Here we are taking your current location, so that user can easily get to your parking location. Please make sure, you are at your parking location");
        AllOnClick();


    }

    private void InitializeActivity() {

        currentLocation = findViewById(R.id.share_current_location);
        parking_name = findViewById(R.id.parking_location_name);
        amount = findViewById(R.id.parking_amount);
        capacity = findViewById(R.id.parking_capacity);
        contact_no = findViewById(R.id.parking_contactNo);
        address = findViewById(R.id.parking_address);
        fromTime = findViewById(R.id.from_time_picker);
        fromTime.setIs24HourView(true);
        toTime = findViewById(R.id.to_time_picker);
        toTime.setIs24HourView(true);
        TandC_box = findViewById(R.id.checkBox_TandC);
        TandC_text = findViewById(R.id.termsandcondition);
        btn_submit = findViewById(R.id.submit_parking_location_btn);
    }

    private void AllOnClick() {
        currentLocation.setOnClickListener(view -> {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            } else if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            }
        });

        TandC_text.setOnClickListener(view -> {
            Intent intentTandC = new Intent(RegisterParkingLocation.this, TermsAndCondition.class);
            startActivity(intentTandC);
        });

        btn_submit.setOnClickListener(view -> {
            if (getLocationFlag) {
                if (!parking_name.getText().toString().isEmpty()) {
                    if (!amount.getText().toString().isEmpty()) {
                        if (!capacity.getText().toString().isEmpty()) {
                            if (!contact_no.getText().toString().isEmpty()) {
                                if (!address.getText().toString().isEmpty()) {
                                    if (TandC_box.isChecked()) {

                                        from_Hr = fromTime.getHour();
                                        from_Min = fromTime.getMinute();

                                        to_Hr = toTime.getHour();
                                        to_Min = toTime.getMinute();

                                        @SuppressLint("DefaultLocale") String complete_from_time = String.format("%02d:%02d", from_Hr, from_Min);
                                        @SuppressLint("DefaultLocale") String complete_to_time = String.format("%02d:%02d", to_Hr, to_Min);


//                              Tejya, he successfull case ahe, ithe tuz pudhach code kar


                                        String success = "(0) parking name " + parking_name.getText().toString().trim() + " (1) Location " + longitude + " " + latitude + "(2) Amount " + amount.getText().toString() + " "
                                                + "(3) Capacity " + capacity.getText().toString() + " " + "(4) Contact no " + contact_no.getText().toString() + " "
                                                + "(4) Address " + address.getText().toString() + " " + "(5) Time - " + complete_from_time + " - " + complete_to_time;

                                        writeNewParking();

//                                        BaseActivity.SingleBtnShowAlertDialog(RegisterParkingLocation.this, "Success", success);


                                    } else {
                                        Toast.makeText(this, "Please check Terms & Conditions", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    address.requestFocus();
                                    address.setError("Please enter address");
                                }
                            } else {
                                contact_no.requestFocus();
                                contact_no.setError("Please enter contact no.");
                            }
                        } else {
                            capacity.requestFocus();
                            capacity.setError("Please enter capacity of your parking place");
                        }
                    } else {
                        amount.requestFocus();
                        amount.setError("Please enter parking charges here");
                    }
                } else {
                    parking_name.requestFocus();
                    parking_name.setError("Please enter parking location name");
                }
            } else {
                Toast.makeText(this, "Please share current location", Toast.LENGTH_LONG).show();
            }
        });

    }

    // Location permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // We got users permission
                getCurrentLocation();
            } else {
                // User denied for location permisson
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation() {

        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            getLocationFlag = true;
                            Toast.makeText(RegisterParkingLocation.this, "Current location taken successfully", Toast.LENGTH_LONG).show();
                        }
                    }
                });


//        LocationManager locationManager = (LocationManager) getSystemService(AppCompatActivity.LOCATION_SERVICE);
//        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                return;
//            }
//            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, new LocationListener() {
//                @Override
//                public void onLocationChanged(@NonNull Location location) {
//                    latitude = location.getLatitude();
//                    longitude = location.getLongitude();
//                    getLocationFlag = true;
//                     Toast.makeText(RegisterParkingLocation.this, "Current location taken successfully", Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void onStatusChanged(String provider, int status, Bundle extras) {
//                }
//
//                @Override
//                public void onProviderEnabled(String provider) {
//                }
//
//                @Override
//                public void onProviderDisabled(String provider) {
//                }
//            }, null);
//        } else {
//            Toast.makeText(this, "Please enable GPS to get your current location", Toast.LENGTH_SHORT).show();
//        }
    }





    public void writeNewParking(){

                 @SuppressLint("DefaultLocale") Parking p=new Parking(
                address.getText().toString(),
                parking_name.getText().toString(),
                contact_no.getText().toString(),
                amount.getText().toString(),
                capacity.getText().toString(),
                contact_no.getText().toString(),
                String.format("%02d", from_Hr),
                String.format("%02d", from_Min),
                String.format("%02d", to_Hr),
                String.format("%02d", to_Min),
                String.format("%f", latitude),
                String.format("%f", longitude)
                );

        mDatabase.child("Parking_locations").child(contact_no.getText().toString()).setValue(p);

        Intent intentHomeScreen = new Intent(this, HomeOwnerApp.class);
        startActivity(intentHomeScreen);

    }
}