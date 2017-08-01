package com.example.mo.healthyfood.QuestionFragment;

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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.QuestionActivity;
import com.example.mo.healthyfood.activity.QuestionSelectActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class AllergyQuestionFragment extends Fragment {
    HealthyFoodDB healthyFoodDB;
    RadioGroup rg_allergy;
    int a;
    String u_date;
    Boolean user,allergy,db,bb;
    Cursor we,use,vt;
    Integer vt_id;


    public AllergyQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static AllergyQuestionFragment newInstance() {
        AllergyQuestionFragment fragment = new AllergyQuestionFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_question_allergy, container, false);
        initInstances(rootView, savedInstanceState);



        use = healthyFoodDB.getMaxUser();
        u_date = use.getString(use.getColumnIndex(HealthyFoodDB.U_Date));

        vt = healthyFoodDB.getUser();
        vt_id = vt.getInt(vt.getColumnIndex(HealthyFoodDB.VT_ID));

        Log.d("Test","date :" + u_date);

        rg_allergy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbNo :
                        a=1;
                        break;
                    case R.id.rbYes :
                        a=2;
                        break;
                }
            }
        });



        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        rg_allergy = (RadioGroup) rootView.findViewById(R.id.rgAllergy);
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
                if(u_date == null) {

                    if(vt_id == 1){
                        //ไม่ทานมัง
                        if(a == 1){
                            //ไม่แพ้อาหาร
                            allergy = healthyFoodDB.UpdateAllergy();
                            if(allergy){
                                Log.e("Insert : ","Allergy");
                                ((QuestionActivity) getActivity()).onActivitiesFragmentClkListener();
                            }else {
                                Log.e("Insert : ","Not Allergy");
                            }

                        }else if (a == 2){
                            //แพ้อาหาร
                            ((QuestionActivity) getActivity()).onFoodAllergyQuestionSelected();
                        }else
                            Toast.makeText(getActivity(),"กรุณาเลือกว่าแพ้อาหารหรือไม่",Toast.LENGTH_LONG).show();
                    }else {
                        //ทานมัง
                        if(a == 1){
                            //ไม่แพ้อาหาร
                            ((QuestionActivity) getActivity()).onActivitiesFragmentClkListener();
                        }else if (a == 2){
                            //แพ้อาหาร
                            ((QuestionActivity) getActivity()).onFoodAllergyQuestionSelected();
                        }else
                            Toast.makeText(getActivity(),"กรุณาเลือกว่าแพ้อาหารหรือไม่",Toast.LENGTH_LONG).show();
                    }


                }else {

                    if(vt_id == 1){
                        //ไม่ทานมัง
                        if(a == 1){
                            //ไม่แพ้อาหาร
                            allergy = healthyFoodDB.UpdateAllergy();
                            if(allergy){
                                Log.e("Insert : ","Allergy");
                                ((QuestionActivity) getActivity()).onActivitiesFragmentClkListener();
                            }else {
                                Log.e("Insert : ","Not Allergy");
                            }

                        }else if (a == 2){
                            //แพ้อาหาร
                            ((QuestionActivity) getActivity()).onFoodAllergyQuestionSelected();
                        }else
                            Toast.makeText(getActivity(),"กรุณาเลือกว่าแพ้อาหารหรือไม่",Toast.LENGTH_LONG).show();
                    }else {
                        //ทานมัง
                        if(a == 1){
                            //ไม่แพ้อาหาร
                            ((QuestionActivity) getActivity()).onActivitiesFragmentClkListener();
                        }else if (a == 2){
                            //แพ้อาหาร
                            ((QuestionActivity) getActivity()).onFoodAllergyQuestionSelected();
                        }else
                            Toast.makeText(getActivity(),"กรุณาเลือกว่าแพ้อาหารหรือไม่",Toast.LENGTH_LONG).show();
                    }


                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
