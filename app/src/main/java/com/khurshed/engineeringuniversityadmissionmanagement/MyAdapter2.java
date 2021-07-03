package com.khurshed.engineeringuniversityadmissionmanagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.CustomAdapterHolder>{
    private List<Model2> arrayListModel;
    private Context context;

    public MyAdapter2(List<Model2> arrayListModel, Context context) {
        this.arrayListModel = arrayListModel;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter2.CustomAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.university_row,parent,false);
        return new MyAdapter2.CustomAdapterHolder(view); //layout set korlam
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.CustomAdapterHolder holder, int position) {
        Model2 model = arrayListModel.get(position);
        holder.uniName.setText(model.getUniversityname());
        holder.faculty.setText(model.getFacultyname());
        holder.department.setText(model.getDepartments());


    }

    @Override
    public int getItemCount() {
        return arrayListModel.size();
    }

    public class CustomAdapterHolder extends RecyclerView.ViewHolder{
        TextView uniName, faculty,department;
        Button admitCard;
        public CustomAdapterHolder(@NonNull View itemView) {
            super(itemView);
            uniName = itemView.findViewById(R.id.universityInfo);
            faculty = itemView.findViewById(R.id.facultyInfo);
            department = itemView.findViewById(R.id.departmentInfo);
            //admitCard = itemView.findViewById(R.id.admit);
        }
    }
}

