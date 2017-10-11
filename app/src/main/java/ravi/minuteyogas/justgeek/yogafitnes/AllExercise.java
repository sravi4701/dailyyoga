package ravi.minuteyogas.justgeek.yogafitnes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import ravi.minuteyogas.justgeek.yogafitnes.Adapter.AllExerciseAdapter;
import ravi.minuteyogas.justgeek.yogafitnes.Model.Exercise;

import ravi.minuteyogas.justgeek.yogafitnes.R;

import ravi.minuteyogas.justgeek.yogafitnes.utils.AllList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

public class AllExercise extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mAllExerciseRecyclerview;
    private AllExerciseAdapter mAllExerciseAdapter;
    private LinearLayoutManager layoutManager;
    private List<Exercise> allList;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_exercise);
        mToolbar = (Toolbar)findViewById(R.id.all_exercise_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Yoga Asanas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Ads
        MobileAds.initialize(this, "ca-app-pub-8199311615587112~4795976126");
        mAdView = (AdView) findViewById(R.id.allexerciseadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        allList = new AllList().getAllList();
        mAllExerciseRecyclerview = (RecyclerView)findViewById(R.id.all_exercise_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        mAllExerciseAdapter = new AllExerciseAdapter(this, allList);

        mAllExerciseRecyclerview.setLayoutManager(layoutManager);
        mAllExerciseRecyclerview.setAdapter(mAllExerciseAdapter);
    }
}
