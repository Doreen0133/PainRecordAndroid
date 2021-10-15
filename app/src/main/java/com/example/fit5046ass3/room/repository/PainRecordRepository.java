package com.example.fit5046ass3.room.repository;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;

import com.example.fit5046ass3.room.dao.PainRecordDAO;
import com.example.fit5046ass3.room.database.PainRecordDatabase;
import com.example.fit5046ass3.room.entity.PainRecord;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class PainRecordRepository {
    private PainRecordDAO painRecordDAO;
    private LiveData<List<PainRecord>> allRecords;
    private PainRecord painRecord;

    public PainRecordRepository(Application application){
        PainRecordDatabase db = PainRecordDatabase.getInstance(application);
        painRecordDAO = db.painRecordDao();
        allRecords = painRecordDAO.getAll();
    }
    public LiveData<List<PainRecord>> getAllRecords(){
        return allRecords;
    }

    public void insert(final PainRecord painRecord){
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painRecordDAO.insert(painRecord);
            }
        });
    }
    public void deleteAll(){
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painRecordDAO.deleteAll();
            }
        });
    }

    public void delete(final PainRecord painRecord){
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painRecordDAO.delete(painRecord);
            }
        });
    }
    public void deleteById(final int recordId){
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painRecordDAO.deleteById(recordId);
            }
        });
    }
    public void insertAll(final PainRecord...painRecord){
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painRecordDAO.insertAll(painRecord);
            }
        });
    }

    public void updateRecord(final PainRecord painRecord){
        PainRecordDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                painRecordDAO.update(painRecord);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<PainRecord> findByIDFuture(final int recordId) {
        return CompletableFuture.supplyAsync(new Supplier<PainRecord>() {
            @Override
            public PainRecord get() {
                return painRecordDAO.findByID(recordId);
            }
        }, PainRecordDatabase.databaseWriteExecutor);
    }
}