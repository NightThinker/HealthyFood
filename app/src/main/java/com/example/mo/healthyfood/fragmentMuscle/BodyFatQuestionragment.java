package com.example.mo.healthyfood.fragmentMuscle;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.mo.healthyfood.activityMuscle.BodyFatQuestionActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class BodyFatQuestionragment extends Fragment {

    EditText edtBodyFat;
 //   FancyButton btnSave;
    String BodyFat,bf_date,ffmi,we_date,Weight;
    HealthyFoodDB healthyFoodDB;
    boolean db,fi,mm;
    Double feet,inches,leanMass,FFMI,weightData,bodyfat;
    int ft ,in,height;
    Cursor dateBase,user,weight,datebase;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());

    public BodyFatQuestionragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static BodyFatQuestionragment newInstance() {
        BodyFatQuestionragment fragment = new BodyFatQuestionragment();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_muscle_body_fat, container, false);
        initInstances(rootView, savedInstanceState);

        user = healthyFoodDB.getUser();
        height = user.getInt(user.getColumnIndex(HealthyFoodDB.U_Height));

        weight = healthyFoodDB.getMaxWeight();
        weightData = weight.getDouble(weight.getColumnIndex(HealthyFoodDB.WE_Weight));

        //cm to ft and in
        feet = (height/2.54)/12;
        ft = feet.intValue();
        inches = (feet - ft)*12;
        in = inches.intValue();




        Log.d("DataMuscle","height :" + height);
        Log.d("DataMuscle","weight :" + weightData);

        Log.d("DataMuscle","feet :" + feet);
        Log.d("DataMuscle","ft :" + ft);
        Log.d("DataMuscle","inches :" + inches);
        Log.d("DataMuscle","in :" + in);


        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        edtBodyFat = (EditText) rootView.findViewById(R.id.edtBodyFat);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.next,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_next:
                BodyFat = edtBodyFat.getText().toString();
                bodyfat = Double.valueOf(edtBodyFat.getText().toString());
                if (edtBodyFat.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "กรุณาใส่ข้อมูลเปอร์เซ็นไขมันในร่างกายคุณ", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(edtBodyFat.getText().toString()) < 5) {
                    Toast.makeText(getActivity(), "ต้องมีไขมันในร่างมากกว่า 5 เปอร์เซ็น", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(edtBodyFat.getText().toString()) > 100) {
                    Toast.makeText(getActivity(), "คุณเปอร์เซ็นไขมันในร่างกายมากเกินไป", Toast.LENGTH_SHORT).show();
                }else {
                    dateBase = healthyFoodDB.getMaxBodyfat();
                    bf_date = dateBase.getString(dateBase.getColumnIndex(HealthyFoodDB.BF_Date));


                    leanMass = weightData * (1 - (bodyfat / 100));

                    FFMI = ((leanMass / 2.2) / (Math.pow(((ft*12)+in)* 0.0254,2)))*2.20462;
                    ffmi = String.valueOf(FFMI);
                    Log.d("DataMuscle","BodyFat :" + bodyfat);
                    Log.d("DataMuscle","leanMass :" + leanMass);
                    Log.d("DataMuscle","FFMI :" + FFMI);


                    if(bf_date == null){
                        db = healthyFoodDB.InsertBodyFat(BodyFat);
                        if (db) {
                            Toast.makeText(getActivity(), "BodyFat Inserted", Toast.LENGTH_SHORT).show();

                            // insert FFMI
                            fi = healthyFoodDB.InsertFFMI(ffmi);
                            if(fi){
                                Toast.makeText(getActivity(), "FFMI Inserted", Toast.LENGTH_SHORT).show();
                                ((BodyFatQuestionActivity) getActivity()).onGoalClkListener();
                            }else {
                                Toast.makeText(getActivity(), "Not FFNI Inserted", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getActivity(), "BodyFat not Inserted", Toast.LENGTH_SHORT).show();
                    }else if (bf_date.equals(formattedDate)) {
                        db = healthyFoodDB.UpdateBodyFat(BodyFat);
                        if (db) {
                            Toast.makeText(getActivity(), "BodyFat Update", Toast.LENGTH_SHORT).show();

                            // insert FFMI
                            fi = healthyFoodDB.UpdateFFMI(ffmi);
                            if(fi){
                                Toast.makeText(getActivity(), "FFMI IUpdate", Toast.LENGTH_SHORT).show();
                                ((BodyFatQuestionActivity) getActivity()).onGoalClkListener();
                            }else {
                                Toast.makeText(getActivity(), "Not FFNI Update", Toast.LENGTH_SHORT).show();
                            }

                        } else
                            Toast.makeText(getActivity(), "BodyFat not Update", Toast.LENGTH_SHORT).show();
                    } else {
                        db = healthyFoodDB.InsertBodyFat(BodyFat);
                        if (db) {
                            Toast.makeText(getActivity(), "BodyFat Inserted", Toast.LENGTH_SHORT).show();

                            // insert FFMI
                            fi = healthyFoodDB.InsertFFMI(ffmi);
                            if(fi){
                                Toast.makeText(getActivity(), "FFMI Inserted", Toast.LENGTH_SHORT).show();
                                ((BodyFatQuestionActivity) getActivity()).onGoalClkListener();
                            }else {
                                Toast.makeText(getActivity(), "Not FFNI Inserted", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            Toast.makeText(getActivity(), "BodyFat not Inserted", Toast.LENGTH_SHORT).show();
                    }

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
