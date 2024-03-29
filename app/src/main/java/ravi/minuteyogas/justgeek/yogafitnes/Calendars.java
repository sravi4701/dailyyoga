package ravi.minuteyogas.justgeek.yogafitnes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import ravi.minuteyogas.justgeek.yogafitnes.Custom.CurrentDayDecorator;
import ravi.minuteyogas.justgeek.yogafitnes.Custom.WorkoutDoneDecorator;

import ravi.minuteyogas.justgeek.yogafitnes.R;

import ravi.minuteyogas.justgeek.yogafitnes.database.YogaDB;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
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
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mToolbar = (Toolbar)findViewById(R.id.calendar_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Calendar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Ads
        MobileAds.initialize(this, "ca-app-pub-8199311615587112~4795976126");
        mAdView = (AdView) findViewById(R.id.calendaradView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        yogaDB = new YogaDB(this);
        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendar);

        List<String> workoutDay = yogaDB.getWorkoutDays();
        HashSet<CalendarDay> convertedList = new HashSet<>();
        for (String value : workoutDay){
            convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
        }
        materialCalendarView.addDecorator(new CurrentDayDecorator());
        materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));
    }
}