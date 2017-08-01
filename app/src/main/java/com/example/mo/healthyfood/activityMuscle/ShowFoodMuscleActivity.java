package com.example.mo.healthyfood.activityMuscle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.fragmentMuscle.DetailFoodMuscleFragment;

public class ShowFoodMuscleActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_show_food);
        initInstances();

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, DetailFoodMuscleFragment.newInstance(bundle))
                    .commit();
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }
    public void onMainClkListener(){
        startActivity(new Intent(ShowFoodMuscleActivity.this, MainMuscleActivity.class));
        finish();
    }

    public void onFoodClkListener(Bundle bundle){
        Intent i =new Intent(ShowFoodMuscleActivity.this, FoodMuscleActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
        finish();
    }

}
