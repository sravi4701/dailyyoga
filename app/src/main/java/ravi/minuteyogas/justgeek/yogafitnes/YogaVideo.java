package ravi.minuteyogas.justgeek.yogafitnes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import ravi.minuteyogas.justgeek.yogafitnes.Adapter.VideoAdapter;

import ravi.minuteyogas.justgeek.yogafitnes.R;

public class YogaVideo extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mVideoRecyclerview;
    private LinearLayoutManager layoutManager;
    private VideoAdapter videoAdapter;
//    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_video);

        mToolbar = (Toolbar)findViewById(R.id.yogavideo_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Yoga Videos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        // Ads
//        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
//        mAdView = (AdView) findViewById(R.id.videoadView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        if(isNetworkAvailable()){
            mVideoRecyclerview = (RecyclerView)findViewById(R.id.video_recycler);
            mVideoRecyclerview.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            videoAdapter = new VideoAdapter(this);
            mVideoRecyclerview.setLayoutManager(layoutManager);
            mVideoRecyclerview.setAdapter(videoAdapter);
        }
        else{
            Toast.makeText(this, "No Internet Connection Please try Again", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
