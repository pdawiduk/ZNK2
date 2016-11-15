package com.example.shogun.znk.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.shogun.znk.R;
import com.example.shogun.znk.requests.RegisterUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterFragment extends Fragment {

    @BindView(R.id.etIndexNo)
    EditText etIndexno;

    @BindView(R.id.etMailForm)
    EditText etMail;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.btnOK)
    Button btnConfirm;

    String password;
    String mail;
    String indexNo;


    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btnOK)
    void register() {

        mail = etMail.getText().toString();
        password = etPassword.getText().toString();
        indexNo = etIndexno.getText().toString();

        RegisterUser registerUser = new RegisterUser(getContext());
        registerUser.registerUser(indexNo,password,mail);
    }

}
