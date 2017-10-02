package com.example.ravi.yogafitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ravi.yogafitness.Adapter.MainRecyclerAdapter;
import com.example.ravi.yogafitness.Model.MainPageModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private List<MainPageModel> mainList = new ArrayList<>();
    private RecyclerView mainRecyclerView;
    private LinearLayoutManager mainLayoutManager;
    private MainRecyclerAdapter mainRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.main_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Yoga Fitness");

        initData();
        mainRecyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        mainLayoutManager = new LinearLayoutManager(this);
        mainRecyclerAdapter = new MainRecyclerAdapter(mainList, getBaseContext());
        mainRecyclerView.setLayoutManager(mainLayoutManager);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);
    }

    private void initData() {
        mainList.add(new MainPageModel(R.drawable.dailyyogaimage, "Start Daily Training"));
        mainList.add(new MainPageModel(R.drawable.allyoga, "All Yoga Poses"));
        mainList.add(new MainPageModel(R.drawable.yogavideos, "Yoga Videos"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.main_calendar){

        }
        else if (item.getItemId() == R.id.main_video_btn){
            Toast.makeText(this, "Video Clicked", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.main_setting_btn){
            Intent settingIntent = new Intent(MainActivity.this, Setting.class);
            startActivity(settingIntent);
        }
        else if(item.getItemId() == R.id.main_alarm_btn){
            Toast.makeText(this, "Alarm Clicked", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.main_camp_btn){
            Toast.makeText(this, "Yoga Camp Clicked", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.main_share_btn){
            Toast.makeText(this, "Share Clicked", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
