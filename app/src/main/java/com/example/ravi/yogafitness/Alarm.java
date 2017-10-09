package com.example.ravi.yogafitness;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ravi.yogafitness.database.YogaDB;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.sql.Time;
import java.util.Date;

public class Alarm extends AppCompatActivity {
    private Toolbar mToolbar;
    private Switch mAlarmSwitch;
    private TimePicker mTimePicker;
    private Button mSaveBtn;
    private TextView mAlarmText;
    private YogaDB yogaDB;
    private AdView mAdView;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        mToolbar = (Toolbar)findViewById(R.id.alarm_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Set Alarm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Ads
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.alarmadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mTimePicker = (TimePicker)findViewById(R.id.alarm_time_picker);

        mAlarmSwitch = (Switch)findViewById(R.id.alarm_switch);

        mSaveBtn = (Button)findViewById(R.id.alarm_savebtn);

        mAlarmText = (TextView)findViewById(R.id.alarm_text);

        yogaDB = new YogaDB(this);
        int alarm = yogaDB.getAlarm();
        if(alarm == 0){
            mAlarmSwitch.setChecked(false);
            mSaveBtn.setVisibility(View.INVISIBLE);
            mAlarmText.setVisibility(View.INVISIBLE);
            mTimePicker.setVisibility(View.INVISIBLE);
        }
        else{
            mAlarmSwitch.setChecked(true);
            mSaveBtn.setVisibility(View.VISIBLE);
            mAlarmText.setVisibility(View.VISIBLE);
            mTimePicker.setVisibility(View.VISIBLE);
            if(yogaDB.getAlarmHour() != 1){
                setAlarmText(yogaDB.getAlarmHour(), yogaDB.getAlarmMinute());
            }
            else{
                mAlarmText.setText("");
            }
        }

        mAlarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTimePicker.setVisibility(View.VISIBLE);
                    mSaveBtn.setVisibility(View.VISIBLE);
                    mAlarmText.setVisibility(View.VISIBLE);
                }
                else{
                    mTimePicker.setVisibility(View.INVISIBLE);
                    yogaDB.saveAlarm(0);
                    yogaDB.saveAlarmHour(-1);
                    yogaDB.saveAlarmMinute(-1);
                    saveAlarm(false);
                    mAlarmText.setText("");
                    mSaveBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAlarm(true);
                setAlarmText(getHour(), getMinute());
                yogaDB.saveAlarm(1);
                yogaDB.saveAlarmHour(mTimePicker.getCurrentHour());
                yogaDB.saveAlarmMinute(mTimePicker.getCurrentMinute());
                Toast.makeText(Alarm.this, "Alarmed Saved for " + mTimePicker.getCurrentHour() + "" +
                        mTimePicker.getCurrentMinute(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAlarmText(int alarmHour, int alarmMinute) {
        String hourString, minuteString;
        String amormp = "AM";
        if(alarmHour > 12){
            amormp = "PM";
            alarmHour = alarmHour-12;
            hourString = String.valueOf(alarmHour);
        }
        else{
            if(alarmHour == 0){
                hourString = "00";
            }
            else {
                hourString = String.valueOf(alarmHour);
            }
            }

        if(alarmMinute < 10){
            minuteString = "0" + String.valueOf(alarmMinute);
        }
        else{
            minuteString = String.valueOf(alarmMinute);
        }

        mAlarmText.setText("We'll remind you everyday at " + hourString + ":" + minuteString + amormp);
    }

    private int getHour(){
        if(Build.VERSION.SDK_INT < 23){
            return mTimePicker.getCurrentHour();
        }
        else{
            return mTimePicker.getHour();
        }
    }

    private int getMinute(){
        if(Build.VERSION.SDK_INT < 23){
            return mTimePicker.getCurrentMinute();
        }
        else{
            return mTimePicker.getMinute();
        }
    }

    private void saveAlarm(boolean checked) {
        if(checked){
            alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(Alarm.this, AlarmNotificationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(Alarm.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            // set time to alarm

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, getHour());
            calendar.set(Calendar.MINUTE, getMinute());
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        else {
            Intent intent = new Intent(Alarm.this, AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }
    }
}