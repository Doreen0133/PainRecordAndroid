package com.example.fit5046ass3.room.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fit5046ass3.room.entity.PainRecord;
import com.example.fit5046ass3.room.repository.PainRecordRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PainRecordViewModel extends AndroidViewModel {
    private PainRecordRepository pRepository;
    private LiveData<List<PainRecord>> allRecords;

    public PainRecordViewModel (Application application) {
        super(application);
        pRepository = new PainRecordRepository(application);
        allRecords = pRepository.getAllRecords();
    }
    public void initial(Application application){
        pRepository = new PainRecordRepository(application);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public CompletableFuture<PainRecord> findByIDFuture(final int recordId){
        return pRepository.findByIDFuture(recordId);
    }
    public LiveData<List<PainRecord>> getAllRecords() {
        return allRecords;
    }
    public void insert(PainRecord painRecord) {
        pRepository.insert(painRecord);
    }
    public void deleteAll() {
        pRepository.deleteAll();
    }
    public void deleteById(final int recordId){pRepository.deleteById(recordId);}
    public void update(PainRecord painRecord) {
        pRepository.updateRecord(painRecord);
    }


}