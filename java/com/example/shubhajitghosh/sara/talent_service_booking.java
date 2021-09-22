package com.example.shubhajitghosh.sara;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhajitghosh.sara.Modules.MyNode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class talent_service_booking extends AppCompatActivity {

    TextView name,date,mail,phone,address;
    Button confirm,cancel;
    String Name;
    String Mail;
    String Date;
    String Phone;
    String Address;
    String smail;
    int flag;

    Button maptwo;

    FirebaseDatabase database;
    DatabaseReference reference;
    MyNode myNode;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_service_booking);

        name=findViewById(R.id.spname);
        mail=findViewById(R.id.spmail);
        date=findViewById(R.id.sptime);
        phone=findViewById(R.id.spmobile);
        address=findViewById(R.id.spaddress);
        maptwo=findViewById(R.id.maptwo);

        confirm=findViewById(R.id.Confirm);
        cancel=findViewById(R.id.scancel);

        Intent i=getIntent();
        Name=i.getStringExtra("name");
        Mail=i.getStringExtra("mail");
        Address=i.getStringExtra("address");
        Date=i.getStringExtra("date");
        Phone=i.getStringExtra("phone");
        //flag=i.getIntExtra("flag",0);

        /*sharedPreferences=getContext().getSharedPreferences("user",0);
        smail=sharedPreferences.getString("email",null);*/
        name.setText(Name);
        mail.setText(Mail);
        phone.setText(Phone);
        address.setText(Address);
        date.setText(Date);


        database=FirebaseDatabase.getInstance();
        reference=database.getReference("node");

        //Log.e("sufiyan21",flag+"");

        confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("sufiyan21",flag+"");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot d:dataSnapshot.getChildren())
                        {
                            for (DataSnapshot e:d.getChildren())
                            {
                                myNode =new MyNode();
                                myNode=e.getValue(MyNode.class);

                                Log.e("sufiyanC",myNode.getFlag()+"");
                                if(Date.equals(myNode.getDate())){

                                    flag=myNode.getFlag();
                                    Log.e("sufiyanscon",flag+"");
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
                    Log.e("sufiyan21",flag+"");
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


                                       //flag=1;
                                       //reference.child(d.getKey()).child(e.getKey()).child("flag").setValue(1);
                                       Log.e("sufiyan21",myNode.getUserphonr());
                                       //Toast.makeText(getApplicationContext(),"order ",Toast.LENGTH_SHORT).show();

                                       Toast.makeText(getApplicationContext(),"Service Confired",Toast.LENGTH_SHORT).show();
                                       Intent intent=new Intent(talent_service_booking.this,talent_homescreen.class);
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

               /* else if (flag==1) {
                    Toast.makeText(getApplicationContext(), "Already confirm", Toast.LENGTH_SHORT).show();
                }
                else if (flag==2)
                {
                    Toast.makeText(getApplicationContext(), "Service Complete", Toast.LENGTH_SHORT).show();
                }
                else if (flag==-1) {
                    Toast.makeText(getApplicationContext(), "Service cancel", Toast.LENGTH_SHORT).show();
                }
                else if (flag==-2)
                {
                    Toast.makeText(getApplicationContext(), "Denied Service", Toast.LENGTH_SHORT).show();
                }*/

            }
        });

        maptwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(Address));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);


            }
        });

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
                                    Log.e("sufiyanscan",flag+"");

                                }

                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                if (flag==0)
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

                                        //flag=-1;
                                       // reference.child(d.getKey()).child(e.getKey()).child("flag").setValue(-1);
                                        Log.e("sufiyan21",myNode.getUserphonr());
                                      //  Toast.makeText(getApplicationContext(),"order ",Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getApplicationContext(),"Service Cancelled",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(talent_service_booking.this,talent_homescreen.class);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    }

                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                /*else if (flag==1) {
                    Toast.makeText(getApplicationContext(), "Service already confirm", Toast.LENGTH_SHORT).show();
                }
                else if (flag==2)
                {
                    Toast.makeText(getApplicationContext(), "Service has been completed", Toast.LENGTH_SHORT).show();
                }
                else if (flag==-1) {
                    Toast.makeText(getApplicationContext(), "Service cancel", Toast.LENGTH_SHORT).show();
                }
                else if (flag==-2)
                {
                    Toast.makeText(getApplicationContext(), "Already denied", Toast.LENGTH_SHORT).show();
                }*/
                //else if(flag==)
            }
        });

    }


}
