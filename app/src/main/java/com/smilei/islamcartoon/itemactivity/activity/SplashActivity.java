package com.smilei.islamcartoon.itemactivity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.smilei.islamcartoon.MainActivity;
import com.smilei.islamcartoon.R;

public class SplashActivity extends AppCompatActivity {
    private TextView appName;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appName = findViewById(R.id.appName);
        imageView = findViewById(R.id.imageView);

        Animation animimag = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.myanim);
        imageView.setAnimation(animimag);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.myanim);
        appName.setAnimation(anim);

        new Thread() {
            public void run() {

                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();

            }
        }.start();


    }
}