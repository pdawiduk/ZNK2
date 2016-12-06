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

import com.example.shogun.znk.ProfileActivity;
import com.example.shogun.znk.R;
import com.example.shogun.znk.models.User;
import com.example.shogun.znk.requests.PutUser;

import butterknife.ButterKnife;

/**
 * Created by Krystian on 2016-12-06.
 */

public class ProfileFragment extends Fragment {


    EditText etFirstName;
    EditText etLastName;
    EditText etConsultationLocalization;
    Button btnSaveProfile;
    TextView etLogin;
    TextView tvConsultationLocalization;
    ProfileActivity profileActivity;




    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(ProfileActivity profileActivity) {
        ProfileFragment fragment = new ProfileFragment();
        fragment.profileActivity = profileActivity;
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
        etConsultationLocalization = (EditText) view.findViewById(R.id.etConsultationLocalization);
        btnSaveProfile = (Button) view.findViewById(R.id.btnSaveProfile);
        etLogin = (TextView) view.findViewById(R.id.tvLogin);
        tvConsultationLocalization = (TextView) view.findViewById(R.id.tvConsultationLocalization);

        etLogin.setText(String.valueOf(User.getInstance().getLogin()));
        etFirstName.setText(String.valueOf(User.getInstance().getFirstName()));
        etLastName.setText(String.valueOf(User.getInstance().getLastName()));
        if(User.getInstance().getAuthorities().contains("ROLE_STUDENT")){
            etConsultationLocalization.setVisibility(View.GONE);
            tvConsultationLocalization.setVisibility(View.GONE);

        } else {
            etConsultationLocalization.setVisibility(View.VISIBLE);
            tvConsultationLocalization.setVisibility(View.VISIBLE);
        }

        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PutUser putUser = new PutUser();
                putUser.updateProfile(etFirstName.getText().toString(),etLastName.getText().toString());
                User.getInstance().setFirstName(etFirstName.getText().toString());
                User.getInstance().setLastName(etLastName.getText().toString());
                profileActivity.finish();
            }
        });

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
