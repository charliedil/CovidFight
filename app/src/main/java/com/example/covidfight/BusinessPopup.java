package com.example.covidfight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class BusinessPopup extends AppCompatActivity {

    private Button btn_close;

    private AlertDialog.Builder dialogBuider;
    private AlertDialog dialog;
    private Button rateButton;
    private Button cancelButton,submitButton;
    private RatingBar ratingBarInPopup;
    private EditText commentEditText;
    String name;
    //DatabaseReference
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_popup);

        Intent intent = getIntent();
        final YelpRestaurant restaurant = intent.getParcelableExtra("YelpRestaurant");

        /** initialize variables */
         name = restaurant.getName();
        Double rating = restaurant.getRating();
        String price = restaurant.getPrice();
        int numReviews = restaurant.getNumReviews();
        String imageUrl = restaurant.getImageUrl();
        //String category = restaurant.getCategory();
        //String address = restaurant.location.getAddress();

        /** find widgets + app components */
        TextView tvName = findViewById(R.id.tvName);
        ImageView imageView = findViewById(R.id.imageView);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView tvNumReviews = findViewById(R.id.tvNumReviews);
        TextView tvAddress = findViewById(R.id.tvAddress);
        final TextView tvCategory = findViewById(R.id.tvCategory);
        TextView tvDistance = findViewById(R.id.tvDistance);
        TextView tvPrice = findViewById(R.id.tvPrice);

        /** set components to data */
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

        //Define DatabaseReferences
        databaseReference=FirebaseDatabase.getInstance().getReference();

        //Rate Business Button:
        rateButton=findViewById(R.id.ratingSubmit);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRateBussiness();
            }
        });



    }

    //Method: Show popup for users to rate the businesses.
    @SuppressLint("WrongViewCast")
    public void onClickRateBussiness(){
        dialogBuider=new AlertDialog.Builder(this);
        final View reviewPopupView=getLayoutInflater().inflate(R.layout.reviewpopup,null);
//        //writing data example--------------------------------------------------
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference();
//        //Writing to database
//        myRef.child("RestaurantName").child("uid").setValue(Settings.Secure.getString(getContentResolver(),
//                Settings.Secure.ANDROID_ID));
//        myRef.child("RestaurantName").child("uid").child("rating").setValue(5.0);
//        myRef.child("RestaurantName").child("uid").child("review").setValue("No one was wearing masks");
//
//        //Reading from databae ???


        cancelButton=reviewPopupView.findViewById(R.id.cancelButton);
        submitButton=reviewPopupView.findViewById(R.id.submitButton);
      commentEditText= reviewPopupView.findViewById(R.id.CommentReview);


       ratingBarInPopup=reviewPopupView.findViewById(R.id.ratingBarInPopUp);

        dialogBuider.setView(reviewPopupView);
        dialog=dialogBuider.create();
        dialog.show();

        //Close popup when clicking cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //define cancel button here
                dialog.dismiss();
            }
        });

        //Submit data to database when submit button is clicked
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Submit data to firebase here
                //Method: addReview
                addReview();

            }
        });

    }

    //Add review(rating stars, and comments to firebase
    public void addReview(){

        //add String comment and float Start
        String comment=commentEditText.getText().toString();
        Float numStart=ratingBarInPopup.getRating();
        String uid=Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        //add if statement to submit when stars and comment are filled, otherwise make Toast error
        if(numStart!=null){

            ReviewItem reviewItem=new ReviewItem(numStart,comment);
            //Set Databasereference:
            databaseReference.child(name).child(uid).setValue(reviewItem);
            commentEditText.setText("");
            ratingBarInPopup.setRating(0);
            Toast.makeText(this, "Your review is submitted, thank you!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Please rate this business", Toast.LENGTH_SHORT).show();
        }
    }


}
