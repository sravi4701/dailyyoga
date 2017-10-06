package com.example.ravi.yogafitness;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private List<MainPageModel> mainList = new ArrayList<>();
    private RecyclerView mainRecyclerView;
    private LinearLayoutManager mainLayoutManager;
    private MainRecyclerAdapter mainRecyclerAdapter;

    //ads
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar)findViewById(R.id.main_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Yoga Fitness");

        // Ads
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.mainadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

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
    public void onBackPressed() {
//        super.onBackPressed();
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure want to exit this app ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
            Intent calendarIntent = new Intent(MainActivity.this, Calendars.class);
            startActivity(calendarIntent);
        }
        else if (item.getItemId() == R.id.main_video_btn){
            Intent videoIntent = new Intent(MainActivity.this, YogaVideo.class);
            startActivity(videoIntent);
        }
        else if (item.getItemId() == R.id.main_setting_btn){
            Intent settingIntent = new Intent(MainActivity.this, Setting.class);
            startActivity(settingIntent);
        }
        else if(item.getItemId() == R.id.main_alarm_btn){
            Intent alarmIntent = new Intent(MainActivity.this, Alarm.class);
            startActivity(alarmIntent);
        }
        return true;
    }
}