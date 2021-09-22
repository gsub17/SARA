package com.example.shubhajitghosh.sara;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class talent_description extends Fragment {


    EditText description, condition, qualification, fees;
    Button save;

    SharedPreferences sharedPreferences;

    String des;
    String cond;
    String fee;
    String qua;
    String key;
    String gmail1;

    FirebaseDatabase database;
    DatabaseReference reference;

    DatabaseReference ref=FirebaseDatabase.getInstance().getReference();

    MyPojo myPojo;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_talent_description, container, false);


        View talent = inflater.inflate(R.layout.fragment_talent_description, container, false);

        description = (EditText) talent.findViewById(R.id.description);
        condition = (EditText) talent.findViewById(R.id.conditions);
        qualification = (EditText) talent.findViewById(R.id.qualification);
        fees = (EditText) talent.findViewById(R.id.fes);
        save = (Button) talent.findViewById(R.id.save);

       /* Intent intent=new Intent();
        mail=intent.getStringExtra("email");*/
       // myMojo=new MyMojo();
        myPojo=new MyPojo();
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("sara");

        sharedPreferences=getContext().getSharedPreferences("user",0);
        gmail1=sharedPreferences.getString("email",null);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    myPojo=d.getValue(MyPojo.class);
                    Log.e("1234567", "onDataChange: gmail" +gmail1 +"mail String pojo"+ myPojo.getMail());
                    if(gmail1.equals(myPojo.getMail()))
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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*des=description.getText().toString().trim();
                 cond=condition.getText().toString().trim();
                 fee=fees.getText().toString().trim();
                qua=qualification.getText().toString().trim();

               // Log.d("1234", "onClick: "+reference.child("LFbenqKRg_kWqYqY6bu").child("description"));

                reference.child(key).child("description").setValue(des);
                reference.child(key).child("condition").setValue(cond);
                reference.child(key).child("fees").setValue(fee);
                reference.child(key).child("qualification").setValue(qua);*/
                /*&String des=description.getText().toString().trim();
                myMojo.setDescription(des);
                String con=condition.getText().toString().trim();
                myMojo.setConditions(con);
                String qua=qualification.getText().toString().trim();
                myMojo.setQualification(qua);
                String fee=fees.getText().toString().trim();
                myMojo.setFees(fee);
                reference.push().setValue(myMojo);*/
                Toast.makeText(getActivity(),"Your description have been saved",Toast.LENGTH_SHORT).show();




            }
       });

        return talent;





    }


    public interface OnFragmentInteractionListener {
    }
}




