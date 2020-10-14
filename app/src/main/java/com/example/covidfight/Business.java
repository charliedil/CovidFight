package com.example.covidfight;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class Business extends AppCompatActivity {
    private ArrayList<YelpRestaurant> businessData;

    /**================================**/

    private RecyclerView bRecyclerView;
    private ResAdapter resAdapter;
    private RecyclerView.LayoutManager bLayoutManager;



    private EditText searchBusiness;
    //private Object Callback;
    //private java.lang.Object Object;


    //private SearchView searchBusiness;  previous code
    public CharSequence searchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        createBusinessList();
        openPopup();

        searchBusiness = findViewById(R.id.searchBusiness);
        //searchQuery = searchBusiness.getQuery();

        /*searchBusiness.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
            }
                return false;
        }*/

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


    public void createBusinessList() {
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
        yelpInt.searchRestaurants("Bearer "+API_KEY, (String) searchQuery, "Richmond").enqueue(new Callback<YelpSearchResult>() {
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

    public void openPopup() {

        resAdapter.setOnItemClickListener(new ResAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                /** start popup activity */
                Intent intent = new Intent(Business.this, BusinessPopup.class);
                intent.putExtra("YelpRestaurant", businessData.get(position));
                //intent.putExtra("YelpLocation", businessData.get(position).location);
                startActivity(intent);
            }
        });
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
