package com.example.mo.healthyfood.activityMuscle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.todayQuestion.ExerciseTodayQuestionFragment;

public class TodayMuscleQuestionActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.portrait_only)){
            // Fix Screen orientation to portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(R.layout.activity_muscle_question_today);

        initInstances();

        if (savedInstanceState == null) {
            // First created
            //Place Fragment here
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, ExerciseTodayQuestionFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    public void onMainMuscleClkListener(){
        SharedPreferences pref = getSharedPreferences("intro", 1);
        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean("intro", true);
        edit.putInt("question", 1);
        edit.apply();
        finish();
        startActivity(new Intent(TodayMuscleQuestionActivity.this, MainMuscleActivity.class));
    }
}
