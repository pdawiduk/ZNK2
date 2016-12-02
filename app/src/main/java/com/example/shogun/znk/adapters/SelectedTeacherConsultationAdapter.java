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
import com.example.shogun.znk.fragments.SelectedTeacherConsultationsFragment;
import com.example.shogun.znk.fragments.TeacherConsultationsFragment;
import com.example.shogun.znk.models.Consultation;
import com.example.shogun.znk.models.User;
import com.example.shogun.znk.requests.BookConsultation;
import com.example.shogun.znk.requests.PostConsultation;
import com.example.shogun.znk.requests.PutConsultation;
import com.example.shogun.znk.requests.UnBookConsultation;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

import static com.example.shogun.znk.StudentActivity.switchContent;


/**
 * Created by Shogun on 15.11.2016.
 */

public class SelectedTeacherConsultationAdapter extends RecyclerView.Adapter<SelectedTeacherConsultationAdapter.TeacherConsultationHolder> {


    List<Consultation> consultations = new ArrayList<>();
    private long id;

    public SelectedTeacherConsultationAdapter(List<Consultation> consultations,long id) {
        this.consultations = consultations;
        this.id = id;
    }

    @Override
    public TeacherConsultationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_teacher_consultation_item,parent,false);
        return new TeacherConsultationHolder(view);
    }

    @Override
    public int getItemCount() {
        return consultations.size();
    }

    @Override
    public void onBindViewHolder(TeacherConsultationHolder holder, int position) {
        holder.tvDate.setText(consultations.get(position).getDate());
        holder.btnBookConsultation.setText("Zapisz");
            for (String login : consultations.get(position).getStudentList()){
                if(login.equals(User.getInstance().getLogin())){
                    holder.btnBookConsultation.setText("Wypisz");
                }
            }
    }

    public class TeacherConsultationHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.btnBookConsultation)
        Button btnBookConsultation;
        @BindView(R.id.clParent)
        ConstraintLayout constraintLayout;

        public TeacherConsultationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.btnBookConsultation)
        void bookConsultation(){
            BookConsultation bookConsultation = new BookConsultation();
            UnBookConsultation unBookConsultation = new UnBookConsultation();
            int consultationId = consultations.get(getLayoutPosition()).getId();
            int studentId = User.getInstance().getId();
            if(btnBookConsultation.getText().equals("Wypisz")) {
                unBookConsultation.unBookOnConsultation(consultationId);
            } else {
                bookConsultation.bookOnConsultation(consultationId);
            }
            notifyDataSetChanged();
            switchContent(SelectedTeacherConsultationsFragment.newInstance(id));
        }


    }
}
