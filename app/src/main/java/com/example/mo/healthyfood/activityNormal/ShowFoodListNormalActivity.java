package com.example.mo.healthyfood.activityNormal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.FoodNormal.ShowListNormalFragment;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.fragmentNormal.DetailFoodNormalFragment;

public class ShowFoodListNormalActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_show_list);
        initInstances();

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, ShowListNormalFragment.newInstance(bundle))
                    .commit();
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }
    public void onMainClkListener(){
        startActivity(new Intent(ShowFoodListNormalActivity.this, MainNormalActivity.class));
        finish();
    }

    public void onFoodClkListener(Bundle bundle){
        Intent i =  new Intent(ShowFoodListNormalActivity.this, FoodNormalActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
        finish();
    }

    public  void  onShowFoodDetail(Bundle bundle){
        Intent i = new Intent(ShowFoodListNormalActivity.this, ShowFoodNormalActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }

}
