package com.example.ravi.yogafitness;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.ravi.yogafitness.Adapter.VideoAdapter;
import com.example.ravi.yogafitness.Model.Video;

import java.util.ArrayList;
import java.util.List;

public class YogaVideo extends AppCompatActivity {
    private Toolbar mToolbar;
    private RecyclerView mVideoRecyclerview;
    private LinearLayoutManager layoutManager;
    private VideoAdapter videoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga_video);

        mToolbar = (Toolbar)findViewById(R.id.yogavideo_action_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Yoga Videos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
