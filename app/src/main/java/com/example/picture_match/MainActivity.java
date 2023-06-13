package com.example.picture_match;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView no_time_limit,normal,hard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        no_time_limit = findViewById(R.id.no_time_limit);
        normal = findViewById(R.id.normal);
        hard = findViewById(R.id.hard);
    }
}