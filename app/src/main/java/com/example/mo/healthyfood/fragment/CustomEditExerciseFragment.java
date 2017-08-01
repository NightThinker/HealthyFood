package com.example.mo.healthyfood.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.CreateExerciseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class CustomEditExerciseFragment extends Fragment {

//    EditText edtNameFood, edtCal;
//    FancyButton btnSave;
//    HealthyFoodDB healthyFoodDB;
//    boolean db, de;
//    String pos, fu_id, NameFood, Cal,al_id,tf_id;
//    Cursor unit,user,brand;

    EditText edtNameEx,edtHour,edtMinute,edtKcal;
    FancyButton btnEdit,btnDel;
    HealthyFoodDB healthyFoodDB;
    Cursor user,logMus,logNor,maxEx,tu;
    String tu_id,name,hour,minute,cal,LM_ID,LN_ID,me_id,ex_id,TU_ID;
    Integer time;
    Boolean db,mm;


    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());


    public CustomEditExerciseFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CustomEditExerciseFragment newInstance() {
        CustomEditExerciseFragment fragment = new CustomEditExerciseFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_exercise_create_custom_edit, container, false);
        initInstances(rootView, savedInstanceState);

        user = healthyFoodDB.getUser();
        TU_ID = String.valueOf(user.getInt(user.getColumnIndex(HealthyFoodDB.TU_ID)));

        tu = healthyFoodDB.getTypeUser(TU_ID);
        tu_id = tu.getString(tu.getColumnIndex(HealthyFoodDB.DTU_ID));

        logMus = healthyFoodDB.getMaxLogMuscle();
        LM_ID = String.valueOf(logMus.getInt(logMus.getColumnIndex(HealthyFoodDB.LM_ID)));

        logNor = healthyFoodDB.getMaxLogNormal();
        LN_ID = String.valueOf(logNor.getInt(logNor.getColumnIndex(HealthyFoodDB.LN_ID)));

        me_id = "ME09";



        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edtNameEx.getText().toString();
                hour = edtHour.getText().toString();
                minute = edtMinute.getText().toString();
                cal = edtKcal.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "กรุณากรอกชื่อการออกกำลังกาย", Toast.LENGTH_SHORT).show();
                }else {
                    if(hour.isEmpty() && minute.isEmpty() && cal.isEmpty()){
                        //กรณีไม่ได้กรอกทั้งคู่
                        Toast.makeText(getActivity(), "กรุณากรอกเวลาและจำนวนแคลอรี่ที่ใช้ในการออกกำลังกาย", Toast.LENGTH_SHORT).show();
                    }else if(hour.isEmpty() && (!minute.isEmpty()) && (!cal.isEmpty())){
                        //กรณีที่กรอกแค่นาที
                        if (Integer.parseInt(minute) > 60) {
                            //ถ้านาทีมากกว่า 60
                            Toast.makeText(getActivity(), "คุณกรอกเวลาเกิน 60 นาที", Toast.LENGTH_SHORT).show();
                        }else {
                            time = Integer.parseInt(minute);

                            if(tu_id.equals("TU01")){
                                //ปกคิ
                                db = healthyFoodDB.InsertCustomExert(name,String.valueOf(time),cal);
                                if(db){
                                    Toast.makeText(getActivity(), "Custom Exert Inserted",Toast.LENGTH_SHORT).show();
                                    maxEx = healthyFoodDB.getMaxExert();
                                    ex_id = String.valueOf(maxEx.getInt(maxEx.getColumnIndex(HealthyFoodDB.EX_ID)));
                                    mm = healthyFoodDB.InsertLogExerciseNormal(LN_ID,me_id,ex_id,String.valueOf(time),cal);
                                    if(mm){
                                        Toast.makeText(getActivity(), "LogNormal Exert Inserted",Toast.LENGTH_SHORT).show();
                                        ((CreateExerciseActivity)getActivity()).onExertNormalClkListener();
                                    }else {
                                        Toast.makeText(getActivity(), "LogNormal Exert Not Inserted",Toast.LENGTH_SHORT).show();
                                    }

                                }else {
                                    Toast.makeText(getActivity(), "Custom Exert Not Inserted",Toast.LENGTH_SHORT).show();
                                }
                            }else if(tu_id.equals("TU02")){
                                //กล้ามเนื้อ
                                db = healthyFoodDB.InsertCustomExert(name,String.valueOf(time),cal);
                                if(db){
                                    Toast.makeText(getActivity(), "Custom Exert Inserted",Toast.LENGTH_SHORT).show();
                                    maxEx = healthyFoodDB.getMaxExert();
                                    ex_id = String.valueOf(maxEx.getInt(maxEx.getColumnIndex(HealthyFoodDB.EX_ID)));
                                    mm = healthyFoodDB.InsertLogExerciseMuscle(LM_ID,me_id,ex_id,String.valueOf(time),cal);
                                    if(mm){
                                        Toast.makeText(getActivity(), "LogMuscle Exert Inserted",Toast.LENGTH_SHORT).show();
                                        ((CreateExerciseActivity)getActivity()).onExertMuscleClkListener();
                                    }else {
                                        Toast.makeText(getActivity(), "LogMuscle Exert Not Inserted",Toast.LENGTH_SHORT).show();
                                    }

                                }else {
                                    Toast.makeText(getActivity(), "Custom Exert Not Inserted",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getActivity(), "tu หาย", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }else if((!hour.isEmpty()) && (minute.isEmpty()) && (!cal.isEmpty()) ){
                        //กรณีที่กรอกแคชั่มโมงี
                        if(Integer.parseInt(hour) > 8){
                            //ถ้าชั่วโมงมากกว่า 8
                            Toast.makeText(getActivity(), "เป็นไปไม่ได้เลยที่ออกกำลังกาย 8 ชั่วโมง ", Toast.LENGTH_SHORT).show();
                        }else {
                            time = Integer.parseInt(hour) * 60;

                            if(tu_id.equals("TU01")){
                                //ปกคิ
                                db = healthyFoodDB.InsertCustomExert(name,String.valueOf(time),cal);
                                if(db){
                                    Toast.makeText(getActivity(), "Custom Exert Inserted",Toast.LENGTH_SHORT).show();
                                    maxEx = healthyFoodDB.getMaxExert();
                                    ex_id = String.valueOf(maxEx.getInt(maxEx.getColumnIndex(HealthyFoodDB.EX_ID)));
                                    mm = healthyFoodDB.InsertLogExerciseNormal(LN_ID,me_id,ex_id,String.valueOf(time),cal);
                                    if(mm){
                                        Toast.makeText(getActivity(), "LogNormal Exert Inserted",Toast.LENGTH_SHORT).show();
                                        ((CreateExerciseActivity)getActivity()).onExertNormalClkListener();
                                    }else {
                                        Toast.makeText(getActivity(), "LogNormal Exert Not Inserted",Toast.LENGTH_SHORT).show();
                                    }

                                }else {
                                    Toast.makeText(getActivity(), "Custom Exert Not Inserted",Toast.LENGTH_SHORT).show();
                                }
                            }else if(tu_id.equals("TU02")){
                                //กล้ามเนื้อ
                                db = healthyFoodDB.InsertCustomExert(name,String.valueOf(time),cal);
                                if(db){
                                    Toast.makeText(getActivity(), "Custom Exert Inserted",Toast.LENGTH_SHORT).show();
                                    maxEx = healthyFoodDB.getMaxExert();
                                    ex_id = String.valueOf(maxEx.getInt(maxEx.getColumnIndex(HealthyFoodDB.EX_ID)));
                                    mm = healthyFoodDB.InsertLogExerciseMuscle(LM_ID,me_id,ex_id,String.valueOf(time),cal);
                                    if(mm){
                                        Toast.makeText(getActivity(), "LogMuscle Exert Inserted",Toast.LENGTH_SHORT).show();
                                        ((CreateExerciseActivity)getActivity()).onExertMuscleClkListener();
                                    }else {
                                        Toast.makeText(getActivity(), "LogMuscle Exert Not Inserted",Toast.LENGTH_SHORT).show();
                                    }

                                }else {
                                    Toast.makeText(getActivity(), "Custom Exert Not Inserted",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(getActivity(), "tu หาย", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }else {
                        //กรณีที่กรอกทั้งคู่
                        time = (Integer.parseInt(hour) * 60) + Integer.parseInt(minute);

                        if(tu_id.equals("TU01")){
                            //ปกคิ
                            db = healthyFoodDB.InsertCustomExert(name,String.valueOf(time),cal);
                            if(db){
                                Toast.makeText(getActivity(), "Custom Exert Inserted",Toast.LENGTH_SHORT).show();
                                maxEx = healthyFoodDB.getMaxExert();
                                ex_id = String.valueOf(maxEx.getInt(maxEx.getColumnIndex(HealthyFoodDB.EX_ID)));
                                mm = healthyFoodDB.InsertLogExerciseNormal(LN_ID,me_id,ex_id,String.valueOf(time),cal);
                                if(mm){
                                    Toast.makeText(getActivity(), "LogNormal Exert Inserted",Toast.LENGTH_SHORT).show();
                                    ((CreateExerciseActivity)getActivity()).onExertNormalClkListener();
                                }else {
                                    Toast.makeText(getActivity(), "LogNormal Exert Not Inserted",Toast.LENGTH_SHORT).show();
                                }

                            }else {
                                Toast.makeText(getActivity(), "Custom Exert Not Inserted",Toast.LENGTH_SHORT).show();
                            }
                        }else if(tu_id.equals("TU02")){
                            //กล้ามเนื้อ
                            db = healthyFoodDB.InsertCustomExert(name,String.valueOf(time),cal);
                            if(db){
                                Toast.makeText(getActivity(), "Custom Exert Inserted",Toast.LENGTH_SHORT).show();
                                maxEx = healthyFoodDB.getMaxExert();
                                ex_id = String.valueOf(maxEx.getInt(maxEx.getColumnIndex(HealthyFoodDB.EX_ID)));
                                mm = healthyFoodDB.InsertLogExerciseMuscle(LM_ID,me_id,ex_id,String.valueOf(time),cal);
                                if(mm){
                                    Toast.makeText(getActivity(), "LogMuscle Exert Inserted",Toast.LENGTH_SHORT).show();
                                    ((CreateExerciseActivity)getActivity()).onExertMuscleClkListener();
                                }else {
                                    Toast.makeText(getActivity(), "LogMuscle Exert Not Inserted",Toast.LENGTH_SHORT).show();
                                }

                            }else {
                                Toast.makeText(getActivity(), "Custom Exert Not Inserted",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getActivity(), "tu หาย", Toast.LENGTH_SHORT).show();
                        }
                    }
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
        edtNameEx = (EditText) rootView.findViewById(R.id.edtNameExert) ;
        edtHour = (EditText) rootView.findViewById(R.id.edtHour);
        edtMinute = (EditText) rootView.findViewById(R.id.edtMinute);
        edtKcal = (EditText) rootView.findViewById(R.id.edtKcal);
   //     btnSave = (FancyButton) rootView.findViewById(R.id.btnSave);


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
