package com.project1.clubclothing.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Shoecart implements Parcelable {
    private String shoeName, shoeBrandName;
    private int shoeImage;
    private double shoePrice;

    public Shoecart(String shoeName, String shoeBrandName, int shoeImage, double shoePrice) {
        this.shoeName = shoeName;
        this.shoeBrandName = shoeBrandName;
        this.shoeImage = shoeImage;
        this.shoePrice = shoePrice;
    }

    protected Shoecart(Parcel in) {
        shoeName = in.readString();
        shoeBrandName = in.readString();
        shoeImage = in.readInt();
        shoePrice = in.readDouble();
    }

    public static final Creator<Shoecart> CREATOR = new Creator<Shoecart>() {
        @Override
        public Shoecart createFromParcel(Parcel in) {
            return new Shoecart(in);
        }

        @Override
        public Shoecart[] newArray(int size) {
            return new Shoecart[size];
        }
    };

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public String getShoeBrandName() {
        return shoeBrandName;
    }

    public void setShoeBrandName(String shoeBrandName) {
        this.shoeBrandName = shoeBrandName;
    }

    public int getShoeImage() {
        return shoeImage;
    }

    public void setShoeImage(int shoeImage) {
        this.shoeImage = shoeImage;
    }

    public double getShoePrice() {
        return shoePrice;
    }

    public void setShoePrice(double shoePrice) {
        this.shoePrice = shoePrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(shoeName);
        parcel.writeString(shoeBrandName);
        parcel.writeInt(shoeImage);
        parcel.writeDouble(shoePrice);
    }
}
