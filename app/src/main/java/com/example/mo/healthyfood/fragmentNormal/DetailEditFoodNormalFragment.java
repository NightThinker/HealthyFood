package com.example.mo.healthyfood.fragmentNormal;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mo.healthyfood.CustomOnItemSelectedListener;
import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityNormal.EditShowFoodNormalActivity;
import com.example.mo.healthyfood.activityNormal.ShowFoodNormalActivity;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class DetailEditFoodNormalFragment extends Fragment {
    TextView txtNameFood, txtNbCal, txtRun, txtWalk, txtSwim, txtBicycle;
    Spinner spNumbers, spUnitFood;
    HealthyFoodDB healthyFoodDB;
    Cursor food, detailFood, unit, nameUnit, log, we, total, totalFood, logFood;
    String f_id, NameFood, fu_id, nUnit, count, m_id, tf_id, ln_id, lfn_id;
    FancyButton btnEdit, btnDel;
    boolean logDB, detailFoodDB, bb, del, mm;
    Double weight, walk, bicycle, swim, run;
    String totalKcal, LN_ID;
    int l_id, f_kcal, kcal, c_count, tdn_kcal, walking, bicycles, swimming, runing, tln_kcal, ttKcalFood;
    int lfn_amount, lfn_kcal, ttKcal;

    EditShowFoodNormalActivity editShowFoodNormalActivity;


    public DetailEditFoodNormalFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static DetailEditFoodNormalFragment newInstance(Bundle args) {
        DetailEditFoodNormalFragment fragment = new DetailEditFoodNormalFragment();
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
        editShowFoodNormalActivity = (EditShowFoodNormalActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_normal_food_detail_edit, container, false);
        initInstances(rootView, savedInstanceState);

        Toast.makeText(getActivity(), "Fid = " + getArguments().getString("f_id"),
                Toast.LENGTH_SHORT).show();


        //TODO: iy[8jk f_id มา
        f_id = getArguments().getString("f_id");
        m_id = getArguments().getString("m_id");
        ln_id = getArguments().getString("ln_id");
        lfn_id = getArguments().getString("lfn_id");

        Log.e("Values", "date : " + getArguments().getString("date"));
        Log.e("Values", "f_id : " + getArguments().getString("f_id"));
        Log.e("Values", "m_id : " + getArguments().getString("m_id"));

        Log.e("Values", "ln_id : " + getArguments().getString("ln_id"));
        Log.e("Values", "lfn_id : " + getArguments().getString("lfn_id"));


        logFood = healthyFoodDB.getLogFoodNormal(lfn_id, ln_id, m_id, f_id);
        lfn_amount = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.LFN_Amount));
        lfn_kcal = logFood.getInt(logFood.getColumnIndex(HealthyFoodDB.LFN_Kcal));

        food = healthyFoodDB.getShowFoodNormal(f_id);
        NameFood = food.getString(food.getColumnIndex(HealthyFoodDB.FN_Name));
        tf_id = food.getString(food.getColumnIndex(HealthyFoodDB.TF_ID));
        Toast.makeText(getActivity(), "name = " + NameFood, Toast.LENGTH_SHORT).show();


        we = healthyFoodDB.getMaxWeight();
        weight = we.getDouble(we.getColumnIndex(HealthyFoodDB.WE_Weight));

        unit = healthyFoodDB.getUnitNormal(f_id);
        fu_id = unit.getString(unit.getColumnIndex(HealthyFoodDB.FU_ID));


        detailFood = healthyFoodDB.getDetailFoodNormal(f_id, fu_id);
        f_kcal = detailFood.getInt(detailFood.getColumnIndex(HealthyFoodDB.FN_Kcal));


        log = healthyFoodDB.getLogNormal(getArguments().getString("date"));
        LN_ID = String.valueOf(log.getInt(log.getColumnIndex(HealthyFoodDB.LN_ID)));
        tdn_kcal = log.getInt(log.getColumnIndex(HealthyFoodDB.TDN_Kcal));

        txtNameFood.setText(NameFood);

