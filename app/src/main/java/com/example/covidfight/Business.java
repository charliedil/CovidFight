package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class Business extends AppCompatActivity {
    private ArrayList<BusinessItem> mBusinessList;
    private ArrayList<YelpRestaurant> businessData;

    /**================================**/

    private RecyclerView bRecyclerView;
    //private BusinessAdapter bAdapter;
    private BusAdapter busAdapter;
    private ResAdapter resAdapter;
    private RecyclerView.LayoutManager bLayoutManager;

    private EditText searchBusiness;
    //private Object Callback;
    //private java.lang.Object Object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        getData();
        //createBusinessList();
        //buildRecyclerView();

        // Get the intent, verify the action and get the query
        /*Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }*/
        searchBusiness=findViewById(R.id.searchBusiness);
        searchBusiness.addTextChangedListener(new TextWatcher() {
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


    }

    public void getData() {
        //final JSONObject json = new JSONObject();

        final String TAG = "Business";
        final String BASE_URL = "https://api.yelp.com/v3/";
        final String API_KEY = "4cgiDC6gAIz5bZaGBCzV_ls0z6wp2O2NChwaZyuQKJMG6--0irgMX-fNXHtkE5MvxLxVNAw4xv2S5S804XIbynWvsjOos0xBSM8Qe9p5Gr0uFyuwjGw7C5Xxrc10X3Yx";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        businessData = new ArrayList<YelpRestaurant>();
        resAdapter = new ResAdapter(this, businessData);
        bRecyclerView = findViewById(R.id.recyclerView);
        bRecyclerView.setAdapter(resAdapter);
        bLayoutManager = new LinearLayoutManager(this);
        bRecyclerView.setLayoutManager(bLayoutManager);


        YelpInterface yelpInt = retrofit.create(YelpInterface.class);
        yelpInt.searchRestaurants("Bearer "+API_KEY, "deli", "New York").enqueue(new Callback<YelpSearchResult>() {
            @Override
            public void onResponse(Call<YelpSearchResult> call, Response<YelpSearchResult> response){
                Log.i(TAG, "onResponse "+response);
                if (response.body() == null) {
                    Log.w(TAG, "Did not receive valid response body from Yelp API... exiting");
                    return;
                }
                businessData.addAll(response.body().restaurants);
                resAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<YelpSearchResult> call, Throwable t) {
                Log.i(TAG, "onFailure "+t);
            }

        });


    }

    /*public void changeItem(int position, String text) {
        mBusinessList.get(position).changeTitle(text);
        bAdapter.notifyItemChanged(position);
    }*/

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
        //bAdapter = new BusinessAdapter(mBusinessList);
        //resAdapter = new ResAdapter(this, businessData);
        bRecyclerView.setLayoutManager(bLayoutManager);
        //bRecyclerView.setAdapter(bAdapter);
        bRecyclerView.setAdapter(resAdapter);

        /**bAdapter.setOnItemClickListener(new BusinessAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                ** start popup activity *
                Intent intent = new Intent(Business.this, BusinessPopup.class);
                intent.putExtra("BusinessItem", mBusinessList.get(position));
                startActivity(intent);
            }
        });*/
    }

    /**public class SearchResult {
        @SerializedName("total") int total;
        @SerializedName("businesses") String businesses;
        String businesses;

        public Search(String terms, String businesses) {
            this.terms = terms;
            this.businesses = businesses;
        }
    }*/

    private void filter(String text) {
        ArrayList<YelpRestaurant> filterList=new ArrayList<>();
        for(YelpRestaurant item: businessData){
            if (item.getName().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        resAdapter.filterList(filterList);
    }

}
