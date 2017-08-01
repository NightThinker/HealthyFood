package com.example.mo.healthyfood.activityNormal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.fragmentNormal.CustomCreateFoodNormalFragment;

public class CreateFoodNormalActivity extends AppCompatActivity {

 Toolbar toolbar;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_create_food);
        initInstances();

        Intent i = getIntent();
        bundle = i.getBundleExtra("bundle");


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, CustomCreateFoodNormalFragment.newInstance(bundle))
                    .commit();
        }

    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    public void onFoodClkListener(Bundle bundle){

        Intent i = new Intent(CreateFoodNormalActivity.this, FoodNormalActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
        finish();
 //       startActivity(new Intent(CreateFoodNormalActivity.this, FoodNormalActivity.class));
    }

}
