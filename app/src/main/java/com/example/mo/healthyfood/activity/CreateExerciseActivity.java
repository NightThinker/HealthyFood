package com.example.mo.healthyfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.FoodMuscleActivity;
import com.example.mo.healthyfood.activityMuscle.MainMuscleActivity;
import com.example.mo.healthyfood.activityNormal.MainNormalActivity;
import com.example.mo.healthyfood.fragment.CustomCreateExerciseFragment;
import com.example.mo.healthyfood.fragmentMuscle.CustomCreateFoodMuscleFragment;

public class CreateExerciseActivity extends AppCompatActivity {

 Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_create);
        initInstances();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, CustomCreateExerciseFragment.newInstance())
                    .commit();
        }

    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    public void onExertNormalClkListener(){
        finish();
        startActivity(new Intent(CreateExerciseActivity.this, MainNormalActivity.class));
    }

    public void onExertMuscleClkListener(){
        finish();
        startActivity(new Intent(CreateExerciseActivity.this, MainMuscleActivity.class));
    }

}
