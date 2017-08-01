package com.example.mo.healthyfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.MainMuscleActivity;
import com.example.mo.healthyfood.activityNormal.MainNormalActivity;
import com.example.mo.healthyfood.fragment.DetailExerciseFragment;
import com.example.mo.healthyfood.fragmentMuscle.DetailFoodMuscleFragment;

public class ShowExerciseActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_show);
        initInstances();

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, DetailExerciseFragment.newInstance(bundle))
                    .commit();
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }
    public void onMainMuscleClkListener(){
        startActivity(new Intent(ShowExerciseActivity.this, MainMuscleActivity.class));
    }

    public void onMainNormalClkListener(){
        startActivity(new Intent(ShowExerciseActivity.this, MainNormalActivity.class));
    }

}
