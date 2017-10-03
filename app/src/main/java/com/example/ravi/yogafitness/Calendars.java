package com.example.ravi.yogafitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.ravi.yogafitness.Custom.WorkoutDoneDecorator;
import com.example.ravi.yogafitness.database.YogaDB;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Calendars extends AppCompatActivity {

    private Toolbar mToolbar;
    private HashSet<CalendarDay> list = new HashSet<>();
    private MaterialCalendarView materialCalendarView;
    YogaDB yogaDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mToolbar = (Toolbar)findViewById(R.id.calendar_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Calendars");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        yogaDB = new YogaDB(this);
        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendar);

        List<String> workoutDay = yogaDB.getWorkoutDays();
        HashSet<CalendarDay> convertedList = new HashSet<>();
        for (String value : workoutDay){
            convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
        }
        materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));
    }
}
