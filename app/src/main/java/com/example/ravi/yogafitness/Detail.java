package com.example.ravi.yogafitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravi.yogafitness.Model.Exercise;
import com.example.ravi.yogafitness.utils.AllList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.List;

public class Detail extends AppCompatActivity {
    private Toolbar mToolbar;
    private ImageView mDetailImage;
    private TextView mDetailName, mDetailDescription;
    private List<Exercise> allList;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i = getIntent();
        int ex_id = i.getIntExtra("ex_id", 0);

        allList = new AllList().getAllList();

        mToolbar = (Toolbar)findViewById(R.id.detail_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(allList.get(ex_id).getName());

        // Ads
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.detailadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");


        mDetailImage = (ImageView)findViewById(R.id.detail_image);
        mDetailName = (TextView) findViewById(R.id.detail_name);
        mDetailDescription = (TextView)findViewById(R.id.detail_description);

        Exercise single = allList.get(ex_id);
        mDetailImage.setImageResource(single.getImageId());
        mDetailName.setText(single.getName());
        mDetailDescription.setText(single.getDescription());

        Toast.makeText(this, "" + ex_id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
            finish();
        }
        else{
            finish();
        }
    }
}
