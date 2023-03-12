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
    private LiveData<List<DataItem>> allCartItemsLiveData;
    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<List<DataItem>> getAllItemsLiveData() {
        return allCartItemsLiveData;
    }

    public Repoo(Application application){
        dataDao = ItemDatabase.getInstance(application).dataDao();
        allCartItemsLiveData = dataDao.getAllItems();

    }
    public  void deleteById(int id){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.deleteById(id);
            }
        });
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
    public void updateJerseyQuantity(int id , int jerseyquantity) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.updateJerseyQuantity(id, jerseyquantity);
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
    public void updateJerseyPrice(int id , double totalJerseyprice){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.updateJerseyPrice(id , totalJerseyprice);
            }
        });
    }

    public void deleteAllShoeItems(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.deleteAllShoeItems();
            }
        });
    }
    public void deleteAllJerseyItems(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                dataDao.deleteAllJerseyItems();
            }
        });
    }


}
