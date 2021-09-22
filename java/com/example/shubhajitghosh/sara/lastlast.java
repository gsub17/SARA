package com.example.shubhajitghosh.sara;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class lastlast extends AppCompatActivity {

    Button message,callnow,maps;
    String n,p,ad;
    TextView name;
    TextView phoneno;
    TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lastlast);

        message = (Button)findViewById(R.id.message);
        callnow = (Button)findViewById(R.id.callnow);
        maps = (Button)findViewById(R.id.maps);

        name = (TextView)findViewById(R.id.name);
        phoneno = (TextView)findViewById(R.id.phoneno);
        address = (TextView)findViewById(R.id.address);

        Intent i = getIntent();
        n = i.getStringExtra("servicename");
        p = i.getStringExtra("servicephoneno");
        ad = i.getStringExtra("address");

        name.setText(n);
        phoneno.setText(p);
        address.setText(ad);

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+p
                                )));


                    }
                });
            }
        });

        callnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent
                        .ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+p ));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //ActivityCompat.requestPermissions(getApplicationContext(),new String[]{Manifest.permission.CALL_PHONE},REQ);

                    return;
                } else {

                    startActivity(callIntent);
                }
            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(ad));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);


               // Intent intent = new Intent(getApplicationContext(),maps2_activity.class);
              //  intent.putExtra("EndLocation",ad);
              //  startActivity(intent);

            }
        });


    }
     public void onBackPressed(){



        super.onBackPressed();
        Intent intent = new Intent(this,userhomescreen.class);
        startActivity(intent);
        finish();

         Toast.makeText(getApplicationContext(),"Open mybooking to look booking history",Toast.LENGTH_LONG).show();

     }
}
