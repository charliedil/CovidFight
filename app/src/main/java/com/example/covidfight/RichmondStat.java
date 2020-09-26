package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class RichmondStat extends AppCompatActivity {

    private RecyclerView richmondCaseRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_richmond_stat);

        richmondCaseRecView=findViewById(R.id.RichmondCaseRecyclerView);
    }
}