package com.example.mo.healthyfood.activityMuscle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.FoodMuscle.ShowListMuscleFragment;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityNormal.ShowFoodNormalActivity;
import com.example.mo.healthyfood.fragment.BodyFatFragment;

public class ShowListFoodMuscleActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_show_list);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");


        initInstances();
        if (savedInstanceState == null) {
            // First created
            //Place Fragment here
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, ShowListMuscleFragment.newInstance(bundle))
                    .commit();
        }
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    public void onMainClkListener(){
        startActivity(new Intent(ShowListFoodMuscleActivity.this, MainMuscleActivity.class));
    }

    public  void  onShowFoodDetail(Bundle bundle){
        Intent i = new Intent(ShowListFoodMuscleActivity.this, ShowFoodMuscleActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }



}
