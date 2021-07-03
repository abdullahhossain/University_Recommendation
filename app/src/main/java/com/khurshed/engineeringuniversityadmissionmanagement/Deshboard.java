package com.khurshed.engineeringuniversityadmissionmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.khurshed.engineeringuniversityadmissionmanagement.Constant.PREF;

public class Deshboard extends AppCompatActivity {
    SessionManager sessionManager;
    ShimmerFrameLayout container;
    TextView logout, sName1,sName2, sscRoll, hscRoll, registration, session,sscgpa,hscgpa,dob;
    private static final String TAG = "Deshboard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshboard);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        container =
                (ShimmerFrameLayout) findViewById(R.id.shimmerLayout);
        container.startShimmer();
        logout = findViewById(R.id.logooout);
        sName1 = findViewById(R.id.studentName);
        sName2 = findViewById(R.id.stuName);
        sscRoll = findViewById(R.id.sscroll);
        hscRoll = findViewById(R.id.hscroll);
        registration = findViewById(R.id.registrationNum);
        session = findViewById(R.id.stuSession);
        sscgpa = findViewById(R.id.sscgpa);
        hscgpa = findViewById(R.id.hscgpa);
        dob = findViewById(R.id.stuDOB);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logout();
            }
        });
        //studentName();
        updateStudentInfo();

    }

    public void Check(View view) {

        Intent intent = new Intent(Deshboard.this, showUniversity.class);
        intent.putExtra("sscGpa", sscgpa.getText().toString());
        intent.putExtra("hscGpa", hscgpa.getText().toString());
        startActivity(intent);

    }

    public void viewApplied(View view) {
        startActivity(new Intent(Deshboard.this, appliedUniversity.class));

    }

    public void updateStudentInfo()
    {
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String email1 = user.get(sessionManager.EMAIL);
        String url = "http://projecttech.xyz/University%20Admission%20Management/update_stu_infos.php?email=" + email1;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                Log.d("Res=", url);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    JSONObject data = jsonArray.getJSONObject(0);

//                    data.getInt("id");
//                    data.getString("student_name");
//                    data.getString("ssc_roll");
//                    data.getString("hsc_roll");
//                    data.getString("student_reg");
                    sName1.setText(data.getString("student_name"));
                    sName2.setText(data.getString("student_name"));

                    sscRoll.setText(data.getString("ssc"));
                    hscRoll.setText(data.getString("hsc"));
                    registration.setText(data.getString("registration_number"));
                    session.setText(data.getString("session"));
                    dob.setText(data.getString("date_of_birth"));

                    String res = registration.getText().toString();
                    updateStudentResults(res);
                    container.hideShimmer();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Deshboard.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }
    public void updateStudentResults(String REG)
    {
        String url = "http://projecttech.xyz/University%20Admission%20Management/update_stu_result.php?registration_number="+REG;

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {
                Log.d("Res=", url);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("Result");
                    JSONObject data = jsonArray.getJSONObject(0);

//                    data.getInt("id");
//                    data.getString("student_name");
//                    data.getString("ssc_roll");
//                    data.getString("hsc_roll");
//                    data.getString("student_reg");

                    sscgpa.setText(data.getString("ssc_gpa"));
                    hscgpa.setText(data.getString("hsc_gpa"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Deshboard.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }
}