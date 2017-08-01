package com.example.mo.healthyfood.MainNormal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mo.healthyfood.MainMuscle.GoalsMuscleFragment;
import com.example.mo.healthyfood.MainMuscle.LogMuscleFragment;
import com.example.mo.healthyfood.MainMuscle.MaDayMuscleFragment;
import com.example.mo.healthyfood.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class InfoNormalFragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());

    public InfoNormalFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static InfoNormalFragment newInstance(Bundle args) {
        InfoNormalFragment fragment = new InfoNormalFragment();
  //      Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_normal, container, false);

        initInstances(rootView, savedInstanceState);




        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {

            private String fragment[] = {"วันนี้", "บันทึก", "เป้าหมาย"};

            @Override
            public Fragment getItem(int position) {
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                       // Bundle bundle = new Bundle();
                        if(getArguments() != null){
                            bundle.putString("date", getArguments().getString("date"));
                           return new MaDayNormalFragment().newInstance(bundle);
                        }else {
                            bundle.putString("date",formattedDate);
                            return new MaDayNormalFragment().newInstance(bundle);

                       }
                    case 1:
                     //   Bundle bundle = new Bundle();
                        if(getArguments() != null) {
                            bundle.putString("date", getArguments().getString("date"));
                            return new LogNormalFragment().newInstance(bundle);
                        }else {
                            bundle.putString("date", formattedDate);
                            return new LogNormalFragment().newInstance(bundle);
                        }
                    case 2:
                        if(getArguments() != null) {
                            bundle.putString("date", getArguments().getString("date"));
                            return new GoalsNormalFragment().newInstance(bundle);
                        }else {
                            bundle.putString("date", formattedDate);
                            return new GoalsNormalFragment().newInstance(bundle);
                        }
                    default:
                        return null;
                }

            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return fragment[position];
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {

        // Init 'View' instance(s) with rootView.findViewById here
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tablayout);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

}
