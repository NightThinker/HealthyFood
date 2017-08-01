package com.example.mo.healthyfood.FoodNormal;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityNormal.FoodNormalActivity;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class SearchNormalFragment extends Fragment {

    String m_id;



    SimpleCursorAdapter dataAdapter;
    ListView listView;
    EditText myFilter;
    HealthyFoodDB healthyFoodDB;

    FoodNormalActivity foodNormalActivity;



//    private SearchView searchView = null;
//    private SearchView.OnQueryTextListener queryTextListener;


    public SearchNormalFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static SearchNormalFragment newInstance(Bundle args) {
        SearchNormalFragment fragment = new SearchNormalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        setHasOptionsMenu(true);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
        healthyFoodDB = new HealthyFoodDB(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_normal_food_search, container, false);
        initInstances(rootView, savedInstanceState);

        //รับค่า m_id
        m_id = getArguments().getString("m_id");

        Toast.makeText(getActivity(),"m_id = " + m_id,Toast.LENGTH_SHORT).show();

        displayListView();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        foodNormalActivity = (FoodNormalActivity)getActivity();
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

//        searchView = (SearchView) rootView.findViewById(R.id.action_search);
//        lvDetail = (ListView) rootView.findViewById(R.id.lvFoodList);
        listView = (ListView) rootView.findViewById(R.id.lvFoodList);
        myFilter = (EditText) rootView.findViewById(R.id.myFilter);
       // myFilter.setShowSoftInputOnFocus(false);
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



    private void displayListView() {

        final Cursor cursor = healthyFoodDB.getAllFoodNormal();

        // The desired columns to be bound
        String[] columns = new String[] {
                healthyFoodDB.FN_Name,
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.txt_food,
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(getActivity(), R.layout.fragment_list_food, cursor, columns, to, 0);


        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //page detail food
                //ส่งค่า f_id ไป DetailFood;Fragment
                Bundle bundle = new Bundle();
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
              //cursor.moveToPosition(position);
                bundle.putString("f_id", cursor.getString(0));
                bundle.putString("m_id",m_id);
                bundle.putString("date",getArguments().getString("date"));
                foodNormalActivity.onOpenActivity(bundle);
            }
        });




        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return healthyFoodDB.getFoodNormakByName(constraint.toString());
            }
        });

    }


}
