package com.khurshed.engineeringuniversityadmissionmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.khurshed.engineeringuniversityadmissionmanagement.Constant.PREF;

public class showUniversity extends AppCompatActivity {
    private static final String TAG = "showUniversity";
    ImageView back;
    SessionManager sessionManager;
    String sscCG, hscCG;
    Double class10, class12;
    Double result;
    List<Model> modelList;
    //List<Model2> modelList2;
    MyAdapter adapter;
    // MyAdapter2 adapter2;
    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    Button apply;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_university);

        arrayList = new ArrayList<>();

        sharedPreferences = getApplicationContext().getSharedPreferences(PREF, MODE_PRIVATE);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        modelList = new ArrayList<>();
        // modelList2 = new ArrayList<>();
        apply = findViewById(R.id.applybtn);
        recyclerView = findViewById(R.id.recylerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(showUniversity.this));

        Intent intent = getIntent();
        sscCG = intent.getStringExtra("sscGpa");
        hscCG = intent.getStringExtra("hscGpa");
        class10 = Double.parseDouble(sscCG);
        class12 = Double.parseDouble(hscCG);


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(showUniversity.this, Deshboard.class));
                finish();
            }
        });
        result = class10 + class12;
        SharedPreferences.Editor editor = sharedPreferences.edit(); //quick shared pref tutorial constant class er variable
        editor.putString("Total", result.toString());
        editor.commit();

        showData();
        //filteredData();

    }

    public void showData() {
        sessionManager = new SessionManager(this);
        // sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        String email1 = user.get(SessionManager.EMAIL);
        String url = "http://projecttech.xyz/University%20Admission%20Management/show_university_that_can_be_applied.php";
        Log.d("TAG", "datashow: " + url);
        String url2 = "https://projecttech.xyz/recommend_university/notAppliedUniversity.php?email=" + email1;


        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, url2, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    JSONArray object1 = object.getJSONArray("specification_data");
                    if (object1.length()==1){
                        modelList.add(new Model(object.getString("id"),
                                object.getString("universityName"),
                                object.getString("faculty"),
                                object.getString("ssc"),
                                object.getString("hsc"),
                                object.getString("total"),
                                object.getString("department")
                        ));
                    }
                }
                Log.d(TAG, "showData: "+modelList.size());
                adapter = new MyAdapter(modelList,showUniversity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            error.printStackTrace();
        }));

    }
}