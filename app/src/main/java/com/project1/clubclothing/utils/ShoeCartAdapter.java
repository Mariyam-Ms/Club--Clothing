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

public class ShoeCartAdapter extends RecyclerView.Adapter<ShoeCartAdapter.CartViewHodler>{

    private CartClickedListeners cartClickedListeners;
    private List<DataItem> dataItemList;

    public ShoeCartAdapter(CartClickedListeners cartClickedListeners) {
        this.cartClickedListeners = cartClickedListeners;
    }

    public void setShoeCartList(List<DataItem> dataItemList) {
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public CartViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachshoecart, parent, false);
        return new CartViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHodler holder, int position) {

        DataItem dataItem= dataItemList.get(position);
        holder.shoeImageView.setImageResource(dataItem.getShoeImage());
        holder.shoeNameTv.setText(dataItem.getShoeName());
        holder.shoeBrandNameTv.setText(dataItem.getShoeBrandName());
        holder.shoeQuantity.setText(dataItem.getShoequantity() + "");
        holder.shoePriceTv.setText(dataItem.getTotalShoesPrice() + "");


        holder.deleteShoeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onDeleteClicked(dataItem);
            }
        });


        holder.addQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onPlusClicked(dataItem);
            }
        });

        holder.minusQuantityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartClickedListeners.onMinusClicked(dataItem);
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




    public class CartViewHodler extends RecyclerView.ViewHolder {

        private TextView shoeNameTv, shoeBrandNameTv, shoePriceTv, shoeQuantity;
        private ImageView deleteShoeBtn;
        private ImageView shoeImageView;
        private ImageButton addQuantityBtn, minusQuantityBtn;

        public CartViewHodler(@NonNull View itemView) {
            super(itemView);

            shoeNameTv = itemView.findViewById(R.id.eachCartItemName);
            shoeBrandNameTv = itemView.findViewById(R.id.eachCartItemBrandNameTv);
            shoePriceTv = itemView.findViewById(R.id.eachCartItemPriceTv);
            deleteShoeBtn = itemView.findViewById(R.id.eachCartItemDeleteBtn);
            shoeImageView = itemView.findViewById(R.id.eachCartItemIV);
            shoeQuantity = itemView.findViewById(R.id.eachCartItemQuantityTV);
            addQuantityBtn = itemView.findViewById(R.id.eachCartItemAddQuantityBtn);
            minusQuantityBtn = itemView.findViewById(R.id.eachCartItemMinusQuantityBtn);
        }
    }




    public interface CartClickedListeners {
        void onDeleteClicked(DataItem dataItem);

        void onPlusClicked(DataItem dataItem);

        void onMinusClicked(DataItem dataItem);
    }
}
