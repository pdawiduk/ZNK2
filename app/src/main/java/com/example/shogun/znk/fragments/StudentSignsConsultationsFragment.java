package com.example.shogun.znk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shogun.znk.R;

public class StudentSignsConsultationsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";




    public StudentSignsConsultationsFragment() {
        // Required empty public constructor
    }

    public static StudentSignsConsultationsFragment newInstance() {
        StudentSignsConsultationsFragment fragment = new StudentSignsConsultationsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consultations, container, false);
    }

}
