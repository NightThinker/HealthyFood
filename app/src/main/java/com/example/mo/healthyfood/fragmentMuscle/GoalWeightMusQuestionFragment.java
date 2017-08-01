package com.example.mo.healthyfood.fragmentMuscle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.QuestionMuscleActivity;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class GoalWeightMusQuestionFragment extends Fragment {
//    UserDB user;

    EditText edt_goal_weight;
    FancyButton btn_next;
    int weight;

    public GoalWeightMusQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static GoalWeightMusQuestionFragment newInstance() {
        GoalWeightMusQuestionFragment fragment = new GoalWeightMusQuestionFragment();
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

        getActivity().setTitle("Goal");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_muscle_question_goal_weight, container, false);
        initInstances(rootView, savedInstanceState);


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_goal_weight.getText().toString().isEmpty()) {
//                    edt_start_weight.setError("Invalid Weight");
//                    edt_start_weight.requestFocus();
                    Toast.makeText(getActivity(), "กรุณากรอกน้ำหนักเป้าหมาย", Toast.LENGTH_SHORT).show();

                }else if (Integer.valueOf(edt_goal_weight.getText().toString()) < 40){
                    Toast.makeText(getActivity(), "ต้องน้ำหนัก 40 เป็นต้นไป", Toast.LENGTH_SHORT).show();
                }else if (Integer.valueOf(edt_goal_weight.getText().toString()) > 200){
                    Toast.makeText(getActivity(), "คุณน้ำหนักมากเกินไป", Toast.LENGTH_SHORT).show();
                }else {
                    weight = Integer.valueOf(edt_goal_weight.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putDouble("weight", weight);
                    ((QuestionMuscleActivity)getActivity()).onGoalBodyfatQuestionSelected(bundle);
                }


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
        edt_goal_weight = (EditText) rootView.findViewById(R.id.edt_goal_weight);
        btn_next = (FancyButton) rootView.findViewById(R.id.btn_next);
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
