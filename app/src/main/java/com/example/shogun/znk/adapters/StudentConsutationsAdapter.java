package com.example.shogun.znk.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.shogun.znk.R;
import com.example.shogun.znk.StudentActivity;
import com.example.shogun.znk.fragments.TeacherProfileFragment;

import java.util.List;

/**
 * Created by Shogun on 04.11.2016.
 */

public class StudentConsutationsAdapter extends RecyclerView.Adapter<StudentConsutationsAdapter.ConsultationHolder> {

    List<String> teacherList;
    Context context;

    public StudentConsutationsAdapter(Context context, List<String> teacherList) {
        this.teacherList = teacherList;
        this.context = context;
    }

    @Override
    public ConsultationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.consultation_item, parent, false);
        ConsultationHolder consultationHolder = new ConsultationHolder(v);
        return consultationHolder;

    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    @Override
    public void onBindViewHolder(ConsultationHolder holder, int position) {

       holder.textView.setText( teacherList.get(position));
    }

    class ConsultationHolder extends RecyclerView.ViewHolder{

        TextView textView ;
        public ConsultationHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tvDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    StudentActivity.switchContent(TeacherProfileFragment.newInstance(teacherList.get(getLayoutPosition())));
                }
            });
        }
    }
}
