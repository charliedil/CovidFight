package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private EditText searchBarTextView;
    private Button findTestLocation;
    final ArrayList<RichmondItem> richmondItem=new ArrayList<>();
    CaseByZipRecViewAdapter adapter=new CaseByZipRecViewAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richmond_stat);

        /** Button to close activity */
        Button closeButton = findViewById(R.id.btnClose);
        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        richmondCaseRecView=findViewById(R.id.RichmondCaseRecyclerView);
        searchBarTextView=findViewById(R.id.RichmondCaseSearch);
        findTestLocation=findViewById(R.id.findTestLocation);

        final Gson gson= new Gson();

        String url="https://data.virginia.gov/resource/8bkr-zfqv.json";

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                DataRichmond[] dataRichmond=gson.fromJson(response,DataRichmond[].class);
//                ArrayList<RichmondItem> richmondItem=new ArrayList<>();
                for(int i=371;i<410;i++){
                    richmondItem.add(new RichmondItem(dataRichmond[i].getZip_code(),dataRichmond[i].getNumber_of_cases(),dataRichmond[i].getNumber_of_pcr_testing()));
                }

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


        searchBarTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });
        findTestLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uriUrl=Uri.parse("https://www.vdh.virginia.gov/coronavirus/covid-19-testing/covid-19-testing-sites/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

    }

    private void filter(String text) {
        ArrayList<RichmondItem> filterList=new ArrayList<>();
        for(RichmondItem item: richmondItem){
            if (item.getZipcode().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        adapter.filterList(filterList);
    }
}