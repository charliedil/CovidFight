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

public class ResAdapter extends RecyclerView.Adapter<ResAdapter.ResViewHolder> {
    private Context context;
    private ArrayList<YelpRestaurant> businessData;
    private ResAdapter.OnItemClickListener rListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(ResAdapter.OnItemClickListener listener) {
        rListener = listener;
    }

    public ResAdapter(Context businessContext, ArrayList<YelpRestaurant> businessList) {
        context = businessContext;
        businessData = businessList;
    }

    @NonNull
    @Override
    public ResAdapter.ResViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false));
        ResAdapter.ResViewHolder rvh = new ResAdapter.ResViewHolder(v, rListener);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ResAdapter.ResViewHolder holder, int position) {
        YelpRestaurant restaurant = businessData.get(position);
        holder.bind(restaurant, context);
    }

    @Override
    public int getItemCount() {
        return businessData.size();
    }

    public static class ResViewHolder extends RecyclerView.ViewHolder {
        TextView name = itemView.findViewById(R.id.tvName);
        ImageView imageView = itemView.findViewById(R.id.imageView);
        RatingBar ratingBar = itemView.findViewById(R.id.ratingBar);
        TextView numReviews = itemView.findViewById(R.id.tvNumReviews);
        TextView address = itemView.findViewById(R.id.tvAddress);
        TextView category = itemView.findViewById(R.id.tvCategory);
        TextView distance = itemView.findViewById(R.id.tvDistance);
        TextView price = itemView.findViewById(R.id.tvPrice);

        public void bind(YelpRestaurant restaurant, Context context) {
            name.setText(restaurant.name);
            Glide.with(context).load(restaurant.imageUrl).into(imageView);
            ratingBar.setRating(restaurant.rating.floatValue());
            numReviews.setText(restaurant.numReviews+" reviews");
            address.setText(restaurant.location.address);
            category.setText(restaurant.categories.get(0).title);
            distance.setText(restaurant.displayDistance());
            price.setText(restaurant.price);
        }

        public ResViewHolder(@NonNull View itemView, final ResAdapter.OnItemClickListener listener) {
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
