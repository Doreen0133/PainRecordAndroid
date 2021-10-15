package com.example.fit5046ass3.Retrofit;

import com.google.gson.annotations.SerializedName;

public class SampleData {
    @SerializedName("main")
    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
