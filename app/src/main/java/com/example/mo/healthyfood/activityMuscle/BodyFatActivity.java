package com.example.mo.healthyfood.activityMuscle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityNormal.MainNormalActivity;
import com.example.mo.healthyfood.fragment.BodyFatFragment;
import com.example.mo.healthyfood.fragmentMuscle.BodyFatQuestionragment;

public class BodyFatActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_body_fat);

        initInstances();
        if (savedInstanceState == null) {
            // First created
            //Place Fragment here
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, BodyFatFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    public void onMainClkListener(){
        startActivity(new Intent(BodyFatActivity.this, MainMuscleActivity.class));
    }
}
