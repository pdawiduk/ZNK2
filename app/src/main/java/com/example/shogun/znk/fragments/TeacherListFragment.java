package com.example.shogun.znk.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.shogun.znk.R;
import com.example.shogun.znk.StudentActivity;
import com.example.shogun.znk.adapters.ConsultationsAdapter;
import com.example.shogun.znk.adapters.StudentConsutationsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TeacherListFragment extends Fragment {

    @BindView(R.id.rvTeachersList)
    RecyclerView rvTeacherList;
    List<String> teachers = new ArrayList<>();
    StudentConsutationsAdapter adapter ;
    public TeacherListFragment() {
        // Required empty public constructor
    }

    public static TeacherListFragment newInstance() {
        TeacherListFragment fragment = new TeacherListFragment();
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
        View view = inflater.inflate(R.layout.fragment_teacher_list, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(((StudentActivity) getActivity())
                .getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(searchMenuItem, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        MenuItemCompat.setActionView(searchMenuItem,searchView);
        searchView.setVisibility(View.VISIBLE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        teachers.add("Karol nowak");
        teachers.add("Jan kowalski");
        teachers.add("Krzysztof skiba");
        teachers.add("Tadeusz Pan");
        teachers.add("Kerry king");
        teachers.add("Markras");
        teachers.add("prof. Dominik Sankowski");
        adapter = new StudentConsutationsAdapter(getContext(),teachers);
        RecyclerView.LayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvTeacherList.setLayoutManager(llm);
        rvTeacherList.setAdapter(adapter);
    }
}
