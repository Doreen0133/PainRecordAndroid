package com.example.fit5046ass3.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.fit5046ass3.databinding.PainRecordFragmentBinding;
import com.example.fit5046ass3.room.entity.PainRecord;

import java.util.List;


public class PainRecordFragment extends Fragment {
    private PainRecordFragmentBinding binding;

    public PainRecordFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        binding = PainRecordFragmentBinding.inflate(inflater,container,false);
        View view=binding.getRoot();


        HomeFragment.painRecordViewModel.getAllRecords().observe(getViewLifecycleOwner(), new Observer<List<PainRecord>>() {
            @Override
            public void onChanged(List<PainRecord> painRecords) {
                for(int i=0;i<painRecords.size();i++)
                {
                    PainRecord pr = painRecords.get(i);
                    String humidity = pr.humidity;
                    String location = pr.location;
                    String mood = pr.mood;
                    String pressure = pr.pressure;
                    String steps = pr.steps;
                    String email = pr.email;
                    int level = pr.level;
                    String temp = pr.temp;
                    String date = pr.date;
                    binding.data.setText("Email: " + email + "     Level: " + level + "     Mood: " + mood + "     Pressure: " + pressure
                            + "   Temperature: " + temp + "      Humidity:" + humidity + "     Date:" + date + "    Step: " + steps + "   Location: " + location);
                }
            }});

        return view;
    }


    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}
