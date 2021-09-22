package com.example.shubhajitghosh.sara;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class complain extends AppCompatActivity {

    EditText name,phone,complain;
    Button submit;
    String mobile;


    SharedPreferences sharedPreferences;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String n="",p="",complaint="";

    int flag1=0,flag2=0,flag3=0;

    tojo ojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        } catch (Exception e) {
            Log.e("12345....", "onCreate: " + e.getMessage());
        }


        sharedPreferences=getSharedPreferences("user",0);
        mobile=sharedPreferences.getString("phone",null);

        ojo=new tojo();
        name = (EditText)findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phone);
        complain = (EditText)findViewById(R.id.complain);

        submit = (Button)findViewById(R.id.submit);

        phone.setText(mobile);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                n = name.getText().toString().trim();
                //p = phone.getText().toString().trim();
                complaint = complain.getText().toString().trim();



                ojo = new tojo();

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("complain");


                if(n.equals("")) {
                    flag1=0;

                    name.setError("Enter your name");

                }else {
                    flag1=1;
                    ojo.setName(n);

                }


                /*if(p.equals("")){
                    flag2=0;

                    phone.setError("Enter phone no");
                }else if(p.length() == 10){
                    ojo.setPhoneno(p);
                    flag2=1;
                }else{
                    phone.setError("Enter valid phone no");
                }
*/


                    if(complaint.equals("")){
                    flag3=0;

                    complain.setError("Enter the complain");

                }else{
                    flag3=1;
                    ojo.setComplain(complaint);
                    }


                    if(flag1==1 && flag3==1){
                        ojo.setPhoneno(mobile);

                    databaseReference.push().setValue(ojo);
                        Toast.makeText(getApplicationContext(),"Your Complain Have Been Submitted",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),userhomescreen.class);
                        startActivity(intent);
                        finish();



                    }else{

                        Toast.makeText(getApplicationContext(),"Technical Error",Toast.LENGTH_SHORT).show();

                    }


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
