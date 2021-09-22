package com.example.shubhajitghosh.sara;



import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.shubhajitghosh.sara.Modules.MyNode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class customerlast extends AppCompatActivity {

    ImageView image1;
    EditText mail;
    TextView name, phoneno, address, description, condition, fees;

    CardView card1;

    Button  maps;


    String n;
    String p;
    String ad;

    String de;//description
    String co;//condition
    String fe;//fees

    String image;
    String email;
    String url="http://192.168.1.9:8084/WebApplication1/Email.do";
    String img;

    RequestParams params;
    AsyncHttpClient client;
   // HashMap<String,ArrayList<String>> hashMap=new HashMap<>();
    String emailuser,emailservice;


    DatabaseReference reference;
    FirebaseDatabase database;

    Date date;
    SimpleDateFormat simpleDateFormat;
    Long time;

    MyNode myNode;
    Myicon myicon;



    BroadcastReceiver broadcastReceiver ;

    sendSms sms = new sendSms();

    private static final String ACCOUNT_SID ="AC3c51f019a855712f8e6fcab8e195b87c" ;
    private static final String AUTH_TOKEN = "e2a0d1e7bee65d8964b7d4a71a138504";

    int flag1,flag2,flag3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlast);


        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        } catch (Exception e) {
            Log.e("12345....", "onCreate: " + e.getMessage());
        }

        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo datac = cm
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null & datac != null)
                && (wifi.isConnected() | datac.isConnected())) {


            //connection is avlilable


            card1 = (CardView) findViewById(R.id.card1);

            image1 = (ImageView) findViewById(R.id.image1);
            name = (TextView) findViewById(R.id.name);
            phoneno = (TextView) findViewById(R.id.phoneno);
            address = (TextView) findViewById(R.id.address);

            description = (TextView) findViewById(R.id.description);
            condition = (TextView) findViewById(R.id.condition);
            fees = (TextView) findViewById(R.id.fees);

            maps = (Button) findViewById(R.id.maps);


            database=FirebaseDatabase.getInstance();
            reference=database.getReference("icon");
            myNode=new MyNode();
//            myicon=new Myicon();


            Intent i = getIntent();
            n = i.getStringExtra("name");
            p = i.getStringExtra("phoneno");
            ad = i.getStringExtra("address");
            image = i.getStringExtra("image");
            de = i.getStringExtra("description");
            co = i.getStringExtra("condition");
            fe = i.getStringExtra("fees");
            email=i.getStringExtra("email");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot d:dataSnapshot.getChildren())
                    {
                        myicon=new Myicon();
                        myicon=d.getValue(Myicon.class);

                        img=myicon.getAppicon();
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            MimeBodyPart imagePart = new MimeBodyPart();
            //imagePart.attachFile("https://firebasestorage.googleapis.com/v0/b/sara-9bec7.appspot.com/o/sara678.jpeg?alt=media&token=af2de81e-4ff5-4ac8-9f19-f18e65b1e387");




            Picasso.get().load(image).into(image1);//It convert the url into image and place it on location image1

            name.setText(n);
            phoneno.setText(p);
            address.setText(ad);
            description.setText("\t" + "Description" + "\n\n" + de);
            condition.setText("\t" + "Conditions" + "\n\n" + co);
            fees.setText("\t" + "Fees" + "\n\n" + fe);



            time = System.currentTimeMillis();
            simpleDateFormat = new SimpleDateFormat("MMM dd,yyyy HH:mm");
            date = new Date(time);



            maps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    View view = LayoutInflater.from(customerlast.this).inflate(R.layout.layout_dialog,null);

                    final EditText mail = (EditText) view.findViewById(R.id.mail);
                    final EditText phoneno = (EditText) view.findViewById(R.id.userphoneno);
                    final EditText name = (EditText) view.findViewById(R.id.username);
                    final EditText uaddress = (EditText)view.findViewById(R.id.useraddress);
                    final EditText udescription = (EditText)view.findViewById(R.id.userdescription);



                    final BroadcastReceiver[] broadcastReceiver = new BroadcastReceiver[1];

                    AlertDialog.Builder mBuilder = new AlertDialog.Builder(customerlast.this);
                    mBuilder.setMessage("Please enter the details")
                            .setView(view)
                            .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {



                                    String mailid = mail.getText().toString().trim();
                                    String userphoneno = phoneno.getText().toString().trim();
                                    String username = name.getText().toString().trim();
                                    String useraddress =  uaddress.getText().toString().trim();
                                    String userdescription = udescription.getText().toString().trim();


                                    emailuser=mailid.trim().toString();
                                    emailservice=email.trim().toString();


                                    usermaidid usermaididone = new usermaidid(mailid);


                                    Log.d("Input 1", "Location 2");



                                    if(emailuser.isEmpty()){
                                        flag1=1;
                                        Toast.makeText(getApplicationContext(),"Pls enter mail again",Toast.LENGTH_SHORT).show();

                                    }
                                    else if(!Patterns.EMAIL_ADDRESS.matcher(emailuser).matches()){
                                        flag1=1;
                                        Toast.makeText(getApplicationContext(),"Wrong mail id",Toast.LENGTH_SHORT).show();

                                    }
                                    else {
                                        flag1 = 0;
                                    }

                                    if(userphoneno.length()==10)
                                    {
                                        flag2=0;

                                    }
                                    else
                                    {
                                        flag2=1;
                                        Toast.makeText(getApplicationContext(),"Enter phone no again",Toast.LENGTH_SHORT).show();
                                    }






                                    if(username.isEmpty()){

                                        flag3=1;

                                       Toast.makeText(getApplicationContext(),"Pls enter correct phone no.",Toast.LENGTH_SHORT).show();

                                   }else{

                                        flag3=0;
                                    }


                                   if(flag1==1&&flag2==1&&flag3==1){

                                        Toast.makeText(getApplicationContext(),"Enter details again",Toast.LENGTH_SHORT).show();
                                   } else{


                                       int returnstatus=0;
                                       try {


                                          // MimeBodyPart imagePart = new MimeBodyPart();
                                           //imagePart.attachFile("https://firebasestorage.googleapis.com/v0/b/sara-9bec7.appspot.com/o/sara678.jpeg?alt=media&token=af2de81e-4ff5-4ac8-9f19-f18e65b1e387");

                                           GMailSender sender=new GMailSender("addonservices24@gmail.com","Addonservices@2597");
                                           returnstatus=sender.sendMail("Addon Service",//user


                                                   "Thanks for booking service . \n Your service provider name "+n+ "and phone number is"+p+" will contact you within" +
                                                           " 30 minutes\n for more assistance fell free to call us on 0188909999 \n\n"+
                                                   Picasso.get(),
                                                   "addonservices24@gmail.com", emailuser);



                                           sender.sendMail("Addon Service",//  service provider
                                                   "body contact"+username+"     "+userphoneno,
                                                   "addonservices24@gmail.com",
                                                   emailservice);



                                           database=FirebaseDatabase.getInstance();
                                           reference=database.getReference("node").child(userphoneno);

                                           myNode.setUsername(username);
                                           myNode.setUsermain(emailuser);
                                           myNode.setUserphonr(userphoneno);
                                           myNode.setServicemail(emailservice);
                                           myNode.setServicename(n);
                                           myNode.setServicephone(p);
                                           myNode.setServiceaddress(ad);
                                           myNode.setDate(simpleDateFormat.format(date));
                                           myNode.setRating(0+"");
                                           //myNode.setCategory(ca);

                                           reference.child(System.currentTimeMillis()+"").setValue(myNode);
                                           Intent intent = new Intent(getApplicationContext(),lastlast.class);
                                           intent.putExtra("address",ad);
                                           intent.putExtra("servicename",n);
                                           intent.putExtra("servicephoneno",p);

                                           startActivity(intent);



                                       }
                                       catch (Exception e)
                                       {
                                           Log.e("sendmail",e.getMessage());
                                       }

                                       if(returnstatus==1){


                                           Log.e("Returnstat","success");


                                       }
                                       else {
                                           Log.e("Returnststat","failed");
                                           //  Toast toast = Toast.makeText(getApplicationContext(), "Please try again", Toast.LENGTH_LONG);
                                           //  toast.show();

                                       }
                                   }




                                    //params.put("recipient", emailuser);

                                /*    client.get(url, params, new JsonHttpResponseHandler()
                                            {
                                                @Override
                                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                                    // super.onSuccess(statusCode, headers, response);
                                                    Log.d("11124144444", response.toString());
                                                    Toast.makeText(getApplicationContext(),"BOOKING CONFIRMED",Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(),fcm.class);
                                                    startActivity(intent);
                                                }

                                                @Override
                                                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                                    //super.onFailure(statusCode, headers, responseString, throwable);
                                                    Log.d("11124144444", responseString);
                                                }
                                            }


                                    );
                                    */




                                    //mail

                                   // Log.i("Send email", "");

                                    /*Intent emailIntent = new Intent(Intent.ACTION_SEND);

                                    emailIntent.setData(Uri.parse(mailid));
                                    emailIntent.setType("text/plain");
                                   // emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                                   // emailIntent.putExtra(Intent.EXTRA_CC, CC);
                                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "BOOKING CONFIRMED");
                                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Sufiyan is your electrician name");


                                    //mail

*/
                                  /*  Toast.makeText(getApplicationContext(),"BOOKING CONFIRMED",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),fcm.class);
                                    startActivity(intent);


*/


                                    //Log.i("Send email", "");





                                    broadcastReceiver[0] = new BroadcastReceiver() {
                                        @Override
                                        public void onReceive(Context context, Intent intent) {

                                        }
                                    };
                                   // registerReceiver(broadcastReceiver[0],new IntentFilter(MyFirebaseInstanceIdService.TOKEN_BROADCAST));
                                   // Toast.makeText(customerlastl"Booking for the service confirmed",Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("Back",null)
                            .setCancelable(false);

                    AlertDialog alert = mBuilder.create();
                    alert.show();

                }
            });




        } else {
            //no connection
            Toast toast = Toast.makeText(getApplicationContext(), "No Internet Connection",
                    Toast.LENGTH_LONG);
            toast.show();
        }



    }

//back action button
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

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }



}

               // Intent call = new Intent(Intent.ACTION_DIAL);
               // call.setData(Uri.parse("tel:" + (R.id.phoneno).getText());
               // startActivity(call);

