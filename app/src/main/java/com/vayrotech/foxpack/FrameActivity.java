package com.vayrotech.foxpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FrameActivity extends AppCompatActivity {

    Button scheduleFragButt, addFragButt, homeButt;
    String personsGivenName, firstFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        scheduleFragButt = findViewById(R.id.toSchedFrag);
        homeButt = findViewById(R.id.mainMenuButt);
        addFragButt = findViewById(R.id.addWorkoutFrag);
        personsGivenName = getIntent().getExtras().getString("personGivenName");
        firstFrag = getIntent().getExtras().getString("firstFrag");

        homeButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        scheduleFragButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new ScheduleFragment());

            }
        });

        addFragButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new newWorkoutFragment());

            }
        });



        if(firstFrag.equals("toScheduleFrag")) {

            replaceFragment(new ScheduleFragment());
        }
        else
        {
            if(firstFrag.equals("toAddFrag"))
                replaceFragment(new newWorkoutFragment());

        }

    }




//personsGivenName




    private void replaceFragment(Fragment fragment) {


        Bundle bundle = new Bundle();
        bundle.putString("personsGivenName", personsGivenName);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();





    }







}