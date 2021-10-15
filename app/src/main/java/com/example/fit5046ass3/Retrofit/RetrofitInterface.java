package com.example.fit5046ass3.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//Java Interface for http operations (API) and return type.
public interface RetrofitInterface {
    @GET("weather?lat=-37.91&lon=145.13&units=metric&appid=74f860da55f15d0d35005f6710a165e8")
    Call<SampleData> getWeather(@Query("q") String name);
}
