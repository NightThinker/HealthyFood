package com.example.mo.healthyfood.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mo.healthyfood.QuestionFragment.AllergyQuestionFragment;
import com.example.mo.healthyfood.QuestionFragment.TypeVegetarianQuestionFragment;
import com.example.mo.healthyfood.QuestionFragment.VegetarianQuestionFragment;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.QuestionFragment.FoodAllergyQuestionFragment;
import com.example.mo.healthyfood.QuestionFragment.GenderQuestionFragment;
import com.example.mo.healthyfood.QuestionFragment.WeightQuestionFragment;
import com.example.mo.healthyfood.fragmentMuscle.TypeMuscleQuestionFragment;


public class QuestionActivity extends AppCompatActivity  {


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
                    .replace(R.id.contentContainer, GenderQuestionFragment.newInstance())
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onWeightQuestionListener(Bundle bundle) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, WeightQuestionFragment.newInstance(bundle))
                .addToBackStack(null)
                .commit();

    }

    public void onVegetQuestionSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, VegetarianQuestionFragment.newInstance())
                .addToBackStack(null)
                .commit();

    }

    public void onTypeVegetarianQuestionSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, TypeVegetarianQuestionFragment.newInstance())
                .addToBackStack(null)
                .commit();

    }
    public void onAllergyQuestionSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, AllergyQuestionFragment.newInstance())
                .addToBackStack(null)
                .commit();

    }

    public void onFoodAllergyQuestionSelected() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, FoodAllergyQuestionFragment.newInstance())
                .addToBackStack(null)
                .commit();

    }
    public void onActivitiesFragmentClkListener(){
//        SharedPreferences pref = getSharedPreferences("intro", 1);
//        SharedPreferences.Editor edit = pref.edit();
//        edit.putBoolean("intro", true);
//        edit.putInt("question", 1);
//        edit.apply();
        finish();
        startActivity(new Intent(QuestionActivity.this, QuestionSelectActivity.class));
    }







}
