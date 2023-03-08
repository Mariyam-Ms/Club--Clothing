package com.project1.clubclothing.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fashion_table")
public class DataItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public DataItem() {

    }

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

    public int getShoequantity() {
        return shoequantity;
    }

    public void setShoequantity(int shoequantity) {
        this.shoequantity = shoequantity;
    }

    public double getTotalShoesPrice() {
        return totalShoesPrice;
    }

    public void setTotalShoesPrice(double totalShoesPrice) {
        this.totalShoesPrice = totalShoesPrice;
    }

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

    public int getJerseyquantity() {
        return jerseyquantity;
    }

    public void setJerseyquantity(int jerseyquantity) {
        this.jerseyquantity = jerseyquantity;
    }

    public double getTotalJerseyPrice() {
        return totalJerseyPrice;
    }

    public void setTotalJerseyPrice(double totalJerseyPrice) {
        this.totalJerseyPrice = totalJerseyPrice;
    }

    private String shoeName, shoeBrandName;
    private int shoeImage;
    private double shoePrice;

    private int shoequantity;
    private double totalShoesPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public DataItem(String jerseyName, String jerseyClubName, int jerseyImage, double jerseyPrice,  int jerseyquantity, double totalJerseyPrice) {
        this.jerseyName = jerseyName;
        this.jerseyClubName = jerseyClubName;
        this.jerseyImage = jerseyImage;
        this.jerseyPrice = jerseyPrice;
        this.jerseyquantity = jerseyquantity;
        this.totalJerseyPrice = totalJerseyPrice;
    }

    public DataItem(int id, String shoeName, String shoeBrandName, int shoeImage, double shoePrice, int shoequantity, double totalShoesPrice) {
        this.id = id;
        this.shoeName = shoeName;
        this.shoeBrandName = shoeBrandName;
        this.shoeImage = shoeImage;
        this.shoePrice = shoePrice;
        this.shoequantity = shoequantity;
        this.totalShoesPrice = totalShoesPrice;
    }

    private String jerseyName, jerseyClubName;
    private int jerseyImage;
    private double jerseyPrice;

    public DataItem(int id) {
        this.id = id;
    }




    private int jerseyquantity;
    private double totalJerseyPrice;


}
