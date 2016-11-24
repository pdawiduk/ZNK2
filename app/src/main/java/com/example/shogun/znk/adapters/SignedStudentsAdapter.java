package com.example.shogun.znk.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shogun.znk.R;

import java.util.List;

/**
 * Created by Krystianek on 2016-11-24.
 */

public class SignedStudentsAdapter extends RecyclerView.Adapter<SignedStudentsAdapter.SignedStudentHolder> {

    private List<String> signedStudents;
    private Context context;

    public SignedStudentsAdapter(Context context, List<String> signedStudents) {
        this.context = context;
        this.signedStudents = signedStudents;
    }

    @Override
    public SignedStudentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.consultation_item, parent, false);
        SignedStudentHolder signedStudentHolder = new SignedStudentHolder(v);
        return signedStudentHolder;
    }

    @Override
    public void onBindViewHolder(SignedStudentHolder holder, int position) {
        holder.textView.setText( signedStudents.get(position));
    }

    @Override
    public int getItemCount() {
        return signedStudents.size();
    }

    public class SignedStudentHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public SignedStudentHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tvDate);
        }
    }
}
