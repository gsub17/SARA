package com.example.shubhajitghosh.sara;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class talent_forgetpassword extends AppCompatActivity {



    EditText passwordEmail;
    Button resetPAssword;

    FirebaseAuth firebaseAuth;

    ProgressBar progressBar;


    /*

    EditText mobile;
    Button otp;

    DatabaseReference reference;
    FirebaseDatabase database;
    MyPojo myPojo;
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks verifycallback;
    PhoneAuthProvider.ForceResendingToken resettoken;
    Boolean found=false;
    String phone;

    String phoneverificationid;

*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_forgetpassword);




        passwordEmail = (EditText)findViewById(R.id.etPasswordEmail);
        resetPAssword = (Button)findViewById(R.id.btnPasswordReset);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar)findViewById(R.id.progress1);


        resetPAssword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                String useremail = passwordEmail.getText().toString().trim();

                if(useremail.equals("")){

                    progressBar.setVisibility(View.INVISIBLE);

                    Toast.makeText(getApplicationContext(),"Enter Your Register Emaail Id",Toast.LENGTH_SHORT).show();

                }else{

                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                progressBar.setVisibility(View.INVISIBLE);

                                Toast.makeText(getApplicationContext(),"Password reset email sent",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(talent_forgetpassword.this,talent_box.class));



                            }else{

                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(),"Error in sending password reset",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });


                }






            }
        });






        /*

        mobile=(EditText) findViewById(R.id.mobile);
        otp=(Button) findViewById(R.id.otp);
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        reference=database.getReference("sara");
        myPojo=new MyPojo();

        otp.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                phone=mobile.getText().toString().trim();
                Log.e("12345787", "onClick: "+phone );


                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot d : dataSnapshot.getChildren()) {
                            myPojo = d.getValue(MyPojo.class);


                            if (myPojo.getMobile().trim().equals(phone)) {
                                found = true;
                                break;
                            }
                        }

                        if (found==true) {
                            setUpCallVerification();
                            Intent intent=new Intent(talent_forgetpassword.this,talent_resetpassword.class);
                            startActivity(intent);
                            PhoneAuthProvider.getInstance().verifyPhoneNumber(phone,60, TimeUnit.SECONDS, talent_forgetpassword.this,verifycallback);

                        }

                        else {
                            Toast.makeText(talent_forgetpassword.this,"failed",Toast.LENGTH_SHORT).show();
                        }
                    }



                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(talent_forgetpassword.this,"wrong entry",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    private void setUpCallVerification() {
        Toast.makeText(talent_forgetpassword.this,"",Toast.LENGTH_SHORT).show();

        verifycallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                Toast.makeText(talent_forgetpassword.this,"Running",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(talent_forgetpassword.this,"Wrond Credentials",Toast.LENGTH_SHORT).show();

            }

            public void onCodeSent(String s,PhoneAuthProvider.ForceResendingToken forceresettoken){

                Toast.makeText(talent_forgetpassword.this,"On code sent......",Toast.LENGTH_SHORT).show();
                phoneverificationid = s;
                resettoken=forceresettoken;

            }
        };






    }


    private  void  signInwithPhoneAuthCredential(PhoneAuthCredential credential){

        Toast.makeText(talent_forgetpassword.this,"Sign In with Phone Auth......",Toast.LENGTH_SHORT).show();

        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(task.isSuccessful()){

                    Toast.makeText(talent_forgetpassword.this,"Successfully Verified",Toast.LENGTH_SHORT).show();


                }

            }
        });
    }*/

    }

}