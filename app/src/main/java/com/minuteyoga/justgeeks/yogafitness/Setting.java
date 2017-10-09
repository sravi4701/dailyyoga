package com.minuteyoga.justgeeks.yogafitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.minuteyoga.justgeeks.yogafitness.database.YogaDB;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class Setting extends AppCompatActivity {

    private Toolbar mToolbar;
    private RadioGroup mRadiogroup;
    private RadioButton mRadioBeginner, mRadioIntermediate, mRadioAdvance;
    private Button mSavebtn, mFeedback;
    private YogaDB yogaDB;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mToolbar = (Toolbar)findViewById(R.id.setting_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Setting");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Ads
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.settingadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        yogaDB = new YogaDB(this);

        mRadiogroup = (RadioGroup)findViewById(R.id.setting_radio_grp);
        mRadioBeginner = (RadioButton)findViewById(R.id.radio_beginner);
        mRadioIntermediate = (RadioButton)findViewById(R.id.radio_intermedite);
        mRadioAdvance = (RadioButton)findViewById(R.id.radio_advance);
        mSavebtn = (Button)findViewById(R.id.setting_save);
        mFeedback = (Button)findViewById(R.id.setting_feedbackbtn);
        mFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedBack();
            }
        });
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

    private void sendFeedBack() {
        final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ getString(R.string.mail_feedback_email) });
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_subject));
        intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.mail_feedback_message));
        startActivity(Intent.createChooser(intent, getString(R.string.title_send_feedback)));
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
