package com.example.ravi.yogafitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.ravi.yogafitness.Adapter.TrainingListAdapter;
import com.example.ravi.yogafitness.Model.Exercise;
import com.example.ravi.yogafitness.database.YogaDB;
import com.example.ravi.yogafitness.utils.AllList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class Training extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView trainingRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private TrainingListAdapter trainingListAdapter;
    private List<Exercise> trainingList;
    private Button mGobtn;
    private YogaDB yogaDB;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        mToolbar = (Toolbar)findViewById(R.id.traing_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Daily Training");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Ads
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.trainingadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        yogaDB = new YogaDB(this);
        int mode = yogaDB.getSettingMode();
        initList(mode);
        trainingRecyclerView = (RecyclerView)findViewById(R.id.training_recyclerview);
        trainingListAdapter = new TrainingListAdapter(this, trainingList);
        mLayoutManager = new LinearLayoutManager(this);
        trainingRecyclerView.setLayoutManager(mLayoutManager);
        trainingRecyclerView.setAdapter(trainingListAdapter);

        mGobtn = (Button)findViewById(R.id.training_gobtn);
        mGobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dailyIntent = new Intent(Training.this, DailyTraining.class);
                startActivity(dailyIntent);
                finish();
            }
        });
    }

    private void initList(int mode) {
        if(mode == 0){
            trainingList = new AllList().getBeginnerList();
        }
        else if(mode == 1){
            trainingList = new AllList().getIntermediateList();
        }
        else if(mode == 2){
            trainingList = new AllList().getAdvanceList();
        }
    }
}
