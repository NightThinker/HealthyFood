package com.example.mo.healthyfood.QuestionFragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.QuestionActivity;
import com.example.mo.healthyfood.adapter.CheckboxAdapter;
import com.example.mo.healthyfood.add.Country;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class FoodAllergyQuestionFragment extends Fragment {


    HealthyFoodDB healthyFoodDB;

    ListView listView;
    EditText edtAllergy;
    FancyButton btnSave;
    String name,weight;
    Boolean db,mm,bb;
    CheckBox checkBox;
    Cursor vt;
    Integer vt_id;

    CheckboxAdapter dataAdapter = null;



    public FoodAllergyQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static FoodAllergyQuestionFragment newInstance() {
        FoodAllergyQuestionFragment fragment = new FoodAllergyQuestionFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);

        getActivity().setTitle("แพ้อาหาร");

        healthyFoodDB = new HealthyFoodDB(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question_food_allergy, container, false);
        initInstances(rootView, savedInstanceState);

        vt = healthyFoodDB.getUser();
        vt_id = vt.getInt(vt.getColumnIndex(HealthyFoodDB.VT_ID));

        if(vt_id == 1){
            //ไม่มัง
            displayListView();

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = edtAllergy.getText().toString();
                    if(edtAllergy.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "กรุณากรอกชื่ออาหารที่แพ้", Toast.LENGTH_SHORT).show();
                    }else {
                        db = healthyFoodDB.InsertAllergy(name);
                        if (db) {
                            Log.e("Insert : ","Allergy");
                            displayListView();
                            edtAllergy.setText("");
                        } else
                            Log.e("Insert : ","Not Allergy");
                    }
                }
            });
        }else if (vt_id == 2){
            //นมไข่
            displayListViewVeget();

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = edtAllergy.getText().toString();
                    if(edtAllergy.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "กรุณากรอกชื่ออาหารที่แพ้", Toast.LENGTH_SHORT).show();
                    }else if (edtAllergy.getText().toString().equals("กุ้ง") || edtAllergy.getText().toString().equals("ปู")
                            || edtAllergy.getText().toString().equals("ปลาหมึก") || edtAllergy.getText().toString().equals("ลอบเตอร์")
                            || edtAllergy.getText().toString().equals("ปลา") || edtAllergy.getText().toString().equals("ไก่")
                            || edtAllergy.getText().toString().equals("หมู") || edtAllergy.getText().toString().equals("เนื้อ")){
                        Toast.makeText(getActivity(), "คุณทานมังสวิรัติ ไม่สามารถทานเนื้อสัตว์ได้", Toast.LENGTH_SHORT).show();
                    } else if (edtAllergy.getText().toString().equals("นม") || edtAllergy.getText().toString().equals("ไข่")){
                        Toast.makeText(getActivity(), "คุณทานมังสวิรัติดื่มนมและกินไข่", Toast.LENGTH_SHORT).show();
                    } else{
                        db = healthyFoodDB.InsertAllergy(name);
                        if (db) {
                            Log.e("Insert : ","Allergy");
                            displayListViewVeget();
                            edtAllergy.setText("");
                        } else
                            Log.e("Insert : ","Not Allergy");
                    }
                }
            });

        }else if (vt_id == 3){
            //นม
            displayListViewVeget();

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = edtAllergy.getText().toString();
                    if(edtAllergy.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "กรุณากรอกชื่ออาหารที่แพ้", Toast.LENGTH_SHORT).show();
                    }else if (edtAllergy.getText().toString().equals("กุ้ง") || edtAllergy.getText().toString().equals("ปู")
                            || edtAllergy.getText().toString().equals("ปลาหมึก") || edtAllergy.getText().toString().equals("ลอบเตอร์")
                            || edtAllergy.getText().toString().equals("ปลา") || edtAllergy.getText().toString().equals("ไก่")
                            || edtAllergy.getText().toString().equals("หมู") || edtAllergy.getText().toString().equals("เนื้อ")
                            || edtAllergy.getText().toString().equals("ไข่")){
                        Toast.makeText(getActivity(), "คุณทานมังสวิรัติ ไม่สามารถทานเนื้อสัตว์ได้", Toast.LENGTH_SHORT).show();
                    } else if (edtAllergy.getText().toString().equals("นม")){
                        Toast.makeText(getActivity(), "คุณทานมังสวิรัติดื่มนม", Toast.LENGTH_SHORT).show();
                    } else {
                        db = healthyFoodDB.InsertAllergy(name);
                        if (db) {
                            Log.e("Insert : ","Allergy");
                            displayListViewVeget();
                            edtAllergy.setText("");
                        } else
                            Log.e("Insert : ","Not Allergy");
                    }
                }
            });

        }else if (vt_id == 4){
            //ไข่
            displayListViewVeget();

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = edtAllergy.getText().toString();
                    if(edtAllergy.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "กรุณากรอกชื่ออาหารที่แพ้", Toast.LENGTH_SHORT).show();
                    }else if (edtAllergy.getText().toString().equals("กุ้ง") || edtAllergy.getText().toString().equals("ปู")
                            || edtAllergy.getText().toString().equals("ปลาหมึก") || edtAllergy.getText().toString().equals("ลอบเตอร์")
                            || edtAllergy.getText().toString().equals("ปลา") || edtAllergy.getText().toString().equals("ไก่")
                            || edtAllergy.getText().toString().equals("หมู") || edtAllergy.getText().toString().equals("เนื้อ")
                            || edtAllergy.getText().toString().equals("นม")){
                        Toast.makeText(getActivity(), "คุณทานมังสวิรัติ ไม่สามารถทานเนื้อสัตว์ได้", Toast.LENGTH_SHORT).show();
                    }  else if (edtAllergy.getText().toString().equals("ไข่")){
                        Toast.makeText(getActivity(), "คุณทานมังสวิรัตกินไข่", Toast.LENGTH_SHORT).show();
                    }else {
                        db = healthyFoodDB.InsertAllergy(name);
                        if (db) {
                            Log.e("Insert : ","Allergy");
                            displayListViewVeget();
                            edtAllergy.setText("");
                        } else
                            Log.e("Insert : ","Not Allergy");
                    }
                }
            });

        }else if (vt_id == 5){
            //บริสุทธิ์
            displayListViewVeget();

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = edtAllergy.getText().toString();
                    if(edtAllergy.getText().toString().isEmpty()){
                        Toast.makeText(getActivity(), "กรุณากรอกชื่ออาหารที่แพ้", Toast.LENGTH_SHORT).show();
                    }else if (edtAllergy.getText().toString().equals("กุ้ง") || edtAllergy.getText().toString().equals("ปู")
                            || edtAllergy.getText().toString().equals("ปลาหมึก") || edtAllergy.getText().toString().equals("ลอบเตอร์")
                            || edtAllergy.getText().toString().equals("ปลา") || edtAllergy.getText().toString().equals("ไก่")
                            || edtAllergy.getText().toString().equals("หมู") || edtAllergy.getText().toString().equals("เนื้อ")
                            || edtAllergy.getText().toString().equals("นม") || edtAllergy.getText().toString().equals("ไข่")){
                        Toast.makeText(getActivity(), "คุณทานมังสวิรัติ ไม่สามารถทานเนื้อสัตว์และผลิตภัณฑ์ของสัตว์ได้", Toast.LENGTH_SHORT).show();
                    } else {
                        db = healthyFoodDB.InsertAllergy(name);
                        if (db) {
                            Log.e("Insert : ","Allergy");
                            displayListViewVeget();
                            edtAllergy.setText("");
                        } else
                            Log.e("Insert : ","Not Allergy");
                    }
                }
            });

        }else {

        }




        return rootView;
    }



    private void displayListView() {

        //Array list of countries
        ArrayList<Country> countryList = new ArrayList<Country>();
        Cursor cursor = healthyFoodDB.getAllAllergy();
        if(cursor != null && cursor.getCount() != 0){
            if(cursor.moveToNext()){
                do{
                    Country country = new Country();

                    country.setName(cursor.getString(cursor.getColumnIndex(HealthyFoodDB.FA_Name)));
                    country.setSelected(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(HealthyFoodDB.FA_Status))));
                    countryList.add(country);
                }while (cursor.moveToNext());


            }
        }

        dataAdapter = new CheckboxAdapter(countryList,getActivity());
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Country country = (Country) parent.getItemAtPosition(position);
                Toast.makeText(getContext(),
                        "Clicked on Row: " + country.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }



    private void displayListViewVeget() {

        //Array list of countries
        ArrayList<Country> countryList = new ArrayList<Country>();
        Cursor cursor = healthyFoodDB.getAllergyVeget();
        if(cursor != null && cursor.getCount() != 0){
            if(cursor.moveToFirst()){
                do{
                    Country country = new Country();

                    country.setName(cursor.getString(cursor.getColumnIndex(HealthyFoodDB.FA_Name)));
                    country.setSelected(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(HealthyFoodDB.FA_Status))));
                    countryList.add(country);
                }while (cursor.moveToNext());


            }
        }

        dataAdapter = new CheckboxAdapter(countryList,getActivity());
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Country country = (Country) parent.getItemAtPosition(position);
                Toast.makeText(getContext(),
                        "Clicked on Row: " + country.getName(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }


    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        listView = (ListView) rootView.findViewById(R.id.lvAllergyList);
        edtAllergy = (EditText) rootView.findViewById(R.id.edtAllergy);
        btnSave = (FancyButton) rootView.findViewById(R.id.btnSave);
        checkBox = (CheckBox) rootView.findViewById(R.id.checkBox);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.next,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_next:
                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");


                    ArrayList<Country> countryList = dataAdapter.getData();
                    for(int i=0;i<countryList.size();i++){
                        Country country = countryList.get(i);
                        if(country.isSelected()){
                            responseText.append("\n" + country.getName());
                            String name = country.getName();
                            db = healthyFoodDB.UpdateFoodAllergy(name);
                            if(db) {
                                Log.e("Update : "," Food Allergy");
                                ((QuestionActivity)getActivity()).onActivitiesFragmentClkListener();
                            }
                            else
                                Log.e("Update : ","Not Food Allergy");

                        }
                    }


                Toast.makeText(getActivity(),
                        responseText, Toast.LENGTH_LONG).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
