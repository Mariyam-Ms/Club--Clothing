package com.project1.clubclothing.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project1.clubclothing.R;
import com.project1.clubclothing.model.DataItem;

import java.util.List;

public class ClothesCartAdapter extends RecyclerView.Adapter<ClothesCartAdapter.ClothCartViewHolder> {
    private JerseyClickedListeners jerseyClickedListeners;
    private List<DataItem> dataItemList;

    public ClothesCartAdapter(JerseyClickedListeners jerseyClickedListeners) {
        this.jerseyClickedListeners = jerseyClickedListeners;
    }
    public void setDataItemList(List<DataItem> dataItemList) {
        this.dataItemList = dataItemList;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ClothCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachjerseycart, parent, false);
        return new ClothCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothCartViewHolder holder, int position) {

        DataItem dataItem= dataItemList.get(position);
        holder.jerseyImageView.setImageResource(dataItem.getShoeImage());
        holder.jerseyNameTv.setText(dataItem.getShoeName());
        holder.jerseyclubNameTv.setText(dataItem.getShoeBrandName());
        holder.jerseyQuantity.setText(dataItem.getShoequantity() + "");
        holder.jerseyPriceTv.setText(dataItem.getTotalShoesPrice() + "");


        holder.deletejerseyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jerseyClickedListeners.onDeleteJerseyClicked(dataItem);
            }
        });


        holder.addjerseyQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jerseyClickedListeners.onPlusJerseyClicked(dataItem);
            }
        });

        holder.minusjerseyQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jerseyClickedListeners.onMinusJerseyClicked(dataItem);
            }
        });

    }

    @Override
    public int getItemCount() {


        if (dataItemList == null) {
            return 0;
        } else {
            return dataItemList.size();
        }

    }

    public class ClothCartViewHolder extends RecyclerView.ViewHolder {

        private TextView jerseyNameTv, jerseyclubNameTv, jerseyPriceTv, jerseyQuantity;
        private ImageView deletejerseyBtn;
        private ImageView jerseyImageView;
        private ImageButton addjerseyQuantityBtn, minusjerseyQuantityBtn;

        public ClothCartViewHolder(@NonNull View itemView) {
            super(itemView);
            jerseyNameTv = itemView.findViewById(R.id.eachJerseyItemName);
            jerseyclubNameTv = itemView.findViewById(R.id.eachJerseyItemClubNameTv);
            jerseyPriceTv = itemView.findViewById(R.id.eachJerseyItemPriceTv);
            deletejerseyBtn = itemView.findViewById(R.id.eachJerseyItemDeleteBtn);
            jerseyImageView = itemView.findViewById(R.id.eachJerseyItemIV);
            jerseyQuantity = itemView.findViewById(R.id.eachJerseyItemQuantityTV);
            addjerseyQuantityBtn = itemView.findViewById(R.id.eachJerseyItemAddQuantityBtn);
            minusjerseyQuantityBtn = itemView.findViewById(R.id.eachJerseyItemMinusQuantityBtn);
        }
    }
        public interface JerseyClickedListeners {


            void onDeleteJerseyClicked(DataItem dataItem);

            void onPlusJerseyClicked(DataItem dataItem);

            void onMinusJerseyClicked(DataItem dataItem);
        }



}
