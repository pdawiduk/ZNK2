package com.example.shogun.znk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shogun.znk.R;
import com.example.shogun.znk.database.FakeDatabase;

import butterknife.ButterKnife;

public class ConsultationListFragment extends Fragment {

    FakeDatabase fakeDatabase = new FakeDatabase();

    public ConsultationListFragment() {
        // Required empty public constructor
    }


    public static ConsultationListFragment newInstance() {
        ConsultationListFragment fragment = new ConsultationListFragment();
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
        View view = inflater.inflate(R.layout.fragment_consultation_list, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

}
