package com.example.shubhajitghosh.sara;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

public class maps extends AppCompatActivity {

    ImageView imageView ;
    String ad;
    Intent i ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        imageView = (ImageView)findViewById(R.id.maps);

        ObjectAnimator flip = ObjectAnimator.ofFloat(imageView,"rotationY",360f,0f);
        flip.setDuration(3000);
        flip.setRepeatCount(Animation.INFINITE);
        flip.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = getIntent();
                ad= i.getStringExtra("EndLocation");
                Log.e("123456maps", "run: "+ ad );
                Intent intent = new Intent(maps.this,maps2_activity.class);
                intent.putExtra("EndLocation", ad);
                startActivity(intent);
                finish();

            }
        },3000);

    }

}
