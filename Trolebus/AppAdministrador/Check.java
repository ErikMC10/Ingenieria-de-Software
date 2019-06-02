package com.example.myapplication;

import com.google.firebase.database.DatabaseReference;

public class Check {

    String checkin, checkout;
    private DatabaseReference mDatabase;// ...



    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;


    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}
