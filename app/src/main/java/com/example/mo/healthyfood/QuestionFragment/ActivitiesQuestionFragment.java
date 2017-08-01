package com.example.mo.healthyfood.QuestionFragment;

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
import com.example.mo.healthyfood.activity.QuestionSelectActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class ActivitiesQuestionFragment extends Fragment {

    HealthyFoodDB healthyFoodDB;
    RadioGroup rg_exercise;
  //  FancyButton btn_next;
    Bundle bundle;
    int a;
    String medical, gender, age, weight, height, exercise;
    boolean db,mm,dd;
    Double activitie;
    Cursor user;
    Cursor dateBase,Activities,TypeUser;
    String date,ac_id,tu_id,TU_ID;


    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());


    public ActivitiesQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ActivitiesQuestionFragment newInstance() {
        ActivitiesQuestionFragment fragment = new ActivitiesQuestionFragment();
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

        getActivity().setTitle("Activities");


        healthyFoodDB = new HealthyFoodDB(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question_activities, container, false);
        initInstances(rootView, savedInstanceState);

       // user = healthyFoodDB.getUser();



        rg_exercise.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_ex_sedentary:
                        activitie = 1.2;

                        a = 1;
                        break;
                    case R.id.rb_ex_light:
                        activitie = 1.375;

                        a = 2;
                        break;
                    case R.id.rb_ex_moderate:
                        activitie = 1.55;

                        a = 3;
                        break;
                    case R.id.rb_ex_hard:
                        activitie = 1.7;

                        a = 4;
                        break;
                    case R.id.rb_ex_veryhard:
                        activitie = 1.9;

                        a = 5;
                        break;
                }
            }
        });



        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        rg_exercise = (RadioGroup) rootView.findViewById(R.id.rg_exercise);
     //   btn_next = (FancyButton) rootView.findViewById(R.id.btn_next);

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
                dateBase = healthyFoodDB.getMaxActivities();
                date = dateBase.getString(dateBase.getColumnIndex(HealthyFoodDB.AC_Date));
                exercise =String.valueOf(activitie);

                if (a == 1 || a == 2) {
                    tu_id = "TU01";
                    if(date == null){
                        mm = healthyFoodDB.InsertActivities(exercise);
                        if(mm){
                            Toast.makeText(getActivity(), "Activities Inserted", Toast.LENGTH_SHORT).show();

                            dd = healthyFoodDB.InsertTypeUser(tu_id);
                            if(dd){
                                Toast.makeText(getActivity(), "TypeUser Inserted", Toast.LENGTH_SHORT).show();
                                Activities = healthyFoodDB.getMaxActivities();
                                ac_id = Activities.getString(Activities.getColumnIndex(HealthyFoodDB.AC_ID));

                                TypeUser = healthyFoodDB.getMaxTypeUser();
                                TU_ID = String.valueOf(TypeUser.getInt(TypeUser.getColumnIndex(HealthyFoodDB.TU_ID)));

                                db = healthyFoodDB.updateUser(ac_id,TU_ID);
                                if (db  ) {
                                    Toast.makeText(getActivity(), "AC_ID Inserted", Toast.LENGTH_SHORT).show();
                                    ((QuestionSelectActivity) getActivity()).onGoalFragmentClkListener();
                                } else {
                                    Toast.makeText(getActivity(), "AC_ID not Inserted", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getActivity(), "Not TypeUser Inserted", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(getActivity(), "Not Activities Inserted", Toast.LENGTH_SHORT).show();
                        }

                    }else if(date.equals(formattedDate)){
                        mm = healthyFoodDB.UpdateActivities(exercise);
                        if(mm ){
                            Toast.makeText(getActivity(), "Activities Update", Toast.LENGTH_SHORT).show();
                            dd = healthyFoodDB.UpdateTypeUser(tu_id);
                            if(dd){
                                Toast.makeText(getActivity(), "TypeUser Update", Toast.LENGTH_SHORT).show();
                                Activities = healthyFoodDB.getMaxActivities();
                                ac_id = Activities.getString(Activities.getColumnIndex(HealthyFoodDB.AC_ID));

                                TypeUser = healthyFoodDB.getMaxTypeUser();
                                TU_ID = String.valueOf(TypeUser.getInt(TypeUser.getColumnIndex(HealthyFoodDB.TU_ID)));

                                db = healthyFoodDB.updateUser(ac_id,TU_ID);
                                if (db ) {
                                    Toast.makeText(getActivity(), "AC_ID Update", Toast.LENGTH_SHORT).show();
                                    ((QuestionSelectActivity) getActivity()).onGoalFragmentClkListener();
                                } else {
                                    Toast.makeText(getActivity(), "AC_ID not Update", Toast.LENGTH_SHORT).show();
                                }

                            }else {
                                Toast.makeText(getActivity(), "Not TypeUser Inserted", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(getActivity(), "Activities Not Update", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        mm = healthyFoodDB.InsertActivities(exercise);
                        if(mm){
                            Toast.makeText(getActivity(), "Activities Inserted", Toast.LENGTH_SHORT).show();
                            dd = healthyFoodDB.InsertTypeUser(tu_id);
                            if(dd){
                                Toast.makeText(getActivity(), "TypeUser Inserted", Toast.LENGTH_SHORT).show();
                                Activities = healthyFoodDB.getMaxActivities();
                                ac_id = Activities.getString(Activities.getColumnIndex(HealthyFoodDB.AC_ID));

                                TypeUser = healthyFoodDB.getMaxTypeUser();
                                TU_ID = String.valueOf(TypeUser.getInt(TypeUser.getColumnIndex(HealthyFoodDB.TU_ID)));

                                db = healthyFoodDB.updateUser(ac_id,TU_ID);
                                if (db  ) {
                                    Toast.makeText(getActivity(), "AC_ID Inserted", Toast.LENGTH_SHORT).show();
                                    ((QuestionSelectActivity) getActivity()).onGoalFragmentClkListener();
                                } else {
                                    Toast.makeText(getActivity(), "AC_ID not Inserted", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getActivity(), "Not TypeUser Inserted", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(getActivity(), "Not Activities Inserted", Toast.LENGTH_SHORT).show();
                        }

                    }

                } else if (a == 3) {
                    if(date == null){
                        mm = healthyFoodDB.InsertActivities(exercise);
                        if(mm) {
                            Toast.makeText(getActivity(), "Activities Inserted", Toast.LENGTH_SHORT).show();
                            ((QuestionSelectActivity) getActivity()).onSelectedQuestionSelected();
                        }else {
                            Toast.makeText(getActivity(), "Activities Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    } else if (date.equals(formattedDate)) {
                        mm = healthyFoodDB.UpdateActivities(exercise);
                        if(mm){
                            Toast.makeText(getActivity(), "Activities Update", Toast.LENGTH_SHORT).show();
                            ((QuestionSelectActivity) getActivity()).onSelectedQuestionSelected();
                        }else {
                            Toast.makeText(getActivity(), "Activities Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        mm = healthyFoodDB.InsertActivities(exercise);
                        if(mm) {
                            Toast.makeText(getActivity(), "Activities Inserted", Toast.LENGTH_SHORT).show();
                            ((QuestionSelectActivity) getActivity()).onSelectedQuestionSelected();
                        }else {
                            Toast.makeText(getActivity(), "Activities Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (a == 4 || a == 5){
                    tu_id = "TU02";
                    if(date == null){
                        mm = healthyFoodDB.InsertActivities(exercise);
                        if(mm){
                            Toast.makeText(getActivity(), "Activities Inserted", Toast.LENGTH_SHORT).show();

                            dd = healthyFoodDB.InsertTypeUser(tu_id);
                            if(dd){
                                Toast.makeText(getActivity(), "TypeUser Inserted", Toast.LENGTH_SHORT).show();
                                Activities = healthyFoodDB.getMaxActivities();
                                ac_id = Activities.getString(Activities.getColumnIndex(HealthyFoodDB.AC_ID));

                                TypeUser = healthyFoodDB.getMaxTypeUser();
                                TU_ID = String.valueOf(TypeUser.getInt(TypeUser.getColumnIndex(HealthyFoodDB.TU_ID)));

                                db = healthyFoodDB.updateUser(ac_id,TU_ID);
                                if (db  ) {
                                    Toast.makeText(getActivity(), "AC_ID Inserted", Toast.LENGTH_SHORT).show();
                                    ((QuestionSelectActivity) getActivity()).onMuscleFragmentClkListener();
                                } else {
                                    Toast.makeText(getActivity(), "AC_ID not Inserted", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getActivity(), "TypeUser Not Inserted", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(getActivity(), "Activities Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }else if(date.equals(formattedDate)){
                        mm = healthyFoodDB.UpdateActivities(exercise);
                        if(mm){
                            Toast.makeText(getActivity(), "Activities Update", Toast.LENGTH_SHORT).show();
                            dd = healthyFoodDB.UpdateTypeUser(tu_id);
                            if(dd){
                                Toast.makeText(getActivity(), "TypeUser Update", Toast.LENGTH_SHORT).show();
                                TypeUser = healthyFoodDB.getMaxTypeUser();
                                TU_ID = String.valueOf(TypeUser.getInt(TypeUser.getColumnIndex(HealthyFoodDB.TU_ID)));

                                Activities = healthyFoodDB.getMaxActivities();
                                ac_id = Activities.getString(Activities.getColumnIndex(HealthyFoodDB.AC_ID));

                                db = healthyFoodDB.updateUser(ac_id,TU_ID);
                                if (db ) {
                                    Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_SHORT).show();
                                    ((QuestionSelectActivity) getActivity()).onMuscleFragmentClkListener();
                                } else {
                                    Toast.makeText(getActivity(), "Data not Inserted", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getActivity(), "TypeUser Not Update", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(getActivity(), "Activities Not Update", Toast.LENGTH_SHORT).show();
                        }

                    }else {

                        mm = healthyFoodDB.InsertActivities(exercise);
                        if(mm){
                            Toast.makeText(getActivity(), "Activities Inserted", Toast.LENGTH_SHORT).show();
                            dd = healthyFoodDB.InsertTypeUser(tu_id);
                            if(dd){
                                Toast.makeText(getActivity(), "TypeUser Inserted", Toast.LENGTH_SHORT).show();
                                Activities = healthyFoodDB.getMaxActivities();
                                ac_id = Activities.getString(Activities.getColumnIndex(HealthyFoodDB.AC_ID));

                                TypeUser = healthyFoodDB.getMaxTypeUser();
                                TU_ID = String.valueOf(TypeUser.getInt(TypeUser.getColumnIndex(HealthyFoodDB.TU_ID)));

                                db = healthyFoodDB.updateUser(ac_id,TU_ID);
                                if (db  ) {
                                    Toast.makeText(getActivity(), "AC_ID Inserted", Toast.LENGTH_SHORT).show();
                                    ((QuestionSelectActivity) getActivity()).onMuscleFragmentClkListener();
                                } else {
                                    Toast.makeText(getActivity(), "AC_ID not Inserted", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getActivity(), "TypeUser Not Inserted", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(getActivity(), "Activities Not Inserted", Toast.LENGTH_SHORT).show();
                        }

                    }


                }else
                    Toast.makeText(getActivity(), "กรุณาเลือกระดับกิจกรรม", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
