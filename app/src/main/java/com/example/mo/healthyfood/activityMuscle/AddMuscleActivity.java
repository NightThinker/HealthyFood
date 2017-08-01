package com.example.mo.healthyfood.activityMuscle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.ExerciseActivity;
import com.example.mo.healthyfood.activity.WeightActivity;
import com.example.mo.healthyfood.add.AddListMuscleFragment;

public class AddMuscleActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lsit);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");

        initInstances();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, AddListMuscleFragment.newInstance(bundle))
                    .commit();
        }
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }
    public void onExerciseClkListener(Bundle bundle){
        Intent i  = new Intent(AddMuscleActivity.this, ExerciseActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }


    public void onBodyFatClkListener(){
        startActivity(new Intent(AddMuscleActivity.this, BodyFatActivity.class));
    }




    public void onOpenDetailActivity(Bundle bundle) {
        Intent i = new Intent(AddMuscleActivity.this, FoodMuscleActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }
}
