package com.khurshed.engineeringuniversityadmissionmanagement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import static com.khurshed.engineeringuniversityadmissionmanagement.Constant.PREF;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomAdapterHolder>
{
    private List<Model> arrayListModel;
    private Context context;
    String uni,department,faculty,id;
    String idlist;
    int idd;
    SessionManager sessionManager;
    int checkCount;

    public MyAdapter(List<Model> arrayListModel, Context context) {
        this.arrayListModel = arrayListModel;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.CustomAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row,parent,false);
        return new CustomAdapterHolder(view); //layout set korlam
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.CustomAdapterHolder holder, int position) {
        Model model = arrayListModel.get(position);
        SharedPreferences sharedPreferences= context.getSharedPreferences(PREF,Context.MODE_PRIVATE);
        String TOTAL = sharedPreferences.getString("Total",null);
        Double res = Double.valueOf(TOTAL);
        Double apiTotal = Double.valueOf(model.getTotal());
        if (res>=apiTotal)
        {

            holder.uniName.setText(model.getUniversityName());
            holder.faculty.setText(model.getFacultyName());
            holder.ssc.setText(model.getSsc());
            holder.hsc.setText(model.getHsc());
            holder.department.setText(model.getDepartments());
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id = model.getId();
                    uni = model.getUniversityName();
                    department = model.getDepartments();
                    faculty = model.getFacultyName();
                    if (holder.checkBox.isChecked())
                    {
                        sessionManager = new SessionManager(context);
                        // sessionManager.checkLogin();

                        HashMap<String, String> user = sessionManager.getUserDetail();
                        String email1 = user.get(sessionManager.EMAIL);
                        Constant.applynow(context,id,uni,faculty,department,email1);
                        //  Intent intent = new Intent(holder.checkBox.getContext(), Deshboard.class);
                        //holder.checkBox.getContext().startActivity(intent);
                        Toast.makeText(context, "You have selected "+uni, Toast.LENGTH_SHORT).show();
                        idlist = id;

                    }
                    else
                    {
                        sessionManager = new SessionManager(context);
                        // sessionManager.checkLogin();

                        HashMap<String, String> user = sessionManager.getUserDetail();
                        String email2 = user.get(sessionManager.EMAIL);
                        Constant.deleteData(context,email2,uni,faculty);
                    }





                }
            });


            Button applyBtn= ((showUniversity)context).findViewById(R.id.applybtn);
            applyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(holder.checkBox.getContext(), Deshboard.class);
                    holder.checkBox.getContext().startActivity(intent);


                }
            });

        }
        else{
            //Toast.makeText(context, ""+model.getTotal(), Toast.LENGTH_SHORT).show();
            holder.uniName.setVisibility(View.GONE);
            holder.faculty.setVisibility(View.GONE);
            holder.ssc.setVisibility(View.GONE);
            holder.hsc.setVisibility(View.GONE);
            holder.department.setVisibility(View.GONE);
            holder.tv1.setVisibility(View.GONE);
            holder.tv2.setVisibility(View.GONE);
            holder.tv3.setVisibility(View.GONE);
            holder.tv4.setVisibility(View.GONE);
            holder.tv5.setVisibility(View.GONE);
            holder.checkBox.setVisibility(View.GONE);
            holder.view.setVisibility(View.GONE);
        }





    }

    @Override
    public int getItemCount() {
        return arrayListModel.size();
    }

    public class CustomAdapterHolder extends RecyclerView.ViewHolder {
        TextView uniName, faculty,ssc,hsc,department;
        TextView tv1,tv2,tv3,tv4,tv5;
        CheckBox checkBox;
        View view;
        public CustomAdapterHolder(@NonNull View itemView) {
            super(itemView);
            uniName = itemView.findViewById(R.id.universityInfo);
            faculty = itemView.findViewById(R.id.facultyInfo);
            ssc = itemView.findViewById(R.id.sscgpaInfo);
            hsc = itemView.findViewById(R.id.hscgpaInfo);
            department = itemView.findViewById(R.id.departmentInfo);
            checkBox = itemView.findViewById(R.id.checkbox1);

            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            tv4 = itemView.findViewById(R.id.tv4);
            tv5 = itemView.findViewById(R.id.tv5);

            view = itemView.findViewById(R.id.view);

        }
    }
}
