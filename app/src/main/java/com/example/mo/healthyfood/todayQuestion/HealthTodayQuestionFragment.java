package com.example.mo.healthyfood.todayQuestion;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.example.mo.healthyfood.AlarmToastReceiverNor;
import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityNormal.TodayNormalQuestionActivity;
import com.example.mo.healthyfood.view.AlarmNotificationReceiverNor;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class HealthTodayQuestionFragment extends Fragment {

    RadioGroup rg_health;
 //   FancyButton btn_next;
    Bundle bundle;
    boolean db,mm,nn;

    HealthyFoodDB healthyFoodDB;

    Cursor user, weight,ac,goal,tg,p,LvEx,log;
    Double weightData,kcal,u_goal,plan = 0.0;
    int gender,height,age,flag,Kcal;
    String tg_id,p_id,tgName,LvExData,totalKcal,ln_id;
    Double stFat,stProtein,stCarb,edFat,edProtein,edCarb,TDEE,cdTDEE,fat,protein,carb;
    Boolean logDB,dd,bb,yy;

    Double gn_weight,ac_value;
    String HT,tu_id,AC_ID,formattedDate;
    int ac_id;

    Cursor dateLog,dateMuscle,tu;
    String dlog,dMus,u_id,TU_ID;

    Cursor  logFood,logExert,logTotal;


    public HealthTodayQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static HealthTodayQuestionFragment newInstance() {
        HealthTodayQuestionFragment fragment = new HealthTodayQuestionFragment();
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
        healthyFoodDB = new HealthyFoodDB(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_normal_question_today_health, container, false);
        initInstances(rootView, savedInstanceState);

        getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", false).apply();

        //date
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = df.format(c.getTime());


        weight = healthyFoodDB.getMaxWeight();
        weightData = weight.getDouble(weight.getColumnIndex(HealthyFoodDB.WE_Weight));
        //     Toast.makeText(getActivity(),"maxweight" + weightData,Toast.LENGTH_SHORT).show();



        user = healthyFoodDB.getUser();
        u_id = String.valueOf(user.getInt(user.getColumnIndex(HealthyFoodDB.U_ID)));
        TU_ID =String.valueOf( user.getInt(user.getColumnIndex(HealthyFoodDB.TU_ID)));
        gender = user.getInt(user.getColumnIndex(HealthyFoodDB.U_Gender));
        height = user.getInt(user.getColumnIndex(HealthyFoodDB.U_Height));
        age = user.getInt(user.getColumnIndex(HealthyFoodDB.U_Age));
        ac_id = user.getInt(user.getColumnIndex(HealthyFoodDB.AC_ID));

        AC_ID = String.valueOf(ac_id);

        ac  = healthyFoodDB.getActivities(AC_ID);
        ac_value = ac.getDouble(ac.getColumnIndex(HealthyFoodDB.AC_Values));

        tu = healthyFoodDB.getTypeUser(TU_ID);
        tu_id =tu.getString(tu.getColumnIndex(HealthyFoodDB.DTU_ID));

        goal = healthyFoodDB.getMaxGoalNormal();
        tg_id = goal.getString(goal.getColumnIndex(HealthyFoodDB.TG_ID));
        p_id = goal.getString(goal.getColumnIndex(HealthyFoodDB.P_ID));
        gn_weight = goal.getDouble(goal.getColumnIndex(HealthyFoodDB.GN_Weight));

        p = healthyFoodDB.getPlan(p_id);
        plan = p.getDouble(p.getColumnIndex(HealthyFoodDB.P_Values));

        rg_health.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                bundle = new Bundle();
                switch (checkedId) {
                    case R.id.rb_ht_fine:
                        HT = "HT01";
                        break;
                    case R.id.rb_ht_sick:
                        HT = "HT02";
                        break;

                }
            }
        });





        Toast.makeText(getActivity(),"TU_ID :" + tu_id +
        "\nTG_ID :" + tg_id,Toast.LENGTH_LONG).show();

        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        rg_health = (RadioGroup) rootView.findViewById(R.id.rg_health);
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


    private void startAlarm(boolean isNotification, boolean isRepeat) {
        AlarmManager manager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;

        if(!isNotification)
        {
            myIntent = new Intent(getActivity(),AlarmToastReceiverNor.class);
            pendingIntent = PendingIntent.getBroadcast(getActivity(),0,myIntent,0);
            //   startActivity(new Intent(MainActivity.this,NewActivity.class));

        }
        else{
            myIntent = new Intent(getActivity(),AlarmNotificationReceiverNor.class);
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
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        //24 ชม 1000 * 60 * 1440
        // manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),60*2000,pendingIntent); 2 นาที
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

                if(HT.equals("HT01")) { //สบายดี
                    if (tu_id.equals("TU01")) {
                        if (gender == 655) {
                            //ผู้หญิง ปกติ
                            TDEE = ((9.99 * weightData) + (6.25 * height) - (5 * age) - 161) * ac_value;
                            if (tg_id.equals("TG01")) {
                                //ลดน้ำหนัก
                                kcal = TDEE - plan;
                            } else if (tg_id.equals("TG03")) {
                                //เพิ่มน้ำหนัก
                                kcal = TDEE + plan;
                            } else if (tg_id.equals("TG02")) {
                                //ควบคุมน้ำหนัก
                                kcal = TDEE;
                            } else {
                                kcal = 1.00;
                            }
                        } else {
                            //ผู้ชายปกติ
                            TDEE = ((9.99 * weightData) + (6.25 * height) - (5 * age) + 5) * ac_value;
                            if (tg_id.equals("TG01")) {
                                //ลดน้ำหนัก
                                kcal = TDEE - plan;
                            } else if (tg_id.equals("TG03")) {
                                //เพิ่มน้ำหนัก
                                kcal = TDEE + plan;
                            } else if (tg_id.equals("TG02")) {
                                //ควบคุมน้ำหนัก
                                kcal = TDEE;
                            } else {
                                kcal = 2.00;
                            }
                        }

                    } else
                        Toast.makeText(getActivity(), "TM fine empty", Toast.LENGTH_SHORT).show();
                }else if(HT.equals("HT02")){ //ป่วย
                    if (tu_id.equals("TU01")) {
                        if (gender == 655) {
                            //ผู้หญิง ปกติ
                            kcal = ((9.99 * weightData) + (6.25 * height) - (5 * age) - 161) * 1.2;
                        } else {
                            //ผู้ชายปกติ
                            kcal = ((9.99 * weightData) + (6.25 * height) - (5 * age) + 5) * 1.2;
                        }

                    } else
                        Toast.makeText(getActivity(), "TM sick empty", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getActivity(), "HT empty", Toast.LENGTH_SHORT).show();

                if(kcal != null) {
                    Kcal = kcal.intValue();
                    totalKcal = String.valueOf(Kcal);


                    dateLog = healthyFoodDB.getMaxLogNormal();
                    dlog = dateLog.getString(dateLog.getColumnIndex(HealthyFoodDB.TD_Date));

                    if (dlog == null) {
                        db = healthyFoodDB.InsertNormalToday(u_id, HT);
                        logDB = healthyFoodDB.InsertLogNormal(u_id, totalKcal, totalKcal);
                        if (db && logDB) {
                            Log.e("Insert","Log Normal Insert");
                            getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", true).apply();
                            startAlarm(true,true);
                            ((TodayNormalQuestionActivity) getActivity()).onMainClkListener();
                        } else {
                            Log.e("Insert","Not Log Normal Insert");
                        }
                    } else if (dlog.equals(formattedDate)) {
                        log = healthyFoodDB.getLogNormal(formattedDate);
                        ln_id = String.valueOf(log.getInt(log.getColumnIndex(HealthyFoodDB.LN_ID)));

                        logFood = healthyFoodDB.getLogFoodNormal(ln_id);
                        logExert = healthyFoodDB.getLogExertNormal(ln_id);

                        if(logFood.getCount() > 0 && logExert.getCount() > 0){
                            Log.e("Check","logFood.getCount() > 0 && logExert.getCount() > 0");
                            dd = healthyFoodDB.deleteLogFoodNormal(ln_id);
                            bb  = healthyFoodDB.deleteLogExertNormal(ln_id);
                            yy = healthyFoodDB.deleteTotalcalNormal(ln_id);


                            if (dd && bb && yy){
                                Log.e("Delete","Delete LogFood LogExert Total");
                                mm = healthyFoodDB.updateNormalToday(u_id, HT);
                                nn = healthyFoodDB.UpdateLogNormal(u_id, totalKcal, totalKcal);

                                if (mm && nn) {
                                    Log.e("Update","Log Normal Update");
                                    getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", true).apply();
                                    startAlarm(true,true);
                                    ((TodayNormalQuestionActivity) getActivity()).onMainClkListener();
                                } else {
                                    Log.e("Update","Not Log Normal Update");
                                }
                            }else {
                                Log.e("Delete","Not Delete LogFood LogExert Total");
                            }
                        }else if (logFood.getCount() > 0 && logExert.getCount() == 0){
                            Log.e("Check","logFood.getCount() > 0 && logExert.getCount() == 0");
                            dd = healthyFoodDB.deleteLogFoodNormal(ln_id);
                            yy = healthyFoodDB.deleteTotalcalNormal(ln_id);


                            if (dd && yy){
                                Log.e("Delete","Delete LogFood LogExert Total");
                                mm = healthyFoodDB.updateNormalToday(u_id, HT);
                                nn = healthyFoodDB.UpdateLogNormal(u_id, totalKcal, totalKcal);

                                if (mm && nn) {
                                    Log.e("Update","Log Normal Update");
                                    getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", true).apply();
                                    startAlarm(true,true);
                                    ((TodayNormalQuestionActivity) getActivity()).onMainClkListener();
                                } else {
                                    Log.e("Update","Not Log Normal Update");
                                }
                            }else {
                                Log.e("Delete","Not Delete LogFood LogExert Total");
                            }

                        }else if (logFood.getCount() == 0 && logExert.getCount() > 0){
                            Log.e("Check","logFood.getCount() == 0 && logExert.getCount() > 0");
                            bb  = healthyFoodDB.deleteLogExertNormal(ln_id);
                            yy = healthyFoodDB.deleteTotalcalNormal(ln_id);


                            if (bb && yy){
                                Log.e("Delete","Delete LogFood LogExert Total");
                                mm = healthyFoodDB.updateNormalToday(u_id, HT);
                                nn = healthyFoodDB.UpdateLogNormal(u_id, totalKcal, totalKcal);

                                if (mm && nn) {
                                    Log.e("Update","Log Normal Update");
                                    getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", true).apply();
                                    startAlarm(true,true);
                                    ((TodayNormalQuestionActivity) getActivity()).onMainClkListener();
                                } else {
                                    Log.e("Update","Not Log Normal Update");
                                }
                            }else {
                                Log.e("Delete","Not Delete LogFood LogExert Total");
                            }
                        }else {

                                mm = healthyFoodDB.updateNormalToday(u_id, HT);
                                nn = healthyFoodDB.UpdateLogNormal(u_id, totalKcal, totalKcal);

                                if (mm && nn) {
                                    Log.e("Update","Log Normal Update");
                                    getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", true).apply();
                                    startAlarm(true,true);
                                    ((TodayNormalQuestionActivity) getActivity()).onMainClkListener();
                                } else {
                                    Log.e("Update","Not Log Normal Update");
                                }

                        }





                    } else {
                        db = healthyFoodDB.InsertNormalToday(u_id, HT);
                        logDB = healthyFoodDB.InsertLogNormal(u_id, totalKcal, totalKcal);
                        if (db && logDB) {
                            Log.e("Insert","Log Normal Insert");
                            getActivity().getSharedPreferences("is_today_added", Context.MODE_PRIVATE).edit().putBoolean("today", true).apply();
                            startAlarm(true,true);
                            ((TodayNormalQuestionActivity) getActivity()).onMainClkListener();
                        } else {
                            Log.e("Insert","Not Log Normal Insert");
                        }

                    }
                }else
                    Toast.makeText(getActivity(), "Kcal to null", Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
