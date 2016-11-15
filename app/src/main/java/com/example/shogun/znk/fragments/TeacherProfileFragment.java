package com.example.shogun.znk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shogun.znk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TeacherProfileFragment extends Fragment {

    private  String name;
    @BindView(R.id.tvNameSurname)
    TextView tvNameSurname;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public TeacherProfileFragment() {
        // Required empty public constructor
    }

    public static TeacherProfileFragment newInstance(String name) {
        TeacherProfileFragment fragment = new TeacherProfileFragment();
        fragment.name = name;
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
        View view = inflater.inflate(R.layout.fragment_teacher_profile, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        tvNameSurname.setText(name);
    }

    @OnClick(R.id.btnSignCloseConsultation)
    void signOnCloseConsultation(){

    }

    @OnClick(R.id.btnOtherConsultations)
    void showConsultations(){}
}
