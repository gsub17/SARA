package com.example.shubhajitghosh.sara;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

//import com.example.shubhajitghosh.sara.talent.talent_box;

public class coinsplash extends AppCompatActivity {

    ImageView coin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coinsplash);

        coin = (ImageView)findViewById(R.id.coin);

        ObjectAnimator flip = ObjectAnimator.ofFloat(coin,"rotationY",360f,0f);
        flip.setDuration(3000);
        flip.setRepeatCount(Animation.INFINITE);
        flip.start();


       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {

          Intent intent = new Intent(coinsplash.this,talent_box.class);
          startActivity(intent);
          finish();

           }
       },3000);


    }
}
