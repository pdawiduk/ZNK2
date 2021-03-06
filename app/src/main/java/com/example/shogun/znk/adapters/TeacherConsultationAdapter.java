package com.example.shogun.znk.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shogun.znk.R;
import com.example.shogun.znk.database.FakeDatabase;
import com.example.shogun.znk.fragments.ModifyConsultationDateFragment;
import com.example.shogun.znk.fragments.TeacherConsultationsFragment;
import com.example.shogun.znk.models.Consultation;
import com.example.shogun.znk.models.User;
import com.example.shogun.znk.requests.CancelConsultation;
import com.example.shogun.znk.requests.PutConsultation;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

import static com.example.shogun.znk.TeacherActivity.switchContent;

/**
 * Created by Shogun on 15.11.2016.
 */

public class TeacherConsultationAdapter extends RecyclerView.Adapter<TeacherConsultationAdapter.TeacherConsultationHolder> {


    List<Consultation> consultations = new ArrayList<>();

    public TeacherConsultationAdapter(List<Consultation> consultations) {
        for(Consultation consultation:consultations){
            if(!consultation.getCancelled()){
                this.consultations.add(consultation);
            }
        }
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
        holder.tvDate.setText(consultations.get(position).getDate() + "\n" + consultations.get(position).getAddress());

        if (consultations.get(position).getCancelled()) {
            holder.btnCancelConsultation.setText("Wznów");
        } else {
            holder.btnCancelConsultation.setText("Odwołaj");
        }
    }

    public class TeacherConsultationHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.btnCancelConsultation)
        Button btnCancelConsultation;
        @BindView(R.id.clParent)
        ConstraintLayout constraintLayout;

        public TeacherConsultationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            tvDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switchContent(ModifyConsultationDateFragment.newInstance(consultations.get(getLayoutPosition()).getStudentList(),consultations.get(getLayoutPosition()).getId(),consultations.get(getLayoutPosition()).getCancelled()));
                }
            });
        }

        @OnClick(R.id.btnCancelConsultation)
        void cancelConsultation(){
            CancelConsultation cancelConsultation = new CancelConsultation();
            int id = consultations.get(getLayoutPosition()).getId();
            cancelConsultation.cancelConsultation(id);
            notifyDataSetChanged();
            switchContent(TeacherConsultationsFragment.newInstance());
        }


    }
}
