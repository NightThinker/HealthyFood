package com.example.mo.healthyfood.add;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityNormal.AddNormalActivity;
import com.example.mo.healthyfood.adapter.AddAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Mo on 4/4/2016.
 */
public class AddListNormalFragment extends Fragment {

    AddNormalActivity addNormalActivity;


    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());

    ListView lvDetail;
    Context context = getActivity();
    ArrayList<AddData> myList = new ArrayList<AddData>();

    String[] title = new String[]{
            "มื้อเช้า", "มื้อกลางวัน", "มื้อเย็น", "มื้อว่าง",
            "ออกกำลังกาย", "น้ำหนัก"
    };
    String[] desc = new String[]{
            "Desc 1", "Desc 2", "Desc 3", "Desc 4",
            "Desc 5", "Desc 6"
    };
    int[] img = new int[]{
            R.drawable.ic_breakfast, R.drawable.ic_lunch, R.drawable.ic_diner, R.drawable.ic_snack,
            R.drawable.ic_exercise, R.drawable.ic_weight
    };

    Bundle bundle = new Bundle();
    public AddListNormalFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static AddListNormalFragment newInstance(Bundle args) {
        AddListNormalFragment fragment = new AddListNormalFragment();
  //      Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addNormalActivity = (AddNormalActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        initInstances(rootView, savedInstanceState);


        // insert data into the list before setting the adapter
        // otherwise it will generate NullPointerException  - Obviously
        getDataInList();
        lvDetail.setAdapter(new AddAdapter(myList, getActivity()));
        lvDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"ตำแหน่ง"+position,Toast.LENGTH_SHORT).show();
                //ส่งค่า m_id

                //มื้อเช้า
                if(position == 0) {
                    if(getArguments() != null){
                        bundle.putString("m_id","ME01");
                        bundle.putString("date",getArguments().getString("date"));
                        addNormalActivity.onOpenDetailActivity(bundle);
                    }else {
                        bundle.putString("m_id","ME01");
                        bundle.putString("date",formattedDate);
                        addNormalActivity.onOpenDetailActivity(bundle);

                    }

                   // ((AddNormalActivity) getActivity()).onFoodClkListener();
                }
                //มื้อกลางวัน
                else if (position == 1){
                    if(getArguments() != null){
                        bundle.putString("m_id","ME02");
                        bundle.putString("date",getArguments().getString("date"));
                        addNormalActivity.onOpenDetailActivity(bundle);
                    }else {
                        bundle.putString("m_id","ME02");
                        bundle.putString("date",formattedDate);
                        addNormalActivity.onOpenDetailActivity(bundle);

                    }

                }
                //มื้อเย็น
                else if (position == 2){
                    if(getArguments() != null){
                        bundle.putString("m_id","ME03");
                        bundle.putString("date",getArguments().getString("date"));
                        addNormalActivity.onOpenDetailActivity(bundle);
                    }else {
                        bundle.putString("m_id","ME03");
                        bundle.putString("date",formattedDate);
                        addNormalActivity.onOpenDetailActivity(bundle);

                    }

                }
                //มื้อว่าง
                else if (position == 3){
                    if(getArguments() != null){
                        bundle.putString("m_id","ME05");
                        bundle.putString("date",getArguments().getString("date"));
                        addNormalActivity.onOpenDetailActivity(bundle);
                    }else {
                        bundle.putString("m_id","ME05");
                        bundle.putString("date",formattedDate);
                        addNormalActivity.onOpenDetailActivity(bundle);

                    }

                }
                //ออกกำลังาย
                else if (position == 4){
                    if(getArguments() != null){
                        bundle.putString("m_id","ME07");
                        bundle.putString("date",getArguments().getString("date"));
                        addNormalActivity.onExerciseClkListener(bundle);
                    }else {
                        bundle.putString("m_id","ME07");
                        bundle.putString("date",formattedDate);
                        addNormalActivity.onExerciseClkListener(bundle);
                    }

                }
                //น้ำหนัก
                else if (position == 5){
                    ((AddNormalActivity)getActivity()).onWeightClkListener();
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
        lvDetail = (ListView) rootView.findViewById(R.id.lvCustomList);
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

    private void getDataInList() {
        for (int i = 0; i < title.length; i++) {
            // Create a new object for each list item
            AddData ld = new AddData();
            ld.setTitle(title[i]);
            ld.setDescription(desc[i]);
            ld.setImgResId(img[i]);
            // Add this object into the ArrayList myList
            myList.add(ld);
        }
    }

}


