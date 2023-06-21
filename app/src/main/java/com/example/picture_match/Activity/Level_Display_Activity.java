package com.example.picture_match.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.picture_match.Adapter.Level_Display_Adapter;
import com.example.picture_match.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Level_Display_Activity extends AppCompatActivity {

    GridView level_gridview;
    ImageView back;
    TextView timeshow,leveltype1;
    ProgressBar progressBar;
    int column;
    private ArrayList<String> imgArr=new ArrayList<>();
    private List<String> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_display);

        level_gridview = findViewById(R.id.level_gridview);
        back = findViewById(R.id.back);
        timeshow = findViewById(R.id.timeshow);
        progressBar = findViewById(R.id.progressBar);
        leveltype1 = findViewById(R.id.leveltype1);
        String level = getIntent().getStringExtra("level");
        Log.d("TTT", "onCreate: level"+level);
        leveltype1.setText(level);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Level_Display_Activity.this, Level_ListView_Activity.class);
                startActivity(intent);
            }
        });
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(Level_Display_Activity.this);
        builder.setTitle("YOU HAVE 5 SECONDS TO MEMORIZE ALL IMAGES");
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int levelno = getIntent().getIntExtra("levelno",1);
                String[] images = new String[0];
                try {
                    images = getAssets().list("Images");
                    imgArr = new ArrayList<String>(Arrays.asList(images));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (levelno >=1 && levelno <=3)
                {
                    arrayList = imgArr.subList(0,6);
                    column=3;
                }
                if (levelno >=4 && levelno<=6)
                {
                    arrayList = imgArr.subList(0,8);
                    column=4;
                }
                if (levelno >=7)
                {
                    arrayList = imgArr.subList(0,10);
                    column=5;
                }
                arrayList.addAll(arrayList);
                Collections.shuffle(arrayList);

                Level_Display_Adapter adapter = new Level_Display_Adapter(Level_Display_Activity.this,arrayList,timeshow,progressBar,level);
                level_gridview.setNumColumns(column);
                level_gridview.setAdapter(adapter);
                dialog.dismiss();
            }
        });
        builder.show();
    }
}