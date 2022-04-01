package com.vayrotech.foxpack;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link newWorkoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class newWorkoutFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    View view;
    private TextView dateText, timeText;
    private DatePickerDialog.OnDateSetListener onDateSetListener, confirmListener;
    int hour, min;
    boolean  arms=false, core=false, shoulders=false, back=false, chest=false, legs=false;
    DBHelper DB;
    String personsGivenName;
    CheckBox armsCb, backCb, coreCb, shouldersCb, chestCb, legsCb;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public newWorkoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newWorkoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static newWorkoutFragment newInstance(String param1, String param2) {
        newWorkoutFragment fragment = new newWorkoutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        personsGivenName = this.getArguments().getString("personsGivenName");


        view = inflater.inflate(R.layout.fragment_new_workout, container, false);
        initViews();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateText = getView().findViewById(R.id.tvDate);
        timeText = getView().findViewById(R.id.tvTime);
        DB = new DBHelper(this.getContext());






        //confirm button
        getView().findViewById(R.id.buttonConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewButton();
            }
        });




        //select date button
        getView().findViewById(R.id.selectDate).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        //select time button
        getView().findViewById(R.id.selectTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // hour and minute initialize
                                hour = hourOfDay;
                                min = minute;
                                String time;
                                Calendar calendars = Calendar.getInstance();
                                calendars.set(0, 0, 0, hour, min);


                                if(min<10){
                                    time = hour + ":" + "0" + min;
                                }
                                else {
                                    time = hour + ":" + min;
                                }
                                timeText.setText(time);
                            }
                        },12,0,false

                );
                //display old selected time
                timePickerDialog.updateTime(hour,min);
                timePickerDialog.show();
            }
        });









    }


    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this.getContext(),
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        );
        datePickerDialog.show();

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = month + "/" + dayOfMonth + "/" + year;
        dateText.setText(date);
    }





    private void initViews() {
        armsCb = (CheckBox) view.findViewById(R.id.checkBoxArms);
        armsCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Log.d("TAG=>isChecked", "True");
                    arms=true;}
                else{
                    Log.d("TAG=>isChecked", "false");
                    arms=false;}
            }});

        backCb = (CheckBox) view.findViewById(R.id.checkBoxBack);
        backCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Log.d("TAG=>isChecked", "True");
                    back=true;}
                else{
                    Log.d("TAG=>isChecked", "false");
                    back=false;}
            }});

        chestCb = (CheckBox) view.findViewById(R.id.checkBoxChest);
        chestCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Log.d("TAG=>isChecked", "True");
                    chest=true;}
                else{
                    Log.d("TAG=>isChecked", "false");
                    chest=false;}
            }});

        coreCb = (CheckBox) view.findViewById(R.id.checkBoxCore);
        coreCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Log.d("TAG=>isChecked", "True");
                    core=true;}
                else{
                    Log.d("TAG=>isChecked", "false");
                    core=false;}
            }});

        shouldersCb = (CheckBox) view.findViewById(R.id.checkBoxShoulders);
        shouldersCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Log.d("TAG=>isChecked", "True");
                    shoulders=true;}
                else{
                    Log.d("TAG=>isChecked", "false");
                    shoulders=false;}
            }});

        legsCb = (CheckBox) view.findViewById(R.id.checkBoxLegs);
        legsCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    Log.d("TAG=>isChecked", "True");
                    legs=true;}
                else{
                    Log.d("TAG=>isChecked", "false");
                    legs=false;}
            }});










    }













    public String muscleString()
    {
        String muscleString="";


        if(arms)
        {
            muscleString=muscleString+"Arms. ";
        }
        if(back)
        {
            muscleString=muscleString+"Back. ";
        }
        if(core)
        {
            muscleString=muscleString+"Core. ";
        }
        if(legs)
        {
            muscleString=muscleString+"Legs. ";
        }
        if(shoulders)
        {
            muscleString=muscleString+"Shoulders. ";
        }
        if(chest)
        {
            muscleString=muscleString+"Chest. ";
        }








        return muscleString;
    }


















    public void addNewButton()
    {

        String nameTXT = personsGivenName;
        String musclesTXT = muscleString();
        String dateTXT=dateText.getText().toString();
        String timeTxt=timeText.getText().toString();





        Boolean checkinsertdata = DB.insertuserdata(nameTXT, musclesTXT, dateTXT, timeTxt);
        if(checkinsertdata==true){
            Toast.makeText(this.getContext(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
        else{
            Toast.makeText(this.getContext(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();}







    }




}