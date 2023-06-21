package com.example.picture_match.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.picture_match.Adapter.Level_List_Adapter;
import com.example.picture_match.R;

public class Level_ListView_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView leveltype;
    ImageView back1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_list_view);
        recyclerView = findViewById(R.id.recyclerview);
        leveltype = findViewById(R.id.leveltype);
        back1 = findViewById(R.id.back1);
        String level = getIntent().getStringExtra("level");

        leveltype.setText(level);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level_ListView_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Level_List_Adapter adapter = new Level_List_Adapter(Level_ListView_Activity.this,level);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}