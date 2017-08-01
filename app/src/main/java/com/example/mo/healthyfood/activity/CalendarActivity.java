package com.example.mo.healthyfood.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.activityMuscle.MainMuscleActivity;
import com.example.mo.healthyfood.activityNormal.MainNormalActivity;
import com.example.mo.healthyfood.fragment.CalenderFragment;
import com.example.mo.healthyfood.R;

public class CalendarActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initInstances();

        if (savedInstanceState == null) {
            // First created
            //Place Fragment here
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, CalenderFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    public void onDateNormalClkListener(){
        startActivity(new Intent(CalendarActivity.this, MainNormalActivity.class));
    }

    public void onDateMuscleClkListener(){
        startActivity(new Intent(CalendarActivity.this, MainMuscleActivity.class));
    }

    public  void onMyDayNormalClkListener(Bundle bundle){
        Intent i = new Intent(CalendarActivity.this,MainNormalActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }

    public  void onMyDayMuscleClkListener(Bundle bundle){
        Intent i = new Intent(CalendarActivity.this,MainMuscleActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);

    }
}
