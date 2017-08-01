package com.example.mo.healthyfood.activityNormal;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.mo.healthyfood.FoodNormal.ShowListNormalFragment;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.FoodNormal.BrandsNormalFragment;
import com.example.mo.healthyfood.FoodNormal.MyFoodNormalFragment;
import com.example.mo.healthyfood.FoodNormal.RecommendNormalFragment;
import com.example.mo.healthyfood.FoodNormal.SearchNormalFragment;
import com.example.mo.healthyfood.activityMuscle.ShowFoodMuscleActivity;

public class FoodNormalActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_food);
        initInstances();

        //รับค่า m_id
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
                    return new SearchNormalFragment().newInstance(bundle);
                case 1:
                    return new RecommendNormalFragment().newInstance(bundle);
                case 2:
                    return new MyFoodNormalFragment().newInstance(bundle);
                case 3:
                    return new BrandsNormalFragment().newInstance(bundle);
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragment[position];
        }
    }

    public void onCreateFoodClkListener(Bundle bundle){
        Intent i = new Intent(FoodNormalActivity.this, CreateFoodNormalActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    //    startActivity(new Intent(FoodNormalActivity.this, CreateFoodNormalActivity.class));
    }


    public void onOpenActivity(Bundle bundle) {
        //การส่งค่า f_id กับ m_id ไป ShowFoodNormalActivity
        Intent i = new Intent(FoodNormalActivity.this, ShowFoodNormalActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }

    public  void  onShowFoodDetail(Bundle bundle){
        Intent i = new Intent(FoodNormalActivity.this, ShowFoodNormalActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }

    public  void  onShowListFood(Bundle bundle){
        Intent i = new Intent(FoodNormalActivity.this, ShowFoodListNormalActivity.class);
        i.putExtra("bundle", bundle);
        startActivity(i);
    }



}

