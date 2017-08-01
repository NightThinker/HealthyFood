package com.example.mo.healthyfood.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.MainMuscleActivity;
import com.example.mo.healthyfood.activityNormal.MainNormalActivity;
import com.example.mo.healthyfood.fragment.DetailEditExerciseFragment;
import com.example.mo.healthyfood.fragment.DetailExerciseFragment;

public class EditShowExerciseActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_show_edit);
        initInstances();

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, DetailEditExerciseFragment.newInstance(bundle))
                    .commit();
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }
    public void onMainMuscleClkListener(){
        startActivity(new Intent(EditShowExerciseActivity.this, MainMuscleActivity.class));
    }

    public void onMainNormalClkListener(){
        startActivity(new Intent(EditShowExerciseActivity.this, MainNormalActivity.class));
    }

}
