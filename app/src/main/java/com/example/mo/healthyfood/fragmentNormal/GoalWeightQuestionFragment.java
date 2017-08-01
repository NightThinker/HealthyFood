package com.example.mo.healthyfood.fragmentNormal;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityNormal.QuestionNormalActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class GoalWeightQuestionFragment extends Fragment {
//    UserDB user;

    HealthyFoodDB healthyFoodDB;
    EditText edt_goal_weight;
//    FancyButton btn_next;
    Double weight;
    String gn_weight,u_id,date;
    Cursor res,user,dateBase;
    Boolean db;
    String p_id;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());

    public GoalWeightQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static GoalWeightQuestionFragment newInstance() {
        GoalWeightQuestionFragment fragment = new GoalWeightQuestionFragment();
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

        healthyFoodDB = new HealthyFoodDB(getActivity());

//        user = new UserDB(getActivity());
//        user.getReadableDatabase();

//        Cursor res = user.getWeight();
//        res.moveToFirst();
//        weight = res.getDouble(res.getColumnIndex(UserDB.U_weight));
//        Toast.makeText(getActivity(), "น้ำหนัก" + weight, Toast.LENGTH_SHORT).show();
        healthyFoodDB.TypeGoalData();



         res = healthyFoodDB.getMaxWeight();
        weight = res.getDouble(res.getColumnIndex(HealthyFoodDB.WE_Weight));
        Toast.makeText(getActivity(), "น้ำหนัก" + weight, Toast.LENGTH_SHORT).show();

        user = healthyFoodDB.getUser();
        u_id = String.valueOf(user.getInt(user.getColumnIndex(HealthyFoodDB.U_ID)));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_normal_question2_goal, container, false);
        initInstances(rootView, savedInstanceState);


//        btn_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);

    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        edt_goal_weight = (EditText) rootView.findViewById(R.id.edt_goal_weight);
  //      btn_next = (FancyButton) rootView.findViewById(R.id.btn_next);
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
                if (edt_goal_weight.getText().toString().isEmpty()) {
//                    edt_start_weight.setError("Invalid Weight");
//                    edt_start_weight.requestFocus();
                    Toast.makeText(getActivity(), "กรุณากรอกน้ำหนัก", Toast.LENGTH_SHORT).show();
                }else if (Integer.valueOf(edt_goal_weight.getText().toString()) < 40){
                    Toast.makeText(getActivity(), "ต้องน้ำหนัก 40 เป็นต้นไป", Toast.LENGTH_SHORT).show();
                }else if (Integer.valueOf(edt_goal_weight.getText().toString()) > 200){
                    Toast.makeText(getActivity(), "คุณน้ำหนักมากเกินไป", Toast.LENGTH_SHORT).show();
                }else {

                    //ลดน้ำหนัก
                    if (Double.valueOf(edt_goal_weight.getText().toString()) < weight) {
                        Double goal = Double.parseDouble(edt_goal_weight.getText().toString());
                        Bundle bundle = new Bundle();
                        bundle.putDouble("goal", goal);
                        ((QuestionNormalActivity) getActivity()).onPlanLoseQuestionSelected(bundle);

                        //เพิ่มน้ำหนัก
                    } else if (Double.valueOf(edt_goal_weight.getText().toString()) > weight) {
                        Double goal = Double.parseDouble(edt_goal_weight.getText().toString());
                        Bundle bundle = new Bundle();
                        bundle.putDouble("goal", goal);
                        ((QuestionNormalActivity) getActivity()).onPlanGainQuestionSelected(bundle);
                    }

                    //ควบคุมน้ำหนักคงที่
                    else {
                        dateBase = healthyFoodDB.getMaxGoalNormal();
                        date = dateBase.getString(dateBase.getColumnIndex(HealthyFoodDB.GN_Date));

                        String tg_id = "TG02";
                        p_id = "P05";
                        gn_weight = edt_goal_weight.getText().toString();

                        if (date == null) {
                            db = healthyFoodDB.InsertGoalNormal(u_id, tg_id, p_id, gn_weight);
                            if (db) {
                                Toast.makeText(getActivity(), "GoalNormal Insert", Toast.LENGTH_SHORT).show();
                                ((QuestionNormalActivity) getActivity()).onTodayNormalClkListener();
                            } else
                                Toast.makeText(getActivity(), "GoalNormal Insert", Toast.LENGTH_SHORT).show();
                        } else if (date.equals(formattedDate)) {
                            db = healthyFoodDB.UpdateGoalNormal(u_id, tg_id, p_id, gn_weight);
                            if (db) {
                                Toast.makeText(getActivity(), "GoalNormal Update", Toast.LENGTH_SHORT).show();
                                ((QuestionNormalActivity) getActivity()).onTodayNormalClkListener();
                            } else
                                Toast.makeText(getActivity(), "Not GoalNormal Update", Toast.LENGTH_SHORT).show();
                        } else {
                            db = healthyFoodDB.InsertGoalNormal(u_id, tg_id, p_id, gn_weight);
                            if (db) {
                                Toast.makeText(getActivity(), "GoalNormal Insert", Toast.LENGTH_SHORT).show();
                                ((QuestionNormalActivity) getActivity()).onTodayNormalClkListener();
                            } else
                                Toast.makeText(getActivity(), "GoalNormal Insert", Toast.LENGTH_SHORT).show();

                        }
                    }
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
