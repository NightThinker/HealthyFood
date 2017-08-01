package com.example.mo.healthyfood.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.add.Country;
import com.example.mo.healthyfood.add.Food;

import java.util.ArrayList;

/**
 * Created by Mo on 10/29/2016.
 */
public class FoodRecommendAdapter extends BaseAdapter {


    private ArrayList<Food> foodList;
    Activity activity;

    public FoodRecommendAdapter(ArrayList<Food> foodList , Activity activity) {
        this.foodList = foodList;
        this.activity = activity;
//        super(context, textViewResourceId, countryList);
//        this.countryList = new ArrayList<Country>();
//        this.countryList.addAll(countryList);
    }

    private class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Food getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));
        // MyViewHolder mViewHolder;

        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.fragment_list_food, parent, false);
            holder = new ViewHolder();
            //           convertView.setTag(holder);

//            LayoutInflater vi = (LayoutInflater)getSystemService(
//                    Context.LAYOUT_INFLATER_SERVICE);
//            convertView = vi.inflate(R.layout.country_info, null);
//
//            holder = new ViewHolder();
            //           holder.code = (TextView) convertView.findViewById(R.id.code);
            holder.name = (TextView) convertView.findViewById(R.id.txt_food);
            convertView.setTag(holder);

//            holder.name.setOnClickListener( new View.OnClickListener() {
//                public void onClick(View v) {
//                    CheckBox cb = (CheckBox) v ;
//                    Country country = (Country) cb.getTag();
//                    Toast.makeText(parent.getContext(),
//                            "Clicked on Checkbox: " + cb.getText() +
//                                    " is " + cb.isChecked(),
//                            Toast.LENGTH_LONG).show();
//                    country.setSelected(cb.isChecked());
//                }
//            });
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Food food = foodList.get(position);
//        holder.code.setText(" (" +  country.getCode() + ")");
        holder.name.setText(food.getName());
//        holder.name.setChecked(country.isSelected());
//        holder.name.setTag(country);

        return convertView;

    }

    public ArrayList<Food> getData() {
        return foodList;
    }
}
