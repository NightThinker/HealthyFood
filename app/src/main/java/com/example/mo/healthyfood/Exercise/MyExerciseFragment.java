package com.example.mo.healthyfood.Exercise;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.ExerciseActivity;
import com.example.mo.healthyfood.activityNormal.FoodNormalActivity;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class MyExerciseFragment extends Fragment {
    private SimpleCursorAdapter dataAdapter;
    ListView listView;
    HealthyFoodDB healthyFoodDB;
    FancyButton btnCreate;

    ExerciseActivity exerciseActivity;

    public MyExerciseFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static MyExerciseFragment newInstance(Bundle args) {
        MyExerciseFragment fragment = new MyExerciseFragment();
 //       Bundle args = new Bundle();
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
    public void onAttach(Context context) {
        super.onAttach(context);
        exerciseActivity = (ExerciseActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise_myexercise, container, false);
        initInstances(rootView, savedInstanceState);
        displayListView();
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ExerciseActivity)getActivity()).onCreateExercisClkListener();
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
        listView = (ListView) rootView.findViewById(R.id.lvCustomExertList);
        btnCreate = (FancyButton) rootView.findViewById(R.id.btnCreate);
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
        Cursor cursor = healthyFoodDB.getCustomExert();
        String[] columns = new String[] {
                healthyFoodDB.EX_Name,healthyFoodDB.EX_Time,
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.txtExert,R.id.txtTime,
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(getActivity(), R.layout.fragment_list_exercise_create, cursor, columns, to, 0);


        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                //cursor.moveToPosition(position);
                bundle.putString("ex_id", cursor.getString(0));
                bundle.putString("m_id",getArguments().getString("m_id"));
                bundle.putString("date",getArguments().getString("date"));
                exerciseActivity.onShowExerciseClkListener(bundle);

                //TODO ยังกดไปไหนไม่ได้ ไปเข้าหน้า detaial Exert

            }
        });
    }

}
