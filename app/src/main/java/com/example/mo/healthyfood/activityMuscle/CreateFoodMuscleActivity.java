package com.example.mo.healthyfood.activityMuscle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityNormal.AddNormalActivity;
import com.example.mo.healthyfood.fragmentMuscle.CustomCreateFoodMuscleFragment;

public class CreateFoodMuscleActivity extends AppCompatActivity {

 Toolbar toolbar;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_create_food);
        initInstances();

        Intent i = getIntent();
        bundle = i.getBundleExtra("bundle");


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, CustomCreateFoodMuscleFragment.newInstance(bundle))
                    .commit();
        }

    }

    private void initInstances() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    public void onFoodClkListener(Bundle bundle){
        finish();
        Intent i = new Intent(CreateFoodMuscleActivity.this, FoodMuscleActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
  //      startActivity(new Intent(CreateFoodMuscleActivity.this, FoodMuscleActivity.class));
    }

}
