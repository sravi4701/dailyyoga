package com.minuteyoga.justgeeks.yogafitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Thread splashThread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3000);
                    Intent mainInten = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(mainInten);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        splashThread.start();
    }
}
