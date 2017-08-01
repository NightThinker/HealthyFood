package com.example.mo.healthyfood.activityNormal;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mo.healthyfood.MainNormal.InfoNormalFragment;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.CalendarActivity;
import com.example.mo.healthyfood.activity.EditShowExerciseActivity;
import com.example.mo.healthyfood.activity.QuestionSelectActivity;
import com.example.mo.healthyfood.MainNormal.GoalsNormalFragment;
import com.example.mo.healthyfood.MainNormal.LogNormalFragment;
import com.example.mo.healthyfood.MainNormal.MaDayNormalFragment;
import com.example.mo.healthyfood.activity.WeightActivity;


public class MainNormalActivity extends AppCompatActivity {

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            // Fix Screen orientation to portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(R.layout.activity_normal_main);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");

        initInstances();

        if (savedInstanceState == null) {
            // First created
            //Place Fragment here
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, InfoNormalFragment.newInstance(bundle))
                    .commit();
        }


    }

    private void initInstances() {
        //tvHello = (TextView) findViewById(R.id.tvHello);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_muscle :
                startActivity(new Intent(MainNormalActivity.this,QuestionSelectActivity.class));
                finish();
                return true;
            case R.id.action_calendar :
                startActivity(new Intent(MainNormalActivity.this, CalendarActivity.class));
                return true;

            default: return  super.onOptionsItemSelected(item);
        }
    }

    public void onAddClkListener(Bundle bundle){
        Intent i = new Intent(MainNormalActivity.this, AddNormalActivity.class);
        i.putExtra("bundle",bundle);
      //  startActivity(new Intent(MainNormalActivity.this, AddNormalActivity.class));\
        startActivity(i);
    }

    public  void  onWeightClkListener(){
        startActivity(new Intent(MainNormalActivity.this, WeightActivity.class));
    }

    public  void  onChangeGoalClkListener(){
        startActivity(new Intent(MainNormalActivity.this,QuestionSelectActivity.class));
    }

    public  void  onQuestionTodayClkListener(){
        startActivity(new Intent(MainNormalActivity.this,TodayNormalQuestionActivity.class));
    }

    public void onEditShowFoodDetail(Bundle bundle){
        Intent i = new Intent(MainNormalActivity.this, EditShowFoodNormalActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }

    public void onEditShowExertdDetail(Bundle bundle){
        Intent i = new Intent(MainNormalActivity.this, EditShowExerciseActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }
}
