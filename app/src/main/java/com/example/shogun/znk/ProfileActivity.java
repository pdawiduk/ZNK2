package com.example.shogun.znk;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.example.shogun.znk.fragments.MainFragment;
import com.example.shogun.znk.fragments.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Krystian on 2016-12-06.
 */

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container)
    RelativeLayout rlFragmentContainer;
    private FragmentTransaction fragmentTransaction ;
    private static FragmentManager switchFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switchFragmentTransaction = getSupportFragmentManager();
        fragmentTransaction.replace(R.id.fragment_container, ProfileFragment.newInstance(this)).commit();
    }
}
