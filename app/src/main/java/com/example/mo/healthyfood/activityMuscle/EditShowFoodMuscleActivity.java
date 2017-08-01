package com.example.mo.healthyfood.activityMuscle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.fragmentMuscle.DetailEditFoodMuscleFragment;
import com.example.mo.healthyfood.fragmentMuscle.DetailFoodMuscleFragment;

public class EditShowFoodMuscleActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_show_food_edit);
        initInstances();

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, DetailEditFoodMuscleFragment.newInstance(bundle))
                    .commit();
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }
    public void onMainClkListener(){
        startActivity(new Intent(EditShowFoodMuscleActivity.this, MainMuscleActivity.class));
        finish();
    }

    public void onFoodClkListener(Bundle bundle){
        Intent i =new Intent(EditShowFoodMuscleActivity.this, FoodMuscleActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
        finish();
    }

}
