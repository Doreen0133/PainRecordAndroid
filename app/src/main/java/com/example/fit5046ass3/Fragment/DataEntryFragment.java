package com.example.fit5046ass3.Fragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.fit5046ass3.R;
import com.example.fit5046ass3.Retrofit.Main;
import com.example.fit5046ass3.Retrofit.RetrofitClient;
import com.example.fit5046ass3.Retrofit.RetrofitInterface;
import com.example.fit5046ass3.Retrofit.SampleData;
import com.example.fit5046ass3.databinding.DataEntryFragmentBinding;
import com.example.fit5046ass3.room.entity.PainRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataEntryFragment extends Fragment {
    private DataEntryFragmentBinding dataBinding;
    private int painLevelData;
    private String painLocation="";
    private String mood="";
    private String goal="";
    private String step="";
    private String name;
    private String email="";
    private String temperature="";
    private String humidity;
    private String pressure;

    public DataEntryFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        dataBinding = DataEntryFragmentBinding.inflate(inflater,container,false);
        View view=dataBinding.getRoot();

        //pain level
        List<String> painDataList = new ArrayList<String>();
        painDataList.add("0");
        painDataList.add("1");
        painDataList.add("2");
        painDataList.add("3");
        painDataList.add("4");
        painDataList.add("5");
        painDataList.add("6");
        painDataList.add("7");
        painDataList.add("8");
        painDataList.add("9");
        painDataList.add("10");
        final ArrayAdapter<String> spinnerAdapter = new
                ArrayAdapter<String>(view.getContext() ,android.R.layout.simple_spinner_item,painDataList);
        dataBinding.levelSpinner.setAdapter(spinnerAdapter);
        dataBinding.levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = parent.getItemAtPosition(position).toString();
                if(!data.isEmpty())
                painLevelData = Integer.parseInt(data);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }


        } );

        //pain location
        dataBinding.location.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton:
                        painLocation = "back";
                        break;
                    case R.id.radioButton1:
                        painLocation = "neck";
                        break;
                    case R.id.radioButton2:
                        painLocation = "head";
                        break;
                    case R.id.radioButton3:
                        painLocation = "knees";
                        break;
                    case R.id.radioButton4:
                        painLocation = "hips";
                        break;
                    case R.id.radioButton5:
                        painLocation = "abdomen";
                        break;
                    case R.id.radioButton6:
                        painLocation = "elbows";
                        break;
                    case R.id.radioButton7:
                        painLocation = "shoulders";
                        break;
                    case R.id.radioButton8:
                        painLocation = "shins";
                        break;
                    case R.id.radioButton9:
                        painLocation = "jaw";
                        break;
                    case R.id.radioButton10:
                        painLocation = "facial";
                        break;
                }
            }
        });

        //mood
        dataBinding.mood.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.mood1:
                        mood = "very low";
                        break;
                    case R.id.mood2:
                        mood = "low";
                        break;
                    case R.id.mood3:
                        mood = "average";
                        break;
                    case R.id.mood4:
                        mood = "high";
                        break;
                    case R.id.mood5:
                        mood = "very high";
                        break;
                }
            }
        });

        //save
        dataBinding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal = dataBinding.goal.getText().toString().trim();
                step = dataBinding.step.getText().toString().trim();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date data = new Date();
                String time=formatter.format(data);
                dataBinding.levelSpinner.setEnabled(false);
                dataBinding.radioButton.setEnabled(false);
                dataBinding.radioButton1.setEnabled(false);
                dataBinding.radioButton2.setEnabled(false);
                dataBinding.radioButton3.setEnabled(false);
                dataBinding.radioButton4.setEnabled(false);
                dataBinding.radioButton5.setEnabled(false);
                dataBinding.radioButton6.setEnabled(false);
                dataBinding.radioButton7.setEnabled(false);
                dataBinding.radioButton8.setEnabled(false);
                dataBinding.radioButton9.setEnabled(false);
                dataBinding.radioButton10.setEnabled(false);
                dataBinding.mood1.setEnabled(false);
                dataBinding.mood2.setEnabled(false);
                dataBinding.mood3.setEnabled(false);
                dataBinding.mood4.setEnabled(false);
                dataBinding.mood5.setEnabled(false);
                dataBinding.goal.setEnabled(false);
                dataBinding.step.setEnabled(false);

                PainRecord pr = new PainRecord(painLevelData,painLocation,mood,step,time,HomeFragment.temperature,HomeFragment.humidity,HomeFragment.pressure,HomeFragment.email);
                HomeFragment.painRecordViewModel.insert(pr);
                Toast.makeText(getContext(),"Your data has been saved",Toast.LENGTH_LONG).show();

            }
        });

        dataBinding.edit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dataBinding.levelSpinner.setEnabled(true);
                dataBinding.radioButton.setEnabled(true);
                dataBinding.radioButton1.setEnabled(true);
                dataBinding.radioButton2.setEnabled(true);
                dataBinding.radioButton3.setEnabled(true);
                dataBinding.radioButton4.setEnabled(true);
                dataBinding.radioButton5.setEnabled(true);
                dataBinding.radioButton6.setEnabled(true);
                dataBinding.radioButton7.setEnabled(true);
                dataBinding.radioButton8.setEnabled(true);
                dataBinding.radioButton9.setEnabled(true);
                dataBinding.radioButton10.setEnabled(true);
                dataBinding.mood1.setEnabled(true);
                dataBinding.mood2.setEnabled(true);
                dataBinding.mood3.setEnabled(true);
                dataBinding.mood4.setEnabled(true);
                dataBinding.mood5.setEnabled(true);
                dataBinding.goal.setEnabled(true);
                dataBinding.step.setEnabled(true);
                Toast.makeText(getContext(),"You can edit your data now.",Toast.LENGTH_LONG).show();

            }
        });

        return view;

    }

    public void onDestroyView(){
        super.onDestroyView();
        dataBinding = null;
    }

}
