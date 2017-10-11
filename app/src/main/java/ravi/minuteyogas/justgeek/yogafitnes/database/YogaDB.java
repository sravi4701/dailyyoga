package ravi.minuteyogas.justgeek.yogafitnes.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi on 27-09-2017.
 */

public class YogaDB extends SQLiteAssetHelper {
    private static final String DB_NAME = "yoga.db";
    private static final int DB_VER = 1;

    public YogaDB(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }
    public int getSettingMode() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect = {"mode"};
        String sqlTable = "setting";
        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("mode"));
    }
    public int getAlarm() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect = {"ison"};
        String sqlTable = "alarm";
        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("ison"));
    }
    public int getAlarmHour() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect = {"hour"};
        String sqlTable = "alarm";
        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("hour"));
    }
    public int getAlarmMinute() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect = {"minute"};
        String sqlTable = "alarm";
        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("minute"));
    }

    public void saveSettingMode(int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE setting SET mode = " + value;
        db.execSQL(query);
    }

    public void saveAlarm(int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE alarm SET ison = " + value;
        db.execSQL(query);
    }
    public void saveAlarmHour(int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE alarm SET hour = " + value;
        db.execSQL(query);
    }
    public void saveAlarmMinute(int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE alarm SET minute = " + value;
        db.execSQL(query);
    }

    public List<String> getWorkoutDays() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String [] sqlSelect = {"day"};
        String sqlTable = "workoutdays";
        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        List<String> result = new ArrayList<>();

        if(c.moveToFirst()){
            do {
                result.add(c.getString(c.getColumnIndex("day")));
            }
            while (c.moveToNext());
        }
        return result;
    }
    public void saveDay(String day){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("Insert into workoutdays(day) values('%s');", day);
        db.execSQL(query);
    }
}
