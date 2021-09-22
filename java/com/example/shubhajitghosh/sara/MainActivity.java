package com.example.shubhajitghosh.sara;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ViewPager mSlideViewPAger;
    LinearLayout dotslayout;
    SliderAdapter sliderAdapter;

    TextView[] mDots;
    TextView mDots2;

    Button nextbutton,prevbutton;

    int mCurrentPage;//to check the current page we are in



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSlideViewPAger = (ViewPager)findViewById(R.id.viewPagerLayout);
        dotslayout = (LinearLayout)findViewById(R.id.dotslayout);
        nextbutton = (Button)findViewById(R.id.next_button);
        prevbutton = (Button)findViewById(R.id.prev_button);


        sliderAdapter = new SliderAdapter(this);

        mSlideViewPAger.setAdapter(sliderAdapter);

        mSlideViewPAger.addOnPageChangeListener(viewpager);

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp=getSharedPreferences("user",0);
                Boolean flag1=sp.getBoolean("islogin",false);

                if(flag1)
                {
                    Boolean flag2=sp.getBoolean("isuser",false);
                    if(flag2)
                    {
                        Intent intent=new Intent(MainActivity.this,userhomescreen.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent=new Intent(MainActivity.this,talent_homescreen.class);
                        startActivity(intent);

                    }
                }
                else {
                    Intent intent = new Intent(MainActivity.this, login_activity.class);
                    startActivity(intent);
                }
                finish();



               // Toast.makeText(MainActivity.this,"Welcome To SARA",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void addDotIndicator(int position){

        mDots = new TextView[3];
        dotslayout.removeAllViews();

        for(int i=0 ;i < mDots.length; i++){

            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorWhite));

            dotslayout.addView(mDots[i]);//we have created 3  dots but it does not show the current page

        }

        if(mDots.length > 0){

            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }


    }

    ViewPager.OnPageChangeListener viewpager = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotIndicator(i);

            mCurrentPage = i;
           if(i == mDots.length-1){

                prevbutton.setEnabled(true);
                nextbutton.setEnabled(true);
                nextbutton.setVisibility(View.VISIBLE);
                prevbutton.setText("BACK");
                nextbutton.setText("FINISH");


            }else{

                prevbutton.setEnabled(false);
                nextbutton.setEnabled(false);
                prevbutton.setVisibility(View.INVISIBLE);
               nextbutton.setVisibility(View.INVISIBLE);
                prevbutton.setText("");
                nextbutton.setText("");
            }



        }



        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}


