package com.example.ravi.yogafitness;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ravi.yogafitness.Model.Exercise;
import com.example.ravi.yogafitness.database.YogaDB;
import com.example.ravi.yogafitness.utils.AllList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Exchanger;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class DailyTraining extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView mExerciseName;
    private LinearLayout mGetreadyLayout;
    private MaterialProgressBar mProgressBar;
    private ImageView mExerciseImage;
    private Button mStartBtn;
    private TextView mGetready, mBreakCountdown;
    private TextView mCountdowntxt;
    int ex_id = 0;
    private YogaDB yogaDB;
    private List<Exercise> exerciseList;
    int mode;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private MediaPlayer mpStart, mpStop;
    MyCount counter;
    Long s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_training);
        mToolbar = (Toolbar)findViewById(R.id.daily_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Daily Yoga");

        mpStart = MediaPlayer.create(this, R.raw.startsound);
        mpStop = MediaPlayer.create(this, R.raw.stopsound);

        // Ads
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.dailytrainingadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());

        mExerciseImage = (ImageView)findViewById(R.id.daily_training_image);
        mExerciseName = (TextView)findViewById(R.id.daily_training_name);
        mProgressBar = (MaterialProgressBar)findViewById(R.id.daily_training_progressbar);
        mGetready = (TextView)findViewById(R.id.daily_training_get_ready);
        mBreakCountdown = (TextView)findViewById(R.id.daily_training_countdown);
        mCountdowntxt = (TextView)findViewById(R.id.daily_training_timer);
        mGetreadyLayout = (LinearLayout)findViewById(R.id.layout_get_ready);
        mStartBtn = (Button)findViewById(R.id.daily_training_startbtn);

        yogaDB = new YogaDB(this);
        mode = yogaDB.getSettingMode();
        
        initList(mode);
        mProgressBar.setMax(exerciseList.size());
        setExerciseInfo();

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mStartBtn.getText().toString().toLowerCase().equals("start")){
                    showGetReady();
                    mStartBtn.setText("pause");
                }
                else if(mStartBtn.getText().toString().toLowerCase().equals("pause")){
                    counter.cancel();
                    mStartBtn.setText("resume");
                }
                else if(mStartBtn.getText().toString().toLowerCase().equals("resume")){
                    counter = new MyCount(s1, 1000);
                    counter.start();
                    mStartBtn.setText("pause");
                }
                else if(mStartBtn.getText().toString().toLowerCase().equals("next")){
                    Intent calendarIntent = new Intent(DailyTraining.this, Calendars.class);
                    startActivity(calendarIntent);
                    finish();
                }
                else{
                    counter.cancel();
                    if(ex_id < exerciseList.size()){
                        setExerciseInfo();
                    }
                    else{
                        showFinished();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        final AlertDialog.Builder builder = new AlertDialog.Builder(DailyTraining.this);
        builder.setMessage("Are you sure yout want to quit this yoga training ?");
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

    private void showGetReady() {
        mGetreadyLayout.setVisibility(View.VISIBLE);
        mExerciseImage.setVisibility(View.INVISIBLE);
        mCountdowntxt.setVisibility(View.INVISIBLE);
        mExerciseName.setVisibility(View.INVISIBLE);
        mStartBtn.setVisibility(View.INVISIBLE);
        mGetready.setText("GET READY");

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mBreakCountdown.setText("" + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                showExercise();
                mpStart.start();
            }
        }.start();
    }

    private void showExercise() {
        if(ex_id < exerciseList.size()){
            mGetreadyLayout.setVisibility(View.INVISIBLE);
            mExerciseImage.setVisibility(View.VISIBLE);
            mCountdowntxt.setVisibility(View.VISIBLE);
            mExerciseName.setVisibility(View.VISIBLE);
            mStartBtn.setVisibility(View.VISIBLE);
            counter = new MyCount(10000, 1000);
            counter.start();
            mExerciseName.setText(exerciseList.get(ex_id).getName());
            mExerciseImage.setImageResource(exerciseList.get(ex_id).getImageId());
        }
        else{
            showFinished();
        }
    }
    //countdown
    public class MyCount extends CountDownTimer
    {
        public MyCount(long millisInFuture, long countDownInterval)
        {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish()
        {
            if(ex_id < exerciseList.size()-1){
                ex_id ++;
                mProgressBar.setProgress(ex_id);
                mCountdowntxt.setText("");
                setExerciseInfo();
                showGetReady();
                mpStop.start();
            }
            else{
                showFinished();
            }
        }
        @Override
        public void onTick(long millisUntilFinished)
        {
            s1=millisUntilFinished;
            mCountdowntxt.setText("" + millisUntilFinished/1000);
        }
    }


    private void showFinished() {
        mExerciseImage.setVisibility(View.INVISIBLE);
        mStartBtn.setVisibility(View.VISIBLE);
        mCountdowntxt.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        mExerciseName.setVisibility(View.INVISIBLE);
        mGetreadyLayout.setVisibility(View.VISIBLE);
        mGetready.setText("FINISHED !!!");
        mBreakCountdown.setText("Congratulations ! You completed today's task");
        mBreakCountdown.setTextSize(20);
        mStartBtn.setText("next");
        // Save today's exercise in databases;
        yogaDB.saveDay("" + Calendar.getInstance().getTimeInMillis());

        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }

    private void setExerciseInfo() {
        mExerciseImage.setImageResource(exerciseList.get(ex_id).getImageId());
        mExerciseName.setText(exerciseList.get(ex_id).getName());
        mGetreadyLayout.setVisibility(View.INVISIBLE);
        mExerciseImage.setVisibility(View.VISIBLE);
        mCountdowntxt.setVisibility(View.VISIBLE);
        mExerciseName.setVisibility(View.VISIBLE);
    }

    private void initList(int mode) {
        if(mode == 0){
            exerciseList = new AllList().getBeginnerList();
        }
        else if(mode == 1){
            exerciseList = new AllList().getIntermediateList();
        }
        else if(mode == 2){
            exerciseList = new AllList().getAdvanceList();
        }
    }
}