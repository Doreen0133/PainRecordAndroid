package com.example.fit5046ass3.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.fit5046ass3.room.entity.PainRecord;

import java.util.List;

@Dao
public interface PainRecordDAO {

    @Query("SELECT * FROM PainRecord")
    LiveData<List<PainRecord>> getAll();

    @Query("SELECT * FROM PainRecord WHERE record_id = :recordId LIMIT 1")
    PainRecord findByID(int recordId);

    @Insert
    void insertAll(PainRecord...painRecords);

    @Insert
    void insert(PainRecord painRecord);

    @Delete
    void delete(PainRecord painRecord);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(PainRecord... painRecords);

    @Query("DELETE FROM PainRecord")
    void deleteAll();


    @Query("DELETE FROM PainRecord WHERE record_id=:recordId")
    void deleteById(int recordId);

}