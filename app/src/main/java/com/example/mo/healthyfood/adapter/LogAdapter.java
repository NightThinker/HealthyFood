package com.example.mo.healthyfood.adapter;

/**
 * Created by Mo on 4/13/2016.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.add.AddData;
import com.example.mo.healthyfood.view.MealFoodListItem;
import com.example.mo.healthyfood.view.MealListItem;

import java.util.ArrayList;

public class LogAdapter extends BaseAdapter {


    ArrayList<AddData> myList = new ArrayList<>();
    Activity activity;
//    AddData currentAddData;

    public LogAdapter(ArrayList<AddData> myList, Activity activity) {
        this.myList = myList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return myList.size() ;
    }

    @Override
    public AddData getItem(int position) {
        return myList.get(position);
    }
//
//    @Override
//    public int getViewTypeCount() {
//        //จำนวนประเภท listview
//        return 2;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        //MealListItem return 0; MealFoodListItem return 1;
//        return position % 2 == 0 ? 0 : 1 ;
//    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if(convertView == null){
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.fragment_list_mealfood, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        }else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }
        AddData currentAddData = getItem(position);
        mViewHolder.tvFood.setText(currentAddData.getNameFood());
        mViewHolder.tvAmount.setText(currentAddData.getAmount());
        mViewHolder.tvUnit.setText(currentAddData.getUnit());
        mViewHolder.tvKcal.setText(currentAddData.getKcal1());

        return convertView;
    }



    private class MyViewHolder {
        TextView tvFood, tvAmount,tvUnit,tvKcal;


        public MyViewHolder(View item) {
            tvFood = (TextView) item.findViewById(R.id.tvFood);
            tvAmount = (TextView) item.findViewById(R.id.tvAmount);
            tvUnit = (TextView) item.findViewById(R.id.tvUnit);
            tvKcal = (TextView) item.findViewById(R.id.tvKcal);

        }
    }




//        if (getItemViewType(position) == 0) {
//            MealListItem meal;
//            if (convertView != null)
//                meal = (MealListItem) convertView;
//            else
//                meal = new MealListItem(parent.getContext());
//            // รับค่าจาก base มาแสดง  ชื่อมื้อ
//            AddData currentAddData = getItem(position);
//            meal.setNameMeal(currentAddData.getMeal());
//
//            return meal;
//
//        } else {
//            MealFoodListItem meal;
//            if (convertView != null)
//                meal = (MealFoodListItem) convertView;
//            else
//                meal = new MealFoodListItem(parent.getContext());
//            // รับค่าจาก base มาแสดง ชื่ออาหาร จำนวน หน่วย จำนวนแคลอรี่
//           AddData currentAddData = getItem(position);
//            meal.setNameFood(currentAddData.getNameFood());
//            meal.setAmount(currentAddData.getAmount());
//            meal.setUnit(currentAddData.getUnit());
//            meal.setKcal2(currentAddData.getKcal2());
//            return meal;
//        }




//
//        AddData item =  getItem(position);
//        if (position % 2 == 0) {
//            convertView = inflateMeal(convertView, parent, item);
//            return convertView;
//        }else {
//            convertView = inflateMealFood(convertView, parent, item);
//            return convertView;
//        }
    }

//    private View inflateMealFood(View convertView, ViewGroup parent, AddData item) {
//        MyViewHolder2 mViewHolder;
//
//        if (convertView == null) {
//            LayoutInflater inflater = activity.getLayoutInflater();
//            convertView = inflater.inflate(R.layout.fragment_list_mealfood, parent, false);
//            mViewHolder = new MyViewHolder2(convertView);
//            convertView.setTag(mViewHolder);
//        } else {
//            mViewHolder = (MyViewHolder2) convertView.getTag();
//        }
//
//
//        mViewHolder.tvFood.setText(item.getTitle());
//        mViewHolder.tvAmount.setText(item.getAmount());
//        mViewHolder.tvUnit.setText(item.getUnit());
//        mViewHolder.tvKcal.setText(item.getKcal1());
//
//
//
//
//        return convertView;
//    }
//
//    private View inflateMeal(View convertView, ViewGroup parent, AddData item) {
//        MyViewHolder mViewHolder;
//
//        if (convertView == null) {
//            LayoutInflater inflater = activity.getLayoutInflater();
//            convertView = inflater.inflate(R.layout.fragment_list_meal, parent, false);
//            mViewHolder = new MyViewHolder(convertView);
//            convertView.setTag(mViewHolder);
//        } else {
//            mViewHolder = (MyViewHolder) convertView.getTag();
//        }
//
//
//        mViewHolder.tvMeal.setText(item.getTitle());
//        mViewHolder.tvTotalKcal.setText(item.getDescription());
//
//        return convertView;
//    }
//
//    private class MyViewHolder {
//        TextView tvMeal, tvTotalKcal;
//
//        public MyViewHolder(View item) {
//            tvMeal = (TextView) item.findViewById(R.id.tvMeal);
//            tvTotalKcal = (TextView) item.findViewById(R.id.tvTotalKcal);
//        }
//    }
//
//    private class MyViewHolder2 {
//        TextView tvFood, tvAmount,tvUnit,tvKcal;
//
//        public MyViewHolder2(View item) {
//            tvFood = (TextView) item.findViewById(R.id.tvFood);
//            tvAmount = (TextView) item.findViewById(R.id.tvAmount);
//            tvUnit = (TextView) item.findViewById(R.id.tvUnit);
//            tvKcal = (TextView) item.findViewById(R.id.tvKcal);
//
//        }
//    }



