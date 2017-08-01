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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.EditShowExerciseActivity;
import com.example.mo.healthyfood.activity.ShowExerciseActivity;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class DetailEditExerciseFragment extends Fragment {


    TextView txtNameExert,txtNbCal;
    FancyButton btnEdit,btnDel;
    HealthyFoodDB healthyFoodDB;
    EditText edtHour,edtMinute;

    String me_id,ex_id,NameEx,tu_id,hour,minute,date,ln_id,lm_id,lem_id,len_id;
    Cursor exert,logMus,user,logNor,we,kcal,logExert;
    Integer TimeEx,MinHr,Hour,cal,Minute,Min,time,tln_kcal,tlm_kcal,TotalKcal,Cal = 0;
    Integer len_time,len_kcal,lem_time,lem_kcal,min,hr;
    Double KcalEx,Kcal,weight,Hr,Minu;
    Boolean db,bb,del;


    public DetailEditExerciseFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static DetailEditExerciseFragment newInstance(Bundle args) {
        DetailEditExerciseFragment fragment = new DetailEditExerciseFragment();
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise_detail_edit, container, false);
        initInstances(rootView, savedInstanceState);




        //รับค่า me_id มา
        ex_id = getArguments().getString("ex_id");
        me_id = getArguments().getString("m_id");
        date = getArguments().getString("date");
        lm_id = getArguments().getString("lm_id");
        ln_id = getArguments().getString("ln_id");
        lem_id = getArguments().getString("lem_id");
        len_id = getArguments().getString("len_id");



        Log.e("DEE Values","date : " + getArguments().getString("date"));
        Log.e("DEE Values","ex_id : " + getArguments().getString("ex_id"));
        Log.e("DEE Values","m_id : " + getArguments().getString("m_id"));

        Log.e("DEE Values","lem_id : " + getArguments().getString("lem_id"));
        Log.e("DEE Values","len_id : " + getArguments().getString("len_id"));

        Log.e("DEE Values","lm_id : " + getArguments().getString("lm_id"));
        Log.e("DEE Values","ln_id : " + getArguments().getString("ln_id"));


        exert = healthyFoodDB.getShowExert(ex_id);
        NameEx = exert.getString(exert.getColumnIndex(HealthyFoodDB.EX_Name));
        TimeEx = exert.getInt(exert.getColumnIndex(HealthyFoodDB.EX_Time));
        KcalEx = exert.getDouble(exert.getColumnIndex(HealthyFoodDB.EX_Kcal));




        Toast.makeText(getActivity(),"Name :" + NameEx,Toast.LENGTH_SHORT).show();

        user = healthyFoodDB.getMaxTypeUser();
        tu_id = user.getString(user.getColumnIndex(HealthyFoodDB.DTU_ID));
        if (tu_id.equals("TU01")){
            logExert = healthyFoodDB.getLogExertNormal(len_id,ln_id,me_id,ex_id);
            len_time = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.LEN_Time));
        //    len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.LEN_Kcal));

            if (len_time >= 60){
                min = 0;
                hr = 0;
                Hr = len_time / 60.0 ;
                hr = Hr.intValue();
                Minu =  (Hr - hr) *60.0;
                min = Minu.intValue();
                edtHour.setText(String.valueOf(hr));
                edtMinute.setText(String.valueOf(min));

            }else {
                min = len_time;
                edtHour.setText("0");
                edtMinute.setText(String.valueOf(min));
            }

        }else {
            logExert = healthyFoodDB.getLogExertMuscle(lem_id,lm_id,me_id,ex_id);
            lem_time = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.LEM_Time));
       //     lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.LEM_Kcal));

            if (lem_time >= 60){
                min = 0;
                hr = 0;
                Hr = lem_time / 60.0 ;
                hr = Hr.intValue();
                Minu =  (Hr - hr) *60.0;
                min = Minu.intValue();
                edtHour.setText(String.valueOf(hr));
                edtMinute.setText(String.valueOf(min));

            }else {
                min = lem_time;
                edtHour.setText("0");
                edtMinute.setText(String.valueOf(min));
            }
        }

        we = healthyFoodDB.getMaxWeight();
        weight = we.getDouble(we.getColumnIndex(HealthyFoodDB.WE_Weight));



        txtNameExert.setText(NameEx);




        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hour = edtHour.getText().toString();
                minute = edtMinute.getText().toString();

                if((hour.isEmpty() && (!minute.isEmpty()))) {
                    //ถ้าชั่วโมงไม่ใส่แต่นาทีใส่
                    Log.e("DEE Check","minute");
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
                            Log.e("DEE Check","TU01");
                            db = healthyFoodDB.UpdateLogExertNormal(len_id,ln_id, me_id, ex_id, String.valueOf(time), String.valueOf(Kcal));
                            if (db) {
                                Log.e("DEE Update","Update Log Exercise Normal");
                                kcal = healthyFoodDB.getTotalCalNormal(ln_id,me_id);
                                if (kcal.getCount() > 0){
                                    ArrayList<Integer> TotalKcalNormal = healthyFoodDB.getKcalExertNormal(ln_id,me_id);
                                    int KcalExert = 0;
                                    for (int i = 0 ;i < TotalKcalNormal.size();i++){
                                        KcalExert += TotalKcalNormal.get(i);
                                    }
//                                    tln_kcal = kcal.getInt(kcal.getColumnIndex(HealthyFoodDB.TLN_Kcal));
//
//                                    TotalKcal = Cal + tln_kcal;
                                    Log.e("DEE KcalExert","KcalExert :" + KcalExert);

                                    bb = healthyFoodDB.UpdateTotalCalNormal(ln_id,me_id,String.valueOf(KcalExert));
                                    if(bb){
                                        Log.e("DEE Update","Update Total Cal Normal");
                                        Toast.makeText(getActivity(), "LogExNormal Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((EditShowExerciseActivity) getActivity()).onMainNormalClkListener();
                                    }else {
                                        Log.e("DEE Update","Not Update Total Cal Normal");
                                    }
                                }else {
                                    bb = healthyFoodDB.InsertTotalCalNormal(ln_id,me_id,String.valueOf(Kcal));
                                    if(bb){
                                        Log.e("DEE Insert","Insert Total Cal Normal");
                                        Toast.makeText(getActivity(), "LogExNormal Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((EditShowExerciseActivity) getActivity()).onMainNormalClkListener();
                                    }else {
                                        Log.e("DEE Insert","Not Insert Total Cal Normal");
                                    }
                                }



                            } else {
                                Log.e("DEE Update","Not Update Log Exercise Normal");
                            }
                        } else if (tu_id.equals("TU02")) {
                            //Muscle
                            Log.e("DEE Check","TU02");
                            db = healthyFoodDB.UpdateLogExertMuscle(lem_id,lm_id, me_id, ex_id, String.valueOf(time), String.valueOf(Kcal));
                            if (db) {
                                Log.e("DEE Update","Update Log Exercise Muscle");

                                kcal = healthyFoodDB.getTotalCalMuscle(lm_id,me_id);
                                if (kcal.getCount() > 0){
                                    ArrayList<Integer> TotalKcalMuscle = healthyFoodDB.getKcalExertMuscle(lm_id,me_id);
                                    int KcalExert = 0;
                                    for (int i = 0 ;i < TotalKcalMuscle.size();i++){
                                        KcalExert += TotalKcalMuscle.get(i);
                                    }
//                                    tlm_kcal = kcal.getInt(kcal.getColumnIndex(HealthyFoodDB.TLM_Kcal));
//
//                                    TotalKcal = Cal + tlm_kcal;
                                    bb = healthyFoodDB.UpdateTotalCalMuscle(lm_id,me_id,String.valueOf(KcalExert),null,null,null);
                                    if(bb){
                                        Log.e("DEE Update","Update Total Cal Muscle");
                                        Toast.makeText(getActivity(), "LogExMuscle Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((EditShowExerciseActivity) getActivity()).onMainMuscleClkListener();
                                    }else {
                                        Log.e("DEE Update","Not Update Total Cal Muscle");
                                    }
                                }else {
                                    bb = healthyFoodDB.InsertTotalCalMuscle(lm_id,me_id,String.valueOf(Kcal),null,null,null);
                                    if (bb){
                                        Log.e("DEE Insert","Insert Total Cal Muscle");
                                        Toast.makeText(getActivity(), "LogExMuscle Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((EditShowExerciseActivity) getActivity()).onMainMuscleClkListener();

                                    }else {
                                        Log.e("DEE Insert","Not Insert Total Cal Muscle");
                                    }
                                }

                            } else {
                                Log.e("DEE Update","Not Update Log Exercise Muscle");
                            }
                        } else {
                            Toast.makeText(getActivity(), "งง เบย", Toast.LENGTH_SHORT).show();
                        }
                    }


                } else if(((!hour.isEmpty()) && (minute.isEmpty()))){
                    //ถ้าใส่ชั่วโมงแต่นาทีไม่ใส
                    Log.e("DEE Check","hour");
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
                            Log.e("DEE Check","TU01");
                            db = healthyFoodDB.UpdateLogExertNormal(len_id,ln_id, me_id, ex_id, String.valueOf(time), String.valueOf(Kcal));
                            if (db) {
                                Log.e("DEE Update","Update Log Exercise Normal");
                                kcal = healthyFoodDB.getTotalCalNormal(ln_id,me_id);
                                if (kcal.getCount() > 0){

                                    ArrayList<Integer> TotalKcalNormal = healthyFoodDB.getKcalExertNormal(ln_id,me_id);
                                    int KcalExert = 0;
                                    for (int i = 0 ;i < TotalKcalNormal.size();i++){
                                        KcalExert += TotalKcalNormal.get(i);
                                    }
//                                    tln_kcal = kcal.getInt(kcal.getColumnIndex(HealthyFoodDB.TLN_Kcal));
//
//                                    TotalKcal = Cal + tln_kcal;
                                    bb = healthyFoodDB.UpdateTotalCalNormal(ln_id,me_id,String.valueOf(KcalExert));
                                    if(bb){
                                        Log.e("DEE Update","Update Total Cal Normal");
                                        Toast.makeText(getActivity(), "LogExNormal Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((EditShowExerciseActivity) getActivity()).onMainNormalClkListener();
                                    }else {
                                        Log.e("DEE Update","Not Update Total Cal Normal");
                                    }
                                }else {
                                    bb = healthyFoodDB.InsertTotalCalNormal(ln_id,me_id,String.valueOf(Kcal));
                                    if(bb){
                                        Log.e("DEE Insert","Insert Total Cal Normal");
                                        Toast.makeText(getActivity(), "LogExNormal Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((EditShowExerciseActivity) getActivity()).onMainNormalClkListener();
                                    }else {
                                        Log.e("DEE Insert","Not Insert Total Cal Normal");
                                    }
                                }
                            } else {
                                Log.e("DEE Update","Not Update Log Exercise Normal");
                            }
                        } else if (tu_id.equals("TU02")) {
                            //Muscle
                            Log.e("DEE Check","TU02");
                            db = healthyFoodDB.UpdateLogExertMuscle(lem_id,lm_id, me_id, ex_id, String.valueOf(time), String.valueOf(Kcal));
                            if (db) {
                                Log.e("DEE Update","Update Log Exercise Muscle");

                                kcal = healthyFoodDB.getTotalCalMuscle(lm_id,me_id);
                                if (kcal.getCount() > 0){
                                    ArrayList<Integer> TotalKcalMuscle = healthyFoodDB.getKcalExertMuscle(lm_id,me_id);
                                    int KcalExert = 0;
                                    for (int i = 0 ;i < TotalKcalMuscle.size();i++){
                                        KcalExert += TotalKcalMuscle.get(i);
                                    }
//                                    tlm_kcal = kcal.getInt(kcal.getColumnIndex(HealthyFoodDB.TLM_Kcal));
//
//                                    TotalKcal = Cal + tlm_kcal;
                                    bb = healthyFoodDB.UpdateTotalCalMuscle(lm_id,me_id,String.valueOf(KcalExert),null,null,null);
                                    if(bb){
                                        Log.e("DEE Update","Update Total Cal Muscle");
                                        Toast.makeText(getActivity(), "LogExMuscle Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((EditShowExerciseActivity) getActivity()).onMainMuscleClkListener();
                                    }else {
                                        Log.e("DEE Update","Not Update Total Cal Muscle");
                                    }
                                }else {
                                    bb = healthyFoodDB.InsertTotalCalMuscle(lm_id,me_id,String.valueOf(Kcal),null,null,null);
                                    if (bb){
                                        Log.e("DEE Insert","Insert Total Cal Muscle");
                                        Toast.makeText(getActivity(), "LogExMuscle Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((EditShowExerciseActivity) getActivity()).onMainMuscleClkListener();

                                    }else {
                                        Log.e("DEE Insert","Not Insert Total Cal Muscle");
                                    }
                                }
                            } else {
                                Log.e("DEE Update","Not Update Log Exercise Muscle");

                            }
                        } else {
                            Toast.makeText(getActivity(), "งง เบย", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
                else if(hour.isEmpty() && minute.isEmpty()){
                    //กรณ๊ไม่ใส่ทั้งคู่
                    Log.e("DEE Check","Empty");
                    Toast.makeText(getActivity(), "กรุณากรอกเวลาที่ใช้ในการออกกำลังกาย", Toast.LENGTH_SHORT).show();
                }else {
                    //กรณ๊ที่ใส่ทั้งคู่
                    Log.e("DEE Check","hour minute");
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
                            Log.e("DEE Check","TU01");
                            db = healthyFoodDB.UpdateLogExertNormal(len_id,ln_id, me_id, ex_id, String.valueOf(time), String.valueOf(Kcal));
                            if (db) {
                                Log.e("DEE Update","Update Log Exercise Normal");
                                kcal = healthyFoodDB.getTotalCalNormal(ln_id,me_id);
                                if (kcal.getCount() > 0){
                                    ArrayList<Integer> TotalKcalNormal = healthyFoodDB.getKcalExertNormal(ln_id,me_id);
                                    int KcalExert = 0;
                                    for (int i = 0 ;i < TotalKcalNormal.size();i++){
                                        KcalExert += TotalKcalNormal.get(i);
                                    }
//                                    tln_kcal = kcal.getInt(kcal.getColumnIndex(HealthyFoodDB.TLN_Kcal));
//
//                                    TotalKcal = Cal + tln_kcal;
                                    bb = healthyFoodDB.UpdateTotalCalNormal(ln_id,me_id,String.valueOf(KcalExert));
                                    if(bb){
                                        Log.e("DEE Update","Update Total Cal Normal");
                                        Toast.makeText(getActivity(), "LogExNormal Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((EditShowExerciseActivity) getActivity()).onMainNormalClkListener();
                                    }else {
                                        Log.e("DEE Update","Not Update Total Cal Normal");
                                    }
                                }else {
                                    bb = healthyFoodDB.InsertTotalCalNormal(ln_id,me_id,String.valueOf(Kcal));
                                    if(bb){
                                        Log.e("DEE Insert","Insert Total Cal Normal");
                                        Toast.makeText(getActivity(), "LogExNormal Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((EditShowExerciseActivity) getActivity()).onMainNormalClkListener();
                                    }else {
                                        Log.e("DEE Insert","Not Insert Total Cal Normal");
                                    }
                                }
                            } else {
                                Log.e("DEE Update","Not Update Log Exercise Normal");
                            }
                        } else if (tu_id.equals("TU02")) {
                            Log.e("DEE Check","TU02");
                            //Muscle
                            db = healthyFoodDB.UpdateLogExertMuscle(lem_id,lm_id, me_id, ex_id, String.valueOf(time), String.valueOf(Kcal));
                            if (db) {
                                Log.e("DEE Update","Update Log Exercise Muscle");

                                kcal = healthyFoodDB.getTotalCalMuscle(lm_id,me_id);
                                if (kcal.getCount() > 0){
                                    ArrayList<Integer> TotalKcalMuscle = healthyFoodDB.getKcalExertMuscle(lm_id,me_id);
                                    int KcalExert = 0;
                                    for (int i = 0 ;i < TotalKcalMuscle.size();i++){
                                        KcalExert += TotalKcalMuscle.get(i);
                                    }
//                                    tlm_kcal = kcal.getInt(kcal.getColumnIndex(HealthyFoodDB.TLM_Kcal));
//
//                                   TotalKcal = Cal + tlm_kcal;
                                    bb = healthyFoodDB.UpdateTotalCalMuscle(lm_id,me_id,String.valueOf(KcalExert),null,null,null);
                                    if(bb){
                                        Log.e("DEE Update","Update Total Cal Muscle");
                                        Toast.makeText(getActivity(), "LogExMuscle Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((EditShowExerciseActivity) getActivity()).onMainMuscleClkListener();
                                    }else {
                                        Log.e("DEE Update","Not Update Total Cal Muscle");
                                    }
                                }else {
                                    bb = healthyFoodDB.InsertTotalCalMuscle(lm_id,me_id,String.valueOf(Kcal),null,null,null);
                                    if (bb){
                                        Log.e("DEE Insert","Insert Total Cal Muscle");
                                        Toast.makeText(getActivity(), "LogExMuscle Inserted", Toast.LENGTH_SHORT).show();
                                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().putBoolean("excise", true).apply();
                                        getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                        ((EditShowExerciseActivity) getActivity()).onMainMuscleClkListener();

                                    }else {
                                        Log.e("DEE Insert","Not Insert Total Cal Muscle");
                                    }
                                }
                            } else {
                                Log.e("DEE Update","Not Update Log Exercise Muscle");

                            }
                        } else {
                            Toast.makeText(getActivity(), "งง เบย", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


                Log.e("DEE KcalEx","KcalEX : " + Kcal);



            }

        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tu_id.equals("TU01")){

                    del = healthyFoodDB.deleteLogExertEditNormal(len_id,ln_id,me_id,ex_id);
                    if (del){
                        Log.e("DEE Delete","Delete Log Exert Normal");

                            ArrayList<Integer> TotalKcalNormal = healthyFoodDB.getKcalExertNormal(ln_id,me_id);
                            int KcalExert = 0;
                            for (int i = 0 ;i < TotalKcalNormal.size();i++){
                                KcalExert += TotalKcalNormal.get(i);
                            }

                            bb = healthyFoodDB.UpdateTotalCalNormal(ln_id,me_id,String.valueOf(KcalExert));
                            if(bb) {
                                Log.e("DEE Update", "Update Total Cal Exert Normal");
                                getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                                ((EditShowExerciseActivity) getActivity()).onMainNormalClkListener();
                            }else {
                                Log.e("DEE Update", "Not Update Total Cal Exert Normal");
                            }




//

                    }else {
                        Log.e("DEE Delete","Not Delete Log Exert Normal");
                    }
                }else if (tu_id.equals("TU02")){
                    del = healthyFoodDB.deleteLogExertEditMuscle(lem_id,lm_id,me_id,ex_id);
                    if (del){
                        Log.e("DEE Delete","Delete Log Exert Muscle");

                        ArrayList<Integer> TotalKcalMuscle = healthyFoodDB.getKcalExertMuscle(lm_id,me_id);
                        int KcalExert = 0;
                        for (int i = 0 ;i < TotalKcalMuscle.size();i++){
                            KcalExert += TotalKcalMuscle.get(i);
                        }
//                                    tlm_kcal = kcal.getInt(kcal.getColumnIndex(HealthyFoodDB.TLM_Kcal));
//
//                                   TotalKcal = Cal + tlm_kcal;
                        bb = healthyFoodDB.UpdateTotalCalMuscle(lm_id,me_id,String.valueOf(KcalExert),null,null,null);
                        if(bb) {
                            Log.e("DEE Update", "Update Total Cal Exert Muscle");
                            getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().putBoolean("logexert", true).apply();
                            ((EditShowExerciseActivity) getActivity()).onMainMuscleClkListener();
                        }else {
                            Log.e("DEE Update", "Not Update Total Cal Exert Muscle");
                        }
                    }else {
                        Log.e("DEE Delete","Not Delete Log Exert Muscle");
                    }
                }else {
                    Toast.makeText(getActivity(), "งง เบย", Toast.LENGTH_SHORT).show();
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
        txtNameExert = (TextView) rootView.findViewById(R.id.txtNameExert);
        txtNbCal = (TextView) rootView.findViewById(R.id.txtNbCal);
        edtHour = (EditText) rootView.findViewById(R.id.edtHour);
        edtMinute = (EditText) rootView.findViewById(R.id.edtMinute);
        btnEdit = (FancyButton) rootView.findViewById(R.id.btnEdit);
        btnDel = (FancyButton) rootView.findViewById(R.id.btnDel);
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
