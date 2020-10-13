package com.example.covidfight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BusinessPopup extends AppCompatActivity {

    private Button btn_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_popup);

        Intent intent = getIntent();
        final YelpRestaurant restaurant = intent.getParcelableExtra("YelpRestaurant");

        String name = restaurant.getName();
        Double rating = restaurant.getRating();
        String price = restaurant.getPrice();
        int numReviews = restaurant.getNumReviews();
        String imageUrl = restaurant.getImageUrl();
        //String category = restaurant.getCategory();
        //String address = restaurant.location.getAddress();

        TextView tvName = findViewById(R.id.tvName);
        ImageView imageView = findViewById(R.id.imageView);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView tvNumReviews = findViewById(R.id.tvNumReviews);
        TextView tvAddress = findViewById(R.id.tvAddress);
        final TextView tvCategory = findViewById(R.id.tvCategory);
        TextView tvDistance = findViewById(R.id.tvDistance);
        TextView tvPrice = findViewById(R.id.tvPrice);

        tvName.setText(name);
        //Glide.with(context).load(imageUrl).into(imageView);
        ratingBar.setRating(rating.floatValue());
        tvNumReviews.setText(numReviews+" reviews");
        //tvAddress.setText(address);
        //tvCategory.setText(category);
        //tvDistance.setText(restaurant.displayDistance());
        tvPrice.setText(price);

        /*ImageView pRating = findViewById(R.id.pRatingFull);
        ClipDrawable pRatingDrawable = (ClipDrawable) pRating.getDrawable();
        pRatingDrawable.setLevel(popRating);*/

        /** layout */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Button getRating = findViewById(R.id.ratingSubmit);
        final RatingBar bar = findViewById(R.id.ratingBar);
        getRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurant.setRating(bar.getRating());
                tvCategory.setText(String.valueOf(restaurant.getRating()));

            }
        });
        //getWindow().setLayout((int)(width*.8), (int)(height*.75));

        /** button to close popup */
        /**btn_close = (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

    }



}
