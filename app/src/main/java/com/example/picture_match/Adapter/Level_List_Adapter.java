package com.example.picture_match.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.picture_match.Activity.Level_Display_Activity;
import com.example.picture_match.Activity.Level_ListView_Activity;
import com.example.picture_match.R;

public class Level_List_Adapter extends RecyclerView.Adapter<Level_List_Adapter.levellistholder> {

    Level_ListView_Activity level_listView_activity;
    String level;
    public Level_List_Adapter(Level_ListView_Activity level_listView_activity, String level) {
        this.level_listView_activity = level_listView_activity;
        this.level=level;
    }
    @NonNull
    @Override
    public levellistholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(level_listView_activity).inflate(R.layout.level_listview_item,parent,false);
        levellistholder holder = new levellistholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull levellistholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class levellistholder extends RecyclerView.ViewHolder {
        ListView listView;
        public levellistholder(@NonNull View itemView) {
            super(itemView);

            listView = itemView.findViewById(R.id.listview);
            ListView_Adapter adapter = new ListView_Adapter(level_listView_activity,level);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(level_listView_activity,Level_Display_Activity.class);
                    intent.putExtra("levelno",position+1);
                    intent.putExtra("level",level);
                    level_listView_activity.startActivity(intent);
                }
            });
        }
    }
}
