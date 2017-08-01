package com.example.mo.healthyfood.fragmentMuscle;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.media.audiofx.LoudnessEnhancer;
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
import com.example.mo.healthyfood.activityMuscle.ShowFoodMuscleActivity;
import com.example.mo.healthyfood.activityNormal.ShowFoodNormalActivity;

import java.util.ArrayList;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class DetailFoodMuscleFragment extends Fragment {
    TextView txtNameFood, txtNbCal, txtFat, txtProtein, txtCarb;
    Spinner spNumbers, spUnitFood;
    HealthyFoodDB healthyFoodDB;
    Cursor food,detailFood,unit,nameUnit,log,goal,usermus,total,totalFood;
    String f_id,NameFood,fu_id,nUnit,count,m_id,tf_id;
    Double c_count,kcal,fat,protein,carb,f_fat,f_protein,f_carb,tdm_fat,tdm_protein,tdm_carb;
    Double tlm_fat,tlm_protein,tlm_carb;
    FancyButton btnSave;
    boolean logDB,detailFoodDB,bb,mm;
    String totalKcal,totalFat,totalProtein,totalCarb,LM_ID,p_id,tm_id,um_id;
    int f_kcal,tdm_kcal,tlm_kcal,Kcal;


    int ttKcalFood = 0;
    double ttFatFood = 0,ttProteinFood = 0,ttCarbFood = 0;

    int ttKcal = 0;
    double ttFat = 0,ttProtein = 0,ttCarb = 0;

    ShowFoodMuscleActivity showFoodMuscleActivity;


    public DetailFoodMuscleFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static DetailFoodMuscleFragment newInstance(Bundle args) {
        DetailFoodMuscleFragment fragment = new DetailFoodMuscleFragment();
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
        showFoodMuscleActivity = (ShowFoodMuscleActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_muscle_food_detail, container, false);
        initInstances(rootView, savedInstanceState);

        Toast.makeText(getActivity(), "Fid = " + getArguments().getString("f_id"),
                Toast.LENGTH_SHORT).show();


        //TODO: iy[8jk f_id มา
        f_id = getArguments().getString("f_id");
        m_id = getArguments().getString("m_id");


        Log.e("Values","date : " + getArguments().getString("date"));
        Log.e("Values","f_id : " + getArguments().getString("f_id"));
        Log.e("Values","m_id : " + getArguments().getString("m_id"));



        food = healthyFoodDB.getShowFoodMuscle(f_id);
        NameFood = food.getString(food.getColumnIndex(HealthyFoodDB.FM_Name));
        tf_id = food.getString(food.getColumnIndex(HealthyFoodDB.TF_ID));
        Toast.makeText(getActivity(),"name = " + NameFood,Toast.LENGTH_SHORT).show();

        unit = healthyFoodDB.getUnitMuscle(f_id);
        fu_id = unit.getString(unit.getColumnIndex(HealthyFoodDB.FU_ID));


        detailFood = healthyFoodDB.getDetailFoodMuscle(f_id, fu_id);
        f_kcal = detailFood.getInt(detailFood.getColumnIndex(HealthyFoodDB.FM_Kcal));
        f_fat = detailFood.getDouble(detailFood.getColumnIndex(HealthyFoodDB.FM_Fat));
        f_protein = detailFood.getDouble(detailFood.getColumnIndex(HealthyFoodDB.FM_Protein));
        f_carb = detailFood.getDouble(detailFood.getColumnIndex(HealthyFoodDB.FM_Carb));

        log = healthyFoodDB.getLogMuscle(getArguments().getString("date"));
        LM_ID = String.valueOf(log.getInt(log.getColumnIndex(HealthyFoodDB.LM_ID)));
        tdm_kcal = log.getInt(log.getColumnIndex(HealthyFoodDB.TDM_Kcal));
        tdm_fat = log.getDouble(log.getColumnIndex(HealthyFoodDB.TDM_Fat));
        tdm_protein = log.getDouble(log.getColumnIndex(HealthyFoodDB.TDM_Protein));
        tdm_carb = log.getDouble(log.getColumnIndex(HealthyFoodDB.TDM_Carb));

        goal = healthyFoodDB.getMaxGoalMuscle();
        p_id = goal.getString(goal.getColumnIndex(HealthyFoodDB.P_ID));
        um_id = String.valueOf(goal.getInt(goal.getColumnIndex(HealthyFoodDB.UM_ID)));


        usermus = healthyFoodDB.getUserMuscle(um_id);
        tm_id = usermus.getString(usermus.getColumnIndex(HealthyFoodDB.TM_ID));









        txtNameFood.setText(NameFood);
//        addListenerOnSpinnerItemSelection();

        addItemsOnUnitFood();

        if(tf_id.equals("TF01")){
            //อาหารจานเดียว
            addItemsOnNumberFastFood();
        }else if(tf_id.equals("TF02")){
            //ของหวาน
            addItemsOnNumberFastFood();
        }
        else if(tf_id.equals("TF03")){
            //ผลไม้
            addItemsOnNumberFruit();
        }
        else if(tf_id.equals("TF04")){
            //เครื่องดื่ม
            addItemsOnNumberFastFood();

        }else if(tf_id.equals("TF05")){
            //ซุปเปอร์มาร์เก็ต
        }else if(tf_id.equals("TF06")){
            //อื่นๆ
        }else {
            Log.i("Empty","TF_ID Empty");
        }

        spNumbers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                count = parent.getItemAtPosition(position).toString();

                Toast.makeText(getActivity(),
                        "count : " + count,
                        Toast.LENGTH_SHORT).show();

                c_count = Double.parseDouble(count);
                c_count = c_count/100;
                kcal = c_count * f_kcal;
                fat = c_count * f_fat;
                protein = c_count * f_protein;
                carb = c_count * f_carb;

                Kcal = kcal.intValue();

                totalKcal = Double.toString(kcal);
                totalFat =Double.toString(fat);
                totalProtein = Double.toString(protein);
                totalCarb = Double.toString(carb);

                txtNbCal.setText(totalKcal);
                txtFat.setText(totalFat);
                txtProtein.setText(totalProtein);
                txtCarb.setText(totalCarb);



                if(c_count == 1 && tdm_kcal < kcal){
                    AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                    //ลดไขมัน
                    if(tm_id.equals("TM01") && tm_id.equals("TM03")){
                        alog.setMessage("จำนวนแคลอรี่ที่คุณกำลังทานมากกว่าแคลอรี่ที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกทานอาหารที่มีจำนวนแคลลอรี่ที่น้อยกว่า" + kcal +
                        " น้ำหนักของคุณอาจจะเพิ่มขึ้นได้");
                        //เพิ่มกล้ามเนื้อ
                    }else  if (tm_id.equals("TM02")) {
                        alog.setMessage("จำนวนแคลอรี่ที่คุณกำลังทานมากกว่าแคลอรี่ที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกทานอาหารที่มีจำนวนแคลลอรี่ที่น้อยกว่า" + kcal +
                        " น้ำหนักของคุณอาจจะเพิ่มได้เร็วขึ้น ถ้าน้ำหนักเพิ่มขึ้นเร็วเกินไปก็ไม่ส่งผลดีต่อร่างกายของคุณหรอกนะ");
                    }
                    alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Bundle bundle = new Bundle();
                            bundle.putString("m_id",m_id);
                            bundle.putString("date",getArguments().getString("date"));
                            showFoodMuscleActivity.onFoodClkListener(bundle);
                         //   ((ShowFoodMuscleActivity)getActivity()).onFoodClkListener();
                        }
                    });
                    alog.setNegativeButton("ไม่เป็นไร", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"จะกินตามนี้มีปัญหาป่ะ",Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog alertDialog = alog.create();
                    alertDialog.show();
                } else if (c_count > 1 && tdm_kcal < kcal){
                    AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                    //ลดไขมัน
                    if(tm_id.equals("TM01") && tm_id.equals("TM03")){
                        alog.setMessage("จำนวนแคลอรี่ที่คุณกำลังทานมากกว่าแคลอรี่ที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกจำนวนอาหารที่ทานให้น้อยกว่านี้ น้ำหนักของคุณอาจจะเพิ่มขึ้นได้");
                        //เพิ่มกล้ามเนื้อ
                    }else  if (tm_id.equals("TM02")) {
                        alog.setMessage("จำนวนแคลอรี่ที่คุณกำลังทานมากกว่าแคลอรี่ที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกจำนวนอาหารที่ทานให้น้อยกว่านี้ น้ำหนักของคุณอาจจะเพิ่มได้เร็วขึ้น ถ้าน้ำหนักเพิ่มขึ้นเร็วเกินไปก็ไม่ส่งผลดีต่อร่างกายของคุณหรอกนะ");
                    }

                    alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"เลือกให้น้อยละสิ",Toast.LENGTH_SHORT).show();
                        }
                    });
                    alog.setNegativeButton("ไม่เป็นไร", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"จะกินตามนี้มีปัญหาป่ะ",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else if (c_count == 1 && tdm_fat < fat){
                    AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                        alog.setMessage("จำนวนไขมันที่คุณกำลังทานมากกว่าจำนวนไขมันที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกทานอาหารที่มีจำนวนไขมันที่น้อยกว่า" + fat
                        + "การที่กินไขมันเกินกว่ากำหนดไว้ในจำนวนมากๆ นั้นไม่ส่งผลดีที่สุขภาพของคุณเลย");
                    alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Bundle bundle = new Bundle();
                            bundle.putString("m_id",m_id);
                            bundle.putString("date",getArguments().getString("date"));
                            showFoodMuscleActivity.onFoodClkListener(bundle);
                        }
                    });
                    alog.setNegativeButton("ไม่เป็นไร", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"จะกินตามนี้มีปัญหาป่ะ",Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog alertDialog = alog.create();
                    alertDialog.show();

                }else if (c_count > 1 && tdm_fat < fat){
                    AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());

                        alog.setMessage("จำนวนไขมันที่คุณกำลังทานมากกว่าจำนวนไขมันที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกจำนวนอาหารที่ทานให้น้อยกว่านี้");

                    alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"เลือกให้น้อยละสิ",Toast.LENGTH_SHORT).show();
                        }
                    });
                    alog.setNegativeButton("ไม่เป็นไร", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"จะกินตามนี้มีปัญหาป่ะ",Toast.LENGTH_SHORT).show();
                        }
                    });


                }else if (c_count == 1 && tdm_carb < carb){
                    AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                    //ลดไขมัน
                    if(tm_id.equals("TM01") && tm_id.equals("TM03")){
                        alog.setMessage("จำนวนแป้งที่คุณกำลังทานมากกว่าแป้งที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกทานอาหารที่มีจำนวนแป้งที่น้อยกว่า" + kcal
                        + "อาจจะทำให้น้ำหนักของคุณเพิ่มขึ้นได้");
                        //เพิ่มกล้ามเนื้อ
                    }else  if (tm_id.equals("TM02")) {
                        alog.setMessage("จำนวนแป้งที่คุณกำลังทานมากกว่าแป้งที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกทานอาหารที่มีจำนวนแป้งที่น้อยกว่า" + kcal +
                        "อาจจะทำให้น้ำหนักของคุณเพิ่มขึ้นได้เร็วขึ้น");

                    }

                    alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Bundle bundle = new Bundle();
                            bundle.putString("m_id",m_id);
                            bundle.putString("date",getArguments().getString("date"));
                            showFoodMuscleActivity.onFoodClkListener(bundle);
                        }
                    });
                    alog.setNegativeButton("ไม่เป็นไร", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"จะกินตามนี้มีปัญหาป่ะ",Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog alertDialog = alog.create();
                    alertDialog.show();

                }else if (c_count > 1 && tdm_carb < carb){
                    AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                    //ลดไขมัน
                    if(tm_id.equals("TM01") && tm_id.equals("TM03")){
                        alog.setMessage("จำนวนแป้งที่คุณกำลังทานมากกว่าแป้งที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกจำนวนอาหารที่ทานให้น้อยกว่านี้ อาจจะทำให้น้ำหนักของคุณเพิ่มขึ้นได้");
                        //เพิ่มกล้ามเนื้อ
                    }else  if (tm_id.equals("TM02")) {
                        alog.setMessage("จำนวนแป้งที่คุณกำลังทานมากกว่าแป้งที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกจำนวนอาหารที่ทานให้น้อยกว่านี้ อาจจะทำให้น้ำหนักของคุณเพิ่มขึ้นได้เร็วขึ้น");
                    }

                    alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"เลือกให้น้อยละสิ",Toast.LENGTH_SHORT).show();
                        }
                    });
                    alog.setNegativeButton("ไม่เป็นไร", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"จะกินตามนี้มีปัญหาป่ะ",Toast.LENGTH_SHORT).show();
                        }
                    });

                }else if (c_count == 1 && tdm_protein < protein){
                    AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                    //ลดไขมัน
                    if(tm_id.equals("TM01")){
                        alog.setMessage("จำนวนโปรตีนที่คุณกำลังทานมากกว่าโปรตีนที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกทานอาหารที่มีจำนวนโปรตีนที่น้อยกว่า" + kcal);
                        //เพิ่มกล้ามเนื้อ
                    }else  if (tm_id.equals("TM02")) {
                        alog.setMessage("จำนวนโปรตีนที่คุณกำลังทานมากกว่าโปรตีนที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกทานอาหารที่มีจำนวนโปรตีนที่น้อยกว่า" + kcal);
                        //ควบคุมน้ำหนัก
                    }else if(tm_id.equals("TM03")){
                        alog.setMessage("จำนวนโปรตีนที่คุณกำลังทานมากกว่าโปรตีนที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกทานอาหารที่มีจำนวนโปรตีนที่น้อยกว่า" + kcal);
                    }

                    alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Bundle bundle = new Bundle();
                            bundle.putString("m_id",m_id);
                            bundle.putString("date",getArguments().getString("date"));
                            showFoodMuscleActivity.onFoodClkListener(bundle);
                        }
                    });
                    alog.setNegativeButton("ไม่เป็นไร", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"จะกินตามนี้มีปัญหาป่ะ",Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog alertDialog = alog.create();
                    alertDialog.show();

                }else if (c_count > 1 && tdm_protein < protein){
                    AlertDialog.Builder alog = new AlertDialog.Builder(getActivity());
                    //ลดไขมัน
                    if(tm_id.equals("TM01")){
                        alog.setMessage("จำนวนโปรตีนที่คุณกำลังทานมากกว่าโปรตีนที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกจำนวนอาหารที่ทานให้น้อยกว่านี้");
                        //เพิ่มกล้ามเนื้อ
                    }else  if (tm_id.equals("TM02")) {
                        alog.setMessage("จำนวนโปรตีนที่คุณกำลังทานมากกว่าโปรตีนที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกจำนวนอาหารที่ทานให้น้อยกว่านี้");
                        //ควบคุมน้ำหนัก
                    }else if(tm_id.equals("TM03")){
                        alog.setMessage("จำนวนโปรตีนที่คุณกำลังทานมากกว่าโปรตีนที่คุณสามารถทานได้ในวันนี้ คุณควรเลือกจำนวนอาหารที่ทานให้น้อยกว่านี้");
                    }
                    alog.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"เลือกให้น้อยละสิ",Toast.LENGTH_SHORT).show();
                        }
                    });
                    alog.setNegativeButton("ไม่เป็นไร", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(),"จะกินตามนี้มีปัญหาป่ะ",Toast.LENGTH_SHORT).show();
                        }
                    });
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                detailFoodDB =healthyFoodDB.InsertLogFoodMuscle(LM_ID,m_id,f_id,fu_id,count,
                        totalKcal,totalFat,totalProtein,totalCarb);
                if( detailFoodDB ){
                    Log.e("DFM Insert","Insert Log Food Muscle");

                    total = healthyFoodDB.getTotalCalMuscle(LM_ID,m_id);
                    totalFood = healthyFoodDB.getTotalCalMuscle(LM_ID,"ME10");

                    if (total.getCount() > 0 && totalFood.getCount() > 0){


                        ttKcalFood = totalFood.getInt(totalFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                        ttFatFood = totalFood.getDouble(totalFood.getColumnIndex(HealthyFoodDB.TLM_Fat));
                        ttProteinFood = totalFood.getDouble(totalFood.getColumnIndex(HealthyFoodDB.TLM_Protein));
                        ttCarbFood = totalFood.getDouble(totalFood.getColumnIndex(HealthyFoodDB.TLM_Carb));

                        ArrayList<Integer> TotalKcal = healthyFoodDB.getKcalFoodMuscle(LM_ID,m_id);
                        int KcalFood = 0;
                        for (int i = 0 ;i < TotalKcal.size();i++){
                            KcalFood += TotalKcal.get(i);
                        }

                        ArrayList<Double> TotalFat = healthyFoodDB.getFatFoodMuscle(LM_ID,m_id);
                        double FatFood = 0;
                        for (int i = 0 ;i < TotalFat.size();i++){
                            FatFood += TotalFat.get(i);
                        }


                        ArrayList<Double> TotalProtein = healthyFoodDB.getProteinFoodMuscle(LM_ID,m_id);
                        double ProteinFood = 0;
                        for (int i = 0 ;i < TotalProtein.size();i++){
                            ProteinFood += TotalProtein.get(i);
                        }

                        ArrayList<Double> TotalCarb = healthyFoodDB.getCarbFoodMuscle(LM_ID,m_id);
                        double CarbFood = 0;
                        for (int i = 0 ;i < TotalCarb.size();i++){
                            CarbFood += TotalCarb.get(i);
                        }



                        Log.e("DFM KcalFood","KcalFood :" + KcalFood);
                        Log.e("DFM FatFood","FatFood :" + FatFood);
                        Log.e("DFM ProteinFood","ProteinFood :" + ProteinFood);
                        Log.e("DFM CarbFood","CarbFood :" + CarbFood);

                        ttKcal = ttKcalFood + Kcal;
                        ttFat = ttFatFood + fat;
                        ttProtein = ttProteinFood + protein;
                        ttCarb = ttCarbFood + carb;




                        bb = healthyFoodDB.UpdateTotalCalMuscle(LM_ID,m_id,String.valueOf(KcalFood),String.valueOf(FatFood),String.valueOf(ProteinFood),String.valueOf(CarbFood));
                        mm = healthyFoodDB.UpdateTotalCalMuscle(LM_ID,"ME10",String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                        if (bb && mm){
                            Log.e("DFM Update","Update Total Cal Muscle");
                            Toast.makeText(getActivity(), "LogFood Inserted", Toast.LENGTH_SHORT).show();
                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().putBoolean("food", true).apply();
                            getActivity().getSharedPreferences("food_added", Context.MODE_PRIVATE).edit().putBoolean("logfood", true).apply();
                            ((ShowFoodMuscleActivity)getActivity()).onMainClkListener();

                        }else {
                            Log.e("DFM Update","Not Update Total Cal Muscle");
                        }

                    }else if (total.getCount() > 0 && totalFood.getCount() == 0){
                        Log.e("DFM Check","Sure? total > 0 && totalFood == 0");

                    }else if (total.getCount() == 0 && totalFood.getCount() > 0){
                        Log.e("DFM Check","Sure?");

                        ttKcalFood = totalFood.getInt(totalFood.getColumnIndex(HealthyFoodDB.TLM_Kcal));
                        ttFatFood = totalFood.getDouble(totalFood.getColumnIndex(HealthyFoodDB.TLM_Fat));
                        ttProteinFood = totalFood.getDouble(totalFood.getColumnIndex(HealthyFoodDB.TLM_Protein));
                        ttCarbFood = totalFood.getDouble(totalFood.getColumnIndex(HealthyFoodDB.TLM_Carb));

                        // totalKcal,totalFat,totalProtein,totalCarb

                        ArrayList<Integer> TotalKcal = healthyFoodDB.getKcalFoodMuscle(LM_ID,m_id);
                        int KcalFood = 0;
                        for (int i = 0 ;i < TotalKcal.size();i++){
                            KcalFood += TotalKcal.get(i);
                        }

                        ArrayList<Double> TotalFat = healthyFoodDB.getFatFoodMuscle(LM_ID,m_id);
                        double FatFood = 0;
                        for (int i = 0 ;i < TotalFat.size();i++){
                            FatFood += TotalFat.get(i);
                        }


                        ArrayList<Double> TotalProtein = healthyFoodDB.getProteinFoodMuscle(LM_ID,m_id);
                        double ProteinFood = 0;
                        for (int i = 0 ;i < TotalProtein.size();i++){
                            ProteinFood += TotalProtein.get(i);
                        }

                        ArrayList<Double> TotalCarb = healthyFoodDB.getCarbFoodMuscle(LM_ID,m_id);
                        double CarbFood = 0;
                        for (int i = 0 ;i < TotalCarb.size();i++){
                            CarbFood += TotalCarb.get(i);
                        }


                        Log.e("DFM KcalFood","KcalFood :" + KcalFood);
                        Log.e("DFM FatFood","FatFood :" + FatFood);
                        Log.e("DFM ProteinFood","ProteinFood :" + ProteinFood);
                        Log.e("DFM CarbFood","CarbFood :" + CarbFood);

                        ttKcal = ttKcalFood + KcalFood;
                        ttFat = ttFatFood + FatFood;
                        ttProtein = ttProteinFood + ProteinFood;
                        ttCarb = ttCarbFood + CarbFood;


                        bb = healthyFoodDB.InsertTotalCalMuscle(LM_ID,m_id,String.valueOf(KcalFood),String.valueOf(FatFood),String.valueOf(ProteinFood),String.valueOf(CarbFood));
                        mm = healthyFoodDB.UpdateTotalCalMuscle(LM_ID,"ME10",String.valueOf(ttKcal),String.valueOf(ttFat),String.valueOf(ttProtein),String.valueOf(ttCarb));

                        if (bb && mm){
                            Log.e("DFM Update","Update Total Cal Muscle");
                            Toast.makeText(getActivity(), "LogFood Inserted", Toast.LENGTH_SHORT).show();
                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().putBoolean("food", true).apply();
                            getActivity().getSharedPreferences("food_added", Context.MODE_PRIVATE).edit().putBoolean("logfood", true).apply();
                            ((ShowFoodMuscleActivity)getActivity()).onMainClkListener();

                        }else {
                            Log.e("DFM Update","Not Update Total Cal Muscle");
                        }
                    }else {
                        bb =  healthyFoodDB.InsertTotalCalMuscle(LM_ID,m_id,String.valueOf(kcal),String.valueOf(fat),String.valueOf(protein),String.valueOf(carb));
                        mm =  healthyFoodDB.InsertTotalCalMuscle(LM_ID,"ME10",String.valueOf(kcal),String.valueOf(fat),String.valueOf(protein),String.valueOf(carb));
                        if (bb && mm){
                            Log.e("DFM Insert","Insert Total Cal Muscle");
                            Toast.makeText(getActivity(), "LogFood Inserted", Toast.LENGTH_SHORT).show();
                            getActivity().getSharedPreferences("is_food_added", Context.MODE_PRIVATE).edit().putBoolean("food", true).apply();
                            getActivity().getSharedPreferences("food_added", Context.MODE_PRIVATE).edit().putBoolean("logfood", true).apply();
                            ((ShowFoodMuscleActivity)getActivity()).onMainClkListener();
                        }else {
                            Log.e("DFM Insert","Not Insert Total Cal Muscle");
                        }
                    }


                } else {
                    Log.e("DFM Insert","Not Insert Log Food Muscle");
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
        txtFat = (TextView) rootView.findViewById(R.id.txtFat);
        txtProtein = (TextView) rootView.findViewById(R.id.txtProtein);
        txtCarb = (TextView) rootView.findViewById(R.id.txtCarb);
        spNumbers = (Spinner) rootView.findViewById(R.id.spNumbers);
        spUnitFood = (Spinner) rootView.findViewById(R.id.spUnitFood);
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

    // add items into spinner dynamically
    public void addItemsOnUnitFood() {
//
//        List<String> list = new ArrayList<String>();
//        list.add("list 1");
//        list.add("list 2");
//        list.add("list 3");
        List<String> list =  healthyFoodDB.getAllUnit(fu_id);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnitFood.setAdapter(dataAdapter);
    }

    public void addItemsOnNumberFastFood() {
        List<String> list = new ArrayList<String>();
        list.add("100");
        list.add("200");
        list.add("300");
        list.add("400");
        list.add("500");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNumbers.setAdapter(dataAdapter);
    }

    public void addItemsOnNumberFruit() {
        List<String> list = new ArrayList<String>();
        list.add("100");
        list.add("200");
        list.add("300");
//        list.add("400");
//        list.add("500");
//        list.add("600");
//        list.add("700");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNumbers.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        spNumbers.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }





}
