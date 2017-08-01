package com.example.mo.healthyfood.FoodNormal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityNormal.FoodNormalActivity;

import java.nio.BufferUnderflowException;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class BrandsNormalFragment extends Fragment {
    FancyButton btnFastFood,btnSweet,btnFruit,btnDrink,btnSuper,btnOther;

    FoodNormalActivity foodNormalActivity;

    public BrandsNormalFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static BrandsNormalFragment newInstance(Bundle args) {
        BrandsNormalFragment fragment = new BrandsNormalFragment();
//        Bundle args = new Bundle();
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
    public void onAttach(Context context) {
        super.onAttach(context);
        foodNormalActivity = (FoodNormalActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_normal_food_brands, container, false);
        initInstances(rootView, savedInstanceState);
        btnFastFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tf_id","TF01");
                bundle.putString("m_id",getArguments().getString("m_id"));
                bundle.putString("date",getArguments().getString("date"));
                foodNormalActivity.onShowListFood(bundle);

            }
        });

        btnSweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tf_id","TF02");
                bundle.putString("m_id",getArguments().getString("m_id"));
                bundle.putString("date",getArguments().getString("date"));
                foodNormalActivity.onShowListFood(bundle);
            }
        });

        btnFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tf_id","TF03");
                bundle.putString("m_id",getArguments().getString("m_id"));
                bundle.putString("date",getArguments().getString("date"));
                foodNormalActivity.onShowListFood(bundle);
            }
        });
        btnDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tf_id","TF04");
                bundle.putString("m_id",getArguments().getString("m_id"));
                bundle.putString("date",getArguments().getString("date"));
                foodNormalActivity.onShowListFood(bundle);
            }
        });

        btnSuper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tf_id","TF05");
                bundle.putString("m_id",getArguments().getString("m_id"));
                bundle.putString("date",getArguments().getString("date"));
                foodNormalActivity.onShowListFood(bundle);
            }
        });

        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("tf_id","TF06");
                bundle.putString("m_id",getArguments().getString("m_id"));
                bundle.putString("date",getArguments().getString("date"));
                foodNormalActivity.onShowListFood(bundle);
            }
        });


        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        btnFastFood = (FancyButton) rootView.findViewById(R.id.btnFastFood);
        btnSweet = (FancyButton) rootView.findViewById(R.id.btnSweet);
        btnFruit = (FancyButton) rootView.findViewById(R.id.btnFruit);
        btnDrink = (FancyButton) rootView.findViewById(R.id.btnDrink);
        btnSuper = (FancyButton) rootView.findViewById(R.id.btnSuper);
        btnOther = (FancyButton) rootView.findViewById(R.id.btnOther);
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
