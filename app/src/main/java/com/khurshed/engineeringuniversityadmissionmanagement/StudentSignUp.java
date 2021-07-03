package com.khurshed.engineeringuniversityadmissionmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class StudentSignUp extends AppCompatActivity {
    TextView dob;
    ImageView back;
    EditText sname, semail,ssc,hsc, session,password,reg_no;
    int day,month,year;
    String timee;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(StudentSignUp.this,MainActivity.class));
                finish();
            }
        });
        sname = findViewById(R.id.user_name);
        semail = findViewById(R.id.user_email);
        ssc = findViewById(R.id.user_ssc);
        hsc = findViewById(R.id.user_hsc);
        session = findViewById(R.id.user_session);
        password = findViewById(R.id.user_pass);
        reg_no = findViewById(R.id.user_reg_no);
        dob = findViewById(R.id.user_dob);
        Calendar calendar = Calendar.getInstance();

        dob.setOnClickListener(v -> {
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    StudentSignUp.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener
            ,year,month,day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();

        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                dob.setText(dayOfMonth+"-"+month+"-"+year);
            }
        };
    }

    public void signUp(View view) {
        String email=semail.getText().toString();
        String name = sname.getText().toString();
        String SSC = ssc.getText().toString();
        String HSC = hsc.getText().toString();
        String SESSION = session.getText().toString();
        String PASSWORD = password.getText().toString();
        String DATEOFBIRTH = dob.getText().toString();
        String REGISTRATION = reg_no.getText().toString();
       // insertStudentInfo();
        verifyData(SSC,HSC,SESSION,REGISTRATION);

    }
    public void verifyData(String SSc, String HSc, String Session, String reg)
    {
        String url = "http://projecttech.xyz/University%20Admission%20Management/verify.php?ssc="+SSc+"&hsc="+HSc+"&session="+Session+"&registration_number="+reg;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject userObject = jsonArray.getJSONObject(0);
                    if (!userObject.getString("error").equalsIgnoreCase("no")) {

                        Toast.makeText(StudentSignUp.this, "Data Does not Matched", Toast.LENGTH_SHORT).show();

                    } else {

                        //startActivity( new Intent(StudentSignUp.this,DeshboardActivity.class));
                        //sessionManager.createSession(email,pass);
                        insertStudentInfo();
                        Toast.makeText(StudentSignUp.this, "Data Matched", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(StudentSignUp.this, studentLogin.class));
                        finish();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        requestQueue.add(stringRequest);
    }
    public void insertStudentInfo()
    {
        String url = "http://projecttech.xyz/University%20Admission%20Management/user_sign_up.php";
        Log.d("TAG", "insertUniInfo: "+url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(StudentSignUp.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(AddUniversity.this, DeshboardActivity.class));
                //finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentSignUp.this, "Check your Internet Connection.", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> stringStringMap = new HashMap<>();

                stringStringMap.put("email", semail.getText().toString());
                stringStringMap.put("student_name",  sname.getText().toString());
                stringStringMap.put("ssc", ssc.getText().toString());
                stringStringMap.put("hsc", hsc.getText().toString());
                stringStringMap.put("registration_number", reg_no.getText().toString());
                stringStringMap.put("session", session.getText().toString());
                stringStringMap.put("password", password.getText().toString());
                stringStringMap.put("date_of_birth", dob.getText().toString());


                //stringStringMap.put("token", token);




                return stringStringMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}