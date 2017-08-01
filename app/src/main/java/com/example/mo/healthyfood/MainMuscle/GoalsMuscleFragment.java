package com.example.mo.healthyfood.MainMuscle;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activityMuscle.MainMuscleActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class GoalsMuscleFragment extends Fragment implements OnChartGestureListener,
        OnChartValueSelectedListener {

    FloatingActionButton add;
    HealthyFoodDB healthyFoodDB;
    LineChart mChart;
    FancyButton btnWeight,btnBodyFat;
    Cursor goal;
    Float goalWeight,goalBodyFat;

    MainMuscleActivity mainMuscleActivity;

    public GoalsMuscleFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static GoalsMuscleFragment newInstance(Bundle args) {
        GoalsMuscleFragment fragment = new GoalsMuscleFragment();
       // Bundle args = new Bundle();
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
        mainMuscleActivity = (MainMuscleActivity)getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_muscle_main_goals, container, false);
        initInstances(rootView, savedInstanceState);

        goal = healthyFoodDB.getMaxGoalMuscle();
        goalWeight =  goal.getFloat(goal.getColumnIndex(HealthyFoodDB.GM_Weight));
        goalBodyFat = goal.getFloat(goal.getColumnIndex(HealthyFoodDB.GM_Bodyfat));



        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);

        // add data
        setData();


        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);

        // no description text
     //   mChart.setDescription("Demo Line Chart");
        mChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        //float a = 130f;

        LimitLine upper_limit = new LimitLine(goalWeight, "Weight Limit");
        upper_limit.setLineWidth(4f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);

        LimitLine lower_limit = new LimitLine(goalBodyFat, "BodyFat Limit");
        lower_limit.setLineWidth(4f);
        lower_limit.enableDashedLine(10f, 10f, 0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaxValue(200f);
        leftAxis.setAxisMinValue(5f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        mChart.animateX(2500, Easing.EasingOption.EaseInOutQuart);

        //  dont forget to refresh the drawing
        mChart.invalidate();

//        btnWeight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((MainMuscleActivity)getActivity()).onWeightClkListener();
//            }
//        });

        btnBodyFat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainMuscleActivity)getActivity()).onBodyFatClkListener();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("date",getArguments().getString("date"));
                mainMuscleActivity.onAddClkListener(bundle);
              //  ((MainMuscleActivity) getActivity()).onAddClkListener(bundle);

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
        add = (FloatingActionButton) rootView.findViewById(R.id.add);
    //    barChart = (BarChart) rootView.findViewById(R.id.bargrap);
     //   btnWeight = (FancyButton) rootView.findViewById(R.id.btnWeight);
        btnBodyFat = (FancyButton) rootView.findViewById(R.id.btnBodyFat);
        mChart = (LineChart) rootView.findViewById(R.id.linechart);
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

    private ArrayList<String> setXWeightValues() {
        //จำนวนวันของเดือน
        ArrayList<String> xWeight = new ArrayList<String>();
        Cursor c1 = healthyFoodDB.getAllWeight();
        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    // for (int i = 1; i <= dayOfWeek; i++) {
                    xWeight.add(c1.getString(c1.getColumnIndex(HealthyFoodDB.WE_Date)));
                    //  xVals.add(String.valueOf(i));
                    // }
                } while (c1.moveToNext());
            }
        }

        return xWeight;
    }




    private ArrayList<Entry> setYWeightValues() {

        //  ค่าน้ำหนัก
        ArrayList<Entry> yWeight = new ArrayList<Entry>();
        Cursor c1 = healthyFoodDB.getAllWeight();
        int i = 0;
        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    yWeight.add(new Entry(c1.getFloat(c1.getColumnIndex(HealthyFoodDB.WE_Weight)), i));
                    i++;
                } while (c1.moveToNext());
            }
        }

        return yWeight;
    }

    private ArrayList<Entry> setYBodyFatValues() {

        //  ค่าไขมัน
        ArrayList<Entry> yBodyFat = new ArrayList<Entry>();
        Cursor c1 = healthyFoodDB.getAllBodyFat();
        int i = 0;
        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    yBodyFat.add(new Entry(c1.getFloat(c1.getColumnIndex(HealthyFoodDB.BF_BodyFat)), i));
                    i++;
                } while (c1.moveToNext());
            }
        }

        return yBodyFat;
    }

    private ArrayList<Entry> setYFFMIValues() {

        //  ค่าไขมัน
        ArrayList<Entry> yFFMI = new ArrayList<Entry>();
        Cursor c1 = healthyFoodDB.getAllFFMI();
        int i = 0;
        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    yFFMI.add(new Entry(c1.getFloat(c1.getColumnIndex(HealthyFoodDB.FI_FFMI)), i));
                    i++;
                } while (c1.moveToNext());
            }
        }

        return yFFMI;
    }

    private void setData() {
        ArrayList<String> xWeight = setXWeightValues();

        ArrayList<Entry> yWeight = setYWeightValues();
        ArrayList<Entry> yBodyFat = setYBodyFatValues();
        ArrayList<Entry> yFfmi = setYFFMIValues();


        LineDataSet weight;
        LineDataSet bodyFat;
        LineDataSet FFMI;

        // create a dataset and give it a type
        weight = new LineDataSet(yWeight, "Weight");
        bodyFat = new LineDataSet(yBodyFat,"BodyFat");
        FFMI = new LineDataSet(yFfmi,"FFMI");

        weight.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        weight.setColor(Color.GREEN);
        weight.setCircleColor(Color.GREEN);
        weight.setLineWidth(1f);
        weight.setCircleRadius(3f);
        weight.setDrawCircleHole(false);
        weight.setValueTextSize(9f);
        weight.setDrawFilled(true);

        bodyFat.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        bodyFat.setColor(Color.RED);
        bodyFat.setCircleColor(Color.RED);
        bodyFat.setLineWidth(1f);
        bodyFat.setCircleRadius(3f);
        bodyFat.setDrawCircleHole(false);
        bodyFat.setValueTextSize(9f);
        bodyFat.setDrawFilled(true);

        FFMI.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
        //   set1.enableDashedLine(10f, 5f, 0f);
        // set1.enableDashedHighlightLine(10f, 5f, 0f);
        FFMI.setColor(Color.YELLOW);
        FFMI.setCircleColor(Color.YELLOW);
        FFMI.setLineWidth(1f);
        FFMI.setCircleRadius(3f);
        FFMI.setDrawCircleHole(false);
        FFMI.setValueTextSize(9f);
        FFMI.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(weight); // add the datasets
        dataSets.add(bodyFat);
        dataSets.add(FFMI);


        // create a data object with the datasets
        LineData data = new LineData(xWeight, dataSets);


        // set data
        mChart.setData(data);


    }



    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            mChart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + mChart.getLowestVisibleXIndex() + ", high: " + mChart.getHighestVisibleXIndex());
        Log.i("MIN MAX", "xmin: " + mChart.getXChartMin() + ", xmax: " + mChart.getXChartMax() + ", ymin: " + mChart.getYChartMin() + ", ymax: " + mChart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }


}
