package com.example.parkingwala_10.model;

public class Parking {
    private String  address, parking_name,parking_Id,amount,capacity,contact_no,from_Hr ,from_Min,to_Hr ,to_Min,latitude, longitude;


    public Parking() {
    }

    public Parking(String address, String parking_name, String parking_Id, String amount, String capacity, String contact_no, String from_Hr, String from_Min, String to_Hr, String to_Min, String latitude, String longitude) {
        this.address = address;
        this.parking_name = parking_name;
        this.parking_Id = parking_Id;
        this.amount = amount;
        this.capacity = capacity;
        this.contact_no = contact_no;
        this.from_Hr = from_Hr;
        this.from_Min = from_Min;
        this.to_Hr = to_Hr;
        this.to_Min = to_Min;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParking_name() {
        return parking_name;
    }

    public void setParking_name(String parking_name) {
        this.parking_name = parking_name;
    }

    public String getParking_Id() {
        return parking_Id;
    }

    public void setParking_Id(String parking_Id) {
        this.parking_Id = parking_Id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getFrom_Hr() {
        return from_Hr;
    }

    public void setFrom_Hr(String from_Hr) {
        this.from_Hr = from_Hr;
    }

    public String getFrom_Min() {
        return from_Min;
    }

    public void setFrom_Min(String from_Min) {
        this.from_Min = from_Min;
    }

    public String getTo_Hr() {
        return to_Hr;
    }

    public void setTo_Hr(String to_Hr) {
        this.to_Hr = to_Hr;
    }

    public String getTo_Min() {
        return to_Min;
    }

    public void setTo_Min(String to_Min) {
        this.to_Min = to_Min;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
