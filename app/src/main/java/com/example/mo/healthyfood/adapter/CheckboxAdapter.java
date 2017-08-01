package com.example.mo.healthyfood.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.add.Country;

import java.util.ArrayList;

/**
 * Created by Mo on 10/29/2016.
 */
public class CheckboxAdapter extends BaseAdapter {


    private ArrayList<Country> countryList;
    Activity activity;

    public CheckboxAdapter(ArrayList<Country> countryList , Activity activity) {
        this.countryList = countryList;
        this.activity = activity;
//        super(context, textViewResourceId, countryList);
//        this.countryList = new ArrayList<Country>();
//        this.countryList.addAll(countryList);
    }

    private class ViewHolder {
        TextView code;
        CheckBox name;
    }

    @Override
    public int getCount() {
        return countryList.size();
    }

    @Override
    public Country getItem(int position) {
        return countryList.get(position);
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
            convertView = inflater.inflate(R.layout.fragment_list_allergy, parent, false);
            holder = new ViewHolder();
            //           convertView.setTag(holder);

//            LayoutInflater vi = (LayoutInflater)getSystemService(
//                    Context.LAYOUT_INFLATER_SERVICE);
//            convertView = vi.inflate(R.layout.country_info, null);
//
//            holder = new ViewHolder();
            //           holder.code = (TextView) convertView.findViewById(R.id.code);
            holder.name = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);

            holder.name.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    Country country = (Country) cb.getTag();
                    Toast.makeText(parent.getContext(),
                            "Clicked on Checkbox: " + cb.getText() +
                                    " is " + cb.isChecked(),
                            Toast.LENGTH_LONG).show();
                    country.setSelected(cb.isChecked());
                }
            });
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Country country = countryList.get(position);
//        holder.code.setText(" (" +  country.getCode() + ")");
        holder.name.setText(country.getName());
        holder.name.setChecked(country.isSelected());
        holder.name.setTag(country);

        return convertView;

    }

    public ArrayList<Country> getData() {
        return countryList;
    }
}
