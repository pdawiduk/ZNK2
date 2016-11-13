package com.example.shogun.znk.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shogun.znk.R;
import com.example.shogun.znk.StudentActivity;
import com.example.shogun.znk.TeacherActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginFragment extends Fragment {

    @BindView(R.id.etMailForm)
    EditText etMail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnOK)
    Button btnLogin;

    String mail;
    String password;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnOK)
    void login() {


        mail = etMail.getText().toString();
        password = etPassword.getText().toString();

        etMail.setText("");
        etPassword.setText("");

        if (mail.contains("@edu")) {

            Intent intent = new Intent(getActivity(), StudentActivity.class);
            startActivity(intent);
        } else if (mail.contains("@p.lodz.pl")) {
            Intent intent = new Intent(getActivity(), TeacherActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "nie poprawny email", Toast.LENGTH_SHORT).show();
        }


    }

}
