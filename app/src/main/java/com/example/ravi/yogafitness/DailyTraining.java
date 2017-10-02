package com.example.ravi.yogafitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class DailyTraining extends AppCompatActivity {
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_training);
        mToolbar = (Toolbar)findViewById(R.id.daily_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Daily Yoga");
    }
}
