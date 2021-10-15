package com.example.fit5046ass3.room.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity
public class PainRecord {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "record_id")
    public int recordId;


    @ColumnInfo(name = "pain_intensity")
    public int level;

    @ColumnInfo(name = "pain_location")
    @NonNull
    public String location;

    @NonNull
    public String mood;

    @ColumnInfo(name = "steps_taken")
    @NonNull
    public String steps;

    @NonNull
    public String date;

    @ColumnInfo(name = "temperature")
    public String temp;


    public String humidity;

    public String pressure;

    public String email;

    public PainRecord(int level, @NonNull String location, @NonNull String mood,
                      @NonNull String steps, @NonNull String date,String temp,
                       String humidity, String pressure,  String email) {
        this.level=level;
        this.location=location;
        this.mood=mood;
        this.steps=steps;
        this.date=date;
        this.temp=temp;
        this.humidity=humidity;
        this.pressure=pressure;
        this.email=email;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

}