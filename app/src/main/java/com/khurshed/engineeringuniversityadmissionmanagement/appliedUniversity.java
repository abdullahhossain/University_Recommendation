package com.khurshed.engineeringuniversityadmissionmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class appliedUniversity extends AppCompatActivity {
    ImageView back;
    List<Model1> modelList;
    MyAdapter1 adapter;
    RecyclerView recyclerView;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_university);
        modelList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclervieew1);
        recyclerView.setAdapter(adapter);
        sessionManager =new SessionManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(appliedUniversity.this));
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(appliedUniversity.this, Deshboard.class);

                startActivity(intent);

                finish();
            }
        });
        showData();
       // filteredData();
    }
    public void showData()
    {
        sessionManager = new SessionManager(this);
        // sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        String email1 = user.get(sessionManager.EMAIL);
        String url="http://projecttech.xyz/University%20Admission%20Management/appliedone.php?email="+email1;
        Log.d("TAG", "datashow: "+url);


        RequestQueue requestQueue = Volley.newRequestQueue(appliedUniversity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("Res=", url);
                ;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    Log.d("TAG", "onResponse: "+jsonArray);



                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);
                        Log.d("TAG", "onResponse1: "+data);
                        modelList.add(new Model1(
                                data.getString("id"),
                                data.getString("student_id"),
                                data.getString("universityname"),
                                data.getString("facultyname"),
                                data.getString("departments"),
                                data.getString("email")

                        ));


                        //Toast.makeText(Pending.this, ""+modelList, Toast.LENGTH_SHORT).show();
                    }
                   adapter = new MyAdapter1(modelList,appliedUniversity.this);
                   recyclerView.setAdapter(adapter);
                   adapter.notifyDataSetChanged();


                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(appliedUniversity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }
        );

        requestQueue.add(stringRequest);
    }


}