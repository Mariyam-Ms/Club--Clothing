package com.project1.clubclothing.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ClothesCart implements Parcelable {

    private String jerseyName, jerseyClubName;
    private int jerseyImage;
    private double jerseyPrice;

    public String getJerseyName() {
        return jerseyName;
    }

    public void setJerseyName(String jerseyName) {
        this.jerseyName = jerseyName;
    }

    public String getJerseyClubName() {
        return jerseyClubName;
    }

    public void setJerseyClubName(String jerseyClubName) {
        this.jerseyClubName = jerseyClubName;
    }

    public int getJerseyImage() {
        return jerseyImage;
    }

    public void setJerseyImage(int jerseyImage) {
        this.jerseyImage = jerseyImage;
    }

    public double getJerseyPrice() {
        return jerseyPrice;
    }

    public void setJerseyPrice(double jerseyPrice) {
        this.jerseyPrice = jerseyPrice;
    }

    public ClothesCart(String jerseyName, String jerseyClubName, int jerseyImage, double jerseyPrice) {
        this.jerseyName = jerseyName;
        this.jerseyClubName = jerseyClubName;
        this.jerseyImage = jerseyImage;
        this.jerseyPrice = jerseyPrice;
    }

    protected ClothesCart(Parcel in) {
        jerseyName = in.readString();
        jerseyClubName = in.readString();
        jerseyImage = in.readInt();
        jerseyPrice = in.readDouble();
    }

    public static final Creator<ClothesCart> CREATOR = new Creator<ClothesCart>() {
        @Override
        public ClothesCart createFromParcel(Parcel in) {
            return new ClothesCart(in);
        }

        @Override
        public ClothesCart[] newArray(int size) {
            return new ClothesCart[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(jerseyName);
        dest.writeString(jerseyClubName);
        dest.writeInt(jerseyImage);
        dest.writeDouble(jerseyPrice);
    }
}
