package com.example.shubhajitghosh.sara;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class talent_new_description extends AppCompatActivity {

    Button submit;
    SharedPreferences sharedPreferences;
    EditText description,condition,fees,qualification;
    FirebaseDatabase database;
    DatabaseReference reference;
    MyPojo myPojo;
    String des;
    String cond;
    String fee="";
    String qua="";
    String key="";
    //String gmail="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_new_description);

      //  Intent intent=getIntent();
        //gmail=intent.getStringExtra("mail");

        description=findViewById(R.id.description);
        condition=findViewById(R.id.condition);
        fees=findViewById(R.id.fees);
        qualification=findViewById(R.id.qualification);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("sara");


        sharedPreferences=getSharedPreferences("signup",0);
        final String gmail=sharedPreferences.getString("mail",null);

        Log.e("9999999000", "mail "+gmail );

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    myPojo=new MyPojo();

                    myPojo= d.getValue(MyPojo.class);
                    Log.e("9999999000", "compare "+gmail.equals(myPojo.getMail()) );
                    Log.e("9999999000", "mypojo "+myPojo.getMail() );

                    if(gmail.equals(myPojo.getMail()))
                    {
                        key=d.getKey().toString();
                        break;
                    }

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                des=description.getText().toString().trim();
                cond=condition.getText().toString().trim();
                fee=fees.getText().toString().trim();
                qua=qualification.getText().toString().trim();

                // Log.d("1234", "onClick: "+reference.child("LFbenqKRg_kWqYqY6bu").child("description"));

                reference.child(key).child("description").setValue(des);
                reference.child(key).child("condition").setValue(cond);
                reference.child(key).child("fees").setValue(fee);
                reference.child(key).child("qualification").setValue(qua);

                Toast.makeText(talent_new_description.this,"you have successfully sign up",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),talent_box.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
