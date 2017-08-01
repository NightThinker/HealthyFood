package com.example.mo.healthyfood.fragmentMuscle;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.QuestionMuscleActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class GoalBodyFatQuestionFragment extends Fragment {
//    UserDB user;

    HealthyFoodDB healthyFoodDB;
    EditText edt_goal_bodyFat;
    FancyButton btn_next;
    Cursor bodyfat,fmi,user,we,dateBase,userMus;
    String u_id,fi_id,bf_id,tm_id,date,um_id,p_id,weight,GBodyfat;
    Double bf_bodyfat,GoalWeight,we_weight,GoalBodyfat;
    Boolean um;
    boolean db;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());



    public GoalBodyFatQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static GoalBodyFatQuestionFragment newInstance(Bundle bundle) {
        GoalBodyFatQuestionFragment fragment = new GoalBodyFatQuestionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);

        getActivity().setTitle("Goal");

        healthyFoodDB = new HealthyFoodDB(getActivity());

        Toast.makeText(getActivity(),"weight : "+ getArguments().getDouble("weight")
                , Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_muscle_question_goal_bodayfat, container, false);
        initInstances(rootView, savedInstanceState);

        bodyfat = healthyFoodDB.getMaxBodyfat();
        bf_id = String.valueOf(bodyfat.getInt(bodyfat.getColumnIndex(HealthyFoodDB.BF_ID)));
        bf_bodyfat = bodyfat.getDouble(bodyfat.getColumnIndex(HealthyFoodDB.BF_BodyFat));

        Toast.makeText(getActivity(),"bodyfat : "+ bf_bodyfat
                , Toast.LENGTH_SHORT).show();

        //    user = healthyFoodDB.getMaxUser();

        user = healthyFoodDB.getUser();
        u_id = String.valueOf(user.getInt(user.getColumnIndex(HealthyFoodDB.U_ID)));


        fmi = healthyFoodDB.getMaxFFMI();
        fi_id = String.valueOf(fmi.getInt(fmi.getColumnIndex(HealthyFoodDB.FI_ID)));

        we = healthyFoodDB.getMaxWeight();
        we_weight = we.getDouble(we.getColumnIndex(HealthyFoodDB.WE_Weight));

        Toast.makeText(getActivity(),"we_weight : "+ we_weight
                , Toast.LENGTH_SHORT).show();

        GoalWeight = getArguments().getDouble("weight");
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GoalBodyfat = Double.valueOf(edt_goal_bodyFat.getText().toString());
                if (edt_goal_bodyFat.getText().toString().isEmpty()) {
//                    edt_start_weight.setError("Invalid Weight");
//                    edt_start_weight.requestFocus();
                    Toast.makeText(getActivity(), "กรุณากรอกเปอร์เซ็นไขมันในร่างกาย", Toast.LENGTH_SHORT).show();

                }else if (Integer.valueOf(edt_goal_bodyFat.getText().toString()) > bf_bodyfat){
                    Toast.makeText(getActivity(), "คุณมีเปอร์เซ็นไขมันมากกว่าเปอร์เซ็นไขมันในร่างกายคุณตอนนี้", Toast.LENGTH_SHORT).show();
//                }else if (Integer.valueOf(edt_goal_bodyFat.getText().toString()) < 5){
//                    Toast.makeText(getActivity(), "ต้องมีไขมันในร่างมากกว่า 5 เปอร์เซ็น", Toast.LENGTH_SHORT).show();
                }else if ((GoalBodyfat <= bf_bodyfat) && GoalWeight < we_weight){
                      tm_id = "TM01";

                    um = healthyFoodDB.InsertUserMuscle(u_id,bf_id,fi_id,tm_id);
                    if(um){
                        Toast.makeText(getActivity(), "UserMuscle Inserted", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putDouble("weight", getArguments().getDouble("weight"));
                        bundle.putDouble("bodyfatgoal", GoalBodyfat);
                        ((QuestionMuscleActivity)getActivity()).onPlanLoseQuestionSelected(bundle);
                    }else {
                        Toast.makeText(getActivity(), "Not UserMuscle Inserted", Toast.LENGTH_SHORT).show();
                    }

                }else if ((GoalBodyfat <= bf_bodyfat) && GoalWeight > we_weight) {
                    tm_id = "TM02";
                    um = healthyFoodDB.InsertUserMuscle(u_id, bf_id, fi_id, tm_id);
                    if (um) {
                        Toast.makeText(getActivity(), "UserMuscle Inserted", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putDouble("weight", getArguments().getDouble("weight"));
                        bundle.putDouble("bodyfatgoal", GoalBodyfat);
                        ((QuestionMuscleActivity) getActivity()).onPlanGainQuestionSelected(bundle);
                    } else {
                        Toast.makeText(getActivity(), "Not UserMuscle Inserted", Toast.LENGTH_SHORT).show();
                    }
                }else if((GoalBodyfat <= bf_bodyfat) && String.valueOf( GoalWeight).equals(String.valueOf(we_weight))){
                    tm_id = "TM03";
                    p_id = "P05";
                    GBodyfat = edt_goal_bodyFat.getText().toString();
                    weight = String.valueOf( GoalWeight);
                    um = healthyFoodDB.InsertUserMuscle(u_id, bf_id, fi_id, tm_id);
                    if (um) {
                        Toast.makeText(getActivity(), "UserMuscle Inserted", Toast.LENGTH_SHORT).show();

                        userMus = healthyFoodDB.getMaxUserMuscle();
                        um_id = String.valueOf(userMus.getInt(userMus.getColumnIndex(HealthyFoodDB.UM_ID)));

                        dateBase = healthyFoodDB.getMaxGoalMuscle();
                        date = dateBase.getString(dateBase.getColumnIndex(HealthyFoodDB.GM_Date));
                        if(date == null){
                            db = healthyFoodDB.InsertGoalMuscle(u_id,um_id,p_id,weight,GBodyfat);
                            if (db) {
                                Toast.makeText(getActivity(), "Goal Inserted", Toast.LENGTH_SHORT).show();
                                ((QuestionMuscleActivity) getActivity()).onTodayMuscleClkListener();
                            } else {
                                Toast.makeText(getActivity(), "Goal not Inserted", Toast.LENGTH_SHORT).show();
                            }
                        }else if(date.equals(formattedDate)){
                            db = healthyFoodDB.UpdateGoalMuscle(u_id,um_id,p_id,weight,GBodyfat);
                            if (db) {
                                Toast.makeText(getActivity(), "Goal update", Toast.LENGTH_SHORT).show();
                                ((QuestionMuscleActivity) getActivity()).onTodayMuscleClkListener();
                            } else {
                                Toast.makeText(getActivity(), "Goal not update", Toast.LENGTH_SHORT).show();
                            }
                        }else {

                            db = healthyFoodDB.InsertGoalMuscle(u_id,um_id,p_id,weight,GBodyfat);
                            if (db) {
                                Toast.makeText(getActivity(), "Goal Inserted", Toast.LENGTH_SHORT).show();
                                ((QuestionMuscleActivity) getActivity()).onTodayMuscleClkListener();
                            } else {
                                Toast.makeText(getActivity(), "Goal not Inserted", Toast.LENGTH_SHORT).show();
                            }


                        }

                    } else {
                        Toast.makeText(getActivity(), "Not UserMuscle Inserted", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), "งง เบย", Toast.LENGTH_SHORT).show();
                }

//
//                else {
//                 //   Bundle bundle = new Bundle();
//                 //   bundle.putDouble("weight", getArguments().getInt("weight"));
//                 //   bundle.putInt("bodyfatgoal", Bodyfat);
//                    ((QuestionMuscleActivity)getActivity()).onTypeMuscleQuestionSelected(bundle);
//                }


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
        edt_goal_bodyFat = (EditText) rootView.findViewById(R.id.edt_goal_body_fat);
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
