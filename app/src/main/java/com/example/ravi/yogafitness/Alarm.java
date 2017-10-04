package com.example.ravi.yogafitness;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
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
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;

public class Alarm extends AppCompatActivity {
    private Toolbar mToolbar;
    private Switch mAlarmSwitch;
    private TimePicker mTimePicker;
    private Button mSaveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        mToolbar = (Toolbar)findViewById(R.id.alarm_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Set Alarm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTimePicker = (TimePicker)findViewById(R.id.alarm_time_picker);

        mAlarmSwitch = (Switch)findViewById(R.id.alarm_switch);

        mSaveBtn = (Button)findViewById(R.id.alarm_savebtn);

        mAlarmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mTimePicker.setVisibility(View.VISIBLE);
                    mSaveBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            saveAlarm(true);
                            Toast.makeText(Alarm.this, "Alarmed Saved for " + mTimePicker.getCurrentHour() + "" +
                                    mTimePicker.getCurrentMinute(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    mTimePicker.setVisibility(View.INVISIBLE);
                    saveAlarm(false);
                }
            }
        });
    }

    private void saveAlarm(boolean checked) {
        if(checked){
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;
            intent = new Intent(Alarm.this, AlarmNotificationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            // set time to alarm
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            Date toDay = java.util.Calendar.getInstance().getTime();
            calendar.set(toDay.getYear(), toDay.getMonth(), toDay.getDay(), mTimePicker.getCurrentHour(), mTimePicker.getCurrentMinute());

            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            Log.d("Debug", "Alarm will wake up at" + mTimePicker.getCurrentHour() + ": " + mTimePicker.getCurrentMinute());
        }
        else {
            Intent intent = new Intent(Alarm.this, AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);
        }
    }
}