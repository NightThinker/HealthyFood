package com.example.mo.healthyfood.FoodNormal;

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
import com.example.mo.healthyfood.activityNormal.FoodNormalActivity;
import com.example.mo.healthyfood.activityNormal.ShowFoodListNormalActivity;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class ShowListNormalFragment extends Fragment {

    SimpleCursorAdapter dataAdapter;
    ListView listView;
    HealthyFoodDB healthyFoodDB;
    String m_id,tf_id;

    Cursor  heal;
    String ht_id;

    ShowFoodListNormalActivity showFoodListNormalActivity;

//    FoodRecommendAdapter  dataAdapter = null;

//    public interface OpenActivity{
//        void onOpenActivity(Bundle bundle);
//    }

    public ShowListNormalFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ShowListNormalFragment newInstance(Bundle args) {
        ShowListNormalFragment fragment = new ShowListNormalFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_normal_food_show, container, false);
        initInstances(rootView, savedInstanceState);

        //รับค่า m_id
        m_id = getArguments().getString("m_id");
        tf_id = getArguments().getString("tf_id");

        Log.e("Check","m_id : " + m_id);
        Log.e("Check","tf_id : " + tf_id);



        heal = healthyFoodDB.getNormalToday();
        ht_id = heal.getString(heal.getColumnIndex(HealthyFoodDB.HT_ID));



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
        showFoodListNormalActivity = (ShowFoodListNormalActivity) getActivity();
    }

    private void displayListView() {

        final Cursor cursor = healthyFoodDB.getAllFoodNormal();
        String[] columns = new String[] {
                healthyFoodDB.FN_Name,
        };

        int[] to = new int[] {
                R.id.txt_food,
        };

        dataAdapter = new SimpleCursorAdapter(getActivity(), R.layout.fragment_list_food, cursor, columns, to, 0);

//        // Assign adapter to ListView
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
                showFoodListNormalActivity.onShowFoodDetail(bundle);
               // ((OpenActivity) getActivity()).onOpenActivity(bundle);
            }
        });
    }

}
