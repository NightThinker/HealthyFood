package com.example.mo.healthyfood.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.IntroActivity;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class IntroFragment extends Fragment {



    HealthyFoodDB healthyFoodDb;





    FancyButton btn_spotify;

    public IntroFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static IntroFragment newInstance() {
        IntroFragment fragment = new IntroFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);


        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);

        healthyFoodDb = new HealthyFoodDB(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_intro, container, false);
        initInstances(rootView, savedInstanceState);


        //TODO : เช็คการลงข้อมูลซ้ำ

        if(getActivity().getSharedPreferences("insert_data", Context.MODE_PRIVATE).getBoolean("insert", false)){
            Log.e("insert","insert");
        }else {
            healthyFoodDb.TypeUserData();
            healthyFoodDb.PlanData();
            healthyFoodDb.TypeFoodData();
            healthyFoodDb.MaelData();
            healthyFoodDb.ExertData();
            healthyFoodDb.FoodUnitData();
            healthyFoodDb.AllergyData();
            healthyFoodDb.FoodMuscleData();
            healthyFoodDb.IngredientsMuscle();
            healthyFoodDb.DetailFoodMuscleData();
            healthyFoodDb.FoodNormalData();
            healthyFoodDb.IngredientsNormal();
            healthyFoodDb.DetailFoodNormalData();
            healthyFoodDb.HealthTodayData();
            healthyFoodDb.VegetData();

            healthyFoodDb.ExerciseTodayData();
            healthyFoodDb.TypeMuscleData();

            healthyFoodDb.WeightDataEx();
            healthyFoodDb.BodyFatDataEx();
            healthyFoodDb.FFMIDataEx();

            getActivity().getSharedPreferences("insert_data", Context.MODE_PRIVATE).edit().putBoolean("insert", true).apply();

        }

        return rootView;


    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        btn_spotify = (FancyButton) rootView.findViewById(R.id.btn_spotify);
        btn_spotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((IntroActivity) getActivity()).onFragmentClkListener();
            }
        });
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
