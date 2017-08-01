package com.example.mo.healthyfood.activityMuscle;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.FoodMuscle.ShowListMuscleFragment;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.FoodMuscle.BrandsMuscleFragment;
import com.example.mo.healthyfood.FoodMuscle.MyFoodMuscleFragment;
import com.example.mo.healthyfood.FoodMuscle.RecommendMuscleFragment;
import com.example.mo.healthyfood.FoodMuscle.SearchMuscleFragment;

public class FoodMuscleActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_food);
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

    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager()));
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }


    private class CustomAdapter extends FragmentStatePagerAdapter {
        private String fragment[] = {"ค้นหา", "แนะนำ", "อาหาร", "ประเภท"};

        public CustomAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new SearchMuscleFragment().newInstance(bundle);
                case 1:
                    return new RecommendMuscleFragment().newInstance(bundle);
                case 2:
                    return new MyFoodMuscleFragment().newInstance(bundle);
                case 3:
                    return new BrandsMuscleFragment().newInstance(bundle);
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragment[position];
        }
    }

    public void onCreateFoodClkListener(Bundle bundle){
        Intent i = new Intent(FoodMuscleActivity.this, CreateFoodMuscleActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }

    public void onOpenActivity(Bundle bundle) {
        Intent i = new Intent(FoodMuscleActivity.this, ShowFoodMuscleActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }

    public void onshowFoodDetail(Bundle bundle){
        Intent i = new Intent(FoodMuscleActivity.this, ShowFoodMuscleActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }

    public  void  onShowListFood(Bundle bundle){
        Intent i = new Intent(FoodMuscleActivity.this, ShowListFoodMuscleActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }

}
