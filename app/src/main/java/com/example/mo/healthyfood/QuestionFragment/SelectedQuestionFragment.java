package com.example.mo.healthyfood.QuestionFragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
public class SelectedQuestionFragment extends Fragment {
    HealthyFoodDB healthyFoodDB;
    String medical, gender, age, weight, height, exercise;
    RadioGroup rg_selected;
//    FancyButton btn_next;
    boolean db,mm,dd;
    int a;
    Cursor dateBase,Activities,TypeUser;
    String date,ac_id,tu_id,TU_ID;


    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());


    public SelectedQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static SelectedQuestionFragment newInstance() {
        SelectedQuestionFragment fragment = new SelectedQuestionFragment();
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
        getActivity().setTitle("Select");

        healthyFoodDB = new HealthyFoodDB(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question_selected, container, false);
        initInstances(rootView, savedInstanceState);

        Activities = healthyFoodDB.getMaxActivities();
        ac_id = Activities.getString(Activities.getColumnIndex(HealthyFoodDB.AC_ID));

        rg_selected.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_normal :
                        a=1;
                        break;
                    case R.id.rb_muscle :
                        a=2;
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

//        btn_next = (FancyButton) rootView.findViewById(R.id.btn_next);
        rg_selected = (RadioGroup) rootView.findViewById(R.id.rg_selected);
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

//                TypeUser = healthyFoodDB.getMaxTypeUser();
//                date = TypeUser.getString(TypeUser.getColumnIndex(HealthyFoodDB.TD_Date));


                if(a==1){
                    tu_id = "TU01";

                    if(date == null){
                        dd = healthyFoodDB.InsertTypeUser(tu_id);
                        if(dd){
                            Toast.makeText(getActivity(), "TypeUser Inserted", Toast.LENGTH_SHORT).show();
                            TypeUser = healthyFoodDB.getMaxTypeUser();
                            TU_ID = String.valueOf(TypeUser.getInt(TypeUser.getColumnIndex(HealthyFoodDB.TU_ID)));

                            Log.e("TU","TU " + TU_ID);

                            db = healthyFoodDB.updateUser(ac_id,TU_ID);
                            if (db ) {
                                Toast.makeText(getActivity(), "AC_ID Inserted", Toast.LENGTH_SHORT).show();
                                ((QuestionSelectActivity) getActivity()).onGoalFragmentClkListener();
                            } else {
                                Toast.makeText(getActivity(), "AC_ID not Inserted", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getActivity(), "TypeUser Not Inserted", Toast.LENGTH_SHORT).show();
                        }


                    } else if(date.equals(formattedDate)){
                        dd = healthyFoodDB.InsertTypeUser(tu_id);

//                        dd = healthyFoodDB.UpdateTypeUser(tu_id);
                        if(dd){
                            Toast.makeText(getActivity(), "TypeUser Update", Toast.LENGTH_SHORT).show();
                            TypeUser = healthyFoodDB.getMaxTypeUser();
                            TU_ID = String.valueOf(TypeUser.getInt(TypeUser.getColumnIndex(HealthyFoodDB.TU_ID)));
                            Log.e("TU","TU " + TU_ID);
                            db = healthyFoodDB.updateUser(ac_id,TU_ID);
                            if (db ) {
                                Toast.makeText(getActivity(), "AC_ID Inserted", Toast.LENGTH_SHORT).show();
                                ((QuestionSelectActivity) getActivity()).onGoalFragmentClkListener();
                            } else {
                                Toast.makeText(getActivity(), "AC_ID not Inserted", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getActivity(), "TypeUser Not Update", Toast.LENGTH_SHORT).show();
                        }


                    }else {

                        dd = healthyFoodDB.InsertTypeUser(tu_id);
                        if(dd){
                            Toast.makeText(getActivity(), "TypeUser Inserted", Toast.LENGTH_SHORT).show();
                            TypeUser = healthyFoodDB.getMaxTypeUser();
                            TU_ID = String.valueOf(TypeUser.getInt(TypeUser.getColumnIndex(HealthyFoodDB.TU_ID)));
                            Log.e("TU","TU " + TU_ID);
                            db = healthyFoodDB.updateUser(ac_id,TU_ID);
                            if (db ) {
                                Toast.makeText(getActivity(), "AC_ID Inserted", Toast.LENGTH_SHORT).show();
                                ((QuestionSelectActivity) getActivity()).onGoalFragmentClkListener();
                            } else {
                                Toast.makeText(getActivity(), "AC_ID not Inserted", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(getActivity(), "TypeUser Not Inserted", Toast.LENGTH_SHORT).show();
                        }


                    }

                }
                else if(a == 2) {
                    tu_id = "TU02";

                    if(date == null){

                        dd = healthyFoodDB.InsertTypeUser(tu_id);
                        if(dd){
                            Toast.makeText(getActivity(), "TypeUser Inserted", Toast.LENGTH_SHORT).show();
                            TypeUser = healthyFoodDB.getMaxTypeUser();
                            TU_ID = String.valueOf(TypeUser.getInt(TypeUser.getColumnIndex(HealthyFoodDB.TU_ID)));
                            Log.e("TU","TU " + TU_ID);
                            db = healthyFoodDB.updateUser(ac_id,TU_ID);
                            if (db) {
                                Toast.makeText(getActivity(), "AC_ID Inserted", Toast.LENGTH_SHORT).show();
                                ((QuestionSelectActivity)getActivity()).onMuscleFragmentClkListener();
                            } else {
                                Toast.makeText(getActivity(), "AC_ID not Inserted", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getActivity(), "TypeUser Not Inserted", Toast.LENGTH_SHORT).show();
                        }




                    }else if(date.equals(formattedDate)){
                        dd = healthyFoodDB.InsertTypeUser(tu_id);
//                        dd = healthyFoodDB.UpdateTypeUser(tu_id);
                        if(dd){
                            Toast.makeText(getActivity(), "TypeUser Update", Toast.LENGTH_SHORT).show();
                            TypeUser = healthyFoodDB.getMaxTypeUser();
                            TU_ID = String.valueOf(TypeUser.getInt(TypeUser.getColumnIndex(HealthyFoodDB.TU_ID)));
                            Log.e("TU","TU " + TU_ID);
                            db = healthyFoodDB.updateUser(ac_id,TU_ID);
                            if (db  ) {
                                Toast.makeText(getActivity(), "AC_ID Inserted", Toast.LENGTH_SHORT).show();
                                ((QuestionSelectActivity)getActivity()).onMuscleFragmentClkListener();
                            } else {
                                Toast.makeText(getActivity(), "AC_ID not Inserted", Toast.LENGTH_SHORT).show();
                            }

                        }else {
                            Toast.makeText(getActivity(), "TypeUser Not Update", Toast.LENGTH_SHORT).show();
                        }



                    }else {
                        dd = healthyFoodDB.InsertTypeUser(tu_id);
                        if(dd){
                            Toast.makeText(getActivity(), "TypeUser Inserted", Toast.LENGTH_SHORT).show();
                            TypeUser = healthyFoodDB.getMaxTypeUser();
                            TU_ID = String.valueOf(TypeUser.getInt(TypeUser.getColumnIndex(HealthyFoodDB.TU_ID)));
                            Log.e("TU","TU " + TU_ID);
                            db = healthyFoodDB.updateUser(ac_id,TU_ID);
                            if (db) {
                                Toast.makeText(getActivity(), "AC_ID Inserted", Toast.LENGTH_SHORT).show();
                                ((QuestionSelectActivity)getActivity()).onMuscleFragmentClkListener();
                            } else {
                                Toast.makeText(getActivity(), "AC_ID not Inserted", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getActivity(), "TypeUser Not Inserted", Toast.LENGTH_SHORT).show();
                        }



                    }


                }else
                    Toast.makeText(getActivity(), "กรุณาเลือกประเภทที่ต้องการ", Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
