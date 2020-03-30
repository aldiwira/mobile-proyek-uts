package com.aldi.project_uts.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.aldi.project_uts.R;

public class Splash_activity extends AppCompatActivity {
    private Intent intentHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handleSplash(1000);
    }
    //class untuk splash screen agar dengan waktu yang dapat ditentukan lewat constructor
    private void handleSplash(int time){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intentHandler = new Intent(Splash_activity.this, MainActivity.class);
                startActivity(intentHandler);
                finish();
            }
        }, time);
    }
}
