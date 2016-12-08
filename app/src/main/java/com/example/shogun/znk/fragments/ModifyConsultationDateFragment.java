package com.example.shogun.znk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.shogun.znk.R;
import com.example.shogun.znk.adapters.ConsultationsAdapter;
import com.example.shogun.znk.adapters.SignedStudentsAdapter;
import com.example.shogun.znk.database.FakeDatabase;
import com.example.shogun.znk.models.Consultation;
import com.example.shogun.znk.models.User;
import com.example.shogun.znk.requests.PutConsultation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.shogun.znk.TeacherActivity.switchContent;


public class ModifyConsultationDateFragment extends Fragment {

    private static int id;
    FakeDatabase fakeDatabase = new FakeDatabase();
    @BindView(R.id.tpTime)
    TimePicker tpTime;
    @BindView(R.id.cvCalendar)
    CalendarView cvCalendar;
    @BindView(R.id.etConsultationLocalization)
    EditText etConsultationLocalization;

    @BindView(R.id.rvSignedStudents)
    RecyclerView rvSignedStudents;
    RecyclerView.LayoutManager llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    private String date;
    private String time;
    private boolean cancelled;
    private SignedStudentsAdapter signedStudentsAdapter;
    private List<String> signedStudetnsList;


    public ModifyConsultationDateFragment() {
        // Required empty public constructor
    }


    public static ModifyConsultationDateFragment newInstance(List<String> signedStudentsList, int id, boolean cancelled) {
        ModifyConsultationDateFragment fragment = new ModifyConsultationDateFragment();
        fragment.signedStudetnsList = signedStudentsList;
        fragment.id = id;
        fragment.cancelled = cancelled;
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
        View view = inflater.inflate(R.layout.fragment_edit_fragment_conultation, container, false);
        ButterKnife.bind(this, view);
        cvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                date = i2 + "-" + Integer.valueOf(i1 + 1) + "-" + i;

            }
        });
        tpTime.setIs24HourView(true);
        tpTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                time = i + ":" + i1;

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

            signedStudentsAdapter = new SignedStudentsAdapter(getContext(),signedStudetnsList);
            rvSignedStudents.setLayoutManager(llm);
            rvSignedStudents.setAdapter(signedStudentsAdapter);


    }

    @OnClick(R.id.btnConfirmEdit)
    void editConsultation() {
        synchronized (TeacherConsultationsFragment.consultations) {
            PutConsultation putConsultation = new PutConsultation();

            putConsultation.editDateConsultation(id, time + " " + date, cancelled, etConsultationLocalization.getText().toString());
            TeacherConsultationsFragment.consultations.notify();
            TeacherConsultationsFragment.getAdapter().notifyDataSetChanged();
            switchContent(TeacherConsultationsFragment.newInstance());
        }
    }

}
