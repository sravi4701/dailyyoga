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
import com.example.ravi.yogafitness.utils.AllList;

import java.util.ArrayList;
import java.util.List;

public class Training extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView trainingRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private TrainingListAdapter trainingListAdapter;
    private List<Exercise> trainingList;
    private Button mGobtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        mToolbar = (Toolbar)findViewById(R.id.traing_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Daily Training");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        trainingList = new AllList().getBeginnerList();

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
}
