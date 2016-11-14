package com.example.shogun.znk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shogun.znk.R;

import butterknife.ButterKnife;


public class StudentWelcomeFragment extends Fragment {

    public StudentWelcomeFragment() {

    }

    public static StudentWelcomeFragment newInstance() {
        StudentWelcomeFragment fragment = new StudentWelcomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_student_welcome, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
