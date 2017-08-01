package com.example.mo.healthyfood.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mo.healthyfood.MainNormal.MaDayNormalFragment;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.MainMuscleActivity;
import com.example.mo.healthyfood.activityMuscle.TodayMuscleQuestionActivity;
import com.example.mo.healthyfood.activityNormal.MainNormalActivity;
import com.example.mo.healthyfood.activityNormal.TodayNormalQuestionActivity;
import com.example.mo.healthyfood.fragment.IntroFragment;


public class IntroActivity extends AppCompatActivity {

    //TextView tvHello;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getBoolean(R.bool.portrait_only)){
            // Fix Screen orientation to portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(R.layout.activity_intro);

        initInstances();

        SharedPreferences pref = getSharedPreferences("intro", 1);
        boolean bool = pref.getBoolean("intro", false);
        if(bool){
            if(pref.getInt("question", 0) == 1){
                startActivity(new Intent(IntroActivity.this, MainMuscleActivity.class));
            } else if(pref.getInt("question", 0) == 2) {
                startActivity(new Intent(IntroActivity.this, MainNormalActivity.class));
            }
        }

        if(savedInstanceState == null){
            // First created
            //Place Fragment here
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, IntroFragment.newInstance())
                    .commit();
        }


    }
    private  void  initInstances(){
        //tvHello = (TextView) findViewById(R.id.tvHello);
    }

    public void onFragmentClkListener(){
        startActivity(new Intent(IntroActivity.this, QuestionActivity.class));
    }
}
