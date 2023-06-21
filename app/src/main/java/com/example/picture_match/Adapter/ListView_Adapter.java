package com.example.picture_match.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.picture_match.Activity.Level_ListView_Activity;
import com.example.picture_match.R;

import java.util.ArrayList;
import java.util.List;

public class ListView_Adapter extends BaseAdapter {

    Level_ListView_Activity level_listView_activity;
    String level;
    String levelno[]={"LEVEL 1","LEVEL 2","LEVEL 3","LEVEL 4","LEVEL 5","LEVEL 6","LEVEL 7","LEVEL 8","LEVEL 9","LEVEL 10"};
    List arrayList = new ArrayList<>();

    public ListView_Adapter(Level_ListView_Activity level_listView_activity, String level) {
        this.level_listView_activity = level_listView_activity;
        this.level=level;
    }

    @Override
    public int getCount() {
        return levelno.length;
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
    public View getView(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(level_listView_activity).inflate(R.layout.listview_item,parent,false);

        TextView textView = view.findViewById(R.id.list_view_item);
        textView.setText(""+levelno[position]);
        return view;
    }
}
