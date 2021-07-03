package com.khurshed.engineeringuniversityadmissionmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class adminLogin extends AppCompatActivity {
    ImageView backbtn;
    //TextView tooltext;
    EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
//        tooltext.setText("Admin Login");
        backbtn = findViewById(R.id.back);
        //tooltext = findViewById(R.id.toolbartext);
        email = findViewById(R.id.user_email);
        pass = findViewById(R.id.user_pass);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(adminLogin.this,MainActivity.class));
               finish();
            }
        });
    }

    public void adminLogIn(View view) {
        String mail = email.getText().toString().toLowerCase();
        String pas = pass.getText().toString().toLowerCase();
        if (mail.isEmpty() && pas.isEmpty())
        {
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else if(mail.equals("admin") && pas.equals("admin"))
        {
            startActivity(new Intent(adminLogin.this, AddUniversity.class));
            //finish();
        }

    }

}