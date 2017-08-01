package com.example.mo.healthyfood.FoodMuscle;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.FoodMuscleActivity;
import com.example.mo.healthyfood.activityMuscle.ShowListFoodMuscleActivity;
import com.example.mo.healthyfood.activityNormal.ShowFoodListNormalActivity;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class ShowListMuscleFragment extends Fragment {

   SimpleCursorAdapter dataAdapter;
    ListView listView;
    HealthyFoodDB healthyFoodDB;
    String tf_id,m_id;

    ShowListFoodMuscleActivity  showListFoodMuscleActivity;


//    FoodRecommendAdapter  dataAdapter = null;

    public interface OpenActivity{
        void onOpenActivity(Bundle bundle);
    }

    public ShowListMuscleFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ShowListMuscleFragment newInstance(Bundle args) {
        ShowListMuscleFragment fragment = new ShowListMuscleFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_muscle_food_show, container, false);
        initInstances(rootView, savedInstanceState);

        //รับค่า m_id
        tf_id = getArguments().getString("tf_id");

        m_id = getArguments().getString("m_id");

        Log.e("Check","m_id : " + m_id);
        Log.e("Check","tf_id : " + tf_id);





        displayListView();

        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        listView = (ListView) rootView.findViewById(R.id.lvFoodList);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        showListFoodMuscleActivity = (ShowListFoodMuscleActivity)getActivity();
    }

    private void displayListView() {
//
//        ArrayList<Allergy> allergyList = new ArrayList<Allergy>();
//        Cursor c1 = healthyFoodDB.getAllFoodAllergy();
//        if(c1 != null && c1.getCount() != 0){
//            if(c1.moveToFirst()){
//                do{
//                    Allergy allergy = new Allergy();
//
//                    allergy.setName(c1.getString(c1.getColumnIndex(HealthyFoodDB.FA_Name)));
//                    allergyList.add(allergy);
//                    Log.d("check","allergylist : "+ c1.getString(c1.getColumnIndex(HealthyFoodDB.FA_Name)));
//                }while (c1.moveToNext());
//
//
//            }
//        }




//        ArrayList<Food> foodList = new ArrayList<Food>();
//        for (int a = 0; a < allergyList.size() ; a++){
//            Allergy fnIdAllergy = allergyList.get(a);
//            String fn_id = String.valueOf(fnIdAllergy.getName());
//            Log.d("check","fn_id : " + fn_id);
//            Cursor food = healthyFoodDB.getAllnameFoodMuscle(fn_id);
//            if(food != null && food.getCount() != 0){
//                if(food.moveToFirst()){
//                    do{
//                        Food fo = new Food();
//
//                        fo.setName(food.getString(food.getColumnIndex(HealthyFoodDB.FM_Name)));
//                        foodList.add(fo);
//
//                        Log.d("Testcheck","foodList : "+ food.getString(food.getColumnIndex(HealthyFoodDB.FM_Name)));
//                    }while (food.moveToNext());
//                }
//            }
//
//        }

        final Cursor cursor = healthyFoodDB.getAllFoodMuscle(tf_id);
        String[] columns = new String[] {
                healthyFoodDB.FM_Name,
        };

        int[] to = new int[] {
                R.id.txt_food,
        };

        dataAdapter = new SimpleCursorAdapter(getActivity(), R.layout.fragment_list_food, cursor, columns, to, 0);

      //  dataAdapter = new FoodRecommendAdapter(foodList,getActivity());

       // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //page detail food
                //TODO: ส่งค่า f_id ไป DetailFood;Fragment
                Bundle bundle = new Bundle();
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                //cursor.moveToPosition(position);
                bundle.putString("f_id", cursor.getString(0));
                bundle.putString("m_id",m_id);
                bundle.putString("date",getArguments().getString("date"));
                Log.d("FID","F_ID :" + cursor.getString(0));
                showListFoodMuscleActivity.onShowFoodDetail(bundle);
              //  ((OpenActivity) getActivity()).onOpenActivity(bundle);

            }
        });
    }

}
