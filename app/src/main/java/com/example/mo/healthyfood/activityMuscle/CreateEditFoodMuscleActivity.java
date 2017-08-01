package com.example.mo.healthyfood.activityMuscle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.fragmentMuscle.CustomCreateFoodMuscleFragment;
import com.example.mo.healthyfood.fragmentMuscle.CustomEditFoodMuscleFragment;

public class CreateEditFoodMuscleActivity extends AppCompatActivity {

 Toolbar toolbar;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_create_food_edit);
        initInstances();

        Intent i = getIntent();
        bundle = i.getBundleExtra("bundle");


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, CustomEditFoodMuscleFragment.newInstance(bundle))
                    .commit();
        }

    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    public void onFoodClkListener(Bundle bundle){
        finish();
        Intent i = new Intent(CreateEditFoodMuscleActivity.this, FoodMuscleActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
  //      startActivity(new Intent(CreateFoodMuscleActivity.this, FoodMuscleActivity.class));
    }

}
