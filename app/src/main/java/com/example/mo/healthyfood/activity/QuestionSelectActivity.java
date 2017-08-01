package com.example.mo.healthyfood.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.QuestionFragment.ActivitiesQuestionFragment;
import com.example.mo.healthyfood.QuestionFragment.SelectedQuestionFragment;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.BodyFatQuestionActivity;
import com.example.mo.healthyfood.activityNormal.QuestionNormalActivity;


public class QuestionSelectActivity extends AppCompatActivity {


    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);



        initInstances();
        if (savedInstanceState == null) {
            // First created
            //Place Fragment here
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, ActivitiesQuestionFragment.newInstance())
                    .commit();
        }

    }

    private void initInstances() {
        //tvHello = (TextView) findViewById(R.id.tvHello);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("ชื่อที่ต้องการ");
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }





    public void onSelectedQuestionSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, SelectedQuestionFragment.newInstance())
                .addToBackStack(null)
                .commit();

    }


    /**
     * แล้วถ้าอยากเลือกอันอื่นๆ อีก ก็สร้างฟังก์ชั่นอีก
     */




    public void onGoalFragmentClkListener(){
//        SharedPreferences pref = getSharedPreferences("intro", 1);
//        SharedPreferences.Editor edit = pref.edit();
//        edit.putBoolean("intro", true);
//        edit.putInt("question", 2);
//        edit.apply();
        finish();
        startActivity(new Intent(QuestionSelectActivity.this, QuestionNormalActivity.class));
    }

    public void onMuscleFragmentClkListener(){
//        SharedPreferences pref = getSharedPreferences("intro", 1);
//        SharedPreferences.Editor edit = pref.edit();
//        edit.putBoolean("intro", true);
//        edit.putInt("question", 1);
//        edit.apply();
        finish();
        startActivity(new Intent(QuestionSelectActivity.this, BodyFatQuestionActivity.class));
    }

}