//        addListenerOnSpinnerItemSelection();

        addItemsOnUnitFood();

        if (tf_id.equals("TF01")) {
            //อาหารจานเดียว
            addItemsOnNumberFastFood();
        } else if (tf_id.equals("TF02")) {
            //ของหวาน
            addItemsOnNumberFastFood();
        } else if (tf_id.equals("TF03")) {
            //ผลไม้
            addItemsOnNumberFruit();
        } else if (tf_id.equals("TF04")) {
            //เครื่องดื่ม
            addItemsOnNumberFastFood();
        } else if (tf_id.equals("TF05")) {
            //ซุปเปอร์มาร์เก็ต
        } else if (tf_id.equals("TF06")) {
            //อื่นๆ
        } else {
            Log.i("Empty", "TF_ID Empty");
        }


        spNumbers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                

                count = parent.getItemAtPosition(position).toString();

                Toast.makeText(getActivity(),
                        "count : " + count,
                        Toast.LENGTH_SHORT).show();

                c_count = Integer.parseInt(count);

                kcal = c_count * f_kcal;

                walk = kcal / (0.0175 * weight * 2.0);
                walking = walk.intValue();

                run = kcal / (0.0175 * weight * 7.0);
                runing = run.intValue();


                bicycle = kcal / (0.0175 * weight * 6.0);
                bicycles = bicycle.intValue();


                swim = kcal / (0.0175 * weight * 4.0);
                swimming = swim.intValue();


                totalKcal = String.valueOf(kcal);

                txtNbCal.setText(totalKcal);


                txtWalk.setText(String.valueOf(walking));
                txtRun.setText(String.valueOf(runing));
                txtSwim.setText(String.valueOf(swimming));
                txtBicycle.setText(String.valueOf(bicycles));

                if (c_count == 1 && tdn_kcal < kcal) {
                    AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                    alog.setMessage("จำนวนแคลอรี่ที่คุณกำลังทานมากกว่าแคลอรี่ที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกทานอาหารที่มีจำนวนแคลลอรี่ที่น้อยกว่า" + kcal);
                    alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editShowFoodNormalActivity.onMainClkListener();
//                            Bundle bundle = new Bundle();
//                            bundle.putString("m_id",m_id);
//                            bundle.putString("date",getArguments().getString("date"));
//                            editShowFoodNormalActivity.onFoodClkListener(bundle);
                            //  ((ShowFoodNormalActivity)getActivity()).onFoodClkListener(bundle);
                        }
                    });
                    alog.setNegativeButton("ไม่เป็นไร", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "จะกินตามนี้มีปัญหาป่ะ", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog alertDialog = alog.create();
                    alertDialog.show();
                } else if (c_count > 1 && tdn_kcal < kcal) {
                    AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                    alog.setMessage("จำนวนแคลอรี่ที่คุณกำลังทานมากกว่าแคลอรี่ที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกจำนวนอาหารที่ทานให้น้อยกว่านี้");
                    alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "เลือกให้น้อยละสิ", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alog.setNegativeButton("ไม่เป็นไร", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "จะกินตามนี้มีปัญหาป่ะ", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                detailFoodDB = healthyFoodDB.UpdateLogFoodNormal(lfn_id, ln_id, m_id, f_id, fu_id, count, totalKcal);
                if (detailFoodDB) {
                    Log.e("Update", "Update Log Food Normal");
                    total = healthyFoodDB.getTotalCalNormal(ln_id, m_id);
                    totalFood = healthyFoodDB.getTotalCalNormal(ln_id, "ME10");

                    if (total.getCount() > 0 && totalFood.getCount() > 0) {
                        ttKcalFood = totalFood.getInt(totalFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                        ArrayList<Integer> TotalKcal = healthyFoodDB.getKcalFoodNormal(ln_id, m_id);
                        int KcalFood = 0;
                        for (int i = 0; i < TotalKcal.size(); i++) {
                            KcalFood += TotalKcal.get(i);
                        }
                        Log.e("KcalFood", "KcalFood :" + KcalFood);

                        ttKcal = ttKcalFood - lfn_kcal + kcal;

                        bb = healthyFoodDB.UpdateTotalCalNormal(ln_id, m_id, String.valueOf(KcalFood));
                        mm = healthyFoodDB.UpdateTotalCalNormal(ln_id, "ME10", String.valueOf(ttKcal));
                        if (bb && mm) {
                            Log.e("Update", "Update Total Cal Normal");
                            Toast.makeText(getActivity(), "LogFood Inserted", Toast.LENGTH_SHORT).show();
                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().putBoolean("food", true).apply();
                            getActivity().getSharedPreferences("food_added", Context.MODE_PRIVATE).edit().putBoolean("logfood", true).apply();
                            ((EditShowFoodNormalActivity) getActivity()).onMainClkListener();
                        } else {
                            Log.e("Update", "Not Update Total Cal Normal");
                        }

                    } else if (total.getCount() > 0 && totalFood.getCount() == 0) {
                        Log.e("Check", "Sure? total > 0 && totalFood == 0");

                    } else if (total.getCount() == 0 && totalFood.getCount() > 0) {
                        Log.e("Check", "Sure?");
                        ttKcalFood = totalFood.getInt(totalFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                        ArrayList<Integer> TotalKcal = healthyFoodDB.getKcalFoodNormal(ln_id, m_id);
                        int KcalFood = 0;
                        for (int i = 0; i < TotalKcal.size(); i++) {
                            KcalFood += TotalKcal.get(i);
                        }
                        Log.e("KcalFood", "KcalFood :" + KcalFood);
                        ttKcal = ttKcalFood - kcal;
                        bb = healthyFoodDB.UpdateTotalCalNormal(ln_id, m_id, String.valueOf(KcalFood));
                        mm = healthyFoodDB.UpdateTotalCalNormal(ln_id, "ME10", String.valueOf(ttKcal));
                        if (bb && mm) {
                            Log.e("Update", "Update Total Cal Normal");
                            Toast.makeText(getActivity(), "LogFood Inserted", Toast.LENGTH_SHORT).show();
                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().putBoolean("food", true).apply();
                            getActivity().getSharedPreferences("food_added", Context.MODE_PRIVATE).edit().putBoolean("logfood", true).apply();
                            ((EditShowFoodNormalActivity) getActivity()).onMainClkListener();
                        } else {
                            Log.e("Update", "Not Update Total Cal Normal");
                        }

                    } else {
                        Log.e("Check", "No No");
                        bb = healthyFoodDB.InsertTotalCalNormal(ln_id, m_id, String.valueOf(kcal));
                        mm = healthyFoodDB.InsertTotalCalNormal(ln_id, "ME10", String.valueOf(kcal));
                        if (bb && mm) {
                            Log.e("Insert", "Insert Total Cal Normal");
                            Toast.makeText(getActivity(), "LogFood Inserted", Toast.LENGTH_SHORT).show();
                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().putBoolean("food", true).apply();
                            getActivity().getSharedPreferences("food_added", Context.MODE_PRIVATE).edit().putBoolean("logfood", true).apply();
                            ((EditShowFoodNormalActivity) getActivity()).onMainClkListener();
                        } else {
                            Log.e("Insert", "Not Insert Total Cal Normal");
                        }

                    }


                } else {
                    Log.e("Update", "Not Update Log Food Normal");
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del = healthyFoodDB.deleteLogFoodEditNormal(lfn_id, ln_id, m_id, f_id);
                if (del) {
                    Log.e("Delete", "Delete Log Food Normal");
                    total = healthyFoodDB.getTotalCalNormal(ln_id, m_id);
                    totalFood = healthyFoodDB.getTotalCalNormal(ln_id, "ME10");

                    if(total.getCount() > 0 && totalFood.getCount() > 0){
                        Log.e("DEFN Check","total > 0 && totalFood > 0");
                        ttKcalFood = totalFood.getInt(totalFood.getColumnIndex(HealthyFoodDB.TLN_Kcal));

                        ArrayList<Integer> TotalKcal = healthyFoodDB.getKcalFoodNormal(ln_id, m_id);
                        int KcalFood = 0;
                        for (int i = 0; i < TotalKcal.size(); i++) {
                            KcalFood += TotalKcal.get(i);
                        }
                        Log.e("KcalFood", "KcalFood :" + KcalFood);
                        ttKcal = ttKcalFood - lfn_kcal ;

                        bb = healthyFoodDB.UpdateTotalCalNormal(ln_id, m_id, String.valueOf(KcalFood));
                        mm = healthyFoodDB.UpdateTotalCalNormal(ln_id, "ME10", String.valueOf(ttKcal));
                        if (bb && mm) {
                            Log.e("Update", "Update Total Cal Normal");
                            getActivity().getSharedPreferences("food_del", Context.MODE_PRIVATE).edit().putBoolean("logfood", true).apply();
                            ((EditShowFoodNormalActivity) getActivity()).onMainClkListener();
                        } else {
                            Log.e("Update", "Not Update Total Cal Normal");
                        }
                    }else if (total.getCount() == 0 && totalFood.getCount() > 0){
                        Log.e("DEFN Check","total == 0 && totalFood > 0");

                    }else if (total.getCount() > 0 && totalFood.getCount() == 0){
                        Log.e("DEFN Check","total > 0 && totalFood == 0");

                    }else {
                        Log.e("DEFN Check","total= 0 && totalFood = 0");
                    }



                } else {
                    Log.e("Delete", "Not Delete Log Food Normal");
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
        txtNameFood = (TextView) rootView.findViewById(R.id.txtNameFood);
        txtNbCal = (TextView) rootView.findViewById(R.id.txtNbCal);
        spNumbers = (Spinner) rootView.findViewById(R.id.spNumbers);
        spUnitFood = (Spinner) rootView.findViewById(R.id.spUnitFood);
        txtWalk = (TextView) rootView.findViewById(R.id.txtWalk);
        txtBicycle = (TextView) rootView.findViewById(R.id.txtBicycle);
        txtSwim = (TextView) rootView.findViewById(R.id.txtSwim);
        txtRun = (TextView) rootView.findViewById(R.id.txtRun);
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

    public void addItemsOnNumberFastFood() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNumbers.setAdapter(dataAdapter);
    }

    public void addItemsOnNumberFruit() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNumbers.setAdapter(dataAdapter);
    }

    // add items into spinner dynamically
    public void addItemsOnUnitFood() {
//
//        List<String> list = new ArrayList<String>();
//        list.add("list 1");
//        list.add("list 2");
//        list.add("list 3");
        List<String> list = healthyFoodDB.getAllUnit(fu_id);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnitFood.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spNumbers.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
//    public void addListenerOnButton() {
//
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getActivity(),
//                        "OnClickListener : " +
//                                "\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()) +
//                                "\nSpinner 2 : " + String.valueOf(spinner2.getSelectedItem()),
//                        Toast.LENGTH_SHORT).show();
//            }
//
//        });
//    }


}
