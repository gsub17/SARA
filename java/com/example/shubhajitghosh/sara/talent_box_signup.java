package com.example.shubhajitghosh.sara;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

import static com.example.shubhajitghosh.sara.R.array;
import static com.example.shubhajitghosh.sara.R.id;
import static com.example.shubhajitghosh.sara.R.layout;

public class talent_box_signup extends AppCompatActivity {

    EditText firstname,mobilenumber,gmail,password,address1,address2;
    Spinner spinner,spinnerstate,spinnercity;
    Button signup;

    String imageurl;

    ArrayAdapter<CharSequence> adapter;

    ArrayList<String> arrayListstate;
    ArrayList<String> arrayListcity;
    ArrayList<JSONObject> arrayList;
    HashMap<String,ArrayList<String>> hashMap;
    String url="https://api.myjson.com/bins/uogf2";
    RequestQueue requestQueue;
    TreeSet<String> treeSet;

    FirebaseAuth mAuth;
    String email="",pword="",mobile="",name="",cat="",add1="",add2="",state="",city="",key="";

    int flag;
    DatabaseReference reference;
    FirebaseDatabase database;

    MyPojo myPojo;

    public static final int CHO0SE_IMAGE =  25;


    ProgressBar progressBar;
    Button save;
    ImageView profileimage;

    FirebaseStorage firebaseStorage;
    StorageReference profileimagereference;



    Uri uriprofileimage;


    // ObjectAnimator flip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_talent_box_signup);


        signup = (Button)findViewById(id.signup);
        spinner=(Spinner) findViewById(id.spinner);
        spinnercity=(Spinner) findViewById(id.spinnercity);
        spinnerstate=(Spinner) findViewById(id.spinnerstate);
        gmail = (EditText)findViewById(id.gmail);
        password = (EditText)findViewById(id.password);
        progressBar = findViewById(id.progresssbar);
        mobilenumber=(EditText) findViewById(id.enterphoneno);
        firstname=(EditText) findViewById(id.entername);
        address1=(EditText) findViewById(id.address1);
        address2=(EditText)findViewById(id.address2);

        arrayListstate=new ArrayList<String>();
        arrayListcity=new ArrayList<String>();
        arrayList=new ArrayList<JSONObject>();
        hashMap=new HashMap<String, ArrayList<String>>();
        treeSet=new TreeSet<String>();

        requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray m_jArry = obj.getJSONArray("Cities");

                    for (int i = 0; i < m_jArry.length(); i++) {
                        JSONObject object = m_jArry.getJSONObject(i);

                        String State = object.getString("state");
                        //String id = object.getString("id");
                        treeSet.add(State);
                        arrayList.add(object);
                    }
                    arrayListstate.add("Select State : ");
                    Iterator<String> iterator = treeSet.iterator();
                    while (iterator.hasNext()) {
                        arrayListstate.add(iterator.next());
                    }

                    ArrayList<String> temp = new ArrayList<String>();
                    temp.add("Select City : ");
                    temp.add("First Select State : ");
                    hashMap.clear();
                    hashMap.put("Select State : ", temp);

