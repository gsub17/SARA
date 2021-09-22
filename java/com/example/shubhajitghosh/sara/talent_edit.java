package com.example.shubhajitghosh.sara;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class talent_edit extends AppCompatActivity {
    EditText description,condition,fees,qualification;
    Button submit;
    FirebaseDatabase database;
    DatabaseReference reference;
    MyPojo myPojo;
    SharedPreferences sharedPreferences;

    String des;
    String cond;
    String fee;
    String qua;
    String key;
    //String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_edit);
        Intent intent=getIntent();
        //email=intent.getStringExtra("email");

        description=(EditText) findViewById(R.id.des);
        condition=(EditText) findViewById(R.id.con);
        fees=(EditText) findViewById(R.id.fees);
        qualification=(EditText) findViewById(R.id.qua);
        submit=(Button) findViewById(R.id.submit);

        database=FirebaseDatabase.getInstance();
        reference=database.getReference("sara");
        myPojo=new MyPojo();

        sharedPreferences=getSharedPreferences("talent",0);
        final String email=sharedPreferences.getString("email",null);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    myPojo=d.getValue(MyPojo.class);

                    if(email.equals(myPojo.getMail()))
                    {
                        key=d.getKey().toString();
                        break;
                    }

                }

                description.setText(myPojo.getDescription());
                condition.setText(myPojo.getCondition());
                fees.setText(myPojo.getFees());
                qualification.setText(myPojo.getQualification());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                des=description.getText().toString().trim();
                cond=condition.getText().toString().trim();
                fee=fees.getText().toString().trim();
                qua=qualification.getText().toString().trim();

                reference.child(key).child("description").setValue(des);
                reference.child(key).child("condition").setValue(cond);
                reference.child(key).child("fees").setValue(fee);
                reference.child(key).child("qualification").setValue(qua);

                Intent intent=new Intent(talent_edit.this,talent_homescreen.class);
                startActivity(intent);


                Toast.makeText(talent_edit.this,"Your description have been saved",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
