package com.example.shogun.znk.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shogun.znk.R;
import com.example.shogun.znk.models.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Krystian on 2016-12-06.
 */

public class ProfileFragment extends Fragment {


    EditText etFirstName;
    EditText etLastName;
    EditText etConsultationDate;
    Button btnSaveProfile;
    TextView etLogin;
    TextView tvConsultationDate;




    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        etLastName = (EditText) view.findViewById(R.id.etLastName);
        etConsultationDate = (EditText) view.findViewById(R.id.etConsultationDate);
        btnSaveProfile = (Button) view.findViewById(R.id.btnSaveProfile);
        etLogin = (TextView) view.findViewById(R.id.tvLogin);
        tvConsultationDate = (TextView) view.findViewById(R.id.tvConsultationDate);

        etLogin.setText(String.valueOf(User.getInstance().getLogin()));
        etFirstName.setText(String.valueOf(User.getInstance().getFirstName()));
        etLastName.setText(String.valueOf(User.getInstance().getLastName()));
        if(User.getInstance().getAuthorities().contains("ROLE_STUDENT")){
            etConsultationDate.setVisibility(View.GONE);
            tvConsultationDate.setVisibility(View.GONE);

        } else {
            etConsultationDate.setVisibility(View.VISIBLE);
            tvConsultationDate.setVisibility(View.VISIBLE);
        }

        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
