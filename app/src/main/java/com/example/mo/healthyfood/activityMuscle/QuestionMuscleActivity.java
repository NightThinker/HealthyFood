package com.example.mo.healthyfood.activityMuscle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityNormal.TodayNormalQuestionActivity;
import com.example.mo.healthyfood.fragmentMuscle.GainPlanQuestionMuscleFragment;
import com.example.mo.healthyfood.fragmentMuscle.GoalWeightMusQuestionFragment;
import com.example.mo.healthyfood.fragmentMuscle.LosePlanQuestionMuscleFragment;
import com.example.mo.healthyfood.fragmentNormal.GainPlanQuestionNormalFragment;
import com.example.mo.healthyfood.fragmentMuscle.GoalBodyFatQuestionFragment;
import com.example.mo.healthyfood.fragmentMuscle.TypeMuscleQuestionFragment;
import com.example.mo.healthyfood.fragmentNormal.LosePlanQuestionNormalFragment;

public class QuestionMuscleActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_question);

        initInstances();
        if (savedInstanceState == null) {
            // First created
            //Place Fragment here
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, GoalWeightMusQuestionFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {
        //tvHello = (TextView) findViewById(R.id.tvHello);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("ชื่อที่ต้องการ");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void onGoalBodyfatQuestionSelected(Bundle bundle) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, GoalBodyFatQuestionFragment.newInstance(bundle))
                .addToBackStack(null)
                .commit();

    }
//    public void onTypeMuscleQuestionSelected(Bundle bundle) {
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.contentContainer, TypeMuscleQuestionFragment.newInstance(bundle))
//                .addToBackStack(null)
//                .commit();
//
//    }
    public void onPlanLoseQuestionSelected(Bundle bundle) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, LosePlanQuestionMuscleFragment.newInstance(bundle))
                .addToBackStack(null)
                .commit();

    }

    public void onPlanGainQuestionSelected(Bundle bundle) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, GainPlanQuestionMuscleFragment.newInstance(bundle))
                .addToBackStack(null)
                .commit();


    }

    public void onTodayMuscleClkListener(){
//        SharedPreferences pref = getSharedPreferences("intro", 1);
//        SharedPreferences.Editor edit = pref.edit();
//        edit.putBoolean("intro", true);
//        edit.putInt("question", 1);
//        edit.apply();
        startActivity(new Intent(QuestionMuscleActivity.this, TodayMuscleQuestionActivity.class));
    }
}
