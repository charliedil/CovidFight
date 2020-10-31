package com.example.covidfight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BusinessPopup extends AppCompatActivity {

    private ArrayList<ReviewItem> reviewList;
    //final ArrayList<ReviewItem>[] reviewList;
    private RecyclerView rRecyclerView;
    private ReviewAdapter reviewAdapter;
    private RecyclerView.LayoutManager rLayoutManager;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button rateButton;
    private Button cancelButton,submitButton;
    private RatingBar ratingBarInPopup;
    private EditText commentEditText;
    String name;
    //DatabaseReference
    DatabaseReference databaseReference;

    @RequiresApi(api = Build.VERSION_CODES.N)
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
        final int[] numReviews = {0};
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
        ratingBar.setRating((float) 0.0);
        tvNumReviews.setText(numReviews[0] +" reviews");
        //tvAddress.setText(address);
        //tvCategory.setText(category);
        //tvDistance.setText(restaurant.displayDistance());
        tvPrice.setText(price);
        DatabaseReference db= FirebaseDatabase.getInstance().getReference().child(name);

        db.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    TextView tvNumReviews = findViewById(R.id.tvNumReviews);
                    numReviews[0] =  (int) dataSnapshot.getChildrenCount();
                    tvNumReviews.setText(numReviews[0]+" reviews");
                    if(numReviews[0]>0){
                        float total =  (float) 0.0;
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            ReviewItem r = d.getValue(ReviewItem.class);
                            total +=r.getStarNumbers();

                        }
                        RatingBar ratingBar = findViewById(R.id.ratingBar);
                        ratingBar.setRating((float) total/numReviews[0]);

                    } else {
                        RatingBar ratingBar = findViewById(R.id.ratingBar);
                        ratingBar.setRating((float) 0.0);
                    }
                    System.out.println("ok");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        createReviewList();

        /*reviewList = new ArrayList<ReviewItem>();
        ReviewItem test1 = new ReviewItem((float) 2.5, "wow this is a review");
        reviewList.add(0, test1);
        reviewAdapter = new ReviewAdapter(this, reviewList);
        rRecyclerView = findViewById(R.id.popupRecyclerView);
        rRecyclerView.setAdapter(reviewAdapter);
        rLayoutManager = new LinearLayoutManager(this);
        rRecyclerView.setLayoutManager(rLayoutManager);*/

        /** layout */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Button getRating = findViewById(R.id.ratingSubmit);
        final RatingBar bar = findViewById(R.id.ratingBar);
        getRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurant.setRating(bar.getRating());
                tvCategory.setText(String.valueOf(restaurant.getRating()));

            }
        });

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void createReviewList() {

        reviewList = new ArrayList<ReviewItem>();
        buildAdapter();
        //ReviewItem test1 = new ReviewItem((float) 2.5, "wow this is a review");
        //reviewList.add(0, test1);

        //THIS HAPPENS ASYNCHRONOUSLY

       // final ArrayList[][] reviewList = new ArrayList[][]{new ArrayList[]{new ArrayList<>()}}; // i HAD to because java, 0th element is the thing

        //final ArrayList<ReviewItem>[] reviewList = new ArrayList[]{new ArrayList<>()}; // i HAD to because java, 0th element is the thin
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child(name);
        //sort by date and time
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String currentDateandTime = sdf.format(new Date());

        db.orderByChild("date").startAt(currentDateandTime );
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()){
                    reviewList = getReviews(snapshot);
                    buildAdapter();
                    reviewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


    }

    public void buildAdapter() {
        reviewAdapter = new ReviewAdapter(this, reviewList);
        rRecyclerView = findViewById(R.id.popupRecyclerView);
        rRecyclerView.setAdapter(reviewAdapter);
        rLayoutManager = new LinearLayoutManager(this);
        rRecyclerView.setLayoutManager(rLayoutManager);
    }

    //Method: Show popup for users to rate the businesses.
    @SuppressLint("WrongViewCast")
    public void onClickRateBussiness(){
        dialogBuilder=new AlertDialog.Builder(this);
        final View reviewPopupView=getLayoutInflater().inflate(R.layout.reviewpopup,null);
        cancelButton=reviewPopupView.findViewById(R.id.cancelButton);
        submitButton=reviewPopupView.findViewById(R.id.submitButton);
        commentEditText= reviewPopupView.findViewById(R.id.CommentReview);


        ratingBarInPopup=reviewPopupView.findViewById(R.id.ratingBarInPopUp);

        dialogBuilder.setView(reviewPopupView);
        dialog=dialogBuilder.create();
        dialog.show();

        //When I comment this part out, it works
        DatabaseReference db= FirebaseDatabase.getInstance().getReference().child(name).child(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        final ReviewItem[] temp = {null};
        db.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    final RatingBar ratingBar = findViewById(R.id.ratingBar);
                    temp[0] = dataSnapshot.getValue(ReviewItem.class);
                    ratingBarInPopup.setRating(temp[0].getStarNumbers());
                    ratingBarInPopup.setRating(temp[0].getStarNumbers());
                    commentEditText.setText(temp[0].getComment());
                } else {
                    ratingBarInPopup.setRating(0);
                    commentEditText.setText("");
                }

             }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        //Comment part end

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
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                //Submit data to firebase here
                //Method: addReview

                addReview();

            }
        });

    }

    //Add review(rating stars, and comments to firebase
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addReview(){

        //add String comment and float Start
        String comment=commentEditText.getText().toString();
        Float numStart=ratingBarInPopup.getRating();
        String uid=Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date());


        //add if statement to submit when stars and comment are filled, otherwise make Toast error
        if(numStart!=null){

            ReviewItem reviewItem= new ReviewItem(numStart,comment,date);
            //Set Databasereference:
            databaseReference.child(name).child(uid).setValue(reviewItem);

            Toast.makeText(this, "Your review is submitted, thank you!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Please rate this business", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<ReviewItem> getReviews(DataSnapshot dataSnapshot){
        ArrayList<ReviewItem> reviews = new ArrayList<>();
        for (DataSnapshot d: dataSnapshot.getChildren()){
            reviews.add(d.getValue(ReviewItem.class));

        }
        System.out.println("hello");
        return reviews;

    }

}
