package com.example.mo.healthyfood.fragmentMuscle;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.CreateFoodMuscleActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class CustomEditFoodMuscleFragment extends Fragment {

    EditText edtNameFood, edtCal, edtFat, edtProtein, edtCarb;
    FancyButton btnEdit,btnDel;
    Spinner spUnit,spBrand;
    HealthyFoodDB healthyFoodDB;
    boolean db, de;
    String pos, fu_id, NameFood, Cal, Fat, Protein, Carb,al_id,tf_id;
    Cursor unit,user,brand;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());


    public CustomEditFoodMuscleFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CustomEditFoodMuscleFragment newInstance(Bundle args) {
        CustomEditFoodMuscleFragment fragment = new CustomEditFoodMuscleFragment();
//        Bundle args = new Bundle();
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
        View rootView = inflater.inflate(R.layout.fragment_muscle_food_create_custom_edit, container, false);
        initInstances(rootView, savedInstanceState);



        addItemsOnUnitFood();
        spUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // ต้องการค่า fu_id จากตำแหน่งที่เลือก
                pos = parent.getItemAtPosition(position).toString();
                unit = healthyFoodDB.getIdUnit(pos);
               fu_id = unit.getString(unit.getColumnIndex(HealthyFoodDB.FU_ID));
                Toast.makeText(getActivity(), "pos = " + pos +
                        "\nfu_id = " + fu_id, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        addItemsOnBrand();
        spBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // ต้องการค่า tf_id จากตำแหน่งที่เลือก
                pos = parent.getItemAtPosition(position).toString();
                brand = healthyFoodDB.getIdBrand(pos);
                tf_id = brand.getString(brand.getColumnIndex(HealthyFoodDB.TF_ID));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (edtNameFood.getText().toString().isEmpty()) {
//                    Toast.makeText(getActivity(), "กรุณากรอกชื่อ", Toast.LENGTH_SHORT).show();
//                } else if (edtCal.getText().toString().isEmpty()) {
//                    Toast.makeText(getActivity(), "กรุณากรอกจำนวนกิโลแคลอรี่", Toast.LENGTH_SHORT).show();
//                } else if (spUnit == null && spUnit.getSelectedItem() == null) {
//                    Toast.makeText(getActivity(), "กรุณาเลือกหน่วย", Toast.LENGTH_SHORT).show();
//                }  else if (edtFat.getText().toString().isEmpty()) {
//                    Toast.makeText(getActivity(), "กรุณากรอกจำนวนไขมัน", Toast.LENGTH_SHORT).show();
//                } else if (edtProtein.getText().toString().isEmpty()) {
//                    Toast.makeText(getActivity(), "กรุณากรอกจำนวนโปรตีน", Toast.LENGTH_SHORT).show();
//                } else if (edtCarb.getText().toString().isEmpty()) {
//                    Toast.makeText(getActivity(), "กรุณากรอกจำนวนแป้ง", Toast.LENGTH_SHORT).show();
//                }else {
//                    NameFood = edtNameFood.getText().toString();
//                    Cal = edtCal.getText().toString();
//                    Fat = edtFat.getText().toString();
//                    Protein = edtProtein.getText().toString();
//                    Carb = edtCarb.getText().toString();
//                    db = healthyFoodDB.InsertCustomFoodMuscle(NameFood, tf_id);
//                    if(db){
//                        Log.e("Insert","Insert Custom Food Muscle");
//                        Cursor log = healthyFoodDB.getMaxCustomFoodMuscle();
//                        String fm_id = String.valueOf(log.getInt(log.getColumnIndex(HealthyFoodDB.FM_ID)));
//
//                        de = healthyFoodDB.InsertCustomDetailFoodMuscle(fm_id,fu_id,Cal, Fat, Protein, Carb);
//                        if(db && de){
//                            Toast.makeText(getActivity(), "CustomFood Inserted", Toast.LENGTH_SHORT).show();
//                            Bundle bundle = new Bundle();
//                            bundle.putString("m_id",getArguments().getString("m_id"));
//                            bundle.putString("date",getArguments().getString("date"));
//                            ((CreateFoodMuscleActivity)getActivity()).onFoodClkListener(bundle);
//                        }else {
//                            Toast.makeText(getActivity(), "CustomFood Not Inserted", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }else{
//                        Log.e("Insert","Not Insert Custom Food Muscle");
//                    }
//
//
//                }
//            }
//        });
        return rootView;
    }



    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        edtNameFood = (EditText) rootView.findViewById(R.id.edtNameFood);
        edtCal = (EditText) rootView.findViewById(R.id.edtCal);
        edtFat = (EditText) rootView.findViewById(R.id.edtFat);
        edtProtein = (EditText) rootView.findViewById(R.id.edtProtein);
        edtCarb = (EditText) rootView.findViewById(R.id.edtCarb);
//        btnSave = (FancyButton) rootView.findViewById(R.id.btnSave);
        spUnit = (Spinner) rootView.findViewById(R.id.spUnit);
        spBrand = (Spinner) rootView.findViewById(R.id.spBrand);

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

    public void addItemsOnUnitFood() {
        List<String> list = healthyFoodDB.getAllUnit2();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUnit.setAdapter(dataAdapter);
    }


    public void addItemsOnBrand() {
        List<String> list = healthyFoodDB.getAllBrand();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBrand.setAdapter(dataAdapter);
    }


}
