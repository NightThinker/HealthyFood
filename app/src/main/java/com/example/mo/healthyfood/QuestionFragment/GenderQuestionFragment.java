package com.example.mo.healthyfood.QuestionFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.QuestionActivity;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class GenderQuestionFragment extends Fragment {

    ImageButton imbtn_boy;
    ImageButton imbtn_girl;
    HealthyFoodDB healthyFoodDB;


    public GenderQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static GenderQuestionFragment newInstance() {
        GenderQuestionFragment fragment = new GenderQuestionFragment();
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
        getActivity().setTitle("Gender");
        healthyFoodDB = new HealthyFoodDB(getContext());
        healthyFoodDB.deleteAllUser();
        healthyFoodDB.deleteAllUserMuscle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question_gender, container, false);
        initInstances(rootView, savedInstanceState);


        imbtn_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("gender", 66);
                        ((QuestionActivity) getActivity()).onWeightQuestionListener(bundle);

            }
        });

        imbtn_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("gender", 655);

                        ((QuestionActivity) getActivity()).onWeightQuestionListener(bundle);
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
        imbtn_boy = (ImageButton) rootView.findViewById(R.id.imbtn_boy);
        imbtn_girl = (ImageButton) rootView.findViewById(R.id.imbtn_girl);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home :
                getFragmentManager().popBackStack();
                getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
