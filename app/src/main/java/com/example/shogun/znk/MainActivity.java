package com.example.shogun.znk;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.shogun.znk.fragments.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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
        fragmentTransaction.replace(R.id.fragment_container,MainFragment.newInstance()).commit();
        ActionBar actionBar = getSupportActionBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            actionBar.setBackgroundDrawable(getDrawable(R.color.colorTulRed));
        }


    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    public static void switchContent(Fragment fragment){

        switchFragmentTransaction.beginTransaction().addToBackStack(null).replace(R.id.fragment_container,fragment).commit();

    }
}
