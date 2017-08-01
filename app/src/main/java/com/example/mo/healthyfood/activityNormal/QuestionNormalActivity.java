package com.example.mo.healthyfood.activityNormal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.fragmentNormal.GainPlanQuestionNormalFragment;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.fragmentNormal.GoalWeightQuestionFragment;
import com.example.mo.healthyfood.fragmentNormal.LosePlanQuestionNormalFragment;

public class QuestionNormalActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_question);

        initInstances();
        if (savedInstanceState == null) {
            // First created
            //Place Fragment here
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, GoalWeightQuestionFragment.newInstance())
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


    public void onPlanLoseQuestionSelected(Bundle bundle) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, LosePlanQuestionNormalFragment.newInstance(bundle))
                .addToBackStack(null)
                .commit();

    }

    public void onPlanGainQuestionSelected(Bundle bundle) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, GainPlanQuestionNormalFragment.newInstance(bundle))
                .addToBackStack(null)
                .commit();

    }

    public void onTodayNormalClkListener(){
//        SharedPreferences pref = getSharedPreferences("intro", 1);
//        SharedPreferences.Editor edit = pref.edit();
//        edit.putBoolean("intro", true);
//        edit.putInt("question", 2);
//        edit.apply();
        startActivity(new Intent(QuestionNormalActivity.this, TodayNormalQuestionActivity.class));
    }
}
