package com.example.mo.healthyfood.fragmentMuscle;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.QuestionMuscleActivity;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class TypeMuscleQuestionFragment extends Fragment {
//    UserDB user;

    HealthyFoodDB healthyFoodDB;
   RadioGroup rg_typeMuscle;
    FancyButton btn_next;
    String bodyfat_goal,tm_id,fi_id,bf_id,U_ID;
    Bundle bundle;
    int a,BF_Id,fiId,u_id;
    Cursor bodyfat,fmi,user;
    Boolean um;


    public TypeMuscleQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static TypeMuscleQuestionFragment newInstance(Bundle bundle) {
        TypeMuscleQuestionFragment fragment = new TypeMuscleQuestionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);

        getActivity().setTitle("Goal");
        Toast.makeText(getActivity(),"bodyfat  = " + getArguments().getInt("bodyfatgoal")
                +"weight = " + getArguments().getDouble("weight"),Toast.LENGTH_SHORT).show();
        healthyFoodDB = new HealthyFoodDB(getActivity());



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_muscle_question_type_muscle, container, false);
        initInstances(rootView, savedInstanceState);

        rg_typeMuscle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                bundle = new Bundle();
                switch (checkedId) {
                    case R.id.rb_fatLoss:
                        a = 1;
                        tm_id = "TM01";
                        bundle.putString("TMID","TM01");
                        break;
                    case R.id.rb_gainMuscle:
                        a = 2;
                        tm_id = "TM02";
                        bundle.putString("TMID","TM02");
                        break;
                }
            }
        });


        bodyfat = healthyFoodDB.getMaxBodyfat();
        BF_Id = bodyfat.getInt(bodyfat.getColumnIndex(HealthyFoodDB.BF_ID));
        bf_id = String.valueOf(BF_Id);

    //    user = healthyFoodDB.getMaxUser();

        user = healthyFoodDB.getUser();
        u_id = user.getInt(user.getColumnIndex(HealthyFoodDB.U_ID));
        U_ID = String.valueOf(u_id);



        fmi = healthyFoodDB.getMaxFFMI();
        fiId = fmi.getInt(fmi.getColumnIndex(HealthyFoodDB.FI_ID));
        fi_id = String.valueOf(fiId);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(a == 1){
                    um = healthyFoodDB.InsertUserMuscle(U_ID,bf_id,fi_id,tm_id);
                    if(um){
                        Toast.makeText(getActivity(), "UserMuscle Inserted", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putDouble("weight", getArguments().getInt("weight"));
                        bundle.putInt("bodyfatgoal", getArguments().getInt("bodyfatgoal"));
                        ((QuestionMuscleActivity)getActivity()).onPlanLoseQuestionSelected(bundle);
                    }else {
                        Toast.makeText(getActivity(), "Not UserMuscle Inserted", Toast.LENGTH_SHORT).show();
                    }

                }else if ( a == 2){
                    um = healthyFoodDB.InsertUserMuscle(U_ID,bf_id,fi_id,tm_id);
                    if(um){
                        Toast.makeText(getActivity(), "UserMuscle Inserted", Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putDouble("weight", getArguments().getInt("weight"));
                        bundle.putInt("bodyfatgoal", getArguments().getInt("bodyfatgoal"));
                        ((QuestionMuscleActivity)getActivity()).onPlanGainQuestionSelected(bundle);
                    }else {
                        Toast.makeText(getActivity(), "Not UserMuscle Inserted", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getActivity(), "กรุณาเลือกประเภทที่ต้องการด้วย", Toast.LENGTH_SHORT).show();
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
        rg_typeMuscle = (RadioGroup) rootView.findViewById(R.id.rg_typeMuscle) ;
        btn_next = (FancyButton) rootView.findViewById(R.id.btn_next);
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
