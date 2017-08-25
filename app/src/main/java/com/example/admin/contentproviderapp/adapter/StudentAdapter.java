package com.example.admin.contentproviderapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.admin.contentproviderapp.R;
import com.example.admin.contentproviderapp.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/23/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{

    List<Student> studentList = new ArrayList<>();

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = studentList.get(position);
        //YoYo.with(Techniques.FadeIn).playOn(holder.cvStudentCard);
        holder.tvStudentId.setText("" + student.getSchoolId());
        holder.tvFullName.setText(student.getStudentFirstName() + " " + student.getStudentLastName());
        holder.tvExpectedGradDate.setText(student.getExpectedGradDate());
        holder.tvGpa.setText("" + student.getGpa());
        holder.ivPhoto.setImageResource(R.drawable.student);

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cvStudentCard;
        ImageView ivPhoto;
        TextView tvStudentId, tvFullName, tvExpectedGradDate, tvGpa;

        public ViewHolder(View itemView) {
            super(itemView);

            tvStudentId = (TextView) itemView.findViewById(R.id.tvStudentId);
            tvFullName = (TextView) itemView.findViewById(R.id.tvStudentFullName);
            tvExpectedGradDate = (TextView) itemView.findViewById(R.id.tvGraduationDate);
            tvGpa = (TextView) itemView.findViewById(R.id.tvGpa);
            ivPhoto = (ImageView) itemView.findViewById(R.id.ivStudentPhoto);
            cvStudentCard = (CardView) itemView.findViewById(R.id.cvStudentCard);
        }
    }

}
