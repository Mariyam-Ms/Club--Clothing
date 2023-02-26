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
import com.project1.clubclothing.model.ClothesCart;

import java.util.List;

public class ClothesItemAdapter extends RecyclerView.Adapter<ClothesItemAdapter.ClothViewHolder> {

    private List<ClothesCart> clothcartList;
    private JerseyItemClickedListeners jerseyItemClickedListeners;


    public ClothesItemAdapter(JerseyItemClickedListeners jerseyItemClickedListeners) {
        this.jerseyItemClickedListeners = jerseyItemClickedListeners;
    }

    public void setClothcartList(List<ClothesCart> clothcartList) {
        this.clothcartList = clothcartList;
    }

    @NonNull
    @Override
    public ClothViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachjersy, parent , false);
        return new ClothViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothViewHolder holder, int position) {

        ClothesCart clothescart = clothcartList.get(position);
        holder.jsyNameTv.setText(clothescart.getJerseyName());
        holder.jsyBrandNameTv.setText(clothescart.getJerseyClubName());
        holder.jsyPriceTv.setText(String.valueOf(clothescart.getJerseyPrice()));
        holder.jsyImageView.setImageResource(clothescart.getJerseyImage());

        holder.addTojsyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jerseyItemClickedListeners. onAddtoJerseyCartBtnClicked(clothescart) ;

            }
        });

    }

    @Override
    public int getItemCount() {
        if (clothcartList == null){
            return 0;
        }else{
            return clothcartList.size();
        }

    }

    public class ClothViewHolder extends RecyclerView.ViewHolder{

        private ImageView jsyImageView , addTojsyBtn;
        private TextView jsyNameTv, jsyBrandNameTv, jsyPriceTv;
        private CardView jsycardView;

        public ClothViewHolder(@NonNull View itemView) {
            super(itemView);

            jsycardView = itemView.findViewById(R.id.eachjerseycardview);
            addTojsyBtn = itemView.findViewById(R.id.JerseyAddToCartBtn);
            jsyNameTv = itemView.findViewById(R.id.JerseyName);
            jsyImageView = itemView.findViewById(R.id.eachJerseyImage);
            jsyBrandNameTv = itemView.findViewById(R.id.JerseyClubNameTv);
            jsyPriceTv = itemView.findViewById(R.id.JerseyPriceTv);
        }
    }
    public interface JerseyItemClickedListeners{
        void onAddtoJerseyCartBtnClicked(ClothesCart clothescart) ;
         //   void onCardClicked(Shoecart shoe);
    }
}
