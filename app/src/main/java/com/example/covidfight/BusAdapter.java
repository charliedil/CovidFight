package com.example.covidfight;

import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusViewHolder> {
    private ArrayList<YelpRestaurant> businessData;
    private OnItemClickListener bListener;

    /** interface */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        bListener = listener;
    }

    public static class BusViewHolder extends RecyclerView.ViewHolder {
        public TextView bTextView1;
        public TextView bTextView2;
        public ImageView bImageView;
        public ImageView bRating;
        public ClipDrawable RatingDrawable;

        public BusViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            bTextView1 = itemView.findViewById(R.id.cardTitle);
            bTextView2 = itemView.findViewById(R.id.cardDescription);
            bImageView = itemView.findViewById(R.id.cardImage);
            bRating = itemView.findViewById(R.id.ratingFull);
            RatingDrawable = (ClipDrawable) bRating.getDrawable();
            RatingDrawable.setLevel(0);

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

    public BusAdapter(Context context, ArrayList<YelpRestaurant> businessList) {
        businessData = businessList;
    }

    @Override
    public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_item, parent, false);
        BusViewHolder bvh = new BusViewHolder(v, bListener);
        return bvh;
    }

    @Override
    public void onBindViewHolder(@NonNull BusViewHolder holder, int position) {
        YelpRestaurant currentItem = businessData.get(position);
        // set card info using data from list
        holder.bTextView1.setText(currentItem.getName());
        holder.RatingDrawable.setLevel((int) (Math.round(currentItem.getRating()*2000)));
        holder.bTextView2.setText(currentItem.getPrice());
        //holder.bImageView.setImageResource(currentItem.getImageResource());
    }

    /** Set Limit on # of Cards */
    private final int limit = 10;

    @Override
    public int getItemCount() {
        //return Math.min(businessData.size(), limit);
        return 20;
    }

}