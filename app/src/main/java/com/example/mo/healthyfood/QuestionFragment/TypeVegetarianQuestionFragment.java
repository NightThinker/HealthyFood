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

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class TypeVegetarianQuestionFragment extends Fragment {
    HealthyFoodDB healthyFoodDB;
    RadioGroup rgVeget;
    int a;
    String u_date;
    Boolean user,veget,mm,bb;
    Cursor we,use;


    public TypeVegetarianQuestionFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static TypeVegetarianQuestionFragment newInstance() {
        TypeVegetarianQuestionFragment fragment = new TypeVegetarianQuestionFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_question_type_vegetarian, container, false);
        initInstances(rootView, savedInstanceState);



        use = healthyFoodDB.getMaxUser();
        u_date = use.getString(use.getColumnIndex(HealthyFoodDB.U_Date));

        Log.d("Test","date :" + u_date);

        rgVeget.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbLactoOvo :
                        //นมไข่
                        a=1;
                        break;
                    case R.id.rbLacto :
                        //นม
                        a=2;
                        break;
                    case R.id.rbOvo :
                        //ไข่
                        a=3;
                        break;
                    case R.id.rbVegan :
                        //บริสุทธิ์
                        a=4;
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

        rgVeget = (RadioGroup) rootView.findViewById(R.id.rgVegetarian);
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
                        if (a == 1) {
                            //นมไข่
                            veget = healthyFoodDB.UpdateUserVeget("2");
                            if (veget) {
                                Log.e("Insert : ","Veget");
                                mm = healthyFoodDB.UpdateFoodAllergyVegetEggMilk();
                                if(mm){
                                    Log.e("Update","Food Allergy Veget");
                                    ((QuestionActivity) getActivity()).onAllergyQuestionSelected();
                                }else {
                                    Log.e("Update : ","Not Food Allergy Veget");
                                }

                            } else
                                Log.e("Insert : ","Not Veget");

                        } else if (a == 2) {
                            //นม
                            veget = healthyFoodDB.UpdateUserVeget("3");
                            if (veget) {
                                Log.e("Insert : ","Veget");
                                mm = healthyFoodDB.UpdateFoodAllergyVegetMilk();
                                if(mm){
                                    Log.e("Update","Food Allergy Veget");
                                    ((QuestionActivity) getActivity()).onAllergyQuestionSelected();
                                }else {
                                    Log.e("Update : ","Not Food Allergy Veget");
                                }

                            } else
                                Log.e("Insert : ","Not Veget");

                        } else if (a == 3) {
                            //ไข่
                            veget = healthyFoodDB.UpdateUserVeget("4");
                            if (veget) {
                                Log.e("Insert : ","Veget");
                                mm = healthyFoodDB.UpdateFoodAllergyVegetEgg();
                                bb = healthyFoodDB.UpdateFoodAllergyVegetEggMilk();
                                if(mm && bb){
                                    Log.e("Update","Food Allergy Veget");
                                    ((QuestionActivity) getActivity()).onAllergyQuestionSelected();
                                }else {
                                    Log.e("Update : ","Not Food Allergy Veget");
                                }

                            } else
                                Log.e("Insert : ","Not Veget");
                        } else if (a == 4) {
                            //บริสุทธิ์
                            veget = healthyFoodDB.UpdateUserVeget("5");
                            if (veget) {
                                Log.e("Insert : ","Veget");
                                mm = healthyFoodDB.UpdateFoodAllergyVegetVegan();
                                if(mm){
                                    Log.e("Update","Food Allergy Veget");
                                    ((QuestionActivity) getActivity()).onAllergyQuestionSelected();
                                }else {
                                    Log.e("Update : ","Not Food Allergy Veget");
                                }

                            } else
                                Log.e("Insert : ","Not Veget");
                        } else
                            Toast.makeText(getActivity(), "งงเบย", Toast.LENGTH_SHORT).show();

                }else {

                    if (a == 1) {
                        //นมไข่
                        veget = healthyFoodDB.UpdateUserVeget("2");
                        if (veget) {
                            Log.e("Insert : ","Veget");
                            mm = healthyFoodDB.UpdateFoodAllergyVegetEggMilk();
                            if(mm){
                                Log.e("Update","Food Allergy Veget");
                                ((QuestionActivity) getActivity()).onAllergyQuestionSelected();
                            }else {
                                Log.e("Update : ","Not Food Allergy Veget");
                            }

                        } else
                            Log.e("Insert : ","Not Veget");

                    } else if (a == 2) {
                        //นม
                        veget = healthyFoodDB.UpdateUserVeget("3");
                        if (veget) {
                            Log.e("Insert : ","Veget");
                            mm = healthyFoodDB.UpdateFoodAllergyVegetMilk();
                            if(mm){
                                Log.e("Update","Food Allergy Veget");
                                ((QuestionActivity) getActivity()).onAllergyQuestionSelected();
                            }else {
                                Log.e("Update : ","Not Food Allergy Veget");
                            }

                        } else
                            Log.e("Insert : ","Not Veget");

                    } else if (a == 3) {
                        //ไข่
                        veget = healthyFoodDB.UpdateUserVeget("4");
                        if (veget) {
                            Log.e("Insert : ","Veget");
                            mm = healthyFoodDB.UpdateFoodAllergyVegetEgg();
                            bb = healthyFoodDB.UpdateFoodAllergyVegetEggMilk();
                            if(mm && bb){
                                Log.e("Update","Food Allergy Veget");
                                ((QuestionActivity) getActivity()).onAllergyQuestionSelected();
                            }else {
                                Log.e("Update : ","Not Food Allergy Veget");
                            }

                        } else
                            Log.e("Insert : ","Not Veget");
                    } else if (a == 4) {
                        //บริสุทธิ์
                        veget = healthyFoodDB.UpdateUserVeget("5");
                        if (veget) {
                            Log.e("Insert : ","Veget");
                            mm = healthyFoodDB.UpdateFoodAllergyVegetVegan();
                            if(mm){
                                Log.e("Update","Food Allergy Veget");
                                ((QuestionActivity) getActivity()).onAllergyQuestionSelected();
                            }else {
                                Log.e("Update : ","Not Food Allergy Veget");
                            }

                        } else
                            Log.e("Insert : ","Not Veget");
                    } else
                        Toast.makeText(getActivity(), "งงเบย", Toast.LENGTH_SHORT).show();

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
