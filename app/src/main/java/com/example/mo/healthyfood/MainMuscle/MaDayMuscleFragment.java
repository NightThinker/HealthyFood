package com.example.mo.healthyfood.MainMuscle;


import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.MainMuscleActivity;
import com.example.mo.healthyfood.activityNormal.ShowFoodNormalActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class MaDayMuscleFragment extends Fragment {

    FloatingActionButton add;
    TextView txtKcal, txtFat, txtProtein, txtCrab,txtDay,txtDataGoal,txtDataDay;
    HealthyFoodDB healthyFoodDB;
    Cursor log, logFood, bf, fi,logExert,logTotal;
    int kcal, amount, lm_kcal, ttKcal,days,lem_kcal,totalKcal;
    String Kcal, Fat, Protein, Carb, lm_id, bf_id, fi_id;
    Double fat, protein, carb, lm_fat, lm_protein, lm_carb,totalFat,totalProtein,totalCarb;
    Double ttFat, ttProtein, ttCarb;
    boolean db, mm, bb;

    Cursor goal, weight, usermus, Weight, Goal;
    Double weightData, gm_weight, WeightData, Gm_weight,day;
    String um_id, tm_id, gm_bodyfat, p_id;
    MainMuscleActivity mainMuscleActivity;


    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());


    public MaDayMuscleFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static MaDayMuscleFragment newInstance(Bundle args) {
        MaDayMuscleFragment fragment = new MaDayMuscleFragment();
//       Bundle args = new Bundle();
//        args.putInt("position", postion);
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
        mainMuscleActivity = (MainMuscleActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_muscle_main_myday, container, false);
        initInstances(rootView, savedInstanceState);

//        if(getActivity().getSharedPreferences("is_day_added", Context.MODE_PRIVATE).getBoolean("day", false)) {
//
//        }else {
//            //แสดง alog
//        }


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("date", getArguments().getString("date"));
                mainMuscleActivity.onAddClkListener(bundle);
                //   ((MainMuscleActivity) getActivity()).onAddClkListener(bundle);

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
        txtCrab = (TextView) rootView.findViewById(R.id.txtCarb);
        txtProtein = (TextView) rootView.findViewById(R.id.txtProt);
        txtFat = (TextView) rootView.findViewById(R.id.txtFat);
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

        if (getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).getBoolean("today", false)) {

            log = healthyFoodDB.getLogMuscle(getArguments().getString("date"));
            lm_id = String.valueOf(log.getInt(log.getColumnIndex(HealthyFoodDB.LM_ID)));
            kcal = log.getInt(log.getColumnIndex(HealthyFoodDB.TDM_Kcal));
            fat = log.getDouble(log.getColumnIndex(HealthyFoodDB.TDM_Fat));
            protein = log.getDouble(log.getColumnIndex(HealthyFoodDB.TDM_Protein));
            carb = log.getDouble(log.getColumnIndex(HealthyFoodDB.TDM_Carb));

            totalKcal = log.getInt(log.getColumnIndex(HealthyFoodDB.TDM_TotalKcal));
            totalFat = log.getDouble(log.getColumnIndex(HealthyFoodDB.TDM_TotalFat));
            totalProtein = log.getDouble(log.getColumnIndex(HealthyFoodDB.TDM_TotalProtein));
            totalCarb = log.getDouble(log.getColumnIndex(HealthyFoodDB.TDM_TotalCarb));


            weight = healthyFoodDB.getMaxWeight();
            weightData = weight.getDouble(weight.getColumnIndex(HealthyFoodDB.WE_Weight));


            goal = healthyFoodDB.getMaxGoalMuscle();
            um_id = String.valueOf(goal.getInt(goal.getColumnIndex(HealthyFoodDB.UM_ID)));
            gm_weight = goal.getDouble(goal.getColumnIndex(HealthyFoodDB.GM_Weight));
            gm_bodyfat = String.valueOf(goal.getInt(goal.getColumnIndex(HealthyFoodDB.GM_Bodyfat)));

            usermus = healthyFoodDB.getUserMuscle(um_id);
            tm_id = usermus.getString(usermus.getColumnIndex(HealthyFoodDB.TM_ID));

            Log.e("TM_ID","TM : " + tm_id);


            //เช็คเป้าหมายสำเร้จ และต้องการเปลี่ยนเป้าหมายหรือไม่
            if (tm_id.equals("TM01") || tm_id.equals("TM02")) {
                Log.e("MDMF true","tm");
                if (gm_weight == weightData) {
                    Log.e("MDMF Check","Goal true");
                    AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                    alog.setMessage("ยินดีด้วย คุณทำตามเป้าหมายได้สำเร็จแล้ว  คุณต้องการเปลี่ยนเป้าหมายหรือไม่");
                    alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((MainMuscleActivity) getActivity()).onChangeGoalClkListener();
                        }
                    });
                    alog.setNegativeButton("ไม่เป็นไร", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //update เป็น TM03 ควบคุมน้ำหนัก

                            Log.e("MDMF Check", "Goal true ");

                            bf = healthyFoodDB.getMaxBodyfat();
                            bf_id = String.valueOf(bf.getInt(bf.getColumnIndex(HealthyFoodDB.BF_ID)));

                            fi = healthyFoodDB.getMaxFFMI();
                            fi_id = String.valueOf(fi.getInt(fi.getColumnIndex(HealthyFoodDB.FI_ID)));

                            mm = healthyFoodDB.InsertUserMuscle("1", bf_id, fi_id, "TM03");
                            bb = healthyFoodDB.InsertGoalMuscle("1", um_id, "P05", String.valueOf(gm_weight), gm_bodyfat);
                            //goalmuscle
                            if (mm && bb) {
                                Log.e("MDMF Check", "Change to  Weight Control");

                                if (getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).getBoolean("food", false)) {
                                    //setup new data here  กดบันทึก

                                    Log.e("MDMF Check", "Save Food");

                                    logExert = healthyFoodDB.getTotalCalMuscle(lm_id,"ME07");
                                    logFood = healthyFoodDB.getTotalCalMuscle(lm_id,"ME10");
                                    lm_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                                    lm_fat = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Fat));
                                    lm_protein = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Protein));
                                    lm_carb = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Carb));


                                    if (logExert.getCount() > 0){
                                        lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                                        ttKcal = totalKcal - lm_kcal + lem_kcal;
                                        ttFat = totalFat - lm_fat;
                                        ttProtein = totalProtein - lm_protein;
                                        ttCarb = totalCarb - lm_carb ;

                                        Kcal = String.valueOf(ttKcal);
                                        Fat = String.valueOf(ttFat);
                                        Protein = String.valueOf(ttProtein);
                                        Carb = String.valueOf(ttCarb);
                                        db = healthyFoodDB.UpdateLogMuscleKcal(Kcal, Fat, Protein, Carb);
                                        if (db) {
                                            Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                                            txtKcal.setText(Kcal);
                                            txtFat.setText(String.format("%.1f", ttFat));
                                            txtProtein.setText(String.format("%.1f", ttProtein));
                                            txtCrab.setText(String.format("%.1f", ttCarb));
                                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                                        }
                                    }else if (logExert.getCount() == 0){
                                        ttKcal = totalKcal - lm_kcal;
                                        ttFat = totalFat - lm_fat;
                                        ttProtein = totalProtein - lm_protein;
                                        ttCarb = totalCarb - lm_carb ;

                                        Kcal = String.valueOf(ttKcal);
                                        Fat = String.valueOf(ttFat);
                                        Protein = String.valueOf(ttProtein);
                                        Carb = String.valueOf(ttCarb);
                                        db = healthyFoodDB.UpdateLogMuscleKcal(Kcal, Fat, Protein, Carb);
                                        if (db) {
                                            Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                                            txtKcal.setText(Kcal);
                                            txtFat.setText(String.format("%.1f", ttFat));
                                            txtProtein.setText(String.format("%.1f", ttProtein));
                                            txtCrab.setText(String.format("%.1f", ttCarb));
                                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                                        }
                                    }else {
                                        Log.e("MDMF Check","No No");
                                    }



                                } else if(getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).getBoolean("excise", false)){
                                    //ค่า kcal ที่เผาผลาญไปนำมาบวกกัน


                                    Log.e("MDMF Check", "Save Exert");

                                    logFood = healthyFoodDB.getTotalCalMuscle(lm_id,"ME10");
                                    logExert = healthyFoodDB.getTotalCalMuscle(lm_id,"ME07");


                                    if (logFood.getCount() > 0 ){
                                        Log.e("MDMF Check","Food");
                                        lm_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                                        lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));

                                        ttKcal =  totalKcal - lm_kcal + lem_kcal;
                                        ttFat = fat;
                                        ttProtein = protein;
                                        ttCarb = carb;
                                        db = healthyFoodDB.UpdateLogMuscleKcalExert(String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                                        if (db){
                                            Log.e("MDMF Update","Update Log Muscle");
                                            txtKcal.setText(String.valueOf(ttKcal));
                                            txtFat.setText(String.format("%.1f", ttFat));
                                            txtProtein.setText(String.format("%.1f", ttProtein));
                                            txtCrab.setText(String.format("%.1f", ttCarb));
                                            getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDMF Update","Not Update Log Muscle");
                                        }
                                    }else if (logFood.getCount() == 0){
                                        Log.e("MDMF Check","No Food");
                                        lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));

                                        ttKcal =  totalKcal + lem_kcal;
                                        ttFat = fat;
                                        ttProtein = protein;
                                        ttCarb = carb;

                                        db = healthyFoodDB.UpdateLogMuscleKcalExert(String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                                        if (db){
                                            Log.e("MDMF Update","Update Log Muscle");
                                            txtKcal.setText(String.valueOf(ttKcal));
                                            txtFat.setText(String.format("%.1f", ttFat));
                                            txtProtein.setText(String.format("%.1f", ttProtein));
                                            txtCrab.setText(String.format("%.1f", ttCarb));
                                            getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDMF Update","Not Update Log Muscle");
                                        }
                                    }else {
                                        Log.e("MDMF Check","No No");
                                    }





                                } else if(getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).getBoolean("logfood", false)){

                                    Log.e("MDM Check", "Save Delete Food");
                                    //ลบlog Food

                                    logExert = healthyFoodDB.getTotalCalMuscle(lm_id,"ME07");
                                    logFood = healthyFoodDB.getTotalCalMuscle(lm_id,"ME10");
                                    lm_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                                    lm_fat = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Fat));
                                    lm_protein = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Protein));
                                    lm_carb = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Carb));

                                    if (logExert.getCount() > 0){
                                        Log.e("MDM Check", "logExert.getCount() > 0");
                                        lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                                        ttKcal =  totalKcal - lm_kcal + lem_kcal;
                                        ttFat = totalFat - lm_fat;
                                        ttProtein = totalProtein - lm_protein;
                                        ttCarb = totalCarb - lm_carb;
                                        Kcal = String.valueOf(ttKcal);
                                        Fat = String.valueOf(ttFat);
                                        Protein = String.valueOf(ttProtein);
                                        Carb = String.valueOf(ttCarb);
                                        db = healthyFoodDB.UpdateLogMuscleKcal(Kcal, Fat, Protein, Carb);
                                        if (db) {
                                            Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                                            txtKcal.setText(Kcal);
                                            txtFat.setText(String.format("%.1f", ttFat));
                                            txtProtein.setText(String.format("%.1f", ttProtein));
                                            txtCrab.setText(String.format("%.1f", ttCarb));
                                            getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                                        }

                                    }else if (logExert.getCount() == 0){
                                        Log.e("MDM Check", "logExert.getCount() == 0");
                                        ttKcal = totalKcal - lm_kcal;
                                        ttFat = totalFat - lm_fat;
                                        ttProtein = totalProtein - lm_protein;
                                        ttCarb = totalCarb - lm_carb;
                                        Kcal = String.valueOf(ttKcal);
                                        Fat = String.valueOf(ttFat);
                                        Protein = String.valueOf(ttProtein);
                                        Carb = String.valueOf(ttCarb);
                                        db = healthyFoodDB.UpdateLogMuscleKcal(Kcal, Fat, Protein, Carb);
                                        if (db) {
                                            Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                                            txtKcal.setText(Kcal);
                                            txtFat.setText(String.format("%.1f", ttFat));
                                            txtProtein.setText(String.format("%.1f", ttProtein));
                                            txtCrab.setText(String.format("%.1f", ttCarb));
                                            getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                                        }


                                    }else {
                                        Log.e("MDMF Check","No No");
                                    }








                                } else if(getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).getBoolean("logexert", false)){

                                    Log.e("MDM Check", "Save Delete Exert");
                                    //ลบlog Exert

                                    logFood = healthyFoodDB.getTotalCalMuscle(lm_id,"ME10");
                                    logExert = healthyFoodDB.getTotalCalMuscle(lm_id,"ME07");
                                    lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));


                                    if (logFood.getCount() > 0 ){
                                        Log.e("MDM Check", "logFood > 0");
                                        lm_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                                        lm_fat = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Fat));
                                        lm_protein = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Protein));
                                        lm_carb = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Carb));

                                        ttKcal =  totalKcal - lm_kcal + lem_kcal;
                                        ttFat = fat;
                                        ttProtein = protein;
                                        ttCarb = carb;

                                        Kcal = String.valueOf(ttKcal);
                                        Fat = String.valueOf(ttFat);
                                        Protein = String.valueOf(ttProtein);
                                        Carb = String.valueOf(ttCarb);
                                        db = healthyFoodDB.UpdateLogMuscleKcalExert(String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                                        if (db) {
                                            Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                                            txtKcal.setText(Kcal);
                                            txtFat.setText(String.format("%.1f", ttFat));
                                            txtProtein.setText(String.format("%.1f", ttProtein));
                                            txtCrab.setText(String.format("%.1f", ttCarb));
                                            getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                                        }


                                    }else if (logFood.getCount() == 0 ){
                                        Log.e("MDM Check", "logFood > 0");

                                        ttKcal =  totalKcal + lem_kcal;
                                        ttFat = fat;
                                        ttProtein = protein;
                                        ttCarb = carb;

                                        Kcal = String.valueOf(ttKcal);
                                        Fat = String.valueOf(ttFat);
                                        Protein = String.valueOf(ttProtein);
                                        Carb = String.valueOf(ttCarb);
                                        db = healthyFoodDB.UpdateLogMuscleKcalExert(String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                                        if (db) {
                                            Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                                            txtKcal.setText(Kcal);
                                            txtFat.setText(String.format("%.1f", ttFat));
                                            txtProtein.setText(String.format("%.1f", ttProtein));
                                            txtCrab.setText(String.format("%.1f", ttCarb));
                                            getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().clear().apply();
                                        }else {
                                            Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                                        }

                                    }else {
                                        Log.e("MDMF Check","No No");
                                    }













                                }else {
                                    //ไม่ได้กดบันทึก
                                    Log.e("MDM Check", "No Save");

                                    ttKcal = kcal;
                                    ttFat = fat;
                                    ttProtein = protein;
                                    ttCarb = carb;
                                    Kcal = String.valueOf(ttKcal);

                                    txtKcal.setText(Kcal);
                                    txtFat.setText(String.format("%.1f", ttFat));
                                    txtProtein.setText(String.format("%.1f", ttProtein));
                                    txtCrab.setText(String.format("%.1f", ttCarb));
                                    getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                                    getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();


                                }
                            } else {
                                Log.e("MDMF Insert","Not Insert UserMuscle And GoalMuscle");
                            }

                        }
                    });

                    AlertDialog alertDialog = alog.create();
                    alertDialog.show();
                } else {
                    //กรณีที่ยังไม่สำเร็จ

                    Log.e("MDMF Check","No Goal true");

                    Weight = healthyFoodDB.getMaxWeight();
                    WeightData = Weight.getDouble(Weight.getColumnIndex(HealthyFoodDB.WE_Weight));

                    Goal = healthyFoodDB.getMaxGoalMuscle();
                    Gm_weight = Goal.getDouble(Goal.getColumnIndex(HealthyFoodDB.GM_Weight));
                    p_id = Goal.getString(Goal.getColumnIndex(HealthyFoodDB.P_ID));

                    //ลดไขมัน
                    if (tm_id.equals("TM01")) {
                        Log.e("MDMF true","lost true");
                        if (p_id.equals("P01")) {
                            day = ((weightData - gm_weight) / 0.2) * 7;
                            days = day.intValue();
                            Log.e("MDMF day","TM01 P01 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else if (p_id.equals("P02")) {
                            day = ((weightData - gm_weight) / 0.3) * 7;
                            days = day.intValue();
                            Log.e("MDMF day","TM01 P02 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else if (p_id.equals("P03")) {
                            day = ((weightData - gm_weight) / 0.4) * 7;
                            days = day.intValue();
                            Log.e("MDMF day","TM01 P03 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else if (p_id.equals("P04")) {
                            day = ((weightData - gm_weight) / 0.5) * 7;
                            days = day.intValue();
                            Log.e("MDMF day","TM01 P04 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else {
                            Log.e("MDMF day", "ควบคุมน้ำหนัก");
                        }
                        //เพิ่มกล้ามเนื้อ
                    } else {
                        Log.e("MDMF true","gain true");
                        if (p_id.equals("P01")) {
                            day = ((gm_weight -weightData) / 0.2) * 7;
                            days = day.intValue();
                            Log.e("MDMF day","TM02 P01 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else if (p_id.equals("P02")) {
                            day = ((gm_weight - weightData) / 0.3) * 7;
                            days = day.intValue();
                            Log.e("MDMF day","TM02 P02 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else if (p_id.equals("P03")) {
                            day = ((gm_weight - weightData) / 0.4) * 7;
                            days = day.intValue();
                            Log.e("MDMF day","TM02 P03 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else if (p_id.equals("P04")) {
                            day = ((gm_weight - weightData) / 0.5) * 7;
                            days = day.intValue();
                            Log.e("MDMF day","TM02 P04 : " + days);

                            txtDataGoal.setText("บรรลุเป้าหมายในอีก");
                            txtDay.setText(String.valueOf(days));
                            txtDataDay.setText("วัน");

                        } else {
                            Log.e("MDMF day", "ควบคุมน้ำหนัก");
                        }
                    }



                    if (getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).getBoolean("food", false)) {
                        //setup new data here  กดบันทึก

                        Log.e("MDMF Check", "Save Food");

                        logExert = healthyFoodDB.getTotalCalMuscle(lm_id,"ME07");
                        logFood = healthyFoodDB.getTotalCalMuscle(lm_id,"ME10");
                        lm_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                        lm_fat = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Fat));
                        lm_protein = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Protein));
                        lm_carb = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Carb));


                        if (logExert.getCount() > 0){
                            lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                            ttKcal = totalKcal - lm_kcal + lem_kcal;
                            ttFat = totalFat - lm_fat;
                            ttProtein = totalProtein - lm_protein;
                            ttCarb = totalCarb - lm_carb ;

                            Kcal = String.valueOf(ttKcal);
                            Fat = String.valueOf(ttFat);
                            Protein = String.valueOf(ttProtein);
                            Carb = String.valueOf(ttCarb);
                            db = healthyFoodDB.UpdateLogMuscleKcal(Kcal, Fat, Protein, Carb);
                            if (db) {
                                Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                                txtKcal.setText(Kcal);
                                txtFat.setText(String.format("%.1f", ttFat));
                                txtProtein.setText(String.format("%.1f", ttProtein));
                                txtCrab.setText(String.format("%.1f", ttCarb));
                                getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                            }
                        }else if (logExert.getCount() == 0){
                            ttKcal = totalKcal - lm_kcal;
                            ttFat = totalFat - lm_fat;
                            ttProtein = totalProtein - lm_protein;
                            ttCarb = totalCarb - lm_carb ;

                            Kcal = String.valueOf(ttKcal);
                            Fat = String.valueOf(ttFat);
                            Protein = String.valueOf(ttProtein);
                            Carb = String.valueOf(ttCarb);
                            db = healthyFoodDB.UpdateLogMuscleKcal(Kcal, Fat, Protein, Carb);
                            if (db) {
                                Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                                txtKcal.setText(Kcal);
                                txtFat.setText(String.format("%.1f", ttFat));
                                txtProtein.setText(String.format("%.1f", ttProtein));
                                txtCrab.setText(String.format("%.1f", ttCarb));
                                getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                            }
                        }else {
                            Log.e("MDMF Check","No No");
                        }



                    } else if(getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).getBoolean("excise", false)){
                        //ค่า kcal ที่เผาผลาญไปนำมาบวกกัน

                        Log.e("MDMF Check", "Save Exert");

                        logFood = healthyFoodDB.getTotalCalMuscle(lm_id,"ME10");
                        logExert = healthyFoodDB.getTotalCalMuscle(lm_id,"ME07");


                        if (logFood.getCount() > 0){
                            Log.e("MDMF Check", "Food");
                            lm_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                            lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));

                            ttKcal =  totalKcal - lm_kcal + lem_kcal;
                            ttFat = fat;
                            ttProtein = protein;
                            ttCarb = carb;

                            db = healthyFoodDB.UpdateLogMuscleKcalExert(String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                            if (db){
                                Log.e("MDMF Update","Update Log Muscle");
                                txtKcal.setText(String.valueOf(ttKcal));
                                txtFat.setText(String.format("%.1f", ttFat));
                                txtProtein.setText(String.format("%.1f", ttProtein));
                                txtCrab.setText(String.format("%.1f", ttCarb));
                                getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDMF Update","Not Update Log Muscle");
                            }
                        }else if (logFood.getCount() == 0){
                            Log.e("MDMF Check", "No Food");
                            lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));

                            ttKcal =  totalKcal + lem_kcal;
                            ttFat = fat;
                            ttProtein = protein;
                            ttCarb = carb;

                            db = healthyFoodDB.UpdateLogMuscleKcalExert(String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                            if (db){
                                Log.e("MDMF Update","Update Log Muscle");
                                txtKcal.setText(String.valueOf(ttKcal));
                                txtFat.setText(String.format("%.1f", ttFat));
                                txtProtein.setText(String.format("%.1f", ttProtein));
                                txtCrab.setText(String.format("%.1f", ttCarb));
                                getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDMF Update","Not Update Log Muscle");
                            }
                        }else {
                            Log.e("MDMF Check","No No");
                        }

                    } else if(getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).getBoolean("logfood", false)){

                        Log.e("MDM Check", "Save Delete Food");
                        //ลบlog Food

                        logExert = healthyFoodDB.getTotalCalMuscle(lm_id,"ME07");
                        logFood = healthyFoodDB.getTotalCalMuscle(lm_id,"ME10");
                        lm_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                        lm_fat = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Fat));
                        lm_protein = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Protein));
                        lm_carb = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Carb));

                        if (logExert.getCount() > 0){
                            Log.e("MDM Check", "logExert.getCount() > 0");
                            lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                            ttKcal =  totalKcal - lm_kcal + lem_kcal;
                            ttFat = totalFat - lm_fat;
                            ttProtein = totalProtein - lm_protein;
                            ttCarb = totalCarb - lm_carb;
                            Kcal = String.valueOf(ttKcal);
                            Fat = String.valueOf(ttFat);
                            Protein = String.valueOf(ttProtein);
                            Carb = String.valueOf(ttCarb);
                            db = healthyFoodDB.UpdateLogMuscleKcal(Kcal, Fat, Protein, Carb);
                            if (db) {
                                Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                                txtKcal.setText(Kcal);
                                txtFat.setText(String.format("%.1f", ttFat));
                                txtProtein.setText(String.format("%.1f", ttProtein));
                                txtCrab.setText(String.format("%.1f", ttCarb));
                                getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                            }

                        }else if (logExert.getCount() == 0){
                            Log.e("MDM Check", "logExert.getCount() == 0");
                            ttKcal = totalKcal - lm_kcal;
                            ttFat = totalFat - lm_fat;
                            ttProtein = totalProtein - lm_protein;
                            ttCarb = totalCarb - lm_carb;
                            Kcal = String.valueOf(ttKcal);
                            Fat = String.valueOf(ttFat);
                            Protein = String.valueOf(ttProtein);
                            Carb = String.valueOf(ttCarb);
                            db = healthyFoodDB.UpdateLogMuscleKcal(Kcal, Fat, Protein, Carb);
                            if (db) {
                                Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                                txtKcal.setText(Kcal);
                                txtFat.setText(String.format("%.1f", ttFat));
                                txtProtein.setText(String.format("%.1f", ttProtein));
                                txtCrab.setText(String.format("%.1f", ttCarb));
                                getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                            }


                        }else {
                            Log.e("MDMF Check","No No");
                        }

                    } else if(getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).getBoolean("logexert", false)){

                        Log.e("MDM Check", "Save Delete Exert");
                        //ลบlog Exert

                        logFood = healthyFoodDB.getTotalCalMuscle(lm_id,"ME10");
                        logExert = healthyFoodDB.getTotalCalMuscle(lm_id,"ME07");
                        lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));


                        if (logFood.getCount() > 0 ){
                            Log.e("MDM Check", "logFood > 0");
                            lm_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                            lm_fat = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Fat));
                            lm_protein = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Protein));
                            lm_carb = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Carb));

                            ttKcal =  totalKcal - lm_kcal + lem_kcal;
                            ttFat = fat;
                            ttProtein = protein;
                            ttCarb = carb;

                            Kcal = String.valueOf(ttKcal);
                            Fat = String.valueOf(ttFat);
                            Protein = String.valueOf(ttProtein);
                            Carb = String.valueOf(ttCarb);
                            db = healthyFoodDB.UpdateLogMuscleKcalExert(String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                            if (db) {
                                Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                                txtKcal.setText(Kcal);
                                txtFat.setText(String.format("%.1f", ttFat));
                                txtProtein.setText(String.format("%.1f", ttProtein));
                                txtCrab.setText(String.format("%.1f", ttCarb));
                                getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                            }


                        }else if (logFood.getCount() == 0 ){
                            Log.e("MDM Check", "logFood > 0");

                            ttKcal =  totalKcal + lem_kcal;
                            ttFat = fat;
                            ttProtein = protein;
                            ttCarb = carb;

                            Kcal = String.valueOf(ttKcal);
                            Fat = String.valueOf(ttFat);
                            Protein = String.valueOf(ttProtein);
                            Carb = String.valueOf(ttCarb);
                            db = healthyFoodDB.UpdateLogMuscleKcalExert(String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                            if (db) {
                                Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                                txtKcal.setText(Kcal);
                                txtFat.setText(String.format("%.1f", ttFat));
                                txtProtein.setText(String.format("%.1f", ttProtein));
                                txtCrab.setText(String.format("%.1f", ttCarb));
                                getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().clear().apply();
                            }else {
                                Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                            }

                        }else {
                            Log.e("MDMF Check","No No");
                        }





                    }else {
                        //ไม่ได้กดบันทึก
                        Log.e("MDMF Check", "No Save");

                        ttKcal = kcal;
                        ttFat = fat;
                        ttProtein = protein;
                        ttCarb = carb;
                        Kcal = String.valueOf(ttKcal);

                        txtKcal.setText(Kcal);
                        txtFat.setText(String.format("%.1f", ttFat));
                        txtProtein.setText(String.format("%.1f", ttProtein));
                        txtCrab.setText(String.format("%.1f", ttCarb));
                        getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                        getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();


                    }

                }
            } else {
                //ควบคุมน้ำหนัก
                Log.e("MDMF Check", "weight Control");

                if (getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).getBoolean("food", false)) {
                    //setup new data here  กดบันทึก

                    Log.e("MDMF Check", "Save Food");
                    logExert = healthyFoodDB.getTotalCalMuscle(lm_id,"ME07");
                    logFood = healthyFoodDB.getTotalCalMuscle(lm_id,"ME10");
                    lm_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                    lm_fat = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Fat));
                    lm_protein = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Protein));
                    lm_carb = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Carb));

                    if (logExert.getCount() > 0){
                        lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                        ttKcal = totalKcal - lm_kcal + lem_kcal;
                        ttFat = totalFat - lm_fat;
                        ttProtein = totalProtein - lm_protein;
                        ttCarb = totalCarb - lm_carb ;

                        Kcal = String.valueOf(ttKcal);
                        Fat = String.valueOf(ttFat);
                        Protein = String.valueOf(ttProtein);
                        Carb = String.valueOf(ttCarb);
                        db = healthyFoodDB.UpdateLogMuscleKcal(Kcal, Fat, Protein, Carb);
                        if (db) {
                            Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                            txtKcal.setText(Kcal);
                            txtFat.setText(String.format("%.1f", ttFat));
                            txtProtein.setText(String.format("%.1f", ttProtein));
                            txtCrab.setText(String.format("%.1f", ttCarb));
                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                        }
                    }else if (logExert.getCount() == 0){
                        ttKcal = totalKcal - lm_kcal;
                        ttFat = totalFat - lm_fat;
                        ttProtein = totalProtein - lm_protein;
                        ttCarb = totalCarb - lm_carb ;

                        Kcal = String.valueOf(ttKcal);
                        Fat = String.valueOf(ttFat);
                        Protein = String.valueOf(ttProtein);
                        Carb = String.valueOf(ttCarb);
                        db = healthyFoodDB.UpdateLogMuscleKcal(Kcal, Fat, Protein, Carb);
                        if (db) {
                            Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                            txtKcal.setText(Kcal);
                            txtFat.setText(String.format("%.1f", ttFat));
                            txtProtein.setText(String.format("%.1f", ttProtein));
                            txtCrab.setText(String.format("%.1f", ttCarb));
                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                        }
                    }else {
                        Log.e("MDMF Check","No No");
                    }






                }  else if(getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).getBoolean("excise", false)){
                    //ค่า kcal ที่เผาผลาญไปนำมาบวกกัน

//
                    Log.e("MDMF Check", "Save Exert");


                    logFood = healthyFoodDB.getTotalCalMuscle(lm_id,"ME10");
                    logExert = healthyFoodDB.getTotalCalMuscle(lm_id,"ME07");
                    lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));


                    if (logFood.getCount() > 0){
                        lm_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));


                        ttKcal =  totalKcal - lm_kcal + lem_kcal;
                        ttFat = fat;
                        ttProtein = protein;
                        ttCarb = carb;

                        db = healthyFoodDB.UpdateLogMuscleKcalExert(String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));
                        if (db){
                            Log.e("MDMF Update","Update Log Muscle");
                            txtKcal.setText(String.valueOf(ttKcal));
                            txtFat.setText(String.format("%.1f", ttFat));
                            txtProtein.setText(String.format("%.1f", ttProtein));
                            txtCrab.setText(String.format("%.1f", ttCarb));
                            getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDMF Update","Not Update Log Muscle");
                        }
                    }else if (logFood.getCount() == 0){

                        ttKcal =  totalKcal + lem_kcal;
                        ttFat = fat;
                        ttProtein = protein;
                        ttCarb = carb;

                        db = healthyFoodDB.UpdateLogMuscleKcalExert(String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                        if (db){
                            Log.e("MDMF Update","Update Log Muscle");
                            txtKcal.setText(String.valueOf(ttKcal));
                            txtFat.setText(String.format("%.1f", ttFat));
                            txtProtein.setText(String.format("%.1f", ttProtein));
                            txtCrab.setText(String.format("%.1f", ttCarb));
                            getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDMF Update","Not Update Log Muscle");
                        }
                    }else {
                        Log.e("MDMF Check","No No");
                    }

                } else if(getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).getBoolean("logfood", false)){
                    Log.e("MDM Check", "Save Delete Food");

                    //ลบlog Food

                    logExert = healthyFoodDB.getTotalCalMuscle(lm_id,"ME07");
                    logFood = healthyFoodDB.getTotalCalMuscle(lm_id,"ME10");
                    lm_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                    lm_fat = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Fat));
                    lm_protein = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Protein));
                    lm_carb = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Carb));

                    if (logExert.getCount() > 0){
                        Log.e("MDM Check", "logExert.getCount() > 0");
                        lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                        ttKcal =  totalKcal - lm_kcal + lem_kcal;
                        ttFat = totalFat - lm_fat;
                        ttProtein = totalProtein - lm_protein;
                        ttCarb = totalCarb - lm_carb;
                        Kcal = String.valueOf(ttKcal);
                        Fat = String.valueOf(ttFat);
                        Protein = String.valueOf(ttProtein);
                        Carb = String.valueOf(ttCarb);
                        db = healthyFoodDB.UpdateLogMuscleKcal(Kcal, Fat, Protein, Carb);
                        if (db) {
                            Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                            txtKcal.setText(Kcal);
                            txtFat.setText(String.format("%.1f", ttFat));
                            txtProtein.setText(String.format("%.1f", ttProtein));
                            txtCrab.setText(String.format("%.1f", ttCarb));
                            getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                        }

                    }else if (logExert.getCount() == 0){
                        Log.e("MDM Check", "logExert.getCount() == 0");
                        ttKcal = totalKcal - lm_kcal;
                        ttFat = totalFat - lm_fat;
                        ttProtein = totalProtein - lm_protein;
                        ttCarb = totalCarb - lm_carb;
                        Kcal = String.valueOf(ttKcal);
                        Fat = String.valueOf(ttFat);
                        Protein = String.valueOf(ttProtein);
                        Carb = String.valueOf(ttCarb);
                        db = healthyFoodDB.UpdateLogMuscleKcal(Kcal, Fat, Protein, Carb);
                        if (db) {
                            Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                            txtKcal.setText(Kcal);
                            txtFat.setText(String.format("%.1f", ttFat));
                            txtProtein.setText(String.format("%.1f", ttProtein));
                            txtCrab.setText(String.format("%.1f", ttCarb));
                            getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                        }


                    }else {
                        Log.e("MDMF Check","No No");
                    }


                } else if(getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).getBoolean("logexert", false)){
                    Log.e("MDM Check", "Save Delete Exert");
                    //ลบlog Exert

                    logExert = healthyFoodDB.getTotalCalMuscle(lm_id,"ME07");
                    logFood = healthyFoodDB.getTotalCalMuscle(lm_id,"ME10");
                    lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));



                    if (logFood.getCount() > 0 ){
                        Log.e("MDM Check", "logFood > 0");
                        lm_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                        lm_fat = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Fat));
                        lm_protein = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Protein));
                        lm_carb = logFood.getDouble(logFood.getColumnIndex(HealthyFoodDB.TLM_Carb));

                        ttKcal =  totalKcal - lm_kcal + lem_kcal;
                        ttFat = fat;
                        ttProtein = protein;
                        ttCarb = carb;

                        Kcal = String.valueOf(ttKcal);
                        Fat = String.valueOf(ttFat);
                        Protein = String.valueOf(ttProtein);
                        Carb = String.valueOf(ttCarb);
                        db = healthyFoodDB.UpdateLogMuscleKcalExert(String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                        if (db) {
                            Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                            txtKcal.setText(Kcal);
                            txtFat.setText(String.format("%.1f", ttFat));
                            txtProtein.setText(String.format("%.1f", ttProtein));
                            txtCrab.setText(String.format("%.1f", ttCarb));
                            getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                        }


                    }else if (logFood.getCount() == 0 ){
                        Log.e("MDM Check", "logFood > 0");

                        ttKcal =  totalKcal + lem_kcal;
                        ttFat = fat;
                        ttProtein = protein;
                        ttCarb = carb;

                        Kcal = String.valueOf(ttKcal);
                        Fat = String.valueOf(ttFat);
                        Protein = String.valueOf(ttProtein);
                        Carb = String.valueOf(ttCarb);
                        db = healthyFoodDB.UpdateLogMuscleKcalExert(String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                        if (db) {
                            Log.e("MDMF Update","Update Log Muscle TDM_Kcal");
                            txtKcal.setText(Kcal);
                            txtFat.setText(String.format("%.1f", ttFat));
                            txtProtein.setText(String.format("%.1f", ttProtein));
                            txtCrab.setText(String.format("%.1f", ttCarb));
                            getActivity().getSharedPreferences("exert_del", Context.MODE_PRIVATE).edit().clear().apply();
                        }else {
                            Log.e("MDMF Update","Not Update Log Muscle TDM_Kcal");
                        }

                    }else {
                        Log.e("MDMF Check","No No");
                    }


                }else {
                    //ไม่ได้กดบันทึก
                    Log.e("MDMF Check", "No Save");

                    ttKcal = kcal;
                    ttFat = fat;
                    ttProtein = protein;
                    ttCarb = carb;
                    Kcal = String.valueOf(ttKcal);

                    txtKcal.setText(Kcal);
                    txtFat.setText(String.format("%.1f", ttFat));
                    txtProtein.setText(String.format("%.1f", ttProtein));
                    txtCrab.setText(String.format("%.1f", ttCarb));
                    getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().clear().apply();
                    getActivity().getSharedPreferences("is_ex_added", Context.MODE_PRIVATE).edit().clear().apply();


                }
            }


        } else {
            //ยังไม่ได้กด ให้กลับไปเปิดหน้าคำถาม
            ((MainMuscleActivity) getActivity()).onQuestionTodayClkListener();
        }


        // txtCrab.setText( String.format( "Value of a: %.2f", a ) );

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
