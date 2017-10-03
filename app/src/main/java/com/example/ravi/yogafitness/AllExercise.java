package com.example.ravi.yogafitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.ravi.yogafitness.Adapter.AllExerciseAdapter;
import com.example.ravi.yogafitness.Model.Exercise;
import com.example.ravi.yogafitness.utils.AllList;

import java.util.List;

public class AllExercise extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mAllExerciseRecyclerview;
    private AllExerciseAdapter mAllExerciseAdapter;
    private LinearLayoutManager layoutManager;
    private List<Exercise> allList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_exercise);
        mToolbar = (Toolbar)findViewById(R.id.all_exercise_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Yoga Asanas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        allList = new AllList().getAllList();
        mAllExerciseRecyclerview = (RecyclerView)findViewById(R.id.all_exercise_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        mAllExerciseAdapter = new AllExerciseAdapter(this, allList);

        mAllExerciseRecyclerview.setLayoutManager(layoutManager);
        mAllExerciseRecyclerview.setAdapter(mAllExerciseAdapter);
    }
}
