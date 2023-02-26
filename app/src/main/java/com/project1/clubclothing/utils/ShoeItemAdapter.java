package com.project1.clubclothing.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project1.clubclothing.R;
import com.project1.clubclothing.model.Shoecart;

import java.util.List;

public class ShoeItemAdapter extends RecyclerView.Adapter<ShoeItemAdapter.ShoeItemViewHolder> {

    private List<Shoecart> shoecartList;
    private ShoeClickedListeners shoeClickedListeners;

    public ShoeItemAdapter(ShoeClickedListeners shoeClickedListeners){
        this.shoeClickedListeners = shoeClickedListeners;
    }
    public void setShoecartList(List<Shoecart> shoecartList){
        this.shoecartList = shoecartList;
    }
    @NonNull
    @Override
    public ShoeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachshoe , parent , false);
        return new ShoeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeItemViewHolder holder, int position) {
        Shoecart shoecart = shoecartList.get(position);
        holder.shoeNameTv.setText(shoecart.getShoeName());
        holder.shoeBrandNameTv.setText(shoecart.getShoeBrandName());
        holder.shoePriceTv.setText(String.valueOf(shoecart.getShoePrice()));
        holder.shoeImageView.setImageResource(shoecart.getShoeImage());

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shoeClickedListeners.onCardClicked(shoecart);
//            }
//        });

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoeClickedListeners.onAddToCartBtnClicked(shoecart);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (shoecartList == null){
            return 0;
        }else{
            return shoecartList.size();
        }
    }



    public class ShoeItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView shoeImageView , addToCartBtn;
        private TextView shoeNameTv, shoeBrandNameTv, shoePriceTv;
        private CardView cardView;
        public ShoeItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.eachShoeCardView);
            addToCartBtn = itemView.findViewById(R.id.eachShoeAddToCartBtn);
            shoeNameTv = itemView.findViewById(R.id.eachShoeName);
            shoeImageView = itemView.findViewById(R.id.eachShoeIv);
            shoeBrandNameTv = itemView.findViewById(R.id.eachShoeBrandNameTv);
            shoePriceTv = itemView.findViewById(R.id.eachShoePriceTv);
        }
    }

    public interface ShoeClickedListeners{
     //   void onCardClicked(Shoecart shoe);
        void onAddToCartBtnClicked(Shoecart shoecart);
    }
}
