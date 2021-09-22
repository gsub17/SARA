package com.example.shubhajitghosh.sara;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class talent_profile extends Fragment {

    CircleImageView profile;
    TextView username,phone,mail,category,state,city,address1,address2;
    Switch aSwitch;

    FirebaseDatabase database;
    DatabaseReference reference;
    MyPojo myPojo;

    Uri uri;
    SharedPreferences sharedPreferences;
    String image;
    String key;
    public talent_profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View talent=inflater.inflate(R.layout.fragment_talent_profile,container,false);

        username=(TextView) talent.findViewById(R.id.entername);
        phone=(TextView) talent.findViewById(R.id.enterphoneno);
        mail=(TextView) talent.findViewById(R.id.gmail);
        category=(TextView) talent.findViewById(R.id.category);
        state=(TextView) talent.findViewById(R.id.state);
        city=(TextView) talent.findViewById(R.id.city);
        address1=(TextView) talent.findViewById(R.id.address1);
        address2=(TextView) talent.findViewById(R.id.address2);
        profile=(CircleImageView) talent.findViewById(R.id.profileimage);
        aSwitch=(Switch)talent.findViewById(R.id.switch1);



        database=FirebaseDatabase.getInstance();
        reference=database.getReference("sara");

        myPojo =new MyPojo();

       /* Intent intent=new Intent();
        gmail1=intent.getStringExtra("email");
*/

        //Log.e("123455...", ""+ talent_homescreen.gmail1);

        sharedPreferences=getContext().getSharedPreferences("user",0);
        final String gmail1=sharedPreferences.getString("email",null);
        Log.e("999999999", "onCreateView: "+gmail1 );
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot d:dataSnapshot.getChildren())
                {
                    myPojo=d.getValue(MyPojo.class);
                  //  Log.e("12345...gmail", myPojo.getMail());

                    if ( gmail1 != null && gmail1.equals(myPojo.getMail()) )
                    {

                     // Toast.makeText(talent_profile.this, "Failed /n Pls try again", Toast.LENGTH_SHORT).show();
                       // Toast.makeText(talent_profile.class,"1",Toast.LENGTH_SHORT).show();
                        Log.e("12345......", "onDataChange: "+gmail1);
                        //Log.e("12345......s", "onDataChange: "+gmail1);
                        key=d.getKey().toString();
                        Log.e("12345s......", "onDataChange: "+key);

                        if(myPojo.getFlag()==1)
                            aSwitch.setChecked(true);
                        else
                            aSwitch.setChecked(false);
                        break;
                    }
                   // Toast.makeText(talent_profile.this, "Failed /n Pls try again", Toast.LENGTH_SHORT).show();
                }
                Log.e("1234.....", "onDataChange: out  of if  " );
                username.setText(myPojo.getName());
                image=myPojo.getImageurl();
                //uri= Uri.parse(image);
                phone.setText(myPojo.getMobile());
                mail.setText(myPojo.getMail());
                category.setText(myPojo.getCategroies());
                state.setText(myPojo.getState());
                city.setText(myPojo.getCity());
                address1.setText(myPojo.getAddress1());
                address2.setText(myPojo.getAddress2());
               // profile.setImageURI(uri);
                Picasso.get().load(image).into(profile);


                username.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ObjectAnimator flip = ObjectAnimator.ofFloat(username,"rotationX",360f,0f);
                        flip.setDuration(2000);
                        //flip.setRepeatCount(Animation.INFINITE);
                        flip.start();
                    }
                });

                phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ObjectAnimator flip = ObjectAnimator.ofFloat(phone,"rotationX",360f,0f);
                        flip.setDuration(2000);
                        //flip.setRepeatCount(Animation.INFINITE);
                        flip.start();
                    }
                });

                mail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ObjectAnimator flip = ObjectAnimator.ofFloat(mail,"rotationX",360f,0f);
                        flip.setDuration(2000);
                        //flip.setRepeatCount(Animation.INFINITE);
                        flip.start();
                    }
                });

                category.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ObjectAnimator flip = ObjectAnimator.ofFloat(category,"rotationX",360f,0f);
                        flip.setDuration(2000);
                        //flip.setRepeatCount(Animation.INFINITE);
                        flip.start();
                    }
                });

                state.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ObjectAnimator flip = ObjectAnimator.ofFloat(state,"rotationX",360f,0f);
                        flip.setDuration(2000);
                        //flip.setRepeatCount(Animation.INFINITE);
                        flip.start();
                    }
                });

                city.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ObjectAnimator flip = ObjectAnimator.ofFloat(city,"rotationX",360f,0f);
                        flip.setDuration(2000);
                        //flip.setRepeatCount(Animation.INFINITE);
                        flip.start();
                    }
                });

                address1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ObjectAnimator flip = ObjectAnimator.ofFloat(address1,"rotationX",360f,0f);
                        flip.setDuration(2000);
                        //flip.setRepeatCount(Animation.INFINITE);
                        flip.start();
                    }
                });

                address2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ObjectAnimator flip = ObjectAnimator.ofFloat(address2,"rotationX",360f,0f);
                        flip.setDuration(2000);
                        //flip.setRepeatCount(Animation.INFINITE);
                        flip.start();
                    }
                });

                aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked)
                        {
                            reference.child(key).child("flag").setValue(1);
                            Toast.makeText(getActivity()," Service Available",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            reference.child(key).child("flag").setValue(0);
                            Toast.makeText(getActivity()," Service Not Available",Toast.LENGTH_SHORT).show();
                            //aSwitch.setChecked(isChecked);
                        }
                    }
                });


            }




            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return talent;

    }

    public interface OnFragmentInteractionListener {
    }
}

