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


//    public MyViewModel(@NonNull Application application) {
//        super(application);
//        repoo=new Repoo(application,1,1);
//    }

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repoo = new Repoo(application);
    }




    public LiveData<List<DataItem>> getAllItems() {
        return repoo.getAllItemsLiveData();
    }
    public void insertJerseyItem(DataItem dataItem ,int[] jid) {
        repoo.insertJerseyItem(dataItem);
    }






    public void updateJerseyQuantity(int id, int jerseyquantity) {
        repoo.updateJerseyQuantity(id, jerseyquantity);
    }

    public void updateJerseyPrice(int id, double totalJerseyprice) {
        repoo.updateJerseyPrice(id, totalJerseyprice);
    }

    public void deleteJerseyItem(DataItem dataItem) {
        repoo.deleteJerseyItem(dataItem);
    }

    public void deleteById(int id){
        repoo.deleteById(id);
    }
    public void deleteAllJerseyItems() {
        repoo.deleteAllJerseyItems();
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

    public void deleteAllShoeItems() {
        repoo.deleteAllShoeItems();
    }


}
