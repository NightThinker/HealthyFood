package com.example.mo.healthyfood.MainMuscle;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.MainMuscleActivity;
import com.example.mo.healthyfood.adapter.LogAdapter;
import com.example.mo.healthyfood.add.AddData;

import java.util.ArrayList;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class LogMuscleFragment extends Fragment {

    FloatingActionButton add;
    TextView txtTotalKcal, txtFood, txtExert, txtNet, txtOver;
    HealthyFoodDB healthyFoodDB;
    ListView listViewExert, listViewSnack, listViewDinner, listViewLunch, listViewBreakfast, listViewTea, listViewSupper;
    LogAdapter log;
    Cursor logMuscle, logExert, logTotalExert,logTotalBreakfast,logTotalLunch,logTotalDinner,logTotalSnack,logTotalTea,logTotalSupper;
    Cursor logTotalFood;
    String LM_ID;
    Integer totalKcal, kcal, food, lem_kcal, over, ex_kcal = 0, net, kcal_exert = 0, kcal_food = 0;
    Integer breakfast = 0 , lunch = 0,dinner = 0,snack = 0,tea = 0,supper = 0;
    ImageButton btnDel;


    MainMuscleActivity mainMuscleActivity;


    public LogMuscleFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static LogMuscleFragment newInstance(Bundle args) {
        LogMuscleFragment fragment = new LogMuscleFragment();
        //     Bundle args = new Bundle();
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
        View rootView = inflater.inflate(R.layout.fragment_muscle_main_log, container, false);
        initInstances(rootView, savedInstanceState);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("date", getArguments().getString("date"));
                mainMuscleActivity.onAddClkListener(bundle);
                //   ((MainMuscleActivity) getActivity()).onAddClkListener(bundle);

            }
        });

//        btnDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //ToDo ลบตาม fn_id me_id
//
//            }
//        });

        return rootView;
    }


    private void showListExert() {
        ArrayList<AddData> exert;

        exert = healthyFoodDB.getDetailLogExertMuscle(LM_ID);
        log = new LogAdapter(exert, getActivity());
        listViewExert.setAdapter(log);
        listViewExert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle= new Bundle();
                AddData addData = (AddData) parent.getItemAtPosition(position);
                bundle.putString("lm_id",addData.getLM());
                bundle.putString("m_id",addData.getME());
                bundle.putString("ex_id",addData.getFM());
                bundle.putString("lem_id",addData.getLFM());
                bundle.putString("date", getArguments().getString("date"));
                mainMuscleActivity.onEditShowExertdDetail(bundle);
            }
        });

        setDynamicHeight(listViewExert);
    }

    private void showListTea() {
        ArrayList<AddData> tea;

        tea = healthyFoodDB.getDetailLogTeaMuscle(LM_ID);
        log = new LogAdapter(tea, getActivity());
        listViewTea.setAdapter(log);
        listViewTea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle= new Bundle();
                AddData addData = (AddData) parent.getItemAtPosition(position);
                bundle.putString("lm_id",addData.getLM());
                bundle.putString("m_id",addData.getME());
                bundle.putString("f_id",addData.getFM());
                bundle.putString("lfm_id",addData.getLFM());
                bundle.putString("date", getArguments().getString("date"));
                mainMuscleActivity.onEditShowFoodDetail(bundle);
            }
        });

        setDynamicHeight(listViewTea);
    }

    private void showListSnack() {
        ArrayList<AddData> snack;

        snack = healthyFoodDB.getDetailLogSnackMuscle(LM_ID);
        log = new LogAdapter(snack, getActivity());
        listViewSnack.setAdapter(log);
        listViewSnack.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle= new Bundle();
                AddData addData = (AddData) parent.getItemAtPosition(position);
                bundle.putString("lm_id",addData.getLM());
                bundle.putString("m_id",addData.getME());
                bundle.putString("f_id",addData.getFM());
                bundle.putString("lfm_id",addData.getLFM());
                bundle.putString("date", getArguments().getString("date"));
                mainMuscleActivity.onEditShowFoodDetail(bundle);
            }
        });

        setDynamicHeight(listViewSnack);
    }

    private void showListSupper() {
        ArrayList<AddData> supper;

        supper = healthyFoodDB.getDetailLogSupperMuscle(LM_ID);
        log = new LogAdapter(supper, getActivity());
        listViewSupper.setAdapter(log);
        listViewSupper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle= new Bundle();
                AddData addData = (AddData) parent.getItemAtPosition(position);
                bundle.putString("lm_id",addData.getLM());
                bundle.putString("m_id",addData.getME());
                bundle.putString("f_id",addData.getFM());
                bundle.putString("lfm_id",addData.getLFM());
                bundle.putString("date", getArguments().getString("date"));
                mainMuscleActivity.onEditShowFoodDetail(bundle);
            }
        });

        setDynamicHeight(listViewSupper);
    }

    private void showListDinner() {
        ArrayList<AddData> dinner;

        dinner = healthyFoodDB.getDetailLogDinnerMuscle(LM_ID);
        log = new LogAdapter(dinner, getActivity());
        listViewDinner.setAdapter(log);
        listViewDinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle= new Bundle();
                AddData addData = (AddData) parent.getItemAtPosition(position);
                bundle.putString("lm_id",addData.getLM());
                bundle.putString("m_id",addData.getME());
                bundle.putString("f_id",addData.getFM());
                bundle.putString("lfm_id",addData.getLFM());
                bundle.putString("date", getArguments().getString("date"));
                mainMuscleActivity.onEditShowFoodDetail(bundle);
            }
        });

        setDynamicHeight(listViewDinner);
    }

    private void showListLunch() {
        ArrayList<AddData> lunch;

        lunch = healthyFoodDB.getDetailLogLunchMuscle(LM_ID);
        log = new LogAdapter(lunch, getActivity());
        listViewLunch.setAdapter(log);
        listViewLunch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle= new Bundle();
                AddData addData = (AddData) parent.getItemAtPosition(position);
                bundle.putString("lm_id",addData.getLM());
                bundle.putString("m_id",addData.getME());
                bundle.putString("f_id",addData.getFM());
                bundle.putString("lfm_id",addData.getLFM());
                bundle.putString("date", getArguments().getString("date"));
                mainMuscleActivity.onEditShowFoodDetail(bundle);
            }
        });

        setDynamicHeight(listViewLunch);
    }

    private void showListBreakfast() {

        ArrayList<AddData> breakfast;

        breakfast = healthyFoodDB.getDetailLogBreakfastMuscle(LM_ID);
        log = new LogAdapter(breakfast, getActivity());
        listViewBreakfast.setAdapter(log);
        listViewBreakfast.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle= new Bundle();
                AddData addData = (AddData) parent.getItemAtPosition(position);
                bundle.putString("lm_id",addData.getLM());
                bundle.putString("m_id",addData.getME());
                bundle.putString("f_id",addData.getFM());
                bundle.putString("lfm_id",addData.getLFM());
                bundle.putString("date", getArguments().getString("date"));
                mainMuscleActivity.onEditShowFoodDetail(bundle);
            }
        });

        setDynamicHeight(listViewBreakfast);

    }


    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        add = (FloatingActionButton) rootView.findViewById(R.id.add);

        listViewBreakfast = (ListView) rootView.findViewById(R.id.listViewBreakfast);
        listViewLunch = (ListView) rootView.findViewById(R.id.listViewLunch);
        listViewDinner = (ListView) rootView.findViewById(R.id.listViewDinner);
        listViewSupper = (ListView) rootView.findViewById(R.id.listViewSupper);
        listViewSnack = (ListView) rootView.findViewById(R.id.listViewSnack);
        listViewTea = (ListView) rootView.findViewById(R.id.listViewTea);
        listViewExert = (ListView) rootView.findViewById(R.id.listViewExert);


        txtTotalKcal = (TextView) rootView.findViewById(R.id.txtTotalKcal);
        txtFood = (TextView) rootView.findViewById(R.id.txtFood);
        txtExert = (TextView) rootView.findViewById(R.id.txtExert);
        txtNet = (TextView) rootView.findViewById(R.id.txtNet);
        txtOver = (TextView) rootView.findViewById(R.id.txtOver);


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


    public static void setDynamicHeight(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        //check adapter if null
        if (adapter == null) {
            return;
        }
        int height = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            height += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = height + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(layoutParams);
        listView.requestLayout();
    }

    @Override
    public void onResume() {
        super.onResume();

        logMuscle = healthyFoodDB.getLogMuscle(getArguments().getString("date"));
        LM_ID = String.valueOf(logMuscle.getInt(logMuscle.getColumnIndex(HealthyFoodDB.LM_ID)));
        totalKcal = logMuscle.getInt(logMuscle.getColumnIndex(HealthyFoodDB.TDM_TotalKcal));
        kcal = logMuscle.getInt(logMuscle.getColumnIndex(HealthyFoodDB.TDM_Kcal));

        logExert = healthyFoodDB.getMaxLogExertMuscle(LM_ID);
        lem_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.LEM_Kcal));

        logTotalExert = healthyFoodDB.getTotalCalMuscle(LM_ID, "ME07");
        logTotalFood = healthyFoodDB.getTotalCalMuscle(LM_ID,"ME10");
//        logTotalBreakfast = healthyFoodDB.getTotalCalMuscle(LM_ID,"ME01");
//        logTotalLunch = healthyFoodDB.getTotalCalMuscle(LM_ID,"ME02");
//        logTotalDinner = healthyFoodDB.getTotalCalMuscle(LM_ID,"ME03");
//        logTotalSupper = healthyFoodDB.getTotalCalMuscle(LM_ID,"ME04");
//        logTotalSnack = healthyFoodDB.getTotalCalMuscle(LM_ID,"ME05");
//        logTotalTea = healthyFoodDB.getTotalCalMuscle(LM_ID,"ME06");


        txtTotalKcal.setText(String.valueOf(totalKcal));
        net = totalKcal - kcal;
        txtNet.setText(String.valueOf(net));

        if (getActivity().getSharedPreferences("food_added", Context.MODE_PRIVATE).getBoolean("logfood", false)) {
            //เพิ่มค่าใน txtFood

                if (logTotalFood.getCount() > 0 && logTotalExert.getCount() > 0){
                   kcal_exert = logTotalExert.getInt(logTotalExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                    kcal_food = logTotalFood.getInt(logTotalFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                    txtFood.setText(String.valueOf(kcal_food));
                    txtExert.setText(String.valueOf(kcal_exert));


                }else if (logTotalFood.getCount() == 0 && logTotalExert.getCount() > 0 ){
                    kcal_exert = logTotalExert.getInt(logTotalExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                    txtFood.setText(String.valueOf(kcal_food));
                    txtExert.setText(String.valueOf(kcal_exert));

                }else if (logTotalFood.getCount() > 0 && logTotalExert.getCount() == 0){
                    kcal_food = logTotalFood.getInt(logTotalFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                    txtFood.setText(String.valueOf(kcal_food));
                    txtExert.setText(String.valueOf(kcal_exert));
                }else {
                    Log.e("Check","No No ");
                }

//
//                food = totalKcal - kcal + kcal_exert;
//                txtFood.setText(String.valueOf(food));
//                txtExert.setText(String.valueOf(kcal_exert));
//             else {
//                food = totalKcal - kcal;
//                txtFood.setText(String.valueOf(food));
//                txtExert.setText("0");
//            }
            if (kcal < 0) {
                over = Math.abs(kcal);
                txtOver.setText(String.valueOf(over));
            } else if (kcal > 0) {
                txtOver.setText("0");
            } else {
                Toast.makeText(getActivity(), "เช็คอะไรอีกละ", Toast.LENGTH_SHORT).show();
            }
            getActivity().getSharedPreferences("food_added", Context.MODE_PRIVATE).edit().clear().apply();
        } else if (getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).getBoolean("logexert", false)) {
            //เพิ่มค่าใน exercise
            //           lem_kcal += lem_kcal;

            if (logTotalFood.getCount() > 0 && logTotalExert.getCount() > 0){
                kcal_exert = logTotalExert.getInt(logTotalExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                kcal_food = logTotalFood.getInt(logTotalFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                txtFood.setText(String.valueOf(kcal_food));
                txtExert.setText(String.valueOf(kcal_exert));


            }else if (logTotalFood.getCount() == 0 && logTotalExert.getCount() > 0 ){
                kcal_exert = logTotalExert.getInt(logTotalExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                txtFood.setText(String.valueOf(kcal_food));
                txtExert.setText(String.valueOf(kcal_exert));

            }else if (logTotalFood.getCount() > 0 && logTotalExert.getCount() == 0){
                kcal_food = logTotalFood.getInt(logTotalFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                txtFood.setText(String.valueOf(kcal_food));
                txtExert.setText(String.valueOf(kcal_exert));
            }else {
                Log.e("Check","No No ");
            }

//            logTotal = healthyFoodDB.getTotalCalMuscle(LM_ID, "ME07");
//            kcal_exert = logTotal.getInt(logTotal.getColumnIndex(HealthyFoodDB.TLM_Kcal));
//            txtExert.setText(String.valueOf(kcal_exert));
//            if (kcal == totalKcal) {
//                txtFood.setText("0");
//            } else {
//                food = totalKcal - kcal + kcal_exert;
//                txtFood.setText(String.valueOf(food));
//            }

            if (kcal < 0) {
                over = Math.abs(kcal);
                txtOver.setText(String.valueOf(over));
            } else if (kcal > 0) {
                txtOver.setText("0");
                // net = totalKcal - kcal;
                //  txtNet.setText(String.valueOf(kcal));
            } else {
                Toast.makeText(getActivity(), "เช็คอะไรอีกละ", Toast.LENGTH_SHORT).show();

            }

            getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().clear().apply();
        } else {


            if (kcal == totalKcal) {
                txtFood.setText("0");
            } else if (kcal != totalKcal) {

                if (logTotalFood.getCount() > 0 && logTotalExert.getCount() > 0){
                    kcal_exert = logTotalExert.getInt(logTotalExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                    kcal_food = logTotalFood.getInt(logTotalFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                    txtFood.setText(String.valueOf(kcal_food));
                    txtExert.setText(String.valueOf(kcal_exert));


                }else if (logTotalFood.getCount() == 0 && logTotalExert.getCount() > 0){
                    kcal_exert = logTotalExert.getInt(logTotalExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                    txtFood.setText(String.valueOf(kcal_food));
                    txtExert.setText(String.valueOf(kcal_exert));

                }else if (logTotalFood.getCount() > 0 && logTotalExert.getCount() == 0){
                    kcal_food = logTotalFood.getInt(logTotalFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                    txtFood.setText(String.valueOf(kcal_food));
                    txtExert.setText(String.valueOf(kcal_exert));
                }else {
                    txtFood.setText(String.valueOf(kcal_food));
                    txtExert.setText(String.valueOf(kcal_exert));
                }

//                logTotal = healthyFoodDB.getTotalCalMuscle(LM_ID, "ME07");
//
//                if (logTotal.getCount() > 0) {
//                    kcal_exert = logTotal.getInt(logTotal.getColumnIndex(HealthyFoodDB.TLM_Kcal));
//                    food = totalKcal - kcal + kcal_exert;
//                    txtFood.setText(String.valueOf(food));
//                    txtExert.setText(String.valueOf(kcal_exert));
//                } else {
//                    food = totalKcal - kcal;
//                    txtFood.setText(String.valueOf(food));
//                }


            } else {
                Toast.makeText(getActivity(), "เช็คอะไรอีกละ", Toast.LENGTH_SHORT).show();
            }


//            if (lem_kcal == 0) {
//                txtExert.setText("0");
//            } else if (lem_kcal > 0) {
//
//                if (logTotalExert.getCount() > 0) {
//                    kcal_exert = logTotalExert.getInt(logTotalExert.getColumnIndex(HealthyFoodDB.TLM_Kcal));
//                    txtExert.setText(String.valueOf(kcal_exert));
//                } else {
//                    txtExert.setText("0");
//                }
//            } else {
//                Toast.makeText(getActivity(), "เช็คอะไรอีกละ", Toast.LENGTH_SHORT).show();
//            }

            if (kcal < 0) {
                over = Math.abs(kcal);
                txtOver.setText(String.valueOf(over));
            } else if (kcal > 0) {
                txtOver.setText("0");
                // net = totalKcal - kcal;
                //  txtNet.setText(String.valueOf(kcal));
            } else {
                Toast.makeText(getActivity(), "เช็คอะไรอีกละ", Toast.LENGTH_SHORT).show();

            }
        }


        showListBreakfast();
        showListLunch();
        showListDinner();
        showListSupper();
        showListSnack();
        showListTea();
        showListExert();
    }
}
