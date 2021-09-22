package com.example.shubhajitghosh.sara;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;

public class loginsplash extends AppCompatActivity {

    de.hdodenhof.circleimageview.CircleImageView girly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsplash);

        girly = (de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.girl);

        ObjectAnimator flip = ObjectAnimator.ofFloat(girly,"rotationY",360f,0f);
        flip.setDuration(3000);
        flip.setRepeatCount(Animation.INFINITE);
        flip.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {



                    Intent intent = new Intent(loginsplash.this, login_activity.class);
                    startActivity(intent);

                    finish();


            }
        },3000);

    }




}