                    for (int j = 1; j < arrayListstate.size(); j++) {
                        //Log.e("123456789...", "Size: " + statelist.size());
                        String state = arrayListstate.get(j);
                        //Log.e("Statessss...", "State: " + state);
                        ArrayList<String> city = new ArrayList<>();
                        city.clear();
                        city.add("Select City : ");
                        for (int i = 0; i < arrayList.size(); i++) {
                            JSONObject object = arrayList.get(i);
                            String name = object.getString("name");
                            //Log.e("Size of array list : ", "" + arrayList.size());


                            if (state.equals(object.getString("state"))) {
                                city.add(name);
                                arrayList.remove(object);
                                //Log.e("123asdfg", "City" + state);
                            }
                            //Toast.makeText(getContext(),i+"--"+name,Toast.LENGTH_LONG).show();
                            //Log.e("123456789...", "onResponse: " + name);
                        }
                        hashMap.put(state, city);
                        Log.e("citymap .....", "onResponse: "+state+ ".."+hashMap);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(talent_box_signup.this, layout.support_simple_spinner_dropdown_item,arrayListstate);
                    spinnerstate.setAdapter(adapter);
                    spinnerstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = parent.getItemAtPosition(position).toString();
                            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(talent_box_signup.this, layout.support_simple_spinner_dropdown_item, hashMap.get(selectedItem));
                            spinnercity.setAdapter(adapter1);
                            //Log.e("hashmap print.....", "onResponse: "+selectedItem+"==="+ cityMap.get(selectedItem));
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
                catch (JSONException e)
                {
                    e.getMessage();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        requestQueue.add(stringRequest);


        database=FirebaseDatabase.getInstance();
        reference=database.getReference("sara");
        profileimagereference=FirebaseStorage.getInstance().getReference();
       // save = (Button)findViewById(R.id.save);
        profileimage = (ImageView)findViewById(id.profileimage);

        myPojo=new MyPojo();


        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,CHO0SE_IMAGE);

            }
        });


        adapter=ArrayAdapter.createFromResource(this, array.Categories,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mAuth = FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag1=0,flag2=0,flag3=0,flag4=0,flag5=0,flag6=0,flag7=0,flag8=0,flag9=0;
                email = gmail.getText().toString().trim();
                pword = password.getText().toString().trim();
                mobile =mobilenumber.getText().toString().trim();
                name=firstname.getText().toString().trim();
                add1=address1.getText().toString().trim();
                add2=address2.getText().toString().trim();
                state=spinnerstate.getSelectedItem().toString();
                city=spinnercity.getSelectedItem().toString();
                cat=spinner.getSelectedItem().toString();
                key=System.currentTimeMillis()+"";


                if(email.isEmpty()){
                    flag1=1;
                    //Toast.makeText(getApplicationContext(),"if email...",Toast.LENGTH_SHORT).show();
                    gmail.setError("Email is Required");
                    gmail.requestFocus();
                    }//end of if.....
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    flag1=1;
                    //Toast.makeText(getApplicationContext(),"Else if email...",Toast.LENGTH_SHORT).show();
                    gmail.setError("Please Enter A Valid Email");
                    gmail.requestFocus();
                }//end of if....
                else {
                    myPojo.setMail(email);
                    flag1 = 0;
                }


                if(pword.isEmpty()){
                    flag2=1;
                    //Toast.makeText(getApplicationContext(),"If password...",Toast.LENGTH_SHORT).show();
                    password.setError("Please Enter A Password");
                    password.requestFocus();

                }//end of if....
                else if(pword.length()<6){
                    flag2=1;
                    //Toast.makeText(getApplicationContext(),"Else if password....",Toast.LENGTH_SHORT).show();
                    password.setError("Minimum Length of Password is 6");
                    password.requestFocus();
                }//end of else if...
                else {
                    myPojo.setPassword(pword);
                    flag2 = 0;
                }



                if(mobile.length()==10)
                {
                    flag3=0;
                    myPojo.setMobile(mobile);
                }
                else
                {
                    flag3=1;
                    mobilenumber.setError("enter valid phone number");
                    mobilenumber.requestFocus();
                }



                if(name.isEmpty())
                {
                    flag4=1;
                    firstname.setError("enter name");
                    firstname.requestFocus();

                }else
                {
                    flag4=0;
                    myPojo.setName(name);
                }


                if(cat.equals("Categories"))
                {
                    flag5=1;
                    Toast.makeText(getApplicationContext(),"Select any one Category",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    flag5=0;
                    myPojo.setCategroies(cat);
                }
                if(state.equals("Select State : "))
                {
                    flag6=1;
                    Toast.makeText(getApplicationContext(),"Select State",Toast.LENGTH_SHORT).show();

                }
                else {
                    flag6=0;
                    myPojo.setState(state);
                }

                if(city.equals("Select City : "))
                {
                    flag7=1;
                    Toast.makeText(getApplicationContext(),"Select City",Toast.LENGTH_SHORT).show();

                }
                else {
                    flag7=0;
                    myPojo.setCity(city);
                }
                if (add1.isEmpty())
                {
                    flag8=1;
                    Toast.makeText(getApplicationContext(),"Enter a valid address",Toast.LENGTH_SHORT).show();
                }

                else {
                    flag8=0;
                    myPojo.setAddress1(add1);
                }


                if(flag1==0 && flag2==0 && flag3==0 && flag4==0 && flag5==0 && flag6==0 && flag7==0 && flag8==0) {
                    progressBar.setVisibility(View.VISIBLE);
                    myPojo.setAddress2(add2);
                    myPojo.setDescription("");
                    myPojo.setCondition("");
                    myPojo.setFees("");
                    myPojo.setQualification("");
                    myPojo.setFlag(1);

                  //  Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
                    autho();
                   // Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_SHORT).show();
                }
            }

        });

    }





    public void autho(){
        //Toast.makeText(getApplicationContext(),"4",Toast.LENGTH_SHORT).show();

        mAuth.createUserWithEmailAndPassword(email,pword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    if(uriprofileimage != null){
                        StorageReference reference2= profileimagereference.child("image/data"+System.currentTimeMillis()+".jpg");
                        reference2.putFile(uriprofileimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                                Toast.makeText(talent_box_signup.this, "Entered IF", Toast.LENGTH_SHORT).show();
                              //  Toast.makeText(talent_new_profile.this, "Profile pic have /n been saved", Toast.LENGTH_SHORT).show();
                                imageurl=taskSnapshot.getDownloadUrl().toString();
                              //  Intent intent = new Intent(getApplicationContext(),talent_homescreen.class);
                              //  startActivity(intent);
                                myPojo.setImageurl(imageurl);

                                reference.child(key).setValue(myPojo);



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                               // Toast.makeText(talent_new_profile.this, "Failed /n Pls try again", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                            }
                        });


                    }
                    else
                    {


                                imageurl="https://firebasestorage.googleapis.com/v0/b/sara-9bec7.appspot.com/o/image%2FDefault.gif?alt=media&token=87039865-4f06-49cb-80dc-13697d46bd65";
                        //        Toast.makeText(talent_box_signup.this, "Else entered", Toast.LENGTH_SHORT).show();
                                //  Intent intent = new Intent(getApplicationContext(),talent_homescreen.class);
                                //  startActivity(intent);
                                myPojo.setImageurl(imageurl);
                                reference.child(key).setValue(myPojo);
                        //Toast.makeText(talent_box_signup.this, "Entered", Toast.LENGTH_SHORT).show();

                            }




                    progressBar.setVisibility(View.GONE);



                    //Toast.makeText(talent_box_signup.this,"Sign Up Successfull",Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedPreferences=getSharedPreferences("signup",0);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("mail",email);
                    editor.commit();
                    Intent intent = new Intent(talent_box_signup.this,talent_new_description.class);
                    //intent.putExtra("mail",email);
                    startActivity(intent);
                }else{

                    progressBar.setVisibility(View.GONE);
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){ //Collision is used to check the email is already register or not
                        //Toast.makeText(talent_box_signup.this,"You are already register user",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(talent_box_signup.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==CHO0SE_IMAGE && resultCode == RESULT_OK && data != null ){
            uriprofileimage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uriprofileimage);
                profileimage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}//end of class....




