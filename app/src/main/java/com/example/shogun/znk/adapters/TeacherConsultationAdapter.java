package com.example.shogun.znk.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shogun.znk.R;
import com.example.shogun.znk.database.FakeDatabase;
import com.example.shogun.znk.models.Consultation;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shogun on 15.11.2016.
 */

public class TeacherConsultationAdapter extends RecyclerView.Adapter<TeacherConsultationAdapter.TeacherConsultationHolder> {


    List<Consultation> consultations = new ArrayList<>();

    public TeacherConsultationAdapter(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    @Override
    public TeacherConsultationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_consultation_item,parent,false);
        return new TeacherConsultationHolder(view);
    }

    @Override
    public int getItemCount() {
        return consultations.size();
    }

    @Override
    public void onBindViewHolder(TeacherConsultationHolder holder, int position) {
        holder.tvDate.setText(consultations.get(position).getDate());

    }

    public class TeacherConsultationHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvDate)
        TextView tvDate;
        public TeacherConsultationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.btnDeleteConsultation)
        void delteConsultation(){
            consultations.remove(getLayoutPosition());
            notifyDataSetChanged();
        }
    }
}