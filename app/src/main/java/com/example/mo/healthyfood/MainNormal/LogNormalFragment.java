package com.example.mo.healthyfood.MainNormal;

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
import com.example.mo.healthyfood.activityNormal.MainNormalActivity;
import com.example.mo.healthyfood.adapter.LogAdapter;
import com.example.mo.healthyfood.add.AddData;

import java.util.ArrayList;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class LogNormalFragment extends Fragment {

    TextView txtTotalKcal,txtFood,txtExert,txtNet,txtOver;
    FloatingActionButton add;
    ListView listViewExert,listViewSnack,listViewDinner,listViewLunch,listViewBreakfast;
    LogAdapter log;
    HealthyFoodDB healthyFoodDB;
    Cursor ttKcal,logKcal,logExert,logTotal;
    int LID,totalKcal,maxLogKcal,food,Kcal,len_kcal,over,net,kcal_exert = 0;
    String date,LN_ID;
    ImageButton btnDel;
 //   ArrayList<AddData> detail;

    MainNormalActivity mainNormalActivity;

    public LogNormalFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static LogNormalFragment newInstance(Bundle args) {
        LogNormalFragment fragment = new LogNormalFragment();
     //   Bundle args = new Bundle();
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
        View rootView = inflater.inflate(R.layout.fragment_normal_main_log, container, false);
        initInstances(rootView, savedInstanceState);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("date",getArguments().getString("date"));
                mainNormalActivity.onAddClkListener(bundle);
              //  ((MainNormalActivity) getActivity()).onAddClkListener(bundle);
            }
        });


        return rootView;
    }

    private void showListExert() {
        ArrayList<AddData> exert;
        exert = healthyFoodDB.getDetailLogExertNormal(LN_ID);
        log = new LogAdapter(exert,getActivity());
        listViewExert.setAdapter(log);
        listViewExert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle= new Bundle();
                AddData addData = (AddData) parent.getItemAtPosition(position);
                bundle.putString("ln_id",addData.getLM());
                bundle.putString("m_id",addData.getME());
                bundle.putString("ex_id",addData.getFM());
                bundle.putString("len_id",addData.getLFM());
                bundle.putString("date", getArguments().getString("date"));
                mainNormalActivity.onEditShowExertdDetail(bundle);
            }
        });

        setDynamicHeight(listViewExert);


    }

    private void showListSnack() {
        ArrayList<AddData> snack ;

        snack = healthyFoodDB.getDetailLogSnackNormal(LN_ID);
        log = new LogAdapter(snack,getActivity());
        listViewSnack.setAdapter(log);
        listViewSnack.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle= new Bundle();
                AddData addData = (AddData) parent.getItemAtPosition(position);
                bundle.putString("ln_id",addData.getLM());
                bundle.putString("m_id",addData.getME());
                bundle.putString("f_id",addData.getFM());
                bundle.putString("lfn_id",addData.getLFM());
                bundle.putString("date", getArguments().getString("date"));
                mainNormalActivity.onEditShowFoodDetail(bundle);
            }
        });

        setDynamicHeight(listViewSnack);
    }

    private void showListDinner() {
        ArrayList<AddData> dinner ;

        dinner = healthyFoodDB.getDetailLogDinnerNormal(LN_ID);
        log = new LogAdapter(dinner,getActivity());
        listViewDinner.setAdapter(log);
        listViewDinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle= new Bundle();
                AddData addData = (AddData) parent.getItemAtPosition(position);
                bundle.putString("ln_id",addData.getLM());
                bundle.putString("m_id",addData.getME());
                bundle.putString("f_id",addData.getFM());
                bundle.putString("lfn_id",addData.getLFM());
                bundle.putString("date", getArguments().getString("date"));
                mainNormalActivity.onEditShowFoodDetail(bundle);
            }
        });

        setDynamicHeight(listViewDinner);
    }

    private void showListLunch() {
        ArrayList<AddData> lunch ;

        lunch = healthyFoodDB.getDetailLogLunchNormal(LN_ID);
        log = new LogAdapter(lunch,getActivity());
        listViewLunch.setAdapter(log);
        listViewLunch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle= new Bundle();
                AddData addData = (AddData) parent.getItemAtPosition(position);
                bundle.putString("ln_id",addData.getLM());
                bundle.putString("m_id",addData.getME());
                bundle.putString("f_id",addData.getFM());
                bundle.putString("lfn_id",addData.getLFM());
                bundle.putString("date", getArguments().getString("date"));
                mainNormalActivity.onEditShowFoodDetail(bundle);
            }
        });

        setDynamicHeight(listViewLunch);
    }

    private void showListBreakfast() {

        final ArrayList<AddData> breakfast ;

        breakfast = healthyFoodDB.getDetailLogBreakfastNormal(LN_ID);
        log = new LogAdapter(breakfast,getActivity());
        listViewBreakfast.setAdapter(log);
        listViewBreakfast.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle= new Bundle();
                AddData addData = (AddData) parent.getItemAtPosition(position);
                bundle.putString("ln_id",addData.getLM());
                bundle.putString("m_id",addData.getME());
                bundle.putString("f_id",addData.getFM());
                bundle.putString("lfn_id",addData.getLFM());
                bundle.putString("date", getArguments().getString("date"));
                mainNormalActivity.onEditShowFoodDetail(bundle);
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
     //listView = (ListView) rootView.findViewById(R.id.listView);

        listViewBreakfast = (ListView) rootView.findViewById(R.id.listViewBreakfast);
        listViewLunch =(ListView) rootView.findViewById(R.id.listViewLunch);
        listViewDinner = (ListView) rootView.findViewById(R.id.listViewDinner);
        listViewSnack = (ListView) rootView.findViewById(R.id.listViewSnack);
        listViewExert = (ListView) rootView.findViewById(R.id.listViewExert);

       // TextView txtTotalKcal,txtFood,txtExert,txtNet,txtOver;
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

        ttKcal = healthyFoodDB.getLogNormal(getArguments().getString("date"));
        LN_ID = String.valueOf(ttKcal.getInt(ttKcal.getColumnIndex(HealthyFoodDB.LN_ID)));
        totalKcal = ttKcal.getInt(ttKcal.getColumnIndex(HealthyFoodDB.TDN_TotalKcal));
        Kcal = ttKcal.getInt(ttKcal.getColumnIndex(HealthyFoodDB.TDN_Kcal));

        logExert = healthyFoodDB.getMaxLogExertNormal(LN_ID);
        len_kcal = logExert.getInt(logExert.getColumnIndex(HealthyFoodDB.LEN_Kcal));

        txtTotalKcal.setText(String.valueOf(totalKcal));
        net = totalKcal - Kcal;
        txtNet.setText(String.valueOf(net));

        if (getActivity().getSharedPreferences("food_added", Context.MODE_PRIVATE).getBoolean("logfood", false)) {
            //เพิ่มค่าใน txtFood
            logTotal = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
            if (logTotal.getCount() > 0){
                kcal_exert = logTotal.getInt(logTotal.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                food = totalKcal - Kcal + kcal_exert;
                txtFood.setText(String.valueOf(food));
                txtExert.setText(String.valueOf(kcal_exert));
            }else {
                food = totalKcal - Kcal;
                txtFood.setText(String.valueOf(food));
                txtExert.setText("0");
            }

            if (Kcal < 0 ){
                over = Math.abs(Kcal);
                txtOver.setText(String.valueOf(over));
            }else if (Kcal > 0){
                txtOver.setText("0");
            }else {
                Toast.makeText(getActivity(),"เช็คอะไรอีกละ",Toast.LENGTH_SHORT).show();
            }

                getActivity().getSharedPreferences("food_added", Context.MODE_PRIVATE).edit().clear().apply();



        }else if (getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).getBoolean("logexert", false)){
            //เพิ่มค่าใน exercise

            logTotal = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
            kcal_exert = logTotal.getInt(logTotal.getColumnIndex(HealthyFoodDB.TLN_Kcal));
            txtExert.setText(String.valueOf(kcal_exert));

            if(Kcal == totalKcal){
                txtFood.setText("0");
            }else{
                food = totalKcal - Kcal + kcal_exert;
                txtFood.setText(String.valueOf(food));
            }

            if (Kcal < 0){
                over = Math.abs(Kcal);
                txtOver.setText(String.valueOf(over));
            }else if (Kcal > 0){
                txtOver.setText("0");
            }else {
                Toast.makeText(getActivity(),"เช็คอะไรอีกละ",Toast.LENGTH_SHORT).show();

            }



            getActivity().getSharedPreferences("exert_added", Context.MODE_PRIVATE).edit().clear().apply();
        }else {
            if(Kcal == totalKcal){
                txtFood.setText("0");
            }else if (Kcal != totalKcal){
                logTotal = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                if (logTotal.getCount() > 0){
                    kcal_exert = logTotal.getInt(logTotal.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                    food = totalKcal - Kcal + kcal_exert;
                    txtFood.setText(String.valueOf(food));
                }else {
                    food = totalKcal - Kcal;
                    txtFood.setText(String.valueOf(food));
                }
            }else {
                Toast.makeText(getActivity(),"เช็คอะไรอีกละ",Toast.LENGTH_SHORT).show();
            }


            if (len_kcal == 0){
                txtExert.setText("0");
            }else if (len_kcal > 0){
                logTotal = healthyFoodDB.getTotalCalNormal(LN_ID,"ME07");
                if (logTotal.getCount() > 0) {
                    kcal_exert = logTotal.getInt(logTotal.getColumnIndex(HealthyFoodDB.TLN_Kcal));
                    txtExert.setText(String.valueOf(kcal_exert));
                }else {
                    txtExert.setText("0");
                }

            }else {
                Toast.makeText(getActivity(),"เช็คอะไรอีกละ",Toast.LENGTH_SHORT).show();
            }


            if (Kcal < 0 ){
                over = Math.abs(Kcal);
                txtOver.setText(String.valueOf(over));
            }else if (Kcal > 0){
                txtOver.setText("0");
            }else {
                Toast.makeText(getActivity(),"เช็คอะไรอีกละ",Toast.LENGTH_SHORT).show();
            }
        }





        showListBreakfast();
        showListLunch();
        showListDinner();
        showListSnack();
        showListExert();



    }
}
