package com.example.ravi.yogafitness;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ravi.yogafitness.database.YogaDB;

public class Setting extends AppCompatActivity {

    private Toolbar mToolbar;
    private RadioGroup mRadiogroup;
    private RadioButton mRadioBeginner, mRadioIntermediate, mRadioAdvance;
    private Button mSavebtn;
    private YogaDB yogaDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mToolbar = (Toolbar)findViewById(R.id.setting_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Setting");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        yogaDB = new YogaDB(this);

        mRadiogroup = (RadioGroup)findViewById(R.id.setting_radio_grp);
        mRadioBeginner = (RadioButton)findViewById(R.id.radio_beginner);
        mRadioIntermediate = (RadioButton)findViewById(R.id.radio_intermedite);
        mRadioAdvance = (RadioButton)findViewById(R.id.radio_advance);
        mSavebtn = (Button)findViewById(R.id.setting_save);
        int mode = yogaDB.getSettingMode();
        setRadio(mode);
        mSavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLeveltoDB();
                Toast.makeText(Setting.this, "saved!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setRadio(int mode) {
        if(mode == 0){
            mRadiogroup.check(R.id.radio_beginner);
        }
        else if(mode == 1){
            mRadiogroup.check(R.id.radio_intermedite);
        }
        else if(mode == 2){
            mRadiogroup.check(R.id.radio_advance);
        }
    }

    private void saveLeveltoDB() {

        int selected_id = mRadiogroup.getCheckedRadioButtonId();

        if(selected_id == R.id.radio_beginner){
            yogaDB.saveSettingMode(0);
        }
        else if(selected_id == R.id.radio_intermedite){
            yogaDB.saveSettingMode(1);
        }
        else if(selected_id == R.id.radio_advance){
            yogaDB.saveSettingMode(2);
        }
    }
}
