package com.example.covidfight;

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

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder> {
    private ArrayList<BusinessItem> mBusinessList;
    private OnItemClickListener bListener;

    /** interface */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        bListener = listener;
    }

    public static class BusinessViewHolder extends RecyclerView.ViewHolder {
        public TextView bTextView1;
        public TextView bTextView2;
        public ImageView bImageView;
        public ImageView bRating;
        public ClipDrawable RatingDrawable;

        public BusinessViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
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

    public BusinessAdapter(ArrayList<BusinessItem> businessList) {
        mBusinessList = businessList;
    }

    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_item, parent, false);
        BusinessViewHolder bvh = new BusinessViewHolder(v, bListener);
        return bvh;
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessViewHolder holder, int position) {
        BusinessItem currentItem = mBusinessList.get(position);
        // set card info using data from list
        holder.bTextView1.setText(currentItem.getTitle());
        holder.bTextView2.setText(currentItem.getInfo());
        holder.bImageView.setImageResource(currentItem.getImageResource());
        holder.RatingDrawable.setLevel((currentItem.getRating()));
    }

    /** Set Limit on # of Cards */
    private final int limit = 10;

    @Override
    public int getItemCount() {
        return Math.min(mBusinessList.size(), limit);
    }

}
