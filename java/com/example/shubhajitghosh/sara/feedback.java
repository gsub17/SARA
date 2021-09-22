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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback extends AppCompatActivity {

    RadioGroup radio1,radio2,radio3,radio4;
    RadioButton b1,n1,g1,v1;
    RadioButton b2,n2,g2,v2;
    RadioButton b3,n3,g3,v3;
    RadioButton yes,no;
    EditText suggestion;
    Button submit;
    MyKojo myKojo;
    SharedPreferences sharedPreferences;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        } catch (Exception e) {
            Log.e("12345....", "onCreate: " + e.getMessage());
        }


        sharedPreferences=getSharedPreferences("user",0);
        final String phone=sharedPreferences.getString("phone",null);


        radio1 = (RadioGroup)findViewById(R.id.radio1);
        radio2 = (RadioGroup)findViewById(R.id.radio2);
        radio3 = (RadioGroup)findViewById(R.id.radio3);
        radio4 = (RadioGroup)findViewById(R.id.radio4);
        b1=(RadioButton) findViewById(R.id.b1);
        n1=(RadioButton)findViewById(R.id.n1);
        g1=(RadioButton)findViewById(R.id.g1);
        v1=(RadioButton)findViewById(R.id.v1);

        b2=(RadioButton) findViewById(R.id.b2);
        n2=(RadioButton)findViewById(R.id.n2);
        g2=(RadioButton)findViewById(R.id.g2);
        v2=(RadioButton)findViewById(R.id.v2);

        b3=(RadioButton) findViewById(R.id.b3);
        n3=(RadioButton)findViewById(R.id.n3);
        g3=(RadioButton)findViewById(R.id.g3);
        v3=(RadioButton)findViewById(R.id.v3);

        yes=(RadioButton)findViewById(R.id.yes);
        no=(RadioButton)findViewById(R.id.no);
        suggestion = (EditText)findViewById(R.id.suggestion);
        submit = (Button)findViewById(R.id.submit);

        myKojo=new MyKojo();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("feedback");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exp, step, know,help,suggest;

                suggest=suggestion.getText().toString().trim();

                int id = radio1.getCheckedRadioButtonId();
                b1 = findViewById(id);

                if (b1 != null) {
                    if (b1.getText().toString().equals("Bad")) {
                        exp = "Bad";
                        myKojo.setExp(exp);

                    } else if (n1.getText().toString().equals("Normal")) {
                        exp = "Normal";
                        myKojo.setExp(exp);
                    } else if (g1.getText().toString().equals("Good")) {
                        exp = "Good";
                        myKojo.setExp(exp);
                    } else {
                        exp = "Very Good";
                        myKojo.setExp(exp);
                    }
                }

                int id2 = radio2.getCheckedRadioButtonId();
                b2 = findViewById(id2);

                if (b2 != null) {
                    if (b2.getText().toString().equals("Bad")) {
                        step = "Bad";
                        myKojo.setStep(step);
                    } else if (n2.getText().toString().equals("Normal")) {
                        step = "Normal";
                        myKojo.setStep(step);
                    } else if (g2.getText().toString().equals("Good")) {
                        step = "Good";
                        myKojo.setStep(step);
                    } else {
                        step = "Very Good";
                        myKojo.setStep(step);
                    }
                }

                int id3 = radio3.getCheckedRadioButtonId();
                b3 = findViewById(id3);

                if (b3 != null) {
                    if (b3.getText().toString().equals("Newspaper")) {
                        know = "Newspaper";
                        myKojo.setKnow(know);
                    } else if (n3.getText().toString().equals("Internet")) {
                        know = "Internet";
                        myKojo.setKnow(know);
                    } else if (g3.getText().toString().equals("Friend")) {
                        know = "Friend";
                        myKojo.setKnow(know);
                    } else {
                        know = "Others";
                        myKojo.setKnow(know);
                    }
                }

                int id4=radio4.getCheckedRadioButtonId();
                yes=findViewById(id4);
                if(yes!=null)
                {
                    if(yes.getText().toString().equals("YES")){
                        help="YES";
                        myKojo.setHelp(help);
                    }
                    else {
                        help="NO";
                        myKojo.setHelp(help);
                    }
                }

                myKojo.setSuggestion(suggest);
                myKojo.setPhone(phone);

                databaseReference.push().setValue(myKojo);
                Toast.makeText(feedback.this,"feedback submitted",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(feedback.this,userhomescreen.class);
                startActivity(intent);
                finish();
            }//ent listner
        });//end submit

    }//end on create


    public boolean onOptionsItemSelected(MenuItem item) {


        switch ((item.getItemId())) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }
}//end of class
