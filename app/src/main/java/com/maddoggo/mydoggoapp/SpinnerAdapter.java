package com.maddoggo.mydoggoapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    private List<String> listData;
    private Activity activity;
    private LayoutInflater inflater;

    public SpinnerAdapter(List<String> listData, Activity activity) {
        this.listData = listData;
        this.activity = activity;
        this.inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null)
            view = inflater.inflate(R.layout.spinner_item,null);
        TextView tv = (TextView)view.findViewById(R.id.textView);
        tv.setText(listData.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position,convertView,parent);
        LinearLayout ll = (LinearLayout)view;
        TextView tv = (TextView)ll.findViewById(R.id.textView);
        tv.setGravity(Gravity.LEFT);
        tv.setTextColor(Color.parseColor("#336369"));
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        return view;

    }
}
