package com.example.mo.healthyfood.MainNormal;


import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.WeightActivity;
import com.example.mo.healthyfood.activityMuscle.MainMuscleActivity;
import com.example.mo.healthyfood.activityNormal.MainNormalActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class MaDayNormalFragment extends Fragment {

    FloatingActionButton add;
    TextView txtKcal,txtDay,txtDataGoal,txtDataDay;
    HealthyFoodDB healthyFoodDB;
    Cursor log,foodLog,goal,we,We,Goal,logExert,logFood;
    int tdnTotalKcal,dfKcal ,Kcal,ln_id,amount,logKcal,tdnKcal,days,len_kcal,totalKcal=0,ln_kcal=0,ttKcal = 0;
    String LN_ID,tg_id,p_id,Tg_id;
    Double gn_weight,weight,Weight,Gn_weight,day;

    boolean db,mm;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());


    MainNormalActivity mainNormalActivity;


    public MaDayNormalFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static MaDayNormalFragment newInstance(Bundle args) {
        MaDayNormalFragment fragment = new MaDayNormalFragment();
 //       Bundle args = new Bundle();
   //     args.putInt("position", postion);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        mainNormalActivity = (MainNormalActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_normal_main_myday, container, false);
        initInstances(rootView, savedInstanceState);

        String date = getArguments().getString("date");
        Log.d("DataNormal","Date : " + date);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("date",getArguments().getString("date"));
                mainNormalActivity.onAddClkListener(bundle);
               // ((MainNormalActivity) getActivity()).onAddClkListener(bundle);
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

        add = (FloatingActionButton) rootView.findViewById(R.id.add);
        txtKcal = (TextView) rootView.findViewById(R.id.txtKcal);
        txtDay = (TextView) rootView.findViewById(R.id.txtDay);
        txtDataDay = (TextView) rootView.findViewById(R.id.txtDataDay);
        txtDataGoal = (TextView) rootView.findViewById(R.id.txtDataGoal);



    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();


        if(getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).getBoolean("today", false)) {
            //    log = healthyFoodDB.getMaxLogNormal();
            log = healthyFoodDB.getLogNormal(getArguments().getString("date"));
            ln_id = log.getInt(log.getColumnIndex(HealthyFoodDB.LN_ID));
            tdnKcal = log.getInt(log.getColumnIndex(HealthyFoodDB.TDN_Kcal));
            totalKcal = log.getInt(log.getColumnIndex(HealthyFoodDB.TDN_TotalKcal));


            LN_ID = String.valueOf(ln_id);

            goal = healthyFoodDB.getMaxGoalNormal();
            tg_id = goal.getString(goal.getColumnIndex(HealthyFoodDB.TG_ID));
            gn_weight = goal.getDouble(goal.getColumnIndex(HealthyFoodDB.GN_Weight));

            we = healthyFoodDB.getMaxWeight();
            weight = we.getDouble(we.getColumnIndex(HealthyFoodDB.WE_Weight));

            if (tg_id.equals("TG01") || tg_id.equals("TG03")) {
                //เช็คว่าเป้าหมายสำเร็จหรือไม่
                if (gn_weight == weight) {
                    //เป้าหมายสำเร็จ
                    Log.e("MDNF Check","Goal true");
                    AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                    alog.setMessage("ยินดีด้วย คุณทำตามเป้าหมายได้สำเร็จแล้ว  คุณต้องการเปลี่ยนเป้าหมายหรือไม่");
                    alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((MainNormalActivity) getActivity()).onChangeGoalClkListener();
                        }
                    });
                    alog.setNegativeButton("ไม่เป็นไร", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //insert ข้อมูลการเปลี่ยนเป้าหมายเป็นของการควบคุมน้ำหนักตัว
                            Log.e("MDNF Check", "Goal true ");
                            mm = healthyFoodDB.InsertGoalNormal("1", "TG02", "P05", String.valueOf(gn_weight));
                            if(mm){
                                Log.e("MDNF Check", "Change to  Weight Control");
                                if (getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).getBoolean("food", false)) {
                                    //setup new data here  กดบันทึก
                                    Log.e("MDNF Check", "Save Food");

                                    logFood = healthyFoodDB.getTotalCalNormal(LN_ID,"ME10");
                                    logExert = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                                    ln_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                                    if (logExert.getCount() > 0){
                                        len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                                        ttKcal = totalKcal -ln_kcal + len_kcal;
                                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                                        if(db){
                                            Log.e("MDNF Update", "Update Log Normal TDN_Kcal");
                                            txtKcal.setText(String.valueOf(ttKcal));
                                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDNF Update", "Not Update Log Normal TDN_Kcal");
                                        }
                                    }else if (logExert.getCount() == 0){
                                        ttKcal = totalKcal -ln_kcal;
                                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                                        if(db){
                                            Log.e("MDNF Update", "Update Log Normal TDN_Kcal");
                                            txtKcal.setText(String.valueOf(ttKcal));
                                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDNF Update", "Not Update Log Normal TDN_Kcal");
                                        }
                                    }else {
                                        Log.e("MDNF Check","No No");
                                    }

//


                                }else if(getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).getBoolean("excise", false)){
                                    //ค่า kcal ที่เผาผลาญไปนำมาบวกกัน

                                    logFood = healthyFoodDB.getTotalCalNormal(LN_ID,"ME10");
                                    logExert = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                                    len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                                    if (logFood.getCount() > 0 ){
                                        Log.e("MDNF Check","Food");
                                        ln_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                                        ttKcal = totalKcal - ln_kcal +len_kcal;

                                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                                        if (db){
                                            Log.e("MDNF Update","Update Log Muscle");
                                            txtKcal.setText(String.valueOf(ttKcal));
                                            getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDNF Update","Not Update Log Muscle");
                                        }
                                    }else if (logFood.getCount() == 0){
                                        Log.e("MDNF Check","No Food");
                                        ttKcal = totalKcal + len_kcal;

                                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                                        if (db){
                                            Log.e("MDNF Update","Update Log Muscle");
                                            txtKcal.setText(String.valueOf(ttKcal));
                                            getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDNF Update","Not Update Log Muscle");
                                        }
                                    }else {
                                        Log.e("MDNF Check","No No");
                                    }






                                } else if(getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).getBoolean("logfood", false)){
                                    Log.e("MDM Check", "Save Delete Food");
                                    logFood = healthyFoodDB.getTotalCalNormal(LN_ID,"ME10");
                                    logExert = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                                    ln_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                                    if (logExert.getCount() > 0){
                                        Log.e("MDN Check", "logExert > 0");
                                        len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                                        ttKcal = totalKcal - ln_kcal + len_kcal;
                                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                                        if (db){
                                            Log.e("MDN Update","Update Log Muscle");
                                            txtKcal.setText(String.valueOf(ttKcal));
                                            getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDN Update","Not Update Log Muscle");
                                        }

                                    }else if (logExert.getCount() == 0){
                                        Log.e("MDN Check", "logExert == 0");
                                        ttKcal = totalKcal - ln_kcal ;
                                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                                        if (db){
                                            Log.e("MDN Update","Update Log Muscle");
                                            txtKcal.setText(String.valueOf(ttKcal));
                                            getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDN Update","Not Update Log Muscle");
                                        }
                                    }else {
                                        Log.e("MDN Check","No No");
                                    }

                                } else if(getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).getBoolean("logexert", false)){
                                    Log.e("MDM Check", "Save Delete Food");
                                    //TODO Del Log Exert
                                    logFood = healthyFoodDB.getTotalCalNormal(LN_ID,"ME10");
                                    logExert = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                                    len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                                    if (logFood.getCount() > 0){
                                        Log.e("MDN Check", "logFood > 0");
                                        ln_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                                        ttKcal = totalKcal - ln_kcal + len_kcal;
                                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                                        if (db){
                                            Log.e("MDN Update","Update Log Muscle");
                                            txtKcal.setText(String.valueOf(ttKcal));
                                            getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDN Update","Not Update Log Muscle");
                                        }

                                    }else if (logFood.getCount() == 0){
                                        Log.e("MDN Check", "logFood = 0");
                                        ttKcal = totalKcal + len_kcal;
                                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                                        if (db){
                                            Log.e("MDN Update","Update Log Muscle");
                                            txtKcal.setText(String.valueOf(ttKcal));
                                            getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDN Update","Not Update Log Muscle");
                                        }


                                    }else {
                                        Log.e("MDN Check","No No");
                                    }

                                } else {
                                    //ไม่ได้กดบันทึก

                                    Log.e("MDNF Check", "No Save");

                                    txtKcal.setText(String.valueOf(tdnKcal));
                                    getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                                    getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();

                                }
                            }else {
                                Toast.makeText(getActivity(), "MDNF Not Insert GoalNormal", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                    AlertDialog alertDialog = alog.create();
                    alertDialog.show();

                } else {
                    // เป้าหมายไม่สำเร็จ
                    Log.e("MDNF Check","No Goal true");
                    We =  healthyFoodDB.getMaxWeight();
                    Weight = We.getDouble(We.getColumnIndex(HealthyFoodDB.WE_Weight));

                    Goal = healthyFoodDB.getMaxGoalNormal();
                    Gn_weight = Goal.getDouble(Goal.getColumnIndex(HealthyFoodDB.GN_Weight));
                    p_id = Goal.getString(Goal.getColumnIndex(HealthyFoodDB.P_ID));

                    //ลดน้ำหนัก
                    if(tg_id.equals("TG01")){
                        if (p_id.equals("P01")) {
                            day = ((Weight - Gn_weight) / 0.2) * 7;
                            days = day.intValue();
                            Log.e("MDNF day","TG01 P01 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else if (p_id.equals("P02")) {
                            day = ((Weight - Gn_weight) / 0.3) * 7;
                            days = day.intValue();
                            Log.e("MDNF day","TG01 P02 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else if (p_id.equals("P03")) {
                            day = ((Weight - Gn_weight) / 0.4) * 7;
                            days = day.intValue();
                            Log.e("MDNF day","TG01 P03 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else if (p_id.equals("P04")) {
                            day = ((Weight - Gn_weight) / 0.5) * 7;
                            days = day.intValue();
                            Log.e("MDNF day","TG01 P04 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else {
                            Log.e("MDNF day","ควบคุมน้ำหนัก");
                        }

                    }else {
                        if (p_id.equals("P01")) {
                            day = ((Gn_weight - Weight) / 0.2) * 7;
                            days = day.intValue();
                            Log.e("MDNF day","TG03 P01 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else if (p_id.equals("P02")) {
                            day = ((Gn_weight - Weight) / 0.3) * 7;
                            days = day.intValue();
                            Log.e("MDNF day","TG03 P02 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else if (p_id.equals("P03")) {
                            day = ((Gn_weight - Weight) / 0.4) * 7;
                            days = day.intValue();
                            Log.e("MDNF day","TG03 P02 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else if (p_id.equals("P04")) {
                            day = ((Gn_weight - Weight) / 0.5) * 7;
                            days = day.intValue();
                            Log.e("MDNF day","TG03 P04 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");
                        } else {
                            Log.e("MDNF day","ควบคุมน้ำหนัก");
                        }
                    }



                    if (getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).getBoolean("food", false)) {
                        //setup new data here  กดบันทึก
                        Log.e("MDNF Check", "Save Food");

                        logFood = healthyFoodDB.getTotalCalNormal(LN_ID,"ME10");
                        logExert = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                        ln_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                        if (logExert.getCount() > 0){
                            len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                            ttKcal = totalKcal -ln_kcal + len_kcal;
                            db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                            if(db){
                                Log.e("MDNF Update", "Update Log Normal TDN_Kcal");
                                txtKcal.setText(String.valueOf(ttKcal));
                                getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDNF Update", "Not Update Log Normal TDN_Kcal");
                            }
                        }else if (logExert.getCount() == 0){
                            ttKcal = totalKcal -ln_kcal;
                            db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                            if(db){
                                Log.e("MDNF Update", "Update Log Normal TDN_Kcal");
                                txtKcal.setText(String.valueOf(ttKcal));
                                getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDNF Update", "Not Update Log Normal TDN_Kcal");
                            }
                        }else {
                            Log.e("MDNF Check","No No");
                        }

                    }else if(getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).getBoolean("excise", false)){
                        //ค่า kcal ที่เผาผลาญไปนำมาบวกกัน

                        logFood = healthyFoodDB.getTotalCalNormal(LN_ID,"ME10");
                        logExert = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");

                        if (logFood.getCount() > 0){
                            Log.e("MDNF Check", "Food");
                            ln_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                            len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                            ttKcal = totalKcal - ln_kcal + len_kcal;


                            db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                            if (db){
                                Log.e("MDNF Update","Update Log Muscle");
                                txtKcal.setText(String.valueOf(ttKcal));
                                getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDNF Update","Not Update Log Muscle");
                            }
                        }else if (logFood.getCount() == 0){
                            Log.e("MDNF Check", "No Food");
                            len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                            ttKcal = totalKcal + len_kcal;


                            db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                            if (db){
                                Log.e("MDNF Update","Update Log Muscle");
                                txtKcal.setText(String.valueOf(ttKcal));
                                getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDNF Update","Not Update Log Muscle");
                            }
                        }else {
                            Log.e("MDNF Check","No No");
                        }





                    } else if(getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).getBoolean("logfood", false)){
                        Log.e("MDM Check", "Save Delete Food");
                        logFood = healthyFoodDB.getTotalCalNormal(LN_ID,"ME10");
                        logExert = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                        ln_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                        if (logExert.getCount() > 0){
                            Log.e("MDN Check", "logExert > 0");
                            len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                            ttKcal = totalKcal - ln_kcal + len_kcal;
                            db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                            if (db){
                                Log.e("MDN Update","Update Log Muscle");
                                txtKcal.setText(String.valueOf(ttKcal));
                                getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDN Update","Not Update Log Muscle");
                            }

                        }else if (logExert.getCount() == 0){
                            Log.e("MDN Check", "logExert == 0");
                            ttKcal = totalKcal - ln_kcal ;
                            db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                            if (db){
                                Log.e("MDN Update","Update Log Muscle");
                                txtKcal.setText(String.valueOf(ttKcal));
                                getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDN Update","Not Update Log Muscle");
                            }
                        }else {
                            Log.e("MDN Check","No No");
                        }
                    } else if(getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).getBoolean("logexert", false)){
                        Log.e("MDM Check", "Save Delete Food");
                        //TODO Del Log Exert
                        logFood = healthyFoodDB.getTotalCalNormal(LN_ID,"ME10");
                        logExert = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                        len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                        if (logFood.getCount() > 0){
                            Log.e("MDN Check", "logFood > 0");
                            ln_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                            ttKcal = totalKcal - ln_kcal + len_kcal;
                            db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                            if (db){
                                Log.e("MDN Update","Update Log Muscle");
                                txtKcal.setText(String.valueOf(ttKcal));
                                getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDN Update","Not Update Log Muscle");
                            }

                        }else if (logFood.getCount() == 0){
                            Log.e("MDN Check", "logFood = 0");
                            ttKcal = totalKcal + len_kcal;
                            db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                            if (db){
                                Log.e("MDN Update","Update Log Muscle");
                                txtKcal.setText(String.valueOf(ttKcal));
                                getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDN Update","Not Update Log Muscle");
                            }


                        }else {
                            Log.e("MDN Check","No No");
                        }

                    } else {
                        //ไม่ได้กดบันทึก

                        Log.e("MDNF Check", "No Save");
                        txtKcal.setText(String.valueOf(tdnKcal));
                        getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();

                    }
                }

            } else {
                //กรณีของควบคุมน้ำหนัก ไม่ต้องเช็คเป้าหมาย
                Log.e("MDNF Check", "weight Control");

                if (getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).getBoolean("food", false)) {
                    //setup new data here  กดบันทึก
                    Log.e("MDNF Check", "Save Food");

                    logFood = healthyFoodDB.getTotalCalNormal(LN_ID,"ME10");
                    logExert = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                    ln_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                    if (logExert.getCount() > 0){
                        len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                        ttKcal = totalKcal -ln_kcal + len_kcal;
                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                        if(db){
                            Log.e("MDNF Update", "Update Log Normal TDN_Kcal");
                            txtKcal.setText(String.valueOf(ttKcal));
                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDNF Update", "Not Update Log Normal TDN_Kcal");
                        }
                    }else if (logExert.getCount() == 0){
                        ttKcal = totalKcal -ln_kcal;
                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                        if(db){
                            Log.e("MDNF Update", "Update Log Normal TDN_Kcal");
                            txtKcal.setText(String.valueOf(ttKcal));
                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDNF Update", "Not Update Log Normal TDN_Kcal");
                        }
                    }else {
                        Log.e("MDNF Check","No No");
                    }



                }else if(getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).getBoolean("excise", false)){
                    //ค่า kcal ที่เผาผลาญไปนำมาบวกกัน

                    logFood = healthyFoodDB.getTotalCalNormal(LN_ID,"ME10");
                    logExert = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                    len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                    if (logFood.getCount() > 0){
                        Log.e("MDNF Check", "Food");
                        ln_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                        ttKcal = totalKcal - ln_kcal + len_kcal;

                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                        if (db){
                            Log.e("MDNF Update","Update Log Muscle");
                            txtKcal.setText(String.valueOf(ttKcal));
                            getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDNF Update","Not Update Log Muscle");
                        }
                    }else if (logFood.getCount() == 0){
                        Log.e("MDNF Check", "No Food");
                        ttKcal = totalKcal + len_kcal;

                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                        if (db){
                            Log.e("MDNF Update","Update Log Muscle");
                            txtKcal.setText(String.valueOf(ttKcal));
                            getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDNF Update","Not Update Log Muscle");
                        }
                    }else {
                        Log.e("MDNF Check","No No");
                    }



                } else if(getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).getBoolean("logfood", false)){
                    Log.e("MDM Check", "Save Delete Food");
                    logFood = healthyFoodDB.getTotalCalNormal(LN_ID,"ME10");
                    logExert = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                    ln_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                    if (logExert.getCount() > 0){
                        Log.e("MDN Check", "logExert > 0");
                        len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                        ttKcal = totalKcal - ln_kcal + len_kcal;
                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                        if (db){
                            Log.e("MDN Update","Update Log Muscle");
                            txtKcal.setText(String.valueOf(ttKcal));
                            getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDN Update","Not Update Log Muscle");
                        }

                    }else if (logExert.getCount() == 0){
                        Log.e("MDN Check", "logExert == 0");
                        ttKcal = totalKcal - ln_kcal ;
                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                        if (db){
                            Log.e("MDN Update","Update Log Muscle");
                            txtKcal.setText(String.valueOf(ttKcal));
                            getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDN Update","Not Update Log Muscle");
                        }
                    }else {
                        Log.e("MDN Check","No No");
                    }

                } else if(getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).getBoolean("logexert", false)){
                    Log.e("MDM Check", "Save Delete Food");
                    //TODO Del Log Exert
                    logFood = healthyFoodDB.getTotalCalNormal(LN_ID,"ME10");
                    logExert = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                    len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                    if (logFood.getCount() > 0){
                        Log.e("MDN Check", "logFood > 0");
                        ln_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                        ttKcal = totalKcal - ln_kcal + len_kcal;
                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                        if (db){
                            Log.e("MDN Update","Update Log Muscle");
                            txtKcal.setText(String.valueOf(ttKcal));
                            getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDN Update","Not Update Log Muscle");
                        }

                    }else if (logFood.getCount() == 0){
                        Log.e("MDN Check", "logFood = 0");
                        ttKcal = totalKcal + len_kcal;
                        db = healthyFoodDB.UpdateLogNormalKcal(String.valueOf(ttKcal));
                        if (db){
                            Log.e("MDN Update","Update Log Muscle");
                            txtKcal.setText(String.valueOf(ttKcal));
                            getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDN Update","Not Update Log Muscle");
                        }


                    }else {
                        Log.e("MDN Check","No No");
                    }


                } else {
                    //ไม่ได้กดบันทึก

                    Log.e("MDNF Check", "No Save");
                    txtKcal.setText(String.valueOf(tdnKcal));
                    getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                    getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();

                }
            }



        }else {
            //ยังไม่ได้กด ให้กลับไปเปิดหน้าคำถาม
            ((MainNormalActivity)getActivity()).onQuestionTodayClkListener();
        }



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
