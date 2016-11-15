package com.example.shogun.znk.models;

/**
 * Created by Shogun on 15.11.2016.
 */

public class Consultation {
    String date;
    Boolean signed;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getSigned() {
        return signed;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }

    public Consultation(String date, Boolean signed) {

        this.date = date;
        this.signed = signed;
    }
}
