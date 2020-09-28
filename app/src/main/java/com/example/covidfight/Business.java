package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class Business extends AppCompatActivity {
    private ArrayList<BusinessItem> mBusinessList;

    /**================================**/

    private RecyclerView bRecyclerView;
    private BusinessAdapter bAdapter;
    private RecyclerView.LayoutManager bLayoutManager;

    private SearchView searchBusiness;

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

    }

    public void changeItem(int position, String text) {
        mBusinessList.get(position).changeTitle(text);
        bAdapter.notifyItemChanged(position);
    }

    /** initialize list containing business data (for testing) */
    public void createBusinessList() {
        mBusinessList = new ArrayList<>();
        // rating/2000 = # of stars
        mBusinessList.add(new BusinessItem("Kroger", "Grocery Store", R.drawable.insert_image, 4000));
        mBusinessList.add(new BusinessItem("Panda Express", "Chinese Fast Food Chain", R.drawable.insert_image, 8000));
        mBusinessList.add(new BusinessItem("Chipotle", "Mexican Fast Food Chain", R.drawable.insert_image, 5000));
        mBusinessList.add(new BusinessItem("Barnes & Noble", "Book Store", R.drawable.insert_image, 7000));
        mBusinessList.add(new BusinessItem("Roots", "Restaurant", R.drawable.insert_image, 2000));
        mBusinessList.add(new BusinessItem("Village Cafe", "American Restaurant", R.drawable.insert_image, 6500));
        mBusinessList.add(new BusinessItem("Kung Fu Tea", "Bubble Tea", R.drawable.insert_image, 8500));
        mBusinessList.add(new BusinessItem("Spoon", "Asian Fusion Restaurant", R.drawable.insert_image, 9000));
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

                /** start popup activity */
                Intent intent = new Intent(Business.this, BusinessPopup.class);
                intent.putExtra("BusinessItem", mBusinessList.get(position));
                startActivity(intent);
            }
        });
    }

}
