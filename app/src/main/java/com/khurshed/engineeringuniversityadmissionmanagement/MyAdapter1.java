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

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.CustomAdapterHolder>{
    private List<Model1> arrayListModel;
    private Context context;

    public MyAdapter1(List<Model1> arrayListModel, Context context) {
        this.arrayListModel = arrayListModel;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter1.CustomAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.university_row,parent,false);
        return new MyAdapter1.CustomAdapterHolder(view); //layout set korlam
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter1.CustomAdapterHolder holder, int position) {
        Model1 model = arrayListModel.get(position);
        holder.uniName.setText(model.getUniversityname());
        holder.faculty.setText(model.getFacultyname());
        holder.department.setText(model.getDepartments());
        holder.admitCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.admitCard.getContext(), PaymentGateway.class);
                intent.putExtra("university", holder.uniName.getText().toString());
                intent.putExtra("faculty", holder.faculty.getText().toString());
                intent.putExtra("department", holder.department.getText().toString());
                holder.admitCard.getContext().startActivity(intent);
            }
        });

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
            admitCard = itemView.findViewById(R.id.admit);
        }
    }
}
