package com.example.covidfight;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CaseByZipRecViewAdapter extends RecyclerView.Adapter<CaseByZipRecViewAdapter.ViewHolder> {

    private ArrayList<RichmondItem> richmondCaseItems =new ArrayList<>();

    public CaseByZipRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.richmond_case_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.zipCode.setText(richmondCaseItems.get(position).getZipcode());
        holder.caseNumbers.setText(richmondCaseItems.get(position).getCaseNumbers());
        holder.testNumbers.setText(richmondCaseItems.get(position).getTestNumbers());
    }

    @Override
    public int getItemCount() {
        return richmondCaseItems.size();
    }

    public void filterList(ArrayList<RichmondItem> filterList) {
        richmondCaseItems = filterList;
        notifyDataSetChanged();
    }

    public void setRichmondCaseItem(ArrayList<RichmondItem> richmondCaseItem) {
        this.richmondCaseItems = richmondCaseItem;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView zipCode;
        private TextView caseNumbers;
        private TextView testNumbers;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            zipCode=itemView.findViewById(R.id.zipcode);
            caseNumbers=itemView.findViewById(R.id.caseNumbers);
            testNumbers=itemView.findViewById(R.id.testNumbers);
        }
    }
}