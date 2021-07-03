package com.khurshed.engineeringuniversityadmissionmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddUniversity extends AppCompatActivity {
    ImageView backbtn;
    TextView uniName, faculty, ssc, hsc,departments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_university);
        uniName = findViewById(R.id.University_name);
        faculty = findViewById(R.id.University_faculty);
        ssc = findViewById(R.id.ssc);
        hsc = findViewById(R.id.hsc);
        departments = findViewById(R.id.departments);

       backbtn = findViewById(R.id.back);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddUniversity.this,MainActivity.class));
                finish();
            }
        });
    }

    public void insertInfo(View view) {
        insertUniInfo();

    }
    public void insertUniInfo()
    {
        String url = "http://projecttech.xyz/University%20Admission%20Management/insertUniInfo.php";
        Log.d("TAG", "insertUniInfo: "+url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddUniversity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(AddUniversity.this, DeshboardActivity.class));
                //finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddUniversity.this, "Check your Internet Connection.", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> stringStringMap = new HashMap<>();

                stringStringMap.put("universityName", uniName.getText().toString());
                stringStringMap.put("faculty", faculty.getText().toString());
                stringStringMap.put("ssc", ssc.getText().toString());
                stringStringMap.put("hsc", hsc.getText().toString());
                stringStringMap.put("department", departments.getText().toString());


                //stringStringMap.put("token", token);




                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}