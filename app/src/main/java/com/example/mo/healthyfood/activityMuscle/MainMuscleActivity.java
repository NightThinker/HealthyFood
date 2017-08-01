package com.example.mo.healthyfood.activityMuscle;

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


import com.example.mo.healthyfood.MainMuscle.InfoMuscleFragment;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.CalendarActivity;
import com.example.mo.healthyfood.activity.EditShowExerciseActivity;
import com.example.mo.healthyfood.activity.QuestionSelectActivity;
import com.example.mo.healthyfood.MainMuscle.GoalsMuscleFragment;
import com.example.mo.healthyfood.MainMuscle.MaDayMuscleFragment;
import com.example.mo.healthyfood.MainMuscle.LogMuscleFragment;
import com.example.mo.healthyfood.activity.WeightActivity;
import com.example.mo.healthyfood.fragment.CalenderFragment;


public class MainMuscleActivity extends AppCompatActivity {

    Toolbar toolbar;
//    TabLayout tabLayout;
  // ViewPager viewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            // Fix Screen orientation to portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(R.layout.activity_muscle_main);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("bundle");

        initInstances();

        if (savedInstanceState == null) {
            // First created
            //Place Fragment here
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, InfoMuscleFragment.newInstance(bundle))
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_muscle :
                startActivity(new Intent(MainMuscleActivity.this,QuestionSelectActivity.class));
                finish();
                return true;
            case R.id.action_calendar :
                startActivity(new Intent(MainMuscleActivity.this, CalendarActivity.class));
                return true;

            default: return  super.onOptionsItemSelected(item);
        }

    }

    public void onAddClkListener(Bundle bundle){
        Intent i = new Intent(new Intent(MainMuscleActivity.this, AddMuscleActivity.class));
        i.putExtra("bundle",bundle);
        startActivity(i);
     //   startActivity(new Intent(MainMuscleActivity.this, AddMuscleActivity.class));
    }

    public  void  onWeightClkListener(){
        startActivity(new Intent(MainMuscleActivity.this, WeightActivity.class));
    }

    public  void  onBodyFatClkListener(){
        startActivity(new Intent(MainMuscleActivity.this,BodyFatActivity.class));
    }

    public  void  onChangeGoalClkListener(){
        startActivity(new Intent(MainMuscleActivity.this,QuestionSelectActivity.class));
    }

    public  void  onQuestionTodayClkListener(){
        startActivity(new Intent(MainMuscleActivity.this,TodayMuscleQuestionActivity.class));
    }

    public void onEditShowFoodDetail(Bundle bundle){
        Intent i = new Intent(MainMuscleActivity.this, EditShowFoodMuscleActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }

    public void onEditShowExertdDetail(Bundle bundle){
        Intent i = new Intent(MainMuscleActivity.this, EditShowExerciseActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }
}
