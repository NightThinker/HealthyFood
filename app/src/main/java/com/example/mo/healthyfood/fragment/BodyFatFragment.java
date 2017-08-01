package com.example.mo.healthyfood.fragment;

import android.content.DialogInterface;
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
import com.example.mo.healthyfood.activityMuscle.BodyFatActivity;
import com.example.mo.healthyfood.activityMuscle.BodyFatQuestionActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class BodyFatFragment extends Fragment {

    EditText edtBodyFat, edtWeight;
    FancyButton btnSave;
    String BodyFat, Weight;
    HealthyFoodDB healthyFoodDB;
    boolean db, fi, mm;
    Integer ft, in, height, bodyfat;
    Double feet, inches, leanMass, FFMI, weightData, we_weight,bf_bodyfat;

    Cursor dateBase, user, weight, datebase;
    String bf_date, ffmi, we_date;

    Date d1 = null;
    Date d2 = null;
    Date d3 = null;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());

    public BodyFatFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static BodyFatFragment newInstance() {
        BodyFatFragment fragment = new BodyFatFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_bodyfat, container, false);
        initInstances(rootView, savedInstanceState);

        user = healthyFoodDB.getUser();
        height = user.getInt(user.getColumnIndex(HealthyFoodDB.U_Height));

        weight = healthyFoodDB.getMaxWeight();
        weightData = weight.getDouble(weight.getColumnIndex(HealthyFoodDB.WE_Weight));

        //cm to ft and in
        feet = (height / 2.54) / 12;
        ft = feet.intValue();
        inches = (feet - ft) * 12;
        in = inches.intValue();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BodyFat = edtBodyFat.getText().toString();
                Weight = edtWeight.getText().toString();
                bodyfat = Integer.valueOf(edtBodyFat.getText().toString());

                if (edtBodyFat.getText().toString().isEmpty() && edtWeight.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "กรุณาข้อมูลเกี่ยวกับตัวคุณ", Toast.LENGTH_SHORT).show();
                } else if (edtBodyFat.getText().toString().isEmpty() || edtWeight.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "กรุณาข้อมูลเกี่ยวกับตัวคุณให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(edtBodyFat.getText().toString()) < 5) {
                    Toast.makeText(getActivity(), "ต้องมีไขมันในร่างมากกว่า 5 เปอร์เซ็น", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(edtBodyFat.getText().toString()) > 100) {
                    Toast.makeText(getActivity(), "คุณเปอร์เซ็นไขมันในร่างกายมากเกินไป", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(edtWeight.getText().toString()) < 30) {
                    Toast.makeText(getActivity(), "ต้องน้ำหนัก 30 เป็นต้นไป", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(edtWeight.getText().toString()) > 200) {
                    Toast.makeText(getActivity(), "คุณน้ำหนักมากเกินไป", Toast.LENGTH_SHORT).show();
                } else {
                    dateBase = healthyFoodDB.getMaxBodyfat();
                    bf_date = dateBase.getString(dateBase.getColumnIndex(HealthyFoodDB.BF_Date));
                    bf_bodyfat = dateBase.getDouble(dateBase.getColumnIndex(HealthyFoodDB.BF_BodyFat));

                    datebase = healthyFoodDB.getMaxWeight();
                    we_date = datebase.getString(datebase.getColumnIndex(HealthyFoodDB.WE_Date));
                    we_weight = datebase.getDouble(datebase.getColumnIndex(HealthyFoodDB.WE_Weight));

                    leanMass = weightData * (1 - (bodyfat / 100));
                    FFMI = (leanMass / 2.2) / (((feet * 12) + in) * Math.pow(0.0254, 2)) * 2.20462;
                    ffmi = String.valueOf(FFMI);

                    Log.d("DataMuscle", "leanMass :" + leanMass);
                    Log.d("DataMuscle", "FFMI :" + FFMI);

                    try {

                        d1 = df.parse(bf_date);
                        d2 = df.parse(we_date);
                        d3 = df.parse(formattedDate);

                        Log.e("date", "date Bodyfat :" + d1);
                        Log.e("date", "date Weight :" + d2);
                        Log.e("date", "date Now :" + d3);

                        long diffs = d3.getTime() - d1.getTime();
                        long diffDaysBf = diffs / (24 * 60 * 60 * 1000);

                        long diff = d3.getTime() - d2.getTime();
                        long diffDaysWe = diff / (24 * 60 * 60 * 1000);

                        if (diffDaysBf == 0 && diffDaysWe == 0) {
                            AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                            alog.setMessage("วันนี้คุณได้บันทึกน้ำหนักและไขมันในร่างกายเรียบร้อยแล้ว");
                            alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ((BodyFatActivity) getActivity()).onMainClkListener();
                                }
                            });
                            AlertDialog alertDialog = alog.create();
                            alertDialog.show();
                        } else if (diffDaysBf == 0 && diffDaysWe > 0) {
                            if (diffDaysWe < 7) {
                                if (Math.abs(we_weight - Integer.valueOf(edtWeight.getText().toString())) > 2) {
                                    Toast.makeText(getActivity(), "เป็นไปไม่ได้", Toast.LENGTH_SHORT).show();
                                } else {
                                    db = healthyFoodDB.InsertWeight(Weight);
                                    if (db) {
                                        Log.d("DataBase ", "Weight Inserted");
                                        ((BodyFatActivity) getActivity()).onMainClkListener();
                                    } else {
                                        Log.d("DataBase ", "Not Weight Inserted");
                                    }
                                }
                            } else {
                                db = healthyFoodDB.InsertWeight(Weight);
                                if (db) {
                                    Log.d("DataBase ", "Weight Inserted");
                                    ((BodyFatActivity) getActivity()).onMainClkListener();
                                } else {
                                    Log.d("DataBase ", "Not Weight Inserted");
                                }
                            }

                        } else if (diffDaysBf > 0 && diffDaysWe == 0) {
                            if (diffDaysBf < 7) {
                                if (Math.abs(bf_bodyfat - Integer.valueOf(edtBodyFat.getText().toString())) > 1){
                                    Toast.makeText(getActivity(), "เป็นไปไม่ได้", Toast.LENGTH_SHORT).show();
                                }else {
                                    db = healthyFoodDB.InsertBodyFat(BodyFat);
                                    if (db) {
                                        Log.d("DataBase ", "BodyFat Inserted");
                                        fi = healthyFoodDB.InsertFFMI(ffmi);
                                        if (fi) {
                                            Log.d("DataBase ", "FFMI Inserted");
                                            ((BodyFatActivity) getActivity()).onMainClkListener();
                                        } else {
                                            Log.d("DataBase ", "FFMI Not Inserted");
                                        }
                                    } else {
                                        Log.d("DataBase ", "Not BodyFat Inserted");
                                    }
                                }

                            } else {
                                db = healthyFoodDB.InsertBodyFat(BodyFat);
                                if (db) {
                                    Log.d("DataBase ", "BodyFat Inserted");
                                    fi = healthyFoodDB.InsertFFMI(ffmi);
                                    if (fi) {
                                        Log.d("DataBase ", "FFMI Inserted");
                                        ((BodyFatActivity) getActivity()).onMainClkListener();
                                    } else {
                                        Log.d("DataBase ", "FFMI Not Inserted");
                                    }
                                } else {
                                    Log.d("DataBase ", "Not BodyFat Inserted");
                                }
                            }

                        } else {
                            db = healthyFoodDB.InsertWeight(Weight);
                            mm = healthyFoodDB.InsertBodyFat(BodyFat);
                            if (db && mm) {
                                Log.d("DataBase ", "BodyFat And Weight Inserted");
                                fi = healthyFoodDB.InsertFFMI(ffmi);
                                if (fi) {
                                    Log.d("DataBase ", "FFMI Inserted");
                                    ((BodyFatActivity) getActivity()).onMainClkListener();
                                } else {
                                    Log.d("DataBase ", "FFMI Not Inserted");
                                }
                            } else {
                                Log.d("DataBase ", "BodyFat And Weight Not Inserted");
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    if (bf_date.equals(formattedDate) && we_date.equals(formattedDate)) {
//                        Toast.makeText(getActivity(), "วันนี้คุณได้บันทึกน้ำหนักและไขมันในร่างกายเรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
//                        ((BodyFatActivity) getActivity()).onMainClkListener();
//                    } else if (bf_date.equals(formattedDate) && !we_date.equals(formattedDate)) {
//                        db = healthyFoodDB.InsertWeight(Weight);
//                        if (db) {
//                            Log.d("DataBase ", "Weight Inserted");
//                            ((BodyFatActivity) getActivity()).onMainClkListener();
//                        } else {
//                            Log.d("DataBase ", "Not Weight Inserted");
//                        }
//                    } else if (!bf_date.equals(formattedDate) && we_date.equals(formattedDate)) {
//                        db = healthyFoodDB.InsertBodyFat(BodyFat);
//                        if (db) {
//                            Log.d("DataBase ", "BodyFat Inserted");
//                            fi = healthyFoodDB.InsertFFMI(ffmi);
//                            if (fi) {
//                                Log.d("DataBase ", "FFMI Inserted");
//                                ((BodyFatActivity) getActivity()).onMainClkListener();
//                            } else {
//                                Log.d("DataBase ", "FFMI Not Inserted");
//                            }
//                        } else {
//                            Log.d("DataBase ", "Not BodyFat Inserted");
//                        }
//                    } else {
//                        db = healthyFoodDB.InsertWeight(Weight);
//                        mm = healthyFoodDB.InsertBodyFat(BodyFat);
//                        if (db && mm) {
//                            Log.d("DataBase ", "BodyFat And Weight Inserted");
//                            fi = healthyFoodDB.InsertFFMI(ffmi);
//                            if (fi) {
//                                Log.d("DataBase ", "FFMI Inserted");
//                                ((BodyFatActivity) getActivity()).onMainClkListener();
//                            } else {
//                                Log.d("DataBase ", "FFMI Not Inserted");
//                            }
//                        } else {
//                            Log.d("DataBase ", "BodyFat And Weight Not Inserted");
//                        }
//
//
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
        edtBodyFat = (EditText) rootView.findViewById(R.id.edtBodyFat);
        edtWeight = (EditText) rootView.findViewById(R.id.edt_weight);
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
