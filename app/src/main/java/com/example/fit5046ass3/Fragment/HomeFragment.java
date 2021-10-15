package com.example.fit5046ass3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.fit5046ass3.R;
import com.example.fit5046ass3.Retrofit.RetrofitClient;
import com.example.fit5046ass3.Retrofit.RetrofitInterface;
import com.example.fit5046ass3.Retrofit.SampleData;
import com.example.fit5046ass3.databinding.HomeFragmentBinding;
import com.example.fit5046ass3.room.viewmodel.PainRecordViewModel;
import com.example.fit5046ass3.viewmodel.SharedViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private SharedViewModel model;
    private HomeFragmentBinding addBinding;
    private RetrofitInterface retrofitInterface;
    private String name;
    public static String email="";
    public List<SampleData> weatherData;
    public static PainRecordViewModel painRecordViewModel;

    public static String temperature="";
    public static String humidity="";
    public static String pressure="";


    public HomeFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = HomeFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();


        //get intent from main activity
        final Intent intent=getActivity().getIntent();
        email = intent.getStringExtra("email");
        addBinding.email1.setText(email);

        //get public weather data from the API
        //Retriving from https://github.com/daniyalahmedkhan/WeatherApp
        //Author:Daniyalahmedkhan
        Fragment def = new DataEntryFragment();
        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);
        Call<SampleData> call = retrofitInterface.getWeather(name);
        call.enqueue(new Callback<SampleData>() {
            @Override
            public void onResponse(Call<SampleData> call, Response<SampleData> response) {
                temperature = response.body().getMain().getTemp();
                humidity = response.body().getMain().getHumidity();
                pressure = response.body().getMain().getPressure();
                addBinding.temperature.setText(temperature);
                addBinding.humidity.setText(humidity);
                addBinding.pressure.setText(pressure);


            }
            @Override
            public void onFailure(Call<SampleData> call, Throwable t) {

            }
        });



        //get current date "dd/mm/yyyy"
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String current = dateFormat.format(Calendar.getInstance().getTime());
        addBinding.currentdate.setText(current);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        painRecordViewModel = new ViewModelProvider(this).get(PainRecordViewModel.class);
        painRecordViewModel.initial(getActivity().getApplication());
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
