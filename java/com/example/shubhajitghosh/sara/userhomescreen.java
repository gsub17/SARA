package com.example.shubhajitghosh.sara;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userhomescreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;

    ListView listView;
    String name = "", mail = "";
    FirebaseDatabase database;
    DatabaseReference reference;
    //    MyMojo myMojo;
    String[] cat = {"Babysitting", "Beautician", "Cooking","Electrician", "Fruit and Vegetable seller", "Knitting", "Laundry", "Mehandi Designer","Plumber", "Tuition Teacher", "Tailor", "Stationary"};
    int[] image = {R.drawable.babysittingtwo, R.drawable.beautyone, R.drawable.chefone,R.drawable.electrician, R.drawable.fruitone, R.drawable.knittingone, R.drawable.maidone,  R.drawable.mahendione,R.drawable.plumber, R.drawable.tuitionteacher, R.drawable.tailor,  R.drawable.stationary};
    ArrayList<Model> arrayList = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomescreen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        //navigationView.(new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL));


        listView = findViewById(R.id.listview);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("sara").child("customer");
        // myMojo=new MyMojo();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        sharedPreferences = getSharedPreferences("user", 0);
        // String name=sharedPreferences.getString("name",null);
        final String phone = sharedPreferences.getString("phone", null);
        //   String email=sharedPreferences.getString("email",null);
        Explode explode = new Explode();





        /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d:dataSnapshot.getChildren())
                {
                    myMojo=d.getValue(MyMojo.class);
                    if(phone.equals(myMojo.getPhone()))
                    {
                        name=myMojo.getName();
                        mail=myMojo.getEmail();
                        break;
                    }
                }
                View hView =  navigationView.getHeaderView(0);
                TextView nav_user = (TextView)hView.findViewById(R.id.uname);
                Log.d("12345567", "onCreate: "+name);
                nav_user.setText(name);
                TextView nav_email = (TextView)hView.findViewById(R.id.uemail);
                Log.d("12345567", "onCreate: "+mail);
                nav_email.setText(mail);
                TextView nav_phone = (TextView)hView.findViewById(R.id.uphone);
                Log.d("12345567", "onCreate: "+phone);
                nav_phone.setText(phone);
                navigationView.setNavigationItemSelectedListener(userhomescreen.this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/

        View hView = navigationView.getHeaderView(0);
        /*TextView nav_user = (TextView)hView.findViewById(R.id.uname);
        //Log.d("12345567", "onCreate: "+name);
        nav_user.setText("sufiyan");
        TextView nav_email = (TextView)hView.findViewById(R.id.uemail);
        //Log.d("12345567", "onCreate: "+mail);
        nav_email.setText("sufiyam@gmail.com");
        */
        TextView nav_phone = (TextView) hView.findViewById(R.id.uphone);
        //Log.d("12345567", "onCreate: "+phone);
        nav_phone.setText(phone);
        navigationView.setNavigationItemSelectedListener(userhomescreen.this);


        for (int i = 0; i < cat.length; i++) {
            Model model = new Model();
            model.setCategories(cat[i]);
            model.setImage(image[i]);
            arrayList.add(model);
        }

        customAdaptor Adaptor = new customAdaptor(getApplicationContext(), R.layout.categories, arrayList);

        listView.setAdapter(Adaptor);


        //animation

// navigation view code start here

        //// NavigationView navigationView = (NavigationView)findViewById(R.id.drawer);
        //   navigationView.setNavigationItemSelectedListener(getApplicationContext());


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {

            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            userhomescreen.this.finish();
                            System.exit(0);
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        //super.onBackPressed();

    }


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.userhomescreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.logout) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(this, login_activity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, item.getTitle() + " logout successfully", Toast.LENGTH_SHORT).show();
            // Handle the camera action
        } else if (id == R.id.help) {
            //Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), complain.class);
            startActivity(intent);
        } else if (id == R.id.aboutUs) {
            //Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), aboutus.class);
            startActivity(intent);
        } else if (id == R.id.feedback) {
            Intent intent = new Intent(getApplicationContext(), feedback.class);
            startActivity(intent);
            finish();

        }
        else if (id == R.id.mybooking) {
            Intent intent = new Intent(getApplicationContext(), mybooking.class);
            intent.putExtra("phone",sharedPreferences.getString("phone", null));
            startActivity(intent);

        }
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }



}
