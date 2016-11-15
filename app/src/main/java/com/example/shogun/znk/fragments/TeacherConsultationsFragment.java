package com.example.shogun.znk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shogun.znk.R;
import com.example.shogun.znk.adapters.TeacherConsultationAdapter;
import com.example.shogun.znk.database.FakeDatabase;
import com.example.shogun.znk.models.Consultation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TeacherConsultationsFragment extends Fragment {

FakeDatabase fakeDatabase = new FakeDatabase();
    List<Consultation> consultationList = fakeDatabase.getConsultations();
    TeacherConsultationAdapter adapter = new TeacherConsultationAdapter(consultationList);
    RecyclerView.LayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
    @BindView(R.id.rvTeacherConsultations)
    RecyclerView rvTeacherConsultations;


    public TeacherConsultationsFragment() {
        // Required empty public constructor
    }


    public static TeacherConsultationsFragment newInstance() {
        TeacherConsultationsFragment fragment = new TeacherConsultationsFragment();
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
        View view = inflater.inflate(R.layout.fragment_teacher_consultations, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        rvTeacherConsultations.setLayoutManager(llm);
        rvTeacherConsultations.setAdapter(adapter);
    }
}
