package com.example.covidfight;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Business extends AppCompatActivity {

  private ArrayList<YelpRestaurant> businessData;
  private RecyclerView bRecyclerView;
  private ResAdapter resAdapter;
  private RecyclerView.LayoutManager bLayoutManager;
  private EditText searchBusiness;
  public CharSequence searchQuery;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_business);

    /** Button to close activity */
    Button closeButton = findViewById(R.id.btnClose);
    closeButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        finish();
      }
    });

    /** Method calls */
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
    searchBusiness = findViewById(R.id.searchBusiness);
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

    /** setup array list, adapter, layout manager */
    businessData = new ArrayList<YelpRestaurant>();
    resAdapter = new ResAdapter(this, businessData);
    bRecyclerView = findViewById(R.id.recyclerView);
    bRecyclerView.setAdapter(resAdapter);
    bLayoutManager = new LinearLayoutManager(this);
    bRecyclerView.setLayoutManager(bLayoutManager);

    //get Yelp API data
    YelpInterface yelpInt = retrofit.create(YelpInterface.class);
    yelpInt.searchRestaurants("Bearer " + API_KEY, (String) searchQuery, "Richmond").enqueue(new Callback<YelpSearchResult>() {
      @Override
      public void onResponse(Call<YelpSearchResult> call, Response<YelpSearchResult> response) {
        Log.i(TAG, "onResponse " + response);
        if (response.body() == null) {
          Log.w(TAG, "Did not receive valid response body from Yelp API... exiting");
          return;
        }

        businessData.addAll(response.body().restaurants);

        for (int i = 0; i < businessData.size(); i++) {
          businessData.get(i).setNumReviews(0);
          resAdapter.notifyDataSetChanged();
          businessData.get(i).setRating((float) 0.0);
          resAdapter.notifyDataSetChanged();
        }
        resAdapter.notifyDataSetChanged();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
            for (int i = 0; i < businessData.size(); i++) {
              if (dataSnapshot.child(businessData.get(i).getName()).exists()) {
                businessData.get(i).setNumReviews((int) dataSnapshot.child(businessData.get(i).getName()).getChildrenCount());
                if (businessData.get(i).getNumReviews() > 0) {
                  float total = (float) 0.0;
                  for (DataSnapshot d : dataSnapshot.child(businessData.get(i).getName()).getChildren()) {
                    ReviewItem r = d.getValue(ReviewItem.class);
                    total += r.getStarNumbers();
                  }
                  businessData.get(i).setRating((float) total / businessData.get(i).getNumReviews());
                  resAdapter.notifyDataSetChanged();
                } else {
                  businessData.get(i).setRating((float) 0.0);
                  resAdapter.notifyDataSetChanged();
                }
                System.out.println("ok");
              }
            }
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
          }
        });
        resAdapter.notifyDataSetChanged();
      }

      @Override
      public void onFailure(Call<YelpSearchResult> call, Throwable t) {
        Log.i(TAG, "onFailure " + t);
      }
    });
  }

  public void openPopup() {

    resAdapter.setOnItemClickListener(new ResAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(int position) {

        //start popup activity
        Intent intent = new Intent(Business.this, BusinessPopup.class);
        intent.putExtra("YelpRestaurant", businessData.get(position));
        //intent.putExtra("YelpLocation", businessData.get(position).location);
        startActivity(intent);
      }
    });
  }

  private void filter(String text) {
    ArrayList<YelpRestaurant> filterList = new ArrayList<>();
    for (YelpRestaurant item : businessData) {
      if (item.getName().toLowerCase().contains(text.toLowerCase())) {
        filterList.add(item);
      }
    }
    resAdapter.filterList(filterList);
  }

}
