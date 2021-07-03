package com.khurshed.engineeringuniversityadmissionmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PaymentGateway extends AppCompatActivity {
    String u,f,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);
        Intent intent = getIntent();
        u = intent.getStringExtra("university");
        f = intent.getStringExtra("faculty");
        d = intent.getStringExtra("department");


    }

    public void proceed(View view) {
        //startActivity(new Intent(PaymentGateway.this,AdmitCard.class));
        Intent intent = new Intent(PaymentGateway.this, AdmitCard.class);
        intent.putExtra("setuni", u);
        intent.putExtra("setfa",f);
        intent.putExtra("setdept",d);
        startActivity(intent);
    }

    public void Close(View view) {
        startActivity(new Intent(PaymentGateway.this,Deshboard.class));
        finish();

    }
}