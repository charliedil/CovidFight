package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Business extends AppCompatActivity {
    private ArrayList<BusinessItem> mBusinessList;



    /**================================**/

    private CardView bCardView;

    private RecyclerView bRecyclerView;
    private BusinessAdapter bAdapter;
    private RecyclerView.LayoutManager bLayoutManager;

    private SearchView searchBusiness;


    //public CardView c1, c2, c3, c4, c5, c6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        createBusinessList();
        buildRecyclerView();

        // Get the intent, verify the action and get the query
        /*Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }*/

        /** starts BusinessPopup activity when card is selected
        bCardView = findViewById(R.id.businessCard);
        bCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Business.this, BusinessPopup.class);
                startActivity(intent);
            }
        });*/
    }

    public void changeItem(int position, String text) {
        mBusinessList.get(position).changeTitle(text);
        bAdapter.notifyItemChanged(position);
    }

    /** initialize list containing business data (for testing) */
    public void createBusinessList() {
        mBusinessList = new ArrayList<>();
        mBusinessList.add(new BusinessItem("Kroger", "Description", R.drawable.insert_image));
        mBusinessList.add(new BusinessItem("Panda Express", "Description", R.drawable.insert_image));
        mBusinessList.add(new BusinessItem("Chipotle", "Description", R.drawable.insert_image));
    }

    public void buildRecyclerView() {
        bRecyclerView = findViewById(R.id.recyclerView);
        bRecyclerView.setHasFixedSize(true); //locks size (increases performance)
        bLayoutManager = new LinearLayoutManager(this);
        bAdapter = new BusinessAdapter(mBusinessList);

        bRecyclerView.setLayoutManager(bLayoutManager);
        bRecyclerView.setAdapter(bAdapter);

        bAdapter.setOnItemClickListener(new BusinessAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //changeItem(position, "Clicked");

                /** start popup activity */
                Intent intent=new Intent(Business.this, BusinessPopup.class);
                startActivity(intent);
            }
        });
    }

}
