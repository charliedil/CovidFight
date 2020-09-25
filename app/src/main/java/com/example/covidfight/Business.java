package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

    //private CardView bCardView;

    //List<String> listBusinesses;
    //private Button btnBackToMain;

public class Business extends AppCompatActivity {
    private RecyclerView bRecyclerView;
    private RecyclerView.Adapter bAdapter;
    private RecyclerView.LayoutManager bLayoutManager;

    //public CardView c1, c2, c3, c4, c5, c6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        //initializing test list
        ArrayList<BusinessItem> businessList = new ArrayList<>();
        businessList.add(new BusinessItem("Kroger", "Description", R.drawable.insert_image));
        businessList.add(new BusinessItem("Panda Express", "Description", R.drawable.insert_image));
        businessList.add(new BusinessItem("Chipotle", "Description", R.drawable.insert_image));

        bRecyclerView = findViewById(R.id.recyclerView);
        bRecyclerView.setHasFixedSize(true); //locks size (increases performance)
        bLayoutManager = new LinearLayoutManager(this);
        bAdapter = new BusinessAdapter(businessList);

        bRecyclerView.setLayoutManager(bLayoutManager);
        bRecyclerView.setAdapter(bAdapter);

        //c1 = (CardView) findViewById(R.id.bCard1);

//        bCardView=findViewById(R.id.bCard1);
//        bCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Business.this, BusinessPopup.class);
//                startActivity(intent);
//            }
//        };




    }

}
