package com.project1.clubclothing.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project1.clubclothing.R;

import java.util.List;

public class HorizontalRcyAdapter extends RecyclerView.Adapter<HorizontalRcyAdapter.MyViewHolder> {
    private List<Integer> imageList;

    public HorizontalRcyAdapter(List<Integer> imageList){
        this.imageList=imageList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachitemhrcy, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mImageView.setImageResource(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imagefakesneakers);
        }
    }
}
