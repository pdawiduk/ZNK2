package com.example.shogun.znk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.shogun.znk.R;
import com.example.shogun.znk.models.Consultation;
import com.example.shogun.znk.requests.PostConsultation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;


public class AddFragmentConultationFragment extends Fragment {

    private static Consultation consultation;
    private List<Consultation> consultations;
    @BindView(R.id.tpTime)
    TimePicker tpTime;
    @BindView(R.id.cvCalendar)
    CalendarView cvCalendar;


    private String date;
    private String time;


    public AddFragmentConultationFragment() {
        // Required empty public constructor
    }


    public static AddFragmentConultationFragment newInstance(List<Consultation> consultations) {
        AddFragmentConultationFragment fragment = new AddFragmentConultationFragment();
        fragment.consultations = consultations;
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
        View view = inflater.inflate(R.layout.fragment_add_fragment_conultation, container, false);
        ButterKnife.bind(this, view);
        cvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                date = i2 + "-" + Integer.valueOf(i1+1) + "-" + i;

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

    @OnClick(R.id.btnConfirm)
    void addConsultation() {
        synchronized (TeacherConsultationsFragment.consultations) {
            PostConsultation postConsultation = new PostConsultation();

            postConsultation.addConsultation(time + ":00 " + date);
            Log.d(TAG, "addConsultation: date "+ date + " time "+time);
            TeacherConsultationsFragment.consultations.notify();
            TeacherConsultationsFragment.getAdapter().notifyDataSetChanged();
        }
    }

}
