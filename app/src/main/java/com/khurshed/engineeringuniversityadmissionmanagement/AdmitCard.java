package com.khurshed.engineeringuniversityadmissionmanagement;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;


public class AdmitCard extends AppCompatActivity {
    TextView universityName, faculty,studentname,admissiontestroll,downloadpdf;
    String university,facultyName;
    SessionManager sessionManager;
    LinearLayout pdfDown;
    String dirpath;
    int max = 10000;
    int min = 1;
    int range = max - min + 1;
    int rand = (int)(Math.random() * range) + min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admit_card);
        sessionManager = new SessionManager(this);
        universityName = findViewById(R.id.setuniname);
        faculty = findViewById(R.id.setfaculty);
        studentname = findViewById(R.id.setstudentname);
        admissiontestroll = findViewById(R.id.setrollnum);
        downloadpdf = findViewById(R.id.downloadpdf);
        pdfDown = findViewById(R.id.admitCardPrint);


        Intent intent = getIntent();
        university = intent.getStringExtra("setuni");
        facultyName = intent.getStringExtra("setfa");
        universityName.setText(university);
        faculty.setText(facultyName);
        getStudentName();
        admissiontestroll.setText(""+rand);

        downloadpdf.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                Toast.makeText(AdmitCard.this, "Download PDF", Toast.LENGTH_SHORT).show();
                //createPDFDownloader();
                pdfDown = findViewById(R.id.admitCardPrint);
                pdfDown.setDrawingCacheEnabled(true);
                pdfDown.buildDrawingCache();
                Bitmap bm = pdfDown.getDrawingCache();
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

                File f = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }
    public void getStudentName()
    {

        sessionManager = new SessionManager(this);
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
                    studentname.setText(data.getString("student_name"));



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AdmitCard.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createPDFDownloader()
    {
        Context context = AdmitCard.this;
        PrintManager printManager = (PrintManager)AdmitCard.this.getSystemService(context.PRINT_SERVICE);
        PrintDocumentAdapter adapter = null;
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
        {
            //adapter = webview.createPDF something create kora jabe

        }
        String JobName=getString(R.string.app_name) +"Document";
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            PrintJob printJob=printManager.print(JobName,adapter,new PrintAttributes.Builder().build());
        }
    }
    public void imageToPDF() throws FileNotFoundException {
        try {

        }catch (Exception e)
        {

        }
    }
}