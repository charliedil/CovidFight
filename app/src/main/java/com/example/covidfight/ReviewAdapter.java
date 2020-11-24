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
    private ArrayList<ReviewItem> reviewList;
    //private ReviewAdapter.OnItemClickListener rListener;

    /*public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ReviewAdapter.OnItemClickListener listener) {
        rListener = listener;
    }*/

    public ReviewAdapter(Context reviewContext, ArrayList<ReviewItem> rList) {
        context = reviewContext;
        reviewList = rList;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false));
        ReviewAdapter.ReviewViewHolder rvh = new ReviewAdapter.ReviewViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
        ReviewItem review = reviewList.get(position);
        holder.bind(review, context);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        RatingBar rating = itemView.findViewById(R.id.ratingReview);
        TextView comment = itemView.findViewById(R.id.tvReview);
        TextView date = itemView.findViewById(R.id.tvDate);

        public void bind(ReviewItem review, Context context) {
            rating.setRating(review.getStarNumbers());
            comment.setText(review.getComment());
            date.setText(review.getDate());
        }

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });*/
        }
    }

}
