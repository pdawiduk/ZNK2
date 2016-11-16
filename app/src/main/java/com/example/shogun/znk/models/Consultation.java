package com.example.shogun.znk.models;

import java.util.List;

/**
 * Created by Shogun on 15.11.2016.
 */

public class Consultation {
    private int id;
    String date;
    Boolean signed;
    Boolean cancelled;
    List<String> studentList;

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

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public List<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<String> studentList) {
        this.studentList = studentList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Consultation(String date, Boolean signed) {

        this.date = date;
        this.signed = signed;
    }

    public Consultation(int id,String date, Boolean cancelled, List<String> studentList) {
        this.id = id;
        this.date = date;
        this.cancelled = cancelled;
        this.studentList = studentList;
    }
}
