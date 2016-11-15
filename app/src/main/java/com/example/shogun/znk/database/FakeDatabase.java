package com.example.shogun.znk.database;

import com.example.shogun.znk.models.Consultation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shogun on 15.11.2016.
 */

public class FakeDatabase {
    List<Consultation> consultations = new ArrayList<>();
    List<String> signnedStudents = new ArrayList<>();

    public FakeDatabase() {
        consultations.add(new Consultation("24/07/1955", false));
        consultations.add(new Consultation("24/07/1985", false));
        consultations.add(new Consultation("24/07/2015", false));
        consultations.add(new Consultation("24/07/2014", false));
        consultations.add(new Consultation("24/07/2013", false));
        consultations.add(new Consultation("24/07/2012", false));

        signnedStudents.add("student1");
        signnedStudents.add("student2");
        signnedStudents.add("student3");
        signnedStudents.add("student4");
        signnedStudents.add("student5");
        signnedStudents.add("student6");
        signnedStudents.add("student7");


    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public List<String> getSignnedStudents() {
        return signnedStudents;
    }
}
