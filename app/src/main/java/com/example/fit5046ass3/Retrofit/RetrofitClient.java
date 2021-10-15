package com.example.fit5046ass3.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//create an instance of Retrofit using a Builder
// and then invoke the create() method on the retrofit
// instance to provide the implementation of the interface
public class RetrofitClient {
    private static Retrofit re=null;

    public static Retrofit getClient(){
        if (re==null){
            re = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return re;
    }

}
