package com.example.mo.healthyfood.QuestionFragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.QuestionActivity;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class WeightQuestionFragment extends Fragment {

    HealthyFoodDB healthyFoodDB;
    boolean db ,mm,user;
    EditText edt_start_weight,edt_height,edt_age;
    Cursor dateBase;
    String date,gender,weight,height,age;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());


    public WeightQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static WeightQuestionFragment newInstance(Bundle bundle) {
        WeightQuestionFragment fragment = new WeightQuestionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);



        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);

        healthyFoodDB = new HealthyFoodDB(getActivity());

        Toast.makeText(getActivity(), getArguments().getInt("gender") +"..."
                , Toast.LENGTH_SHORT).show();

        Toast.makeText(getActivity(), "date :" + formattedDate
                , Toast.LENGTH_SHORT).show();
        getActivity().setTitle("ข้อมูลส่วนตัว");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question_weight, container, false);
        initInstances(rootView, savedInstanceState);



        return rootView;
    }


    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        edt_start_weight = (EditText) rootView.findViewById(R.id.edt_weight);
        edt_height = (EditText) rootView.findViewById(R.id.edt_height);
        edt_age = (EditText) rootView.findViewById(R.id.edt_age);




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
            case R.id.action_next :
                String Weight = edt_start_weight.getText().toString();
                if (edt_start_weight.getText().toString().isEmpty() && edt_height.getText().toString().isEmpty()
                        &&  edt_age.getText().toString().isEmpty()) {
//                    edt_start_weight.setError("Invalid Weight");
//                    edt_start_weight.requestFocus();
                    Toast.makeText(getActivity(), "กรุณากรอกข้อมูลเกี่ยวกับตัวท่าน", Toast.LENGTH_SHORT).show();
                }else if (edt_start_weight.getText().toString().isEmpty() || edt_height.getText().toString().isEmpty()
                        ||  edt_age.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "กรุณากรอกข้อมูลเกี่ยวกับตัวท่านให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(edt_start_weight.getText().toString()) < 30) {
                    Toast.makeText(getActivity(), "ต้องน้ำหนัก 30 เป็นต้นไป", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(edt_start_weight.getText().toString()) > 200) {
                    Toast.makeText(getActivity(), "คุณน้ำหนักมากเกินไป", Toast.LENGTH_SHORT).show();
                } else if(Integer.valueOf(edt_height.getText().toString()) < 120){
                    Toast.makeText(getActivity(), "ต้องส่วนสูง 120 เป็นต้นไป", Toast.LENGTH_SHORT).show();
                } else if(Integer.valueOf(edt_height.getText().toString()) > 257){
                    Toast.makeText(getActivity(), "คุณมีส่วนสูงมากที่สุดในโลก", Toast.LENGTH_SHORT).show();
                }else if(Integer.valueOf(edt_age.getText().toString()) < 13){
                    Toast.makeText(getActivity(), "ต้องอายุ 13 เป็นต้นไป", Toast.LENGTH_SHORT).show();
                }else if(Integer.valueOf(edt_age.getText().toString()) > 100){
                    Toast.makeText(getActivity(), "คุณมีอายุมากเกินไป", Toast.LENGTH_SHORT).show();
                } else {
                    dateBase = healthyFoodDB.getMaxWeight();
                    date = dateBase.getString(dateBase.getColumnIndex(HealthyFoodDB.WE_Date));

                    gender = String.valueOf(getArguments().getInt("gender"));
                    weight = "1";
                    height = edt_height.getText().toString();
                    age = edt_age.getText().toString();

                    if(date == null){
                       // int weight = Integer.parseInt(edt_start_weight.getText().toString());
                       // int height = Integer.parseInt(edt_height.getText().toString());
                      //  int age = Integer.parseInt(edt_age.getText().toString());

//                        Bundle bundle = new Bundle();
//                        bundle.putInt("gender", getArguments().getInt("gender"));
//                        bundle.putDouble("weight", weight);
//                        bundle.putInt("height", height);
//                        bundle.putInt("age", age);
                        db = healthyFoodDB.InsertWeight(Weight);
                        if (db ) {
                            user = healthyFoodDB.InsertUser(gender,weight,height,age);
                            Log.e("Insert : ","Weight");
                            if(user){
                                Log.e("Insert : ","User");
                                ((QuestionActivity) getActivity()).onVegetQuestionSelected();
                            }else {
                                Log.e("Insert : ","Not User");
                            }

                        } else {
                            Log.e("Insert : ","Not Weight");
                        }
                    }else if(date.equals(formattedDate)){
//
//                        int weight = Integer.parseInt(edt_start_weight.getText().toString());
//                        int height = Integer.parseInt(edt_height.getText().toString());
//                        int age = Integer.parseInt(edt_age.getText().toString());
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("gender", getArguments().getInt("gender"));
//                        bundle.putDouble("weight", weight);
//                        bundle.putInt("height", height);
//                        bundle.putInt("age", age);
//                        Toast.makeText(getActivity(),"weight = " + Weight,Toast.LENGTH_SHORT).show();
                        mm = healthyFoodDB.updateWeight(Weight);
                        if (mm ) {
                            user = healthyFoodDB.UpdateUser(gender,weight,height,age);
                            Log.e("Update : ","Weight");
                            if(user){
                                Log.e("Update : ","User");
                                ((QuestionActivity) getActivity()).onVegetQuestionSelected();
                            }else {
                                Log.e("Update : ","Not User");
                            }

                        } else {
                            Log.e("Update : ","Not Weight");
                        }

                    } else {

//                        int weight = Integer.parseInt(edt_start_weight.getText().toString());
//                        int height = Integer.parseInt(edt_height.getText().toString());
//                        int age = Integer.parseInt(edt_age.getText().toString());
//                        Bundle bundle = new Bundle();
//                        bundle.putInt("gender", getArguments().getInt("gender"));
//                        bundle.putDouble("weight", weight);
//                        bundle.putInt("height", height);
//                        bundle.putInt("age", age);
                        db = healthyFoodDB.InsertWeight(Weight);
                        if (db ) {
                            user = healthyFoodDB.InsertUser(gender,weight,height,age);
                            Log.e("Insert : ","Weight");
                            if(user){
                                Log.e("Insert : ","User");
                                ((QuestionActivity) getActivity()).onVegetQuestionSelected();
                            }else {
                                Log.e("Insert : ","Not User");
                            }

                        } else {
                            Log.e("Insert : ","Not Weight");
                        }


                    }


                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }



    }
}

