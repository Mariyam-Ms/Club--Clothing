package com.project1.clubclothing.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.project1.clubclothing.model.DataItem;
import com.project1.clubclothing.repositary.Repoo;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private Repoo repoo;


    public MyViewModel(@NonNull Application application) {
        super(application);
        repoo=new Repoo(application,1,1);
    }

    public MyViewModel(@NonNull Application application, int id, int jerseyid) {
        super(application);
        this.repoo = new Repoo(application, id, jerseyid);
    }




    public LiveData<List<DataItem>> getAllShoeItems(int id) {
        return repoo.getAllShoeItemsLiveData(id);
    }
    public void insertJerseyItem(DataItem dataItem) {
        repoo.insertJerseyItem(dataItem);
    }



    public LiveData<List<DataItem>> getAllJerseyItems(int jerseyid) {
        return repoo.getAllCartJerseyItemsLiveData(jerseyid);
    }


    public void updateJerseyQuantity(int jerseyid, int jerseyquantity) {
        repoo.updateJerseyQuantity(jerseyid, jerseyquantity);
    }

    public void updateJerseyPrice(int jerseyid, double totalJerseyprice) {
        repoo.updateJerseyPrice(jerseyid, totalJerseyprice);
    }

    public void deleteJerseyItem(DataItem dataItem) {
        repoo.deleteJerseyItem(dataItem);
    }

    public void deleteAllJerseyItems(int jerseyid) {
        repoo.deleteAllJerseyItems(jerseyid);
    }

    public void insertShoeItem(DataItem dataItem,int[] id) {
        repoo.insertShoeItem(dataItem);
    }

    public void updateShoeQuantity(int id, int shoequantity) {
        repoo.updateShoeQuantity(id, shoequantity);
    }

    public void updateShoePrice(int id, double totalShoeprice) {
        repoo.updateShoePrice(id, totalShoeprice);
    }

    public void deleteShoeItem(DataItem dataItem) {
        repoo.deleteShoeItem(dataItem);
    }

    public void deleteAllShoeItems(int id) {
        repoo.deleteAllShoeItems(id);
    }


}
