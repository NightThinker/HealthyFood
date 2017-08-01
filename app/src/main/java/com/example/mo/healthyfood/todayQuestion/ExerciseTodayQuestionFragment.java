package com.example.mo.healthyfood.todayQuestion;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Bundle;
import android.os.SystemClock;
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

import com.example.mo.healthyfood.AlarmNotificationReceiver;
import com.example.mo.healthyfood.AlarmToastReceiver;
import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.TodayMuscleQuestionActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class ExerciseTodayQuestionFragment extends Fragment {

    RadioGroup rg_exercise;
 //   FancyButton btn_next;
    HealthyFoodDB healthyFoodDB;

    String Exercise,tu_id,tm_id,p_id,tdm_kcal,AC_ID;
    Double weightData,acti,day;
    int bodyfat,gm_bodyfat,kcal,ac_id,days;
    Cursor user, weight,ac,BF,usermus,p,goal,dateLog,tu,log;
    Double LBM,BMR,tdee,ac_value,plan,gm_weight,TDEE;
    Boolean log_kcal,MCTD,dd,mm,db;

    Double ProteinG,ProteinKcal,CrabKcal,CrabG,FatKcal,FatG;
    String PG,CG,FG,dlog,formattedDate,u_id,um_id,TU_ID,lm_id;

    Cursor  logFood,logExert,logTotal;


    Random r = new Random();





    public ExerciseTodayQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ExerciseTodayQuestionFragment newInstance() {
        ExerciseTodayQuestionFragment fragment = new ExerciseTodayQuestionFragment();
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
        
        getActivity().setTitle("Exercise");
        healthyFoodDB = new HealthyFoodDB(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_muscle_question_today_exersice, container, false);
        initInstances(rootView, savedInstanceState);

        getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", false).apply();

        //date
        Calendar c = Calendar.getInstance();
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = df.format(c.getTime());


        weight = healthyFoodDB.getMaxWeight();
        weightData = weight.getDouble(weight.getColumnIndex(HealthyFoodDB.WE_Weight));
        //     Toast.makeText(getActivity(),"maxweight" + weightData,Toast.LENGTH_SHORT).show();

        BF = healthyFoodDB.getMaxBodyfat();
        bodyfat =  BF.getInt(BF.getColumnIndex(HealthyFoodDB.BF_BodyFat));


        user = healthyFoodDB.getUser();
        TU_ID = String.valueOf(user.getInt(user.getColumnIndex(HealthyFoodDB.TU_ID)));
        ac_id = user.getInt(user.getColumnIndex(HealthyFoodDB.AC_ID));

        AC_ID = String.valueOf(ac_id);


        ac  = healthyFoodDB.getActivities(AC_ID);
        ac_value = ac.getDouble(ac.getColumnIndex(HealthyFoodDB.AC_Values));

        tu = healthyFoodDB.getTypeUser(TU_ID);
        tu_id = tu.getString(tu.getColumnIndex(HealthyFoodDB.DTU_ID));

        goal = healthyFoodDB.getMaxGoalMuscle();
        u_id = String.valueOf(goal.getInt(goal.getColumnIndex(HealthyFoodDB.U_ID)));
        um_id = String.valueOf(goal.getInt(goal.getColumnIndex(HealthyFoodDB.UM_ID)));
        p_id = goal.getString(goal.getColumnIndex(HealthyFoodDB.P_ID));
        gm_weight = goal.getDouble(goal.getColumnIndex(HealthyFoodDB.GM_Weight));
        gm_bodyfat = goal.getInt(goal.getColumnIndex(HealthyFoodDB.GM_Bodyfat));

        usermus = healthyFoodDB.getUserMuscle(um_id);
        tm_id = usermus.getString(usermus.getColumnIndex(HealthyFoodDB.TM_ID));






        rg_exercise.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_noexercise:
                        Exercise = "ET01";
                        break;
                    case R.id.rb_exercise:
                        Exercise = "ET02";
                        break;
                }
            }
        });


        Log.d("DataMuscle","TM_ID : " + tm_id);



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

    private void startAlarm(boolean isNotification, boolean isRepeat) {
        AlarmManager manager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;

        if(!isNotification)
        {
            myIntent = new Intent(getActivity(),AlarmToastReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(getActivity(),0,myIntent,0);
            //   startActivity(new Intent(MainActivity.this,NewActivity.class));

        }
        else{
            myIntent = new Intent(getActivity(),AlarmNotificationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(getActivity(),0,myIntent,0);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        if(!isRepeat)
            manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime()+3000,pendingIntent);
        else {
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        //24 ชม 1000 * 60 * 1440
        // manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),60*2000,pendingIntent); 2 นาที
        //
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

                //ปู้ใช้แบบกล้ามเนื้อ
                if(tu_id.equals("TU02")){
                    p = healthyFoodDB.getPlan(p_id);
                    plan = p.getDouble(p.getColumnIndex(HealthyFoodDB.P_Values));


                    LBM = (weightData * (100 - bodyfat))/100;
                    BMR = 370 + (21.6 *LBM);
                    tdee = BMR * ac_value;

                    Log.d("DataMuscle","LBM : " + LBM);
                    Log.d("DataMuscle","BMR : " + BMR);
                    Log.d("DataMuscle","ac_val : " + ac_value);
                    Log.d("DataMuscle","tdee : " + tdee);

                    //ลดไขมัน
                    if(tm_id.equals("TM01")){
                        TDEE = tdee - plan;
                        kcal = TDEE.intValue();
                        tdm_kcal = String.valueOf(kcal);

                        //โปรตีน
                        ProteinG = weightData*1.5;
                        ProteinKcal = ProteinG * 4;
                        PG = String.valueOf(ProteinG);

                        //คาร์โบ
//                        int min = 50;
//                        int max = 55;
//                        int crab = r.nextInt((max-min)+1) + min;
                        double min = 50.0;
                        double max = 55.0;
                        double crab = min +(max - min) * r.nextDouble();
                        CrabKcal = TDEE * (crab/100);
                        CrabG = CrabKcal/4;
                        CG = String.valueOf(CrabG);

                        Log.d("DataMuscle","TDEE : " + TDEE);
                        Log.d("DataMuscle","FatKcal : " + FatKcal);
                        Log.d("DataMuscle","CrabRandom : " + crab);
                        Log.d("DataMuscle","CrabKcal : " + CrabKcal);
                        Log.d("DataMuscle","CrabGG : " + CrabG);

                        //ไขมัน
                        //Fat cal = Daily cal – Carb cal – Protein cal
                        FatKcal = TDEE - CrabKcal - ProteinKcal;
                        FatG = FatKcal / 9;
                        FG = String.valueOf(FatG);








                        //เพิ่มกล้ามเนื้อ
                    }else  if (tm_id.equals("TM02")) {
                        TDEE = tdee + plan;
                        kcal = TDEE.intValue();
                        tdm_kcal = String.valueOf(kcal);

                        //โปรตีน
                        ProteinG = weightData * 3;
                        ProteinKcal = ProteinG * 4;
                        PG = String.valueOf(ProteinG);

                        //คาร์โบ
//                        int min = 55;
//                        int max = 60;
//                        int crab = r.nextDouble((max - min) + 1) + min;

                        double min = 55.0;
                        double max = 60.0;
                        double crab = min +(max - min) * r.nextDouble();
                        CrabKcal = TDEE * (crab / 100);
                        CrabG = CrabKcal / 4;
                        CG = String.valueOf(CrabG);

                        //ไขมัน
                        //Fat cal = Daily cal – Carb cal – Protein cal
                        FatKcal = TDEE - CrabKcal - ProteinKcal;
                        FatG = FatKcal / 9;
                        FG = String.valueOf(FatG);

                        Log.d("DataMuscle","TDEE : " + TDEE);
                        Log.d("DataMuscle","FatKcal : " + FatKcal);
                        Log.d("DataMuscle","CrabRandom : " + crab);
                        Log.d("DataMuscle","CrabKcal : " + CrabKcal);
                        Log.d("DataMuscle","CrabGG : " + CrabG);

                        //ควบคุมน้ำหนัก
                    }else if(tm_id.equals("TM03")){
                        TDEE = tdee ;
                        kcal = TDEE.intValue();
                        tdm_kcal = String.valueOf(kcal);

                        //โปรตีน
                        ProteinG = weightData * 2;
                        ProteinKcal = ProteinG * 4;
                        PG = String.valueOf(ProteinG);

                        //คาร์โบ
//                        int min = 55;
//                        int max = 60;
//                        int crab = r.nextDouble((max - min) + 1) + min;

                        double min = 50.0;
                        double max = 55.0;
                        double crab = min +(max - min) * r.nextDouble();
                        CrabKcal = TDEE * (crab / 100);
                        CrabG = CrabKcal / 4;
                        CG = String.valueOf(CrabG);

                        //ไขมัน
                        //Fat cal = Daily cal – Carb cal – Protein cal
                        FatKcal = TDEE - CrabKcal - ProteinKcal;
                        FatG = FatKcal / 9;
                        FG = String.valueOf(FatG);

                        Log.d("DataMuscle","TDEE : " + TDEE);
                        Log.d("DataMuscle","FatKcal : " + FatKcal);
                        Log.d("DataMuscle","CrabRandom : " + crab);
                        Log.d("DataMuscle","CrabKcal : " + CrabKcal);
                        Log.d("DataMuscle","CrabGG : " + CrabG);
                    }else
                        Log.d("DataMuscle","TM Emtry");
                }

                dateLog = healthyFoodDB.getMaxLogMuscle();
                dlog = dateLog.getString(dateLog.getColumnIndex(HealthyFoodDB.TD_Date));

                if(dlog == null){
                    MCTD = healthyFoodDB.InsertMuscleToday(u_id,um_id,Exercise);
                    log_kcal = healthyFoodDB.InsertLogMuscle(u_id,um_id,tdm_kcal, FG, PG, CG,tdm_kcal, FG, PG, CG);
                    if (log_kcal && MCTD) {
                        Log.e("Insert","Log Muscle Insert");
                        getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", true).apply();
                        startAlarm(true,true);
                        ((TodayMuscleQuestionActivity) getActivity()).onMainMuscleClkListener();
                    } else {
                        Log.e("Insert","Not Log Muscle Insert");
                    }
                }else if (dlog.equals(formattedDate)) {
                    log = healthyFoodDB.getLogMuscle(formattedDate);
                    lm_id = String.valueOf(log.getInt(log.getColumnIndex(HealthyFoodDB.LM_ID)));

                    logFood = healthyFoodDB.getLogFoodMuscle(lm_id);
                    logExert = healthyFoodDB.getLogExertMuscle(lm_id);

                    if (logFood.getCount() > 0 && logExert.getCount() > 0){

                        Log.e("Check","logFood.getCount() > 0 && logExert.getCount() > 0");
                        dd = healthyFoodDB.deleteLogFoodMuscle(lm_id);
                        mm  = healthyFoodDB.deleteLogExertMuscle(lm_id);
                        db = healthyFoodDB.deleteTotalcalMuscle(lm_id);

                        if (dd && mm && db){
                            Log.e("Delete","Delete LogFood LogExert Total");
                            MCTD = healthyFoodDB.updateMuscleToday(u_id,um_id,Exercise);
                            log_kcal = healthyFoodDB.UpdateLogMuscle(u_id,um_id,tdm_kcal, FG, PG, CG,tdm_kcal, FG, PG, CG);

                            if (log_kcal &&  MCTD) {
                                Log.e("Update","Log Muscle Update");
                                getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", true).apply();
                                startAlarm(true,true);
                                ((TodayMuscleQuestionActivity) getActivity()).onMainMuscleClkListener();
                            } else {
                                Log.e("Update","Not Log Muscle Update");
                            }
                        }else {
                            Log.e("Delete","Not Delete LogFood LogExert Total");
                        }

                    }else if (logFood.getCount() > 0 && logExert.getCount() == 0){
                        Log.e("Check","logFood.getCount() > 0 && logExert.getCount() == 0");
                        dd = healthyFoodDB.deleteLogFoodMuscle(lm_id);
                        db = healthyFoodDB.deleteTotalcalMuscle(lm_id);

                        if (dd  && db){
                            Log.e("Delete","Delete LogFood LogExert Total");
                            MCTD = healthyFoodDB.updateMuscleToday(u_id,um_id,Exercise);
                            log_kcal = healthyFoodDB.UpdateLogMuscle(u_id,um_id,tdm_kcal, FG, PG, CG,tdm_kcal, FG, PG, CG);

                            if (log_kcal &&  MCTD) {
                                Log.e("Update","Log Muscle Update");
                                getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", true).apply();
                                startAlarm(true,true);
                                ((TodayMuscleQuestionActivity) getActivity()).onMainMuscleClkListener();
                            } else {
                                Log.e("Update","Not Log Muscle Update");
                            }
                        }else {
                            Log.e("Delete","Not Delete LogFood LogExert Total");
                        }
                    }else if (logFood.getCount() == 0 && logExert.getCount() > 0){
                        Log.e("Check","logFood.getCount() == 0 && logExert.getCount() > 0");
                        mm  = healthyFoodDB.deleteLogExertMuscle(lm_id);
                        db = healthyFoodDB.deleteTotalcalMuscle(lm_id);

                        if (dd && mm){
                            Log.e("Delete","Delete LogFood LogExert Total");
                            MCTD = healthyFoodDB.updateMuscleToday(u_id,um_id,Exercise);
                            log_kcal = healthyFoodDB.UpdateLogMuscle(u_id,um_id,tdm_kcal, FG, PG, CG,tdm_kcal, FG, PG, CG);

                            if (log_kcal &&  MCTD) {
                                Log.e("Update","Log Muscle Update");
                                getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", true).apply();
                                startAlarm(true,true);
                                ((TodayMuscleQuestionActivity) getActivity()).onMainMuscleClkListener();
                            } else {
                                Log.e("Update","Not Log Muscle Update");
                            }
                        }else {
                            Log.e("Delete","Not Delete LogFood LogExert Total");
                        }
                    }else {
                        MCTD = healthyFoodDB.updateMuscleToday(u_id,um_id,Exercise);
                        log_kcal = healthyFoodDB.UpdateLogMuscle(u_id,um_id,tdm_kcal, FG, PG, CG,tdm_kcal, FG, PG, CG);

                        if (log_kcal &&  MCTD) {
                            Log.e("Update","Log Muscle Update");
                            getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", true).apply();
                            startAlarm(true,true);
                            ((TodayMuscleQuestionActivity) getActivity()).onMainMuscleClkListener();
                        } else {
                            Log.e("Update","Not Log Muscle Update");
                        }
                    }






                } else {
                    MCTD = healthyFoodDB.InsertMuscleToday(u_id,um_id,Exercise);
                    log_kcal = healthyFoodDB.InsertLogMuscle(u_id,um_id,tdm_kcal, FG, PG, CG,tdm_kcal, FG, PG, CG);
                    if (log_kcal && MCTD) {
                        Log.e("Insert","Log Muscle Insert");
                        getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", true).apply();
                        startAlarm(true,true);
                        ((TodayMuscleQuestionActivity) getActivity()).onMainMuscleClkListener();
                    } else {
                        Log.e("Insert","Not Log Muscle Insert");
                    }
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
