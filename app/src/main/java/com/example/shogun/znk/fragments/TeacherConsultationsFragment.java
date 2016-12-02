package com.example.shogun.znk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shogun.znk.R;
import com.example.shogun.znk.TeacherActivity;
import com.example.shogun.znk.adapters.TeacherConsultationAdapter;
import com.example.shogun.znk.database.FakeDatabase;
import com.example.shogun.znk.models.Consultation;
import com.example.shogun.znk.models.User;
import com.example.shogun.znk.requests.GetConsultations;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TeacherConsultationsFragment extends Fragment {


    @BindView(R.id.rvTeacherConsultations)
    RecyclerView rvTeacherConsultations;
    FakeDatabase database = new FakeDatabase();
    static List<Consultation> consultations = FakeDatabase.getConsultations();
    static TeacherConsultationAdapter adapter;


    public TeacherConsultationsFragment() {
        // Required empt
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
        GetConsultations getConsultations = new GetConsultations();

        consultations = getConsultations.getAllContultations(User.getInstance().getId());

        adapter = new TeacherConsultationAdapter(consultations);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvTeacherConsultations.setLayoutManager(llm);
        rvTeacherConsultations.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @OnClick(R.id.btnAddConsultation)
    void addConsultation(){
        TeacherActivity.switchContent(AddFragmentConultationFragment.newInstance(consultations));
    }

    public static TeacherConsultationAdapter getAdapter() {
        return adapter;
    }
}
