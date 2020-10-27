package com.example.covidfight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private Context context;
    private ArrayList<ReviewObject> reviewList;
    private ReviewAdapter.OnItemClickListener rListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public ReviewAdapter(Context c, ArrayList<ReviewObject> revList) {
        context = c;
        reviewList = revList;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false));
        ReviewAdapter.ReviewViewHolder rvh = new ReviewAdapter.ReviewViewHolder(v, rListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
        ReviewObject review = reviewList.get(position);
        holder.bind(review, context);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView username = itemView.findViewById(R.id.tvUsername);
        RatingBar revRating = itemView.findViewById(R.id.ratingReview);
        TextView date = itemView.findViewById(R.id.tvDate);
        TextView reviewText = itemView.findViewById(R.id.tvReview);

        public void bind(ReviewObject review, Context context) {
            username.setText(review.username);
            revRating.setRating(review.rating.floatValue());
            date.setText(review.date);
            reviewText.setText(review.review);
        }

        public ReviewViewHolder(@NonNull View itemView, final ReviewAdapter.OnItemClickListener listener) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


}

/** Object for storing review data */
class ReviewObject {
    String username;
    Float rating;
    String date;
    String review;

    public ReviewObject(String username, Float rating, String date, String review) {
        this.username = username;
        this.rating = rating;
        this.date = date;
        this.review = review;
    }
}