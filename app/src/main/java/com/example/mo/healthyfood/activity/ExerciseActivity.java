package com.example.mo.healthyfood.activity;

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

import com.example.mo.healthyfood.Exercise.MyExerciseFragment;
import com.example.mo.healthyfood.Exercise.SearchExerciseFragment;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.CreateFoodMuscleActivity;


public class ExerciseActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.portrait_only)) {
            // Fix Screen orientation to portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(R.layout.activity_exercise);

        initInstances();

        Intent i = getIntent();
        bundle = i.getBundleExtra("bundle");

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });

//        if(savedInstanceState == null){
//            // First created
//            //Place Fragment here
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.contentContainer, MainFragment.newInstance())
//                    .commit();
//        }

    }

    private void initInstances() {
        //tvHello = (TextView) findViewById(R.id.tvHello);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager()));
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);


    }

    private class CustomAdapter extends FragmentStatePagerAdapter {
        private String fragment[] = {"ค้นหา",  "ออกกำลังกาย"};

        public CustomAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SearchExerciseFragment().newInstance(bundle);
                case 1:
                    return new MyExerciseFragment().newInstance(bundle);

            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragment[position];
        }
    }

    public void onCreateExercisClkListener(){
        startActivity(new Intent(ExerciseActivity.this, CreateExerciseActivity.class));
    }

    public  void onShowExerciseClkListener(Bundle bundle){
        Intent i = new Intent(ExerciseActivity.this, ShowExerciseActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }

}
