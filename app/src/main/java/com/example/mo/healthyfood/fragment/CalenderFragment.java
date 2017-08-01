package com.example.mo.healthyfood.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mo.healthyfood.DB.HealthyFoodDB;
import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.activity.CalendarActivity;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class CalenderFragment extends Fragment {

    CompactCalendarView compactCalendarView;
    Button currentMonth;
    HealthyFoodDB healthyFoodDB;
    Cursor user,date;
    String tu_id,u_date;
    Date test;

    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());

    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = df.format(c.getTime());

    CalendarActivity calendarActivity;

    public CalenderFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CalenderFragment newInstance() {
        CalenderFragment fragment = new CalenderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        calendarActivity =  (CalendarActivity)getActivity();
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
        View rootView = inflater.inflate(R.layout.fragment_main_calender, container, false);
        initInstances(rootView, savedInstanceState);

        user = healthyFoodDB.getMaxTypeUser();
        tu_id = user.getString(user.getColumnIndex(HealthyFoodDB.DTU_ID));


        date = healthyFoodDB.getUser();
        u_date = date.getString(date.getColumnIndex(HealthyFoodDB.U_Date));






        compactCalendarView.drawSmallIndicatorForEvents(true);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        //set initial title
        // actionBar.setTitle(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

        currentMonth.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));

//        Toast.makeText(getActivity(),"day : "+yearMonthDay.format(currentCalender),Toast.LENGTH_SHORT).show();

        //set title on calendar scroll
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                try {
                    Date startDate = df.parse(u_date);
                    Log.d("DataMuscle", "Date Convert : " + startDate);
                    Log.d("DataMuscle","Date getTime : " +c.getTime());
                    Log.d("DataMuscle","Date dateClick : " +dateClicked);
                    Bundle bundle = new Bundle();


                    if (tu_id.equals("TU01")) {
                        if (df.format(c.getTime()).equals(yearMonthDay.format(dateClicked))) {
                            Toast.makeText(getActivity(), "Select Date after current date!", Toast.LENGTH_SHORT).show();
                            bundle.putString("date",yearMonthDay.format(dateClicked));
                        //    bundle.putString("date", dateClicked.toString());
                            calendarActivity.onMyDayNormalClkListener(bundle);


                        } else if (c.getTime().after(dateClicked) && startDate.before(dateClicked)) {
                            Toast.makeText(getActivity(), "Select Date after current date!", Toast.LENGTH_SHORT).show();
                            bundle.putString("date",yearMonthDay.format(dateClicked));
//                            bundle.putString("date", dateClicked.toString());
                            calendarActivity.onMyDayNormalClkListener(bundle);

                        } else if (c.getTime().after(dateClicked) && yearMonthDay.format(startDate).equals(yearMonthDay.format(dateClicked))) {
                            Toast.makeText(getActivity(), "Select Date after current date!", Toast.LENGTH_SHORT).show();
                            bundle.putString("date",yearMonthDay.format(dateClicked));
//                            bundle.putString("date", dateClicked.toString());
                            calendarActivity.onMyDayNormalClkListener(bundle);

                        } else if (c.getTime().before(dateClicked)) {
                            Toast.makeText(getActivity(), "Select Data before current date!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Date : " + yearMonthDay.format(dateClicked), Toast.LENGTH_SHORT).show();
                        }
                    } else if (tu_id.equals("TU02")) {
                        if (df.format(c.getTime()).equals(yearMonthDay.format(dateClicked))) {
                            Toast.makeText(getActivity(), "Select Date after current date!", Toast.LENGTH_SHORT).show();
                            bundle.putString("date",yearMonthDay.format(dateClicked));
//                            bundle.putString("date", dateClicked.toString());
                            calendarActivity.onMyDayMuscleClkListener(bundle);

                        } else if (c.getTime().after(dateClicked) && startDate.before(dateClicked)) {
                            Toast.makeText(getActivity(), "Select Date after current date!", Toast.LENGTH_SHORT).show();
                            bundle.putString("date",yearMonthDay.format(dateClicked));
//                            bundle.putString("date", dateClicked.toString());
                            calendarActivity.onMyDayMuscleClkListener(bundle);

                        }
                        else if (c.getTime().after(dateClicked) && yearMonthDay.format(startDate).equals(yearMonthDay.format(dateClicked))) {
                            Toast.makeText(getActivity(), "Select Date after current date!", Toast.LENGTH_SHORT).show();
                            bundle.putString("date",yearMonthDay.format(dateClicked));
//                            bundle.putString("date", dateClicked.toString());
                            calendarActivity.onMyDayMuscleClkListener(bundle);

                        } else if (c.getTime().before(dateClicked)) {
                            Toast.makeText(getActivity(), "Select Data before current date!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Date : " + yearMonthDay.format(dateClicked), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "TU_ID is Empty", Toast.LENGTH_SHORT).show();
                    }


//                if (clicked.equals(test)) {
//                    Toast.makeText(getActivity(), "Select Date after current date!", Toast.LENGTH_SHORT).show();
//                } else if (currentCalender.after(test)) {
//                    Toast.makeText(getActivity(), "Select Date after current date!", Toast.LENGTH_SHORT).show();
//                } else if (currentCalender.before(test)) {
//                    Toast.makeText(getActivity(), "Select Data before current date!", Toast.LENGTH_SHORT).show();
//                } else{
//
//                    Toast.makeText(getActivity(), "Date : " + yearMonthDay.format(dateClicked), Toast.LENGTH_SHORT).show();
//            }
                }catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                // Changes toolbar title on monthChange
                //      actionBar.setTitle(dateFormatForMonth.format(firstDayOfNewMonth));
                currentMonth.setText(dateFormatForMonth.format(firstDayOfNewMonth));

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

        compactCalendarView = (CompactCalendarView) rootView.findViewById(R.id.compactcalendar_view);
        currentMonth = (Button) rootView.findViewById(R.id.currentMonth);
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
