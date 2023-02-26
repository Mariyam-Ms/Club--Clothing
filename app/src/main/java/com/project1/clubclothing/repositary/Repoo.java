package com.project1.clubclothing.repositary;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.project1.clubclothing.Data.dao.DataDao;
import com.project1.clubclothing.Data.db.ItemDatabase;
import com.project1.clubclothing.model.DataItem;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repoo {

    private DataDao dataDao;
    private LiveData<List<DataItem>> allCartShoeItemsLiveData,allCartJerseyItemsLiveData;
    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<List<DataItem>> getAllShoeItemsLiveData( int id) {
        return allCartShoeItemsLiveData;
    }
    public LiveData<List<DataItem>> getAllCartJerseyItemsLiveData(int jerseyid){
        return allCartJerseyItemsLiveData;
    }
    public Repoo(Application application, int id, int jerseyid){
        dataDao = ItemDatabase.getInstance(application).dataDao();
        allCartShoeItemsLiveData = dataDao.getAllShoeItems(id);
        allCartJerseyItemsLiveData = dataDao.getAllJerseyItems(jerseyid);
    }
    public void insertShoeItem(DataItem dataItem){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.insertShoeItem(dataItem);
            }
        });
    }
    public void insertJerseyItem(DataItem dataItem){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.insertJerseyItem(dataItem);
            }
        });
    }
    public void deleteShoeItem(DataItem dataItem){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.deleteShoeItem(dataItem);
            }
        });
    }
    public void deleteJerseyItem(DataItem dataItem){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.deleteJerseyItem(dataItem);
            }
        });
    }

    public void updateShoeQuantity(int id , int shoequantity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.updateShoeQuantity(id, shoequantity);
            }
        });
    }
    public void updateJerseyQuantity(int jerseyid , int jerseyquantity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.updateJerseyQuantity(jerseyid, jerseyquantity);
            }
        });
    }

    public void updateShoePrice(int id , double totalShoeprice){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.updateShoePrice(id , totalShoeprice);
            }
        });
    }
    public void updateJerseyPrice(int jerseyid , double totalJerseyprice){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.updateJerseyPrice(jerseyid , totalJerseyprice);
            }
        });
    }

    public void deleteAllShoeItems(int id){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.deleteAllShoeItems(id);
            }
        });
    }
    public void deleteAllJerseyItems(int jerseyid){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.deleteAllJerseyItems(jerseyid);
            }
        });
    }


}
