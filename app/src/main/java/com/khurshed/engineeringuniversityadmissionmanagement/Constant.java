package com.khurshed.engineeringuniversityadmissionmanagement;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class Constant {
    public static final String PREF = "APP_REF";


    public static void applynow(Context context, String id, String university, String faculty, String department, String email)
    {
        String url = "http://projecttech.xyz/University%20Admission%20Management/save_info_to_apply.php";
        Log.d("TAG", "insertUniInfo: "+url);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(context, "Inserted Successfully", Toast.LENGTH_SHORT).show();

                //finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Check your Internet Connection.", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> stringStringMap = new HashMap<>();

                stringStringMap.put("student_id", id);
                stringStringMap.put("universityname",  university);
                stringStringMap.put("facultyname", faculty);
                stringStringMap.put("departments", department);
                stringStringMap.put("email", email);


                //stringStringMap.put("token", token);




                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static void deleteData(Context context, String email, String universityname, String Faculty)
    {
        String url = "http://projecttech.xyz/University%20Admission%20Management/delete_row.php?email="+email+"&universityname="+universityname+"&facultyname="+Faculty;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Log.d("TAG", "deletemypost: "+url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> stringStringMap = new HashMap<>();

                //stringStringMap.put("user_name", name);
                //stringStringMap.put("email", nemail1);
                //stringStringMap.put("phone_number", phone);
                // stringStringMap.put("blood_group", blood);
                //stringStringMap.put("address", address);
                // stringStringMap.put("date", date);
                //stringStringMap.put("call_made_by", phone);
                //stringStringMap.put("caller_email", nemail1);
                stringStringMap.put("email", email);
                stringStringMap.put("universityname", universityname);
                stringStringMap.put("facultyname", Faculty);



                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    public static void insertintempdb(Context context, String university, String faculty, String department, String email)
    {
        String url = "http://projecttech.xyz/University%20Admission%20Management/insert_apply_by_student.php";
        Log.d("TAG", "insertUniInfo: "+url);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(context, "Inserted Successfully", Toast.LENGTH_SHORT).show();

                //finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Check your Internet Connection.", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> stringStringMap = new HashMap<>();
                stringStringMap.put("universityname",  university);
                stringStringMap.put("facultyname", faculty);
                stringStringMap.put("departments", department);
                stringStringMap.put("email", email);


                //stringStringMap.put("token", token);




                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
