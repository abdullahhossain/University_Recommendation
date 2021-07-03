package com.khurshed.engineeringuniversityadmissionmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class studentLogin extends AppCompatActivity {
    EditText Email,Password;
    ImageView back;
    SessionManager sessionManager;
    TextView sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        sessionManager = new SessionManager(this);
        //sessionManager.checkLogin();
        back = findViewById(R.id.back);
        sign = findViewById(R.id.signUp);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(studentLogin.this,StudentSignUp.class));
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(studentLogin.this,MainActivity.class));
                finish();
            }
        });
        Email = findViewById(R.id.user_email);
        Password = findViewById(R.id.user_pass);
    }

    public void loginNow(View view) {
        String mail = Email.getText().toString();
        String Pass = Password.getText().toString();
        log(mail,Pass);
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();

    }
    public void log(String m, String p)
    {
        String url = "http://projecttech.xyz/University%20Admission%20Management/login_now_now.php?email="+m+"&password="+p;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject userObject = jsonArray.getJSONObject(0);
                    if (!userObject.getString("error").equalsIgnoreCase("no")) {

                        Toast.makeText(studentLogin.this, "Data Does not Matched", Toast.LENGTH_SHORT).show();

                    } else {

                        //startActivity( new Intent(StudentSignUp.this,DeshboardActivity.class));
                        sessionManager.createSession(m,p);

                        Toast.makeText(studentLogin.this, "Data Matched", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(studentLogin.this, Deshboard.class));
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
}