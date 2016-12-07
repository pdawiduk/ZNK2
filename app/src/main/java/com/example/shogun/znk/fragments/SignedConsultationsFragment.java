package com.example.shogun.znk.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shogun.znk.R;
import com.example.shogun.znk.adapters.SelectedTeacherConsultationAdapter;
import com.example.shogun.znk.adapters.SignedConsultationsAdapter;
import com.example.shogun.znk.database.FakeDatabase;
import com.example.shogun.znk.models.Consultation;
import com.example.shogun.znk.models.User;
import com.example.shogun.znk.requests.GetConsultations;
import com.example.shogun.znk.requests.GetStudentSignedConsultations;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Krystian on 2016-12-07.
 */

public class SignedConsultationsFragment extends Fragment {


    @BindView(R.id.rvSignedConsultations)
    RecyclerView rvSignedConsultations;
    FakeDatabase database = new FakeDatabase();
    static List<Consultation> consultations = FakeDatabase.getConsultations();
    static SignedConsultationsAdapter adapter;


    public SignedConsultationsFragment() {
        // Required empt
    }


    public static SignedConsultationsFragment newInstance() {
        SignedConsultationsFragment fragment = new SignedConsultationsFragment();
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
        View view = inflater.inflate(R.layout.fragment_signed_consultations, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        GetStudentSignedConsultations getStudentSignedConsultations = new GetStudentSignedConsultations();

        consultations = getStudentSignedConsultations.getStudentSignedConsultations(User.getInstance().getId());

        adapter = new SignedConsultationsAdapter(consultations, User.getInstance().getId());
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvSignedConsultations.setLayoutManager(llm);
        rvSignedConsultations.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public static SignedConsultationsAdapter getAdapter() {
        return adapter;
    }

    {
    }
}
