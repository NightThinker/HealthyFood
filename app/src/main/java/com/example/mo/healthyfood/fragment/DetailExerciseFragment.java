package com.example.mo.healthyfood.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mo.healthyfood.CustomOnItemSelectedListener;
import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.Exercise.SearchExerciseFragment;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.ShowExerciseActivity;
import com.example.mo.healthyfood.activityMuscle.ShowFoodMuscleActivity;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class DetailExerciseFragment extends Fragment {


    TextView txtNameExert,txtNbCal;
    FancyButton btnSave;
    HealthyFoodDB healthyFoodDB;
    EditText edtHour,edtMinute;

    String me_id,ex_id,NameEx,tu_id,LM_ID,LN_ID,hour,minute,date;
    Cursor exert,logMus,user,logNor,we,kcal;
    Integer TimeEx,MinHr,Hour,cal,Minute,Min,time,tln_kcal,tlm_kcal,TotalKcal,Cal = 0;
    Double KcalEx,Kcal,weight;
    Boolean db,bb;

    public DetailExerciseFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static DetailExerciseFragment newInstance(Bundle args) {
        DetailExerciseFragment fragment = new DetailExerciseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);

        healthyFoodDB = new HealthyFoodDB(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise_detail, container, false);
        initInstances(rootView, savedInstanceState);




        //รับค่า me_id มา
        ex_id = getArguments().getString("ex_id");
        me_id = getArguments().getString("m_id");
        date = getArguments().getString("date");




        exert = healthyFoodDB.getShowExert(ex_id);
        NameEx = exert.getString(exert.getColumnIndex(HealthyFoodDB.EX_Name));
        TimeEx = exert.getInt(exert.getColumnIndex(HealthyFoodDB.EX_Time));
        KcalEx = exert.getDouble(exert.getColumnIndex(HealthyFoodDB.EX_Kcal));
     //   me_id = exert.getString(exert.getColumnIndex(HealthyFoodDB.ME_ID));

        Log.e("EX_ID DEF","EX_ID : " + ex_id);
        Log.e("ME_ID DEF","ME_ID : " + me_id);
        Log.e("Date DEF","Date : " + date);

        Toast.makeText(getActivity(),"Name :" + NameEx,Toast.LENGTH_SHORT).show();

        user = healthyFoodDB.getMaxTypeUser();
        tu_id = user.getString(user.getColumnIndex(HealthyFoodDB.DTU_ID));

        we = healthyFoodDB.getMaxWeight();
        weight = we.getDouble(we.getColumnIndex(HealthyFoodDB.WE_Weight));

        if (tu_id.equals("TU01")){
            logNor = healthyFoodDB.getLogNormal(date);
            LN_ID = String.valueOf(logNor.getInt(logNor.getColumnIndex(HealthyFoodDB.LN_ID)));
        }else if (tu_id.equals("TU02")){
            logMus = healthyFoodDB.getLogMuscle(date);
            LM_ID = String.valueOf(logMus.getInt(logMus.getColumnIndex(HealthyFoodDB.LM_ID)));
        }else{
            Log.e("TU_ID DEF","Null");
        }







        txtNameExert.setText(NameEx);




        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour = edtHour.getText().toString();
                minute = edtMinute.getText().toString();

                if((hour.isEmpty() && (!minute.isEmpty()))) {
                    Log.e("Check DEF","minute");
                    //ถ้าชั่วโมงไม่ใส่แต่นาทีใส่
                    if (Integer.parseInt(minute) > 60) {
                        //ถ้านาทีมากกว่า 60
                        Toast.makeText(getActivity(), "คุณกรอกเวลาเกิน 60 นาที", Toast.LENGTH_SHORT).show();
                    }else {
                        Min = Integer.parseInt(minute);
                        time = Min;
                        Kcal = 0.0175 * weight * time * KcalEx;
                        Cal = Kcal.intValue();
                        //Normal
                        if (tu_id.equals("TU01")) {
                            Log.e("Check DEF","TU01");
                            db = healthyFoodDB.InsertLogExerciseNormal(LN_ID, me_id, ex_id, String.valueOf(time), String.valueOf(Kcal));
                            if (db) {
                                Log.e("Insert DEF","Insert Log Exercise Normal");
                                kcal = healthyFoodDB.getTotalCalNormal(LN_ID,me_id);
                                if (kcal.getCount() > 0){
                                    ArrayList<Integer> TotalKcalNormal = healthyFoodDB.getKcalExertNormal(LN_ID,me_id);
                                    int KcalExert = 0;
                                    for (int i = 0 ;i < TotalKcalNormal.size();i++){
                                        KcalExert += TotalKcalNormal.get(i);
                                    }

                                    Log.e("Sum DEF","KcalExert : " + KcalEx);


                                    bb = healthyFoodDB.UpdateTotalCalNormal(LN_ID,me_id,String.valueOf(KcalExert));
                                    if(bb){
                                        Log.e("Update DEF","Update Total Cal Normal");
                                        Toast.makeText(getActivity(), "LogExNormal Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((ShowExerciseActivity) getActivity()).onMainNormalClkListener();
                                    }else {
                                        Log.e("Update","Not Update Total Cal Normal");
                                    }
                                }else {
                                    bb = healthyFoodDB.InsertTotalCalNormal(LN_ID,me_id,String.valueOf(Kcal));
                                    if(bb){
                                        Log.e("Insert DEF","Insert Total Cal Normal");
                                        Toast.makeText(getActivity(), "LogExNormal Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((ShowExerciseActivity) getActivity()).onMainNormalClkListener();
                                    }else {
                                        Log.e("Insert DEF","Not Insert Total Cal Normal");
                                    }
                                }



                            } else {
                                Log.e("Insert DEF","Not Insert Log Exercise Normal");
                            }
                        } else if (tu_id.equals("TU02")) {
                            Log.e("Check DEF","TU02");
                            //Muscle
                            db = healthyFoodDB.InsertLogExerciseMuscle(LM_ID, me_id, ex_id, String.valueOf(time), String.valueOf(Kcal));
                            if (db) {
                                Log.e("Insert DEF","Insert Log Exercise Muscle");

                                kcal = healthyFoodDB.getTotalCalMuscle(LM_ID,me_id);
                                if (kcal.getCount() > 0){
                                    ArrayList<Integer> TotalKcalMuscle = healthyFoodDB.getKcalExertMuscle(LM_ID,me_id);
                                    int KcalExert = 0;
                                    for (int i = 0 ;i < TotalKcalMuscle.size();i++){
                                        KcalExert += TotalKcalMuscle.get(i);
                                    }
                                    Log.e("Sum DEF","KcalExert : " + KcalEx);

                                    bb = healthyFoodDB.UpdateTotalCalMuscle(LM_ID,me_id,String.valueOf(KcalExert),null,null,null);
                                    if(bb){
                                        Log.e("Update DEF","Update Total Cal Muscle");
                                        Toast.makeText(getActivity(), "LogExMuscle Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((ShowExerciseActivity) getActivity()).onMainMuscleClkListener();
                                    }else {
                                        Log.e("Update DEF","Not Update Total Cal Muscle");
                                    }
                                }else {
                                    bb = healthyFoodDB.InsertTotalCalMuscle(LM_ID,me_id,String.valueOf(Kcal),null,null,null);
                                    if (bb){
                                        Log.e("Insert DEF","Insert Total Cal Muscle");
                                        Toast.makeText(getActivity(), "LogExMuscle Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((ShowExerciseActivity) getActivity()).onMainMuscleClkListener();

                                    }else {
                                        Log.e("Insert DEF","Not Insert Total Cal Muscle");
                                    }
                                }

                            } else {
                                Log.e("Insert DEF","Not Insert Log Exercise Muscle");
                            }
                        } else {
                            Toast.makeText(getActivity(), "งง เบย", Toast.LENGTH_SHORT).show();
                        }
                    }


                } else if(((!hour.isEmpty()) && (minute.isEmpty()))){
                    Log.e("Check DEF","hour");
                    //ถ้าใส่ชั่วโมงแต่นาทีไม่ใส
                    if(Integer.parseInt(hour) > 8){
                        //ถ้าชั่วโมงมากกว่า 8
                        Toast.makeText(getActivity(), "เป็นไปไม่ได้เลยที่ออกกำลังกาย 8 ชั่วโมง ", Toast.LENGTH_SHORT).show();
                    }else {
                        MinHr = Integer.parseInt(hour) * 60;
                        time = MinHr;
                        Kcal = 0.0175 * weight * time * KcalEx;
                        Cal = Kcal.intValue();
                        //Normal
                        if (tu_id.equals("TU01")) {
                            Log.e("Check DEF","TU01");
                            db = healthyFoodDB.InsertLogExerciseNormal(LN_ID, me_id, ex_id, String.valueOf(time), String.valueOf(Kcal));
                            if (db) {
                                Log.e("Insert DEF","Insert Log Exercise Normal");
                                kcal = healthyFoodDB.getTotalCalNormal(LN_ID,me_id);
                                if (kcal.getCount() > 0){

                                    ArrayList<Integer> TotalKcalNormal = healthyFoodDB.getKcalExertNormal(LN_ID,me_id);
                                    int KcalExert = 0;
                                    for (int i = 0 ;i < TotalKcalNormal.size();i++){
                                        KcalExert += TotalKcalNormal.get(i);
                                    }

                                    Log.e("Sum DEF","KcalExert : " + KcalEx);

                                    bb = healthyFoodDB.UpdateTotalCalNormal(LN_ID,me_id,String.valueOf(KcalExert));
                                    if(bb){
                                        Log.e("Update DEF","Update Total Cal Normal");
                                        Toast.makeText(getActivity(), "LogExNormal Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((ShowExerciseActivity) getActivity()).onMainNormalClkListener();
                                    }else {
                                        Log.e("Update DEF","Not Update Total Cal Normal");
                                    }
                                }else {
                                    bb = healthyFoodDB.InsertTotalCalNormal(LN_ID,me_id,String.valueOf(Kcal));
                                    if(bb){
                                        Log.e("Insert DEF","Insert Total Cal Normal");
                                        Toast.makeText(getActivity(), "LogExNormal Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((ShowExerciseActivity) getActivity()).onMainNormalClkListener();
                                    }else {
                                        Log.e("Insert DEF","Not Insert Total Cal Normal");
                                    }
                                }
                            } else {
                                Log.e("Insert DEF","Not Insert Log Exercise Normal");
                            }
                        } else if (tu_id.equals("TU02")) {
                            Log.e("Check DEF","TU02");
                            //Muscle
                            db = healthyFoodDB.InsertLogExerciseMuscle(LM_ID, me_id, ex_id, String.valueOf(time), String.valueOf(Kcal));
                            if (db) {
                                Log.e("Insert DEF","Insert Log Exercise Muscle");

                                kcal = healthyFoodDB.getTotalCalMuscle(LM_ID,me_id);
                                if (kcal.getCount() > 0){
                                    ArrayList<Integer> TotalKcalMuscle = healthyFoodDB.getKcalExertMuscle(LM_ID,me_id);
                                    int KcalExert = 0;
                                    for (int i = 0 ;i < TotalKcalMuscle.size();i++){
                                        KcalExert += TotalKcalMuscle.get(i);
                                    }
                                    Log.e("Sum DEF","KcalExert : " + KcalEx);
//                                    tlm_kcal = kcal.getInt(kcal.getColumnIndex(HealthyFoodDB.TLM_Kcal));
//
//                                    TotalKcal = Cal + tlm_kcal;
                                    bb = healthyFoodDB.UpdateTotalCalMuscle(LM_ID,me_id,String.valueOf(KcalExert),null,null,null);
                                    if(bb){
                                        Log.e("Update DEF","Update Total Cal Muscle");
                                        Toast.makeText(getActivity(), "LogExMuscle Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((ShowExerciseActivity) getActivity()).onMainMuscleClkListener();
                                    }else {
                                        Log.e("Update DEF","Not Update Total Cal Muscle");
                                    }
                                }else {
                                    bb = healthyFoodDB.InsertTotalCalMuscle(LM_ID,me_id,String.valueOf(Kcal),null,null,null);
                                    if (bb){
                                        Log.e("Insert DEF","Insert Total Cal Muscle");
                                        Toast.makeText(getActivity(), "LogExMuscle Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((ShowExerciseActivity) getActivity()).onMainMuscleClkListener();

                                    }else {
                                        Log.e("Insert DEF","Not Insert Total Cal Muscle");
                                    }
                                }
                            } else {
                                Log.e("Insert DEF","Not Insert Log Exercise Muscle");

                            }
                        } else {
                            Toast.makeText(getActivity(), "งง เบย", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
                else if(hour.isEmpty() && minute.isEmpty()){
                    Log.e("Check DEF","no");
                    //กรณ๊ไม่ใส่ทั้งคู่
                    Toast.makeText(getActivity(), "กรุณากรอกเวลาที่ใช้ในการออกกำลังกาย", Toast.LENGTH_SHORT).show();
                }else {
                    Log.e("Check DEF","hour minute");
                    //กรณ๊ที่ใส่ทั้งคู่
                    if(Integer.parseInt(hour) > 8 || Integer.parseInt(minute) > 60){
                        Toast.makeText(getActivity(), "เป็นไปไม่ได้เลยที่ออกกำลังกาย 8 ชั่วโมง และ คุณกรอกเวลาเกิน 60 นาที", Toast.LENGTH_SHORT).show();
                    }else {
                        Min = Integer.parseInt(minute);
                        MinHr = Integer.parseInt(hour) * 60;
                        time = MinHr + Min;
                        Kcal = 0.0175 * weight * time * KcalEx;
                        Cal = Kcal.intValue();
                        //Normal
                        if (tu_id.equals("TU01")) {
                            Log.e("Check DEF","TU01");
                            db = healthyFoodDB.InsertLogExerciseNormal(LN_ID, me_id, ex_id, String.valueOf(time), String.valueOf(Kcal));
                            if (db) {
                                Log.e("Insert DEF","Insert Log Exercise Normal");
                                kcal = healthyFoodDB.getTotalCalNormal(LN_ID,me_id);
                                if (kcal.getCount() > 0){
                                    ArrayList<Integer> TotalKcalNormal = healthyFoodDB.getKcalExertNormal(LN_ID,me_id);
                                    int KcalExert = 0;
                                    for (int i = 0 ;i < TotalKcalNormal.size();i++){
                                        KcalExert += TotalKcalNormal.get(i);
                                    }
                                    Log.e("Sum DEF","KcalExert : " + KcalEx);
//                                    tln_kcal = kcal.getInt(kcal.getColumnIndex(HealthyFoodDB.TLN_Kcal));
//
//                                    TotalKcal = Cal + tln_kcal;
                                    bb = healthyFoodDB.UpdateTotalCalNormal(LN_ID,me_id,String.valueOf(KcalExert));
                                    if(bb){
                                        Log.e("Update DEF","Update Total Cal Normal");
                                        Toast.makeText(getActivity(), "LogExNormal Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((ShowExerciseActivity) getActivity()).onMainNormalClkListener();
                                    }else {
                                        Log.e("Update DEF","Not Update Total Cal Normal");
                                    }
                                }else {
                                    bb = healthyFoodDB.InsertTotalCalNormal(LN_ID,me_id,String.valueOf(Kcal));
                                    if(bb){
                                        Log.e("Insert DEF","Insert Total Cal Normal");
                                        Toast.makeText(getActivity(), "LogExNormal Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((ShowExerciseActivity) getActivity()).onMainNormalClkListener();
                                    }else {
                                        Log.e("Insert DEF","Not Insert Total Cal Normal");
                                    }
                                }
                            } else {
                                Log.e("Insert DEF","Not Insert Log Exercise Normal");
                            }
                        } else if (tu_id.equals("TU02")) {
                            Log.e("Check DEF","TU02");
                            //Muscle
                            db = healthyFoodDB.InsertLogExerciseMuscle(LM_ID, me_id, ex_id, String.valueOf(time), String.valueOf(Kcal));
                            if (db) {
                                Log.e("Insert DEF","Insert Log Exercise Muscle");

                                kcal = healthyFoodDB.getTotalCalMuscle(LM_ID,me_id);
                                if (kcal.getCount() > 0){
                                    ArrayList<Integer> TotalKcalMuscle = healthyFoodDB.getKcalExertMuscle(LM_ID,me_id);
                                    int KcalExert = 0;
                                    for (int i = 0 ;i < TotalKcalMuscle.size();i++){
                                        KcalExert += TotalKcalMuscle.get(i);
                                    }
                                    Log.e("Sum DEF","KcalExert : " + KcalEx);
//                                    tlm_kcal = kcal.getInt(kcal.getColumnIndex(HealthyFoodDB.TLM_Kcal));
//
//                                   TotalKcal = Cal + tlm_kcal;
                                    bb = healthyFoodDB.UpdateTotalCalMuscle(LM_ID,me_id,String.valueOf(KcalExert),null,null,null);
                                    if(bb){
                                        Log.e("Update DEF","Update Total Cal Muscle");
                                        Toast.makeText(getActivity(), "LogExMuscle Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((ShowExerciseActivity) getActivity()).onMainMuscleClkListener();
                                    }else {
                                        Log.e("Update DEF","Not Update Total Cal Muscle");
                                    }
                                }else {
                                    bb = healthyFoodDB.InsertTotalCalMuscle(LM_ID,me_id,String.valueOf(Kcal),null,null,null);
                                    if (bb){
                                        Log.e("Insert DEF","Insert Total Cal Muscle");
                                        Toast.makeText(getActivity(), "LogExMuscle Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((ShowExerciseActivity) getActivity()).onMainMuscleClkListener();

                                    }else {
                                        Log.e("Insert DEF","Not Insert Total Cal Muscle");
                                    }
                                }
                            } else {
                                Log.e("Insert DEF","Not Insert Log Exercise Muscle");

                            }
                        } else {
                            Toast.makeText(getActivity(), "งง เบย", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


               Log.e("KcalEX DEF:" , "Kcal EX :" + Kcal);



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
        txtNameExert = (TextView) rootView.findViewById(R.id.txtNameExert);
        txtNbCal = (TextView) rootView.findViewById(R.id.txtNbCal);
        edtHour = (EditText) rootView.findViewById(R.id.edtHour);
        edtMinute = (EditText) rootView.findViewById(R.id.edtMinute);
        btnSave = (FancyButton) rootView.findViewById(R.id.btnSave);
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
