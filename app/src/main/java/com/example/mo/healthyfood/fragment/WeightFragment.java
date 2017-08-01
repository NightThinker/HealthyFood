package com.example.mo.healthyfood.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.WeightActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class WeightFragment extends Fragment {

    EditText edtWeight;
    FancyButton btnSave;
    String weight,we_date,tu_id;
    HealthyFoodDB healthyFoodDB;
    boolean db;
    Cursor dateBase,user;
    Double we_weight;


    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());

    Date d1 = null;
    Date d2 = null;

    public WeightFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static WeightFragment newInstance() {
        WeightFragment fragment = new WeightFragment();
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
        healthyFoodDB = new HealthyFoodDB(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weight, container, false);
        initInstances(rootView, savedInstanceState);

//        user = healthyFoodDB.getUser();
//        tu_id = user.getString(user.getColumnIndex(HealthyFoodDB.TU_ID));



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight = edtWeight.getText().toString();
                if (edtWeight.getText().toString().isEmpty()) {
//                    edt_start_weight.setError("Invalid Weight");
//                    edt_start_weight.requestFocus();
                    Toast.makeText(getActivity(), "กรุณากรอกน้ำหนัก", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(edtWeight.getText().toString()) < 30) {
                    Toast.makeText(getActivity(), "ต้องน้ำหนัก 30 เป็นต้นไป", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(edtWeight.getText().toString()) > 200) {
                    Toast.makeText(getActivity(), "คุณน้ำหนักมากเกินไป", Toast.LENGTH_SHORT).show();
                } else {


                    dateBase = healthyFoodDB.getMaxWeight();
                    we_date = dateBase.getString(dateBase.getColumnIndex(HealthyFoodDB.WE_Date));
                    we_weight = dateBase.getDouble(dateBase.getColumnIndex(HealthyFoodDB.WE_Weight));

                    try{
                        d1 = df.parse(we_date);
                        d2 = df.parse(formattedDate);

                        Log.e("date","date Weight :" + d1);
                        Log.e("date","date Now :" + d2);

                        long diff = d2.getTime() - d1.getTime();
                        long diffDays = diff / (24 * 60 * 60 * 1000);

                        Log.e("Days",diffDays + "days");

                        if (diffDays == 0){
                            AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                            alog.setMessage("วันนี้คุณได้บันทึกน้ำหนักเรียบร้อยแล้ว");
                            alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ((WeightActivity) getActivity()).onMainNormalClkListener();
                                }
                            });
                            AlertDialog alertDialog = alog.create();
                            alertDialog.show();
                        }else if (diffDays < 7){
                            if(Math.abs(we_weight - Integer.valueOf(edtWeight.getText().toString())) > 2){
                                Toast.makeText(getActivity(), "เป็นไปไม่ได้", Toast.LENGTH_SHORT).show();
                            }else {
                                db = healthyFoodDB.InsertWeight(weight);
                                if (db) {
                                    Log.e("Insert","Insert Weight");
                                    ((WeightActivity) getActivity()).onMainNormalClkListener();
                                }else {
                                    Log.e("Insert","Not Insert Weight");
                                }
                            }

                        }else if (diffDays > 7){
                            db = healthyFoodDB.InsertWeight(weight);
                            if (db) {
                                Log.e("Insert","Insert Weight");
                                ((WeightActivity) getActivity()).onMainNormalClkListener();
                            }else {
                                Log.e("Insert","Not Insert Weight");
                            }
                        }else {
                            Toast.makeText(getActivity(),"งงต่อไป",Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e) {
                        e.printStackTrace();
                    }
//                    if(we_date.equals(formattedDate)){
//                        Toast.makeText(getActivity(), "วันนี้คุณได้บันทึกน้ำหนักเรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
//                    }else {
//
//                        db = healthyFoodDB.InsertWeight(weight);
//                        if (db) {
//                            Toast.makeText(getActivity(), "Weight Inserted", Toast.LENGTH_SHORT).show();
//                                ((WeightActivity) getActivity()).onMainNormalClkListener();
//                        } else
//                            Toast.makeText(getActivity(), "Weight Not Inserted", Toast.LENGTH_SHORT).show();
//                    }
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
        edtWeight = (EditText) rootView.findViewById(R.id.edtWeight);
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
