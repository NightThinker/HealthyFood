package com.example.mo.healthyfood.Exercise;


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
import com.example.mo.healthyfood.activity.ExerciseActivity;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class SearchExerciseFragment extends Fragment {

    String me_id;

    private SimpleCursorAdapter dataAdapter;
    ListView listView;
    EditText myFilter;
    HealthyFoodDB healthyFoodDB;


    ExerciseActivity exerciseActivity;

//    private SearchView searchView = null;
//    private SearchView.OnQueryTextListener queryTextListener;


    public SearchExerciseFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static SearchExerciseFragment newInstance(Bundle args) {
        SearchExerciseFragment fragment = new SearchExerciseFragment();
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
    public void onAttach(Context context) {
        super.onAttach(context);

        exerciseActivity = (ExerciseActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise_search, container, false);
        initInstances(rootView, savedInstanceState);

        me_id = getArguments().getString("m_id");
//        Toast.makeText(getActivity(),"m_id = " + me_id,Toast.LENGTH_SHORT).show();


        displayListView();
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

//        searchView = (SearchView) rootView.findViewById(R.id.action_search);
//        lvDetail = (ListView) rootView.findViewById(R.id.lvFoodList);
        listView = (ListView) rootView.findViewById(R.id.lvExerciseList);
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

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        //SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_search:
//                // Not implemented here
//                return false;
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void displayListView() {

        final Cursor cursor = healthyFoodDB.getAllExercise();

        // The desired columns to be bound
        String[] columns = new String[] {
                healthyFoodDB.EX_Name,
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.txt_exert,
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(getActivity(), R.layout.fragment_list_exercise, cursor, columns, to, 0);


        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //page detail food
                // ส่งค่า ex_id ไป DetailExerciseFragment
                Bundle bundle = new Bundle();
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
              //cursor.moveToPosition(position);
                bundle.putString("ex_id", cursor.getString(0));
                bundle.putString("m_id",me_id);
                bundle.putString("date",getArguments().getString("date"));
                exerciseActivity.onShowExerciseClkListener(bundle);

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
                return healthyFoodDB.getExerciseByName(constraint.toString());
            }
        });

    }


}
