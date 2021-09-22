package com.example.shubhajitghosh.sara;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.shubhajitghosh.sara.Modules.MyNode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mybooking extends AppCompatActivity {

    ArrayList<MyNode> node=new ArrayList<>();

    LinearLayoutManager linearLayoutManager;
    FirebaseDatabase database;
    DatabaseReference reference;
    MyNode myNode;
    RecyclerView recyclerView;
    String phone="";
    static int count =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooking);


        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        } catch (Exception e) {
            Log.e("12345....", "onCreate: " + e.getMessage());
        }

        Intent intent=getIntent();
        phone=intent.getStringExtra("phone");

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("node").child(phone);

        Log.e("sufiyan",phone.toString());

        linearLayoutManager=new LinearLayoutManager(mybooking.this,LinearLayoutManager.VERTICAL,false);
        recyclerView=findViewById(R.id.recycleviewtwo);
        recyclerView.setLayoutManager(linearLayoutManager);
        final MyBookingAdapter adapter=new MyBookingAdapter(node,mybooking.this);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d:dataSnapshot.getChildren())
                {
                    myNode=new MyNode();
                    myNode=d.getValue(MyNode.class);

                    Log.e("sufiyan1",myNode.getServicename().toString());

                   node.add(myNode);
                   adapter.notifyDataSetChanged();
                   //adapter.notifyAll();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
