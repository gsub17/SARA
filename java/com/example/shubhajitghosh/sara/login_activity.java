package com.example.shubhajitghosh.sara;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import static com.example.shubhajitghosh.sara.R.*;

public class login_activity extends AppCompatActivity {

    Button login;
    EditText phoneNo;
    Button talent;

    String MobileNo = "";
    Long time;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;

    AdminPojo adminPojo;
    //MyMojo myMojo;
    Date date;
    SimpleDateFormat simpleDateFormat;


    ProgressBar progres;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    String codeSent;
    String code = "";
    Boolean found = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        int[] images = {drawable.slider1, drawable.slider2, drawable.slider3, drawable.slider4, drawable.slider5, drawable.slider6};




     //start of check of netconnection




            talent = findViewById(R.id.talent);
            login = (Button) findViewById(id.login);
            //signup = (Button) findViewById(R.id.signup);
            //forget=(Button) findViewById(id.forget);
            phoneNo = (EditText) findViewById(id.phoneNo);
            //password = (EditText) findViewById(id.password);
            progres = (ProgressBar) findViewById(id.progres);

            database = FirebaseDatabase.getInstance();
            reference = database.getReference("admin");
            adminPojo = new AdminPojo();
            //myMojo=new MyMojo();

            mAuth = FirebaseAuth.getInstance();
            time = System.currentTimeMillis();
            simpleDateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
            date = new Date(time);



            talent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  Intent intent = new Intent(home_screen.this,coinsplash.class);
                    // startActivity(intent);


                    Intent intent = new Intent(getApplicationContext(),coinsplash.class);
                    startActivity(intent);



                }
            });





            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    MobileNo = phoneNo.getText().toString().trim();
                    // pass = password.getText().toString().trim();

                    int flag1 = 0;


                    if (MobileNo.isEmpty()) {
                        flag1 = 1;
                        phoneNo.setError("Mobile number is Required");
                        phoneNo.requestFocus();
                    } else if (MobileNo.length() != 10) {
                        flag1 = 1;
                        phoneNo.setError("Enter a valid moblie number");
                        phoneNo.requestFocus();
                    } else
                        flag1 = 0;

                /*if (pass.isEmpty()) {
                    flag2 = 1;
                    password.setError("Password is required");
                    password.requestFocus();
                } else if (pass.length() < 6) {
                    flag2 = 1;
                    password.setError("Enter a valid password");
                    password.requestFocus();
                } else
                    flag2 = 0;
*/

                    if (flag1 == 0) {
                    /*ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot d:dataSnapshot.getChildren())
                            {
                                myMojo=d.getValue(MyMojo.class);
                                if(MobileNo.equals(myMojo.getPhone()))
                                {
                                    //Toast.makeText(login_activity.this," user already exist",Toast.LENGTH_SHORT).show();
                                    break;

                                }

                            }//end of for loop....
                        }//end of ()....

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });*/
                        progres.setVisibility(View.VISIBLE);
                        adminPojo.setGmail(MobileNo);
                        adminPojo.setDate(simpleDateFormat.format(date));
                        reference.push().setValue(adminPojo);

                        sign();
                    }

                }


            });

       /* signup.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {

                Intent intent = new Intent(login_activity.this, signup.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(),
                startActivity(intent);

            }
        });
*/


            //when restablished

        }


    //end of oncreate

    public void sign() {

        /*ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot d:dataSnapshot.getChildren())
                            {
                                myMojo=d.getValue( MyMojo.class);
                                if(MobileNo.equals(myMojo.getPhone()) && pass.equals(myMojo.getPassword()))
                                {
                                    *//*name=myPojo.getName();
                                    phone=myPojo.getMobile();
                                    *//*
                                    Log.e("1234567890", "onDataChange: entered " );
                                    found = true ;
        */
        sendVerificationCode();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(MobileNo, 60, TimeUnit.SECONDS, login_activity.this, mCallbacks);


        verifySignInCode();

        // break;
    }
                            /*}
                            Log.e("1234567", "name"+pass );
                            Log.e("23", "onDataChange: "+MobileNo );

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
*/
    /**/ /*     if (!found)
        {
            Toast.makeText(this, "You are not a registered user", Toast.LENGTH_SHORT).show();
        }
                    progres.setVisibility(View.GONE);

        *//*SharedPreferences sharedPreferences=getSharedPreferences("user",0);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    //editor.putString("email",mail);
                    editor.putString("name",name);
                    editor.putString("phone",phone);
                    editor.putBoolean("loginStatus",true);
                    editor.commit();
*//*


                    //Toast.makeText(getApplicationContext(), "Login is Succesfull", Toast.LENGTH_SHORT).show();

                  //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //THIS will clear the stack and if user will press back at talent main it will come back to talent box
                    *//*intent.putExtra("email",gmail);
                    Log.e("123455...", "login"+gmail);
                    intent.putExtra("password",pword);
                    startActivity(intent);*//*


    }



    public void showToast(){

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_signup,(ViewGroup) findViewById(R.id.toastlayout));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }*/

    private void verifySignInCode() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter OTP");
        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                code = input.getText().toString();

                if (code.isEmpty()) {
                    progres.setVisibility(ProgressBar.GONE);
                    Toast.makeText(login_activity.this, "Error in OTP!!", Toast.LENGTH_SHORT).show();
                } else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);

                    signInWithPhoneAuthCredentials(credential);
                }
            }


        }
             //   builder.getWindow().setGravity(Gravity.BOTTOM);
        );
        //SharedPreferences sharedPreferences=getSharedPreferences("user",0);
        //SharedPreferences.Editor editor=sharedPreferences.edit();
        //editor.putString("email",mail);
        //editor.putString("name",name);
        //editor.putString("phone",MobileNo);
        //editor.putBoolean("isuser",true);
        //ditor.putBoolean("islogin",true);
        //editor.commit();

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progres.setVisibility(ProgressBar.GONE);
                Toast.makeText(login_activity.this, "Verification Failed !!", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        builder.show();

      //  builder.getClass().setGravity(Gravity.BOTTOM);

    }

    private void signInWithPhoneAuthCredentials(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential).addOnCompleteListener(login_activity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(login_activity.this, "Login Successful !!!", Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedPreferences = getSharedPreferences("user", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //editor.putString("email",mail);
                    //editor.putString("name",name);
                    editor.putString("phone",MobileNo);
                    editor.putBoolean("isuser",true);
                    editor.putBoolean("islogin",true);
                    editor.commit();

                    Intent intent = new Intent(login_activity.this, userhomescreen.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(login_activity.this, "Error in Verification!!", Toast.LENGTH_SHORT).show();
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        progres.setVisibility(ProgressBar.GONE);
                        Toast.makeText(login_activity.this, "Incorrect Verification Code !!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    public void sendVerificationCode() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                codeSent = s;
                resendingToken = forceResendingToken;
            }
        };
    }








    //super.onBackPressed();


}






