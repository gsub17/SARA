package com.example.shubhajitghosh.sara;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhajitghosh.sara.Modules.MyNode;
import com.example.shubhajitghosh.sara.Modules.Myrating;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class user_bookings extends AppCompatActivity {

    TextView name,date,mail,phone,address;
    Button cancel,success;

    String Name;
    String Mail;
    String Date;
    String Phone;
    String Address;
    int flag;



    MyNode myNode;
    FirebaseDatabase database,firebaseDatabase;
    DatabaseReference reference,databaseReference;
    View view ;
    RadioButton rate1 ;
    RadioButton rate2 ;
    RadioButton rate3 ;
    RadioButton rate4 ;
    RadioGroup rater;

    Myrating myrating;

    LinearLayoutManager linearLayoutManager;
    MyNode n;
    RecyclerView recyclerView;
    String p="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_bookings);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        } catch (Exception e) {
            Log.e("12345....", "onCreate: " + e.getMessage());
        }


        name=findViewById(R.id.usname);
        mail=findViewById(R.id.usmail);
        date=findViewById(R.id.ustime);
        phone=findViewById(R.id.usmobile);
        address=findViewById(R.id.usaddress);


        cancel=findViewById(R.id.cancel);
        success=findViewById(R.id.successful);


        view = LayoutInflater.from(user_bookings.this).inflate(R.layout.layout_dialogtwo,null);
        rate1 = (RadioButton) view.findViewById(R.id.b1);
        rate2 = (RadioButton) view.findViewById(R.id.n1);
        rate3 = (RadioButton) view.findViewById(R.id.g1);
        rate4 = (RadioButton) view.findViewById(R.id.v1);
        rater=(RadioGroup)view.findViewById(R.id.radior);

        final Intent i=getIntent();
        Name=i.getStringExtra("name");
        Mail=i.getStringExtra("mail");
        Address=i.getStringExtra("address");
        Date=i.getStringExtra("date");
        Phone=i.getStringExtra("phone");
        p=i.getStringExtra("phone");
       // flag=i.getIntExtra("flag",0);

        name.setText(Name);
        mail.setText(Mail);
        phone.setText(Phone);
        address.setText(Address);
        date.setText(Date);

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("node");

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Rating");

        myrating =new Myrating();




        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot d:dataSnapshot.getChildren())
                        {
                            for (DataSnapshot e:d.getChildren())
                            {
                                myNode =new MyNode();
                                myNode=e.getValue(MyNode.class);

                                if(Date.equals(myNode.getDate())){

                                    flag=myNode.getFlag();
                                }

                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                if(flag==0)
                {
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot d:dataSnapshot.getChildren())
                            {
                                for (DataSnapshot e:d.getChildren())
                                {
                                    myNode =new MyNode();
                                    myNode=e.getValue(MyNode.class);

                                    if(Date.equals(myNode.getDate())){

                                        //flag=-2;
                                        //reference.child(d.getKey()).child(e.getKey()).child("flag").setValue(-2);
                                        Toast.makeText(getApplicationContext(),"Service Cancelled",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(user_bookings.this,userhomescreen.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }



               /* linearLayoutManager=new LinearLayoutManager(user_bookings.this,LinearLayoutManager.VERTICAL,false);
                recyclerView=findViewById(R.id.recycleviewtwo);
                recyclerView.setLayoutManager(linearLayoutManager);
                final MyBookingAdapter adapter=new MyBookingAdapter(no,user_bookings.this);
                recyclerView.setAdapter(adapter);

                */

                /*else if (flag==1) {
                    Toast.makeText(getApplicationContext(), "service Cannot be cancel", Toast.LENGTH_SHORT).show();
                }
                else if (flag==2)
                {
                    Toast.makeText(getApplicationContext(), "Service has been completed", Toast.LENGTH_SHORT).show();
                }
                else if (flag==-1) {
                    Toast.makeText(getApplicationContext(), "Already cancel", Toast.LENGTH_SHORT).show();
                }
                else if (flag==-2)
                {
                    Toast.makeText(getApplicationContext(), "Service denied", Toast.LENGTH_SHORT).show();
                }*/

            }
        });


        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot d:dataSnapshot.getChildren())
                        {
                            for (DataSnapshot e:d.getChildren())
                            {
                                myNode =new MyNode();
                                myNode=e.getValue(MyNode.class);

                                if(Date.equals(myNode.getDate())){

                                    flag=myNode.getFlag();
                                    Log.e("123456", "Flag " + flag );
                                }

                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot d:dataSnapshot.getChildren())
                            {
                                for (DataSnapshot e:d.getChildren())
                                {
                                    final DataSnapshot dfinal=d;
                                    final DataSnapshot efinal=e;
                                    myNode =new MyNode();
                                    myNode=e.getValue(MyNode.class);

                                    if(Date.equals(myNode.getDate())){

                                        //flag=2;
                                        //reference.child(d.getKey()).child(e.getKey()).child("flag").setValue(2);
                                        Toast.makeText(getApplicationContext(),"Service has been completed",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(user_bookings.this,userhomescreen.class);
                                        startActivity(intent);
                                        finish();

                                       // View view = LayoutInflater.from(user_bookings.this).inflate(R.layout.layout_dialogtwo,null);

                                       /* AlertDialog.Builder builder=new AlertDialog.Builder(user_bookings.this);
                                        int id = rater.getCheckedRadioButtonId();
                                        Log.d("12345678","Rate :: " + id);
                                        rate1 = findViewById(id);

                                        builder.setMessage("Rate ")
                                                .setView(view)
                                                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                        String rating;


                                                        if (rate1 != null) {
                                                            Log.d("12345678","Rate :: " + rate1 + "Rate String :: " + rate1.getText().toString());
                                                            if (rate1.getText().toString().equals("Bad")) {
                                                                rating = "1";
                                                                reference.child(dfinal.getKey()).child(efinal.getKey()).child("rating").setValue(rating);
                                                                Log.d("123456","Bad Entered");


                                                            } else if (rate1.getText().toString().equals("Normal")) {
                                                                rating = "2";
                                                                reference.child(dfinal.getKey()).child(efinal.getKey()).child("rating").setValue(rating);
                                                                Log.d("123456","Normal Entered");


                                                            } else if (rate1.getText().toString().equals("Good")) {
                                                                rating = "3";
                                                                reference.child(dfinal.getKey()).child(efinal.getKey()).child("rating").setValue(rating);
                                                                Log.d("123456","Good Entered");


                                                            } else {
                                                                rating = "4";
                                                                reference.child(dfinal.getKey()).child(efinal.getKey()).child("rating").setValue(rating);
                                                                Log.d("123456","Excell Entered");


                                                            }
                                                        }
                                                        else
                                                            Log.d("12345678","Rate is null :: " + rate1);

                                                        Intent intent=new Intent(user_bookings.this,userhomescreen.class);
                                                        startActivity(intent);
                                                        finish();



                                                    }
                                                })
                                                .setNegativeButton("Back",null)
                                                .setCancelable(false);




                                        AlertDialog alert = builder.create();
                                        alert.show();
*/

                                    }

                                }
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                /*else if(flag==0) {
                    Log.d("12345678","" + flag);
                    Toast.makeText(getApplicationContext(), "Service not confirm", Toast.LENGTH_SHORT).show();
                }
                else if(flag==-1) {
                    Toast.makeText(getApplicationContext(), "Service already cancel", Toast.LENGTH_SHORT).show();
                }
                else if (flag==-2)
                {
                    Toast.makeText(getApplicationContext(), "Service has been denied", Toast.LENGTH_SHORT).show();
                }*/

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
