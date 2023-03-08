package com.project1.clubclothing.Data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.project1.clubclothing.model.DataItem;

import java.util.List;

@Dao
public interface DataDao {

        @Insert
    void insertShoeItem(DataItem dataItem);

    @Insert
    void insertJerseyItem(DataItem dataItem);

    @Delete
    void deleteShoeItem(DataItem dataItem);

    @Delete
    void deleteJerseyItem(DataItem dataItem);

    @Query("SELECT * FROM fashion_table  ")
    LiveData<List<DataItem>> getAllItems();




    @Query("DELETE  FROM fashion_table ")
    void deleteAllShoeItems();

    @Query("DELETE FROM fashion_table ")
    void deleteAllJerseyItems();




    @Query("UPDATE fashion_table SET shoequantity=:shoequantity WHERE id=:id")
    void updateShoeQuantity(int id , int shoequantity);

    @Query("UPDATE fashion_table SET totalShoesPrice=:totalShoesPrice WHERE id=:id")
    void updateShoePrice(int id , double totalShoesPrice);


    @Query("UPDATE fashion_table SET jerseyquantity=:jerseyquantity WHERE id=:id")
    void updateJerseyQuantity(int id , int jerseyquantity);

    @Query("UPDATE fashion_table SET totalJerseyPrice=:totalJerseyPrice WHERE id=:id")
    void updateJerseyPrice(int id , double totalJerseyPrice);



}
