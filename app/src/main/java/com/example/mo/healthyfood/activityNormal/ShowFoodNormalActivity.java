package com.example.mo.healthyfood.activityNormal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.fragmentNormal.DetailFoodNormalFragment;

public class ShowFoodNormalActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_show_food);
        initInstances();

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, DetailFoodNormalFragment.newInstance(bundle))
                    .commit();
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }
    public void onMainClkListener(){
        startActivity(new Intent(ShowFoodNormalActivity.this, MainNormalActivity.class));
        finish();
    }

    public void onFoodClkListener(Bundle bundle){
        Intent i =  new Intent(ShowFoodNormalActivity.this, FoodNormalActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
        finish();
    }

}
