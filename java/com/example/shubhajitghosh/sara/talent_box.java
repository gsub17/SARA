package com.example.shubhajitghosh.sara;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class talent_box extends AppCompatActivity {

    FirebaseAuth mAuth;

    Button signup,login,forget;
    EditText edittext1,edittext2;

    String gmail="",pword="";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_box);

        mAuth = FirebaseAuth.getInstance();



        login = (Button)findViewById(R.id.login);
        signup = (Button)findViewById(R.id.signup);
        edittext1 = (EditText)findViewById(R.id.edittext1);
        edittext2 = (EditText)findViewById(R.id.edittext2);
        forget =(Button)findViewById(R.id.forget);
        progressBar = (ProgressBar)findViewById(R.id.progresssbar);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gmail = edittext1.getText().toString().trim();
                 pword = edittext2.getText().toString().trim();
                 int flag1=0,flag2=0;


                 if(gmail.isEmpty()){
                     flag1=1;
                     edittext1.setError("Email is Required");
                     edittext2.requestFocus();
                 }else if(!Patterns.EMAIL_ADDRESS.matcher(gmail).matches()){
                     flag1=1;
                     edittext1.setError("Enter a valid Email Id");
                     edittext1.requestFocus();
                 }else
                     flag1=0;

                 if(pword.isEmpty()){
                     flag2=1;
                     edittext2.setError("Password is required");
                     edittext2.requestFocus();
                 }else if(pword.length()<6){
                     flag2=1;
                     edittext2.setError("Enter a valid password");
                     edittext2.requestFocus();
                 }else
                     flag2=0;




                 if(flag1==0 && flag2==0) {
                     progressBar.setVisibility(View.VISIBLE);
                     sign();
                 }




            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(talent_box.this,talent_box_signup.class);
                startActivity(intent);
            }
        });


        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(talent_box.this,talent_forgetpassword.class);
                startActivity(intent);

            }
        });

    }





    public void sign(){


        mAuth.signInWithEmailAndPassword(gmail,pword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(getApplicationContext(),"Login is Succesfull",Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedPreferences=getSharedPreferences("user",0);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("email",gmail);
                    editor.putBoolean("isuser",false);
                    editor.putBoolean("islogin",true);
                    editor.commit();
                    Intent intent = new Intent(talent_box.this,talent_homescreen.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //THIS will clear the stack and if user will press back at talent main it will come back to talent box
                    //intent.putExtra("email",gmail);
                    //Log.e("123455...", "login"+gmail);
                    //intent.putExtra("password",pword);
                    startActivity(intent);
                    finish();

                }else{

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
