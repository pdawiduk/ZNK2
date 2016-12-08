package com.example.shogun.znk.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shogun.znk.R;
import com.example.shogun.znk.StudentActivity;
import com.example.shogun.znk.TeacherActivity;
import com.example.shogun.znk.models.User;
import com.example.shogun.znk.requests.GetAccount;
import com.example.shogun.znk.requests.LoginUser;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.android.gms.internal.zzs.TAG;


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

        LoginUser loginUser = new LoginUser(getContext());
        String token = loginUser.signUpUser(mail,password,String.valueOf(false));

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        GetAccount getAccount = new GetAccount();
        User user = getAccount.getUser(token);

        if((user.getAuthorities().contains("ROLE_ADMIN") && user.getAuthorities().contains("ROLE_USER")) || user.getAuthorities().contains("ROLE_TEACHER")) {
            Intent intent = new Intent(getActivity(), TeacherActivity.class);
            startActivity(intent);
        } else if (user.getAuthorities().contains("ROLE_USER") || user.getAuthorities().contains("ROLE_STUDENT"))  {
            Intent intent = new Intent(getActivity(), StudentActivity.class);
            startActivity(intent);
        }
    }

}
