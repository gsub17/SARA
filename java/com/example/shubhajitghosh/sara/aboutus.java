package com.example.shubhajitghosh.sara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class aboutus extends AppCompatActivity {

    ImageView back;
    TextView text1,text2,text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        } catch (Exception e) {
            Log.e("12345....", "onCreate: " + e.getMessage());
        }



        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);
        back = (ImageView)findViewById(R.id.back);

        text1.setText("I am very enthuastic , to be part of this app . I am ready to learn more in life "+"\n"+"\n"+"\t"+"\t"+"SHANKAR LAL");
        text2.setText("Time spent on this app was awesome and I want to extend it " + "\n"+"\n"+"\t"+"\t"+"SUFIYAN");
        text3.setText("Learn a lot on developing SARA two month journey on developing it was awesome ."+"\n"+ "\n"+"\t"+"\t"+ "SUBHAJIT GHOSH");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),userhomescreen.class);
                startActivity(intent);
                finish();
            }
        });



    }

    public boolean onOptionsItemSelected(MenuItem item) {


        switch ((item.getItemId())) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }
}
