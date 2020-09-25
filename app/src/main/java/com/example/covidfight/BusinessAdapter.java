package com.example.covidfight;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder> {
    private ArrayList<BusinessItem> mBusinessList;

    public static class BusinessViewHolder extends RecyclerView.ViewHolder {
        public ImageView bImageView;
        public TextView bTextView1;
        public TextView bTextView2;

        public BusinessViewHolder(@NonNull View itemView) {
            super(itemView);
            bImageView = itemView.findViewById(R.id.imageView);
            bTextView1 = itemView.findViewById(R.id.textView);
            bTextView2 = itemView.findViewById(R.id.textView2);
        }
    }

    public BusinessAdapter(ArrayList<BusinessItem> businessList) {
        mBusinessList = businessList;
    }

    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_item, parent, false);
        BusinessViewHolder bvh = new BusinessViewHolder(v);
        return bvh;
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessViewHolder holder, int position) {
        BusinessItem currentItem = mBusinessList.get(position);

        // get business data from list
        holder.bImageView.setImageResource(currentItem.getImageResource());
        holder.bTextView1.setText(currentItem.getTitle());
        holder.bTextView2.setText(currentItem.getInfo());
    }

    @Override
    public int getItemCount() {
        return mBusinessList.size();
    }
}
