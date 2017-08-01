package com.example.mo.healthyfood.activityNormal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.activity.ExerciseActivity;
import com.example.mo.healthyfood.activity.WeightActivity;
import com.example.mo.healthyfood.add.AddListNormalFragment;
import com.example.mo.healthyfood.R;

public class AddNormalActivity extends AppCompatActivity{
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
                    .replace(R.id.contentContainer, AddListNormalFragment.newInstance(bundle))
                    .commit();
        }
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    public void onWeightClkListener(){
        startActivity(new Intent(AddNormalActivity.this, WeightActivity.class));
    }

    public void onExerciseClkListener(Bundle bundle){
        Intent i =new Intent(AddNormalActivity.this, ExerciseActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }


    public void onOpenDetailActivity(Bundle bundle) {
        //ส่งค่า m_id ไปยัง FoodNormalActivity
        Intent i = new Intent(AddNormalActivity.this, FoodNormalActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }

}
