package com.example.shogun.znk.adapters;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shogun.znk.R;
import com.example.shogun.znk.fragments.SignedConsultationsFragment;
import com.example.shogun.znk.models.Consultation;
import com.example.shogun.znk.models.User;
import com.example.shogun.znk.requests.BookConsultation;
import com.example.shogun.znk.requests.UnBookConsultation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.shogun.znk.StudentActivity.switchContent;

/**
 * Created by Krystian on 2016-12-07.
 */

public class SignedConsultationsAdapter extends RecyclerView.Adapter<SignedConsultationsAdapter.SignedConsultationsHolder> {


    List<Consultation> consultations = new ArrayList<>();
    private long id;

    public SignedConsultationsAdapter(List<Consultation> consultations, long id) {
        for (Consultation consultation : consultations) {
            if (!consultation.getCancelled()) {
                this.consultations.add(consultation);
            }
        }
        this.id = id;
    }

    @Override
    public SignedConsultationsAdapter.SignedConsultationsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.signed_consultations, parent, false);
        return new SignedConsultationsAdapter.SignedConsultationsHolder(view);
    }

    @Override
    public int getItemCount() {
        return consultations.size();
    }

    @Override
    public void onBindViewHolder(SignedConsultationsAdapter.SignedConsultationsHolder holder, int position) {
        holder.tvDate.setText(consultations.get(position).getDate() + "\n" + consultations.get(position).getAddress() + "\n" + consultations.get(position).getTeacherLogin());
        holder.btnBookConsultation.setText("Zapisz");
        for (String login : consultations.get(position).getStudentList()) {
            if (login.equals(User.getInstance().getLogin())) {
                holder.btnBookConsultation.setText("Wypisz");
            }
        }
    }

    public class SignedConsultationsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.btnBookConsultation)
        Button btnBookConsultation;
        @BindView(R.id.clParent)
        ConstraintLayout constraintLayout;

        public SignedConsultationsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.btnBookConsultation)
        void bookConsultation() {
            BookConsultation bookConsultation = new BookConsultation();
            UnBookConsultation unBookConsultation = new UnBookConsultation();
            int consultationId = consultations.get(getLayoutPosition()).getId();
            int studentId = User.getInstance().getId();
            if (btnBookConsultation.getText().equals("Wypisz")) {
                unBookConsultation.unBookOnConsultation(consultationId);
            } else {
                bookConsultation.bookOnConsultation(consultationId);
            }
            notifyDataSetChanged();
            switchContent(SignedConsultationsFragment.newInstance());
        }


    }
}
