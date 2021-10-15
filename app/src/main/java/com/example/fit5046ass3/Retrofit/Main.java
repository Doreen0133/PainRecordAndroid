package com.example.fit5046ass3.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    private String temp;

    @SerializedName("pressure")
    private String pressure;

    @SerializedName("humidity")
    private String humidity;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }


}
