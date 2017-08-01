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

import com.example.mo.healthyfood.R;
import com.example.mo.healthyfood.add.AddData;
import com.example.mo.healthyfood.view.AddListItem;

import java.util.ArrayList;

public class AddAdapter extends BaseAdapter {

    ArrayList<AddData> myList = new ArrayList<>();
    Activity activity;

    public AddAdapter(ArrayList<AddData> myList, Activity activity) {
        this.myList = myList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public AddData getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.fragment_list_add, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        AddData currentAddData = getItem(position);

        mViewHolder.tvTitle.setText(currentAddData.getTitle());
        mViewHolder.tvDesc.setText(currentAddData.getDescription());
        mViewHolder.ivIcon.setImageResource(currentAddData.getImgResId());

        return convertView;

        //    AddListItem add;
//    if (convertView != null)
//    add = (AddListItem) convertView;
//    else
//    add = new AddListItem(parent.getContext());
//    return add;
    }

    private class MyViewHolder {
        TextView tvTitle, tvDesc;
        ImageView ivIcon;

        public MyViewHolder(View item) {
            tvTitle = (TextView) item.findViewById(R.id.tvTitle);
            tvDesc = (TextView) item.findViewById(R.id.tvDesc);
            ivIcon = (ImageView) item.findViewById(R.id.ivIcon);
        }
    }



}