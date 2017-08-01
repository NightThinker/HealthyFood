package com.example.mo.healthyfood;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.mo.healthyfood.activityMuscle.TodayMuscleQuestionActivity;

/**
 * Created by Mo on 12/13/2016.
 */
public class AlarmToastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "THIS IS MY ALARM", Toast.LENGTH_LONG).show();
//        Intent intent1 = new Intent(context,NewActivity.class);
//        context.startActivity(intent1);
        Intent i = new Intent();
        i.setClass(context, TodayMuscleQuestionActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
