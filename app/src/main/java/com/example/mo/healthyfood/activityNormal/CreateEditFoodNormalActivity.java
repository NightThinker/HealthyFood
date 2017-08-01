package com.example.mo.healthyfood.activityNormal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.fragmentNormal.CustomCreateFoodNormalFragment;
import com.example.mo.healthyfood.fragmentNormal.CustomEditFoodNormalFragment;

public class CreateEditFoodNormalActivity extends AppCompatActivity {

 Toolbar toolbar;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_create_food_edit);
        initInstances();

        Intent i = getIntent();
        bundle = i.getBundleExtra("bundle");


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, CustomEditFoodNormalFragment.newInstance(bundle))
                    .commit();
        }

    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    public void onFoodClkListener(Bundle bundle){
        finish();
        Intent i = new Intent(CreateEditFoodNormalActivity.this, FoodNormalActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
 //       startActivity(new Intent(CreateFoodNormalActivity.this, FoodNormalActivity.class));
    }

}
