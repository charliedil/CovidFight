package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ValueAnimator;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RichmondStat extends AppCompatActivity {

    private RecyclerView richmondCaseRecView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richmond_stat);

        richmondCaseRecView=findViewById(R.id.RichmondCaseRecyclerView);



        final Gson gson= new Gson();

        String url="https://data.virginia.gov/resource/8bkr-zfqv.json";

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                DataRichmond[] dataRichmond=gson.fromJson(response,DataRichmond[].class);
                ArrayList<RichmondItem> richmondItem=new ArrayList<>();
                for(int i=10;i<26;i++){
                    richmondItem.add(new RichmondItem(dataRichmond[i].getZip_code(),123));
                }

                richmondItem.add(new RichmondItem("yyu",454));
                richmondItem.add(new RichmondItem("23223",566));

                CaseByZipRecViewAdapter adapter=new CaseByZipRecViewAdapter();
                adapter.setRichmondCaseItem(richmondItem);


                richmondCaseRecView.setAdapter(adapter);
                richmondCaseRecView.setLayoutManager(new LinearLayoutManager(null));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
        queue.start();




//        richmondItem.add(new RichmondItem("23291",143));
//        richmondItem.add(new RichmondItem("23232",145));
//        richmondItem.add(new RichmondItem("23212",176));
//


    }
}