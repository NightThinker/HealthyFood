package com.example.mo.healthyfood.fragmentMuscle;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.QuestionMuscleActivity;
import com.example.mo.healthyfood.activityNormal.QuestionNormalActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class GainPlanQuestionMuscleFragment extends Fragment {

    RadioGroup rg_gain;
//    FancyButton btn_next;
    Bundle bundle;

    HealthyFoodDB healthyFoodDB;
    String p_id,bodyfat,weight;
    boolean db;
    Cursor cursor,user,userMus;
    int goal,U_ID,UM_ID;
    Cursor dateBase;
    String date,u_id,um_id;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());

    public GainPlanQuestionMuscleFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static GainPlanQuestionMuscleFragment newInstance(Bundle bundle) {
        GainPlanQuestionMuscleFragment fragment = new GainPlanQuestionMuscleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
        getActivity().setTitle("My Plan");

        healthyFoodDB = new HealthyFoodDB(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question2_plan_gain, container, false);
        initInstances(rootView, savedInstanceState);

        user = healthyFoodDB.getUser();
        U_ID = user.getInt(user.getColumnIndex(HealthyFoodDB.U_ID));
        u_id = String.valueOf(U_ID);

        userMus = healthyFoodDB.getMaxUserMuscle();
        UM_ID = userMus.getInt(userMus.getColumnIndex(HealthyFoodDB.UM_ID));
        um_id = String.valueOf(UM_ID);

        rg_gain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_gain_two:
                        p_id = "P01";
                        break;
                    case R.id.rb_gain_three:
                        p_id = "P02";
                        break;
                    case R.id.rb_gain_four:
                        p_id = "P03";
                        break;
                    case R.id.rb_gain_five:
                        p_id = "P04";
                        break;
                }
            }
        });
        bodyfat = String.valueOf(getArguments().getDouble("bodyfatgoal"));
        weight = String.valueOf(getArguments().getDouble("weight"));


        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        rg_gain = (RadioGroup) rootView.findViewById(R.id.rg_gain);
 //       btn_next = (FancyButton) rootView.findViewById(R.id.btn_next);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.next,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_next:
                dateBase = healthyFoodDB.getMaxGoalMuscle();
                date = dateBase.getString(dateBase.getColumnIndex(HealthyFoodDB.GM_Date));
                if(date == null){
                    db = healthyFoodDB.InsertGoalMuscle(u_id,um_id,p_id,weight,bodyfat);
                    if (db) {
                        Toast.makeText(getActivity(), "Goal Inserted", Toast.LENGTH_SHORT).show();
                        ((QuestionMuscleActivity) getActivity()).onTodayMuscleClkListener();
                    } else {
                        Toast.makeText(getActivity(), "Goal not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }else if(date.equals(formattedDate)){
                    db = healthyFoodDB.UpdateGoalMuscle(u_id,um_id,p_id,weight,bodyfat);
                    if (db) {
                        Toast.makeText(getActivity(), "Goal Update", Toast.LENGTH_SHORT).show();
                        ((QuestionMuscleActivity) getActivity()).onTodayMuscleClkListener();
                    } else {
                        Toast.makeText(getActivity(), "Goal not Update", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    db = healthyFoodDB.InsertGoalMuscle(u_id,um_id,p_id,weight,bodyfat);
                    if (db) {
                        Toast.makeText(getActivity(), "Goal Inserted", Toast.LENGTH_SHORT).show();
                        ((QuestionMuscleActivity) getActivity()).onTodayMuscleClkListener();
                    } else {
                        Toast.makeText(getActivity(), "Goal not Inserted", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
