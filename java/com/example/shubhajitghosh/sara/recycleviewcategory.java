package com.example.shubhajitghosh.sara;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class recycleviewcategory extends AppCompatActivity {

    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> phone=new ArrayList<>();
    ArrayList<String> address=new ArrayList<>();
    ArrayList<String> imageurl=new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    ArrayList<String> condition = new ArrayList<>();
    ArrayList<String> fees = new ArrayList<>();
    ArrayList<String> email=new ArrayList<>();
    ArrayList<Integer> flag=new ArrayList<>();


    LinearLayoutManager linearLayoutManager;
    FirebaseDatabase database;
    DatabaseReference reference;
    MyPojo myPojo;
    RecyclerView recyclerView;
    String categories="";
    static int count =0;

    //String img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviewcategory);



            //connection is avlilable

            database=FirebaseDatabase.getInstance();
            reference=database.getReference("sara");

            try {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            }catch(Exception e)
            {
                Log.e("12345....", "onCreate: "+e.getMessage());
            }



            Intent intent=getIntent();
            categories=intent.getStringExtra("categories");

            linearLayoutManager=new LinearLayoutManager(recycleviewcategory.this,LinearLayoutManager.VERTICAL,false);
            recyclerView=findViewById(R.id.recycleview);
            recyclerView.setLayoutManager(linearLayoutManager);
            final RecyclerAdapter adapter=new RecyclerAdapter(name,phone,address,imageurl,description,condition,fees,email,flag,recycleviewcategory.this);
            recyclerView.setAdapter(adapter);


            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot d:dataSnapshot.getChildren())
                    {   myPojo =new MyPojo();
                        myPojo= d.getValue(MyPojo.class);

                        if(categories.equals(myPojo.getCategroies()))
                        {
                            imageurl.add(myPojo.getImageurl());
                            name.add(myPojo.getName());
                            phone.add(myPojo.getMobile());
                            address.add(myPojo.getAddress1()+","+myPojo.getAddress2()+","+myPojo.getCity()+","+myPojo.getState());
                            imageurl.add(myPojo.getImageurl());
                            description.add(myPojo.getDescription());
                            condition.add(myPojo.getCondition());
                            fees.add(myPojo.getFees());
                            email.add(myPojo.getMail());
                            flag.add(myPojo.getFlag());

                            adapter.notifyDataSetChanged();
                        }

                        //Toast.makeText(recycleviewcategory.this,""+myPojo.getName(),Toast.LENGTH_SHORT).show();

                    }//end of for loop....

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




         }//end of onCreate()....


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch ((item.getItemId())) {
                                                                                                                                                                                                                                                                                                    case android.R.id.home:
                                                                                                                                                                                                                                                                                                        onBackPressed();
                                                                                                                                                                                                                                                                                                        return true;

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        default:

                                                                                                                                                                                                                                                                                                        return super.onOptionsItemSelected(item);
        }
    }


}//end of class...
