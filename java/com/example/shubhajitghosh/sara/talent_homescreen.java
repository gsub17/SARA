package com.example.shubhajitghosh.sara;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class talent_homescreen extends AppCompatActivity implements  talent_profile.OnFragmentInteractionListener,talent_description.OnFragmentInteractionListener,talent_address.OnFragmentInteractionListener{

    TabLayout tabLayout;
    ViewPager viewPager;

    ActionBarDrawerToggle mtoggle;

    DrawerLayout drawerLayout;
    //String mail;
    //static String gmail1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_homescreen);
        Intent intent=getIntent();
        //mail=intent.getStringExtra("email");
        //gmail1 = mail;

        tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Description"));
        tabLayout.addTab(tabLayout.newTab().setText("Order"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        //adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
       // vpPager.setAdapter(adapterViewPager);




         mtoggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

         viewPager = (ViewPager)findViewById(R.id.pager);




        fragment_pager adapter = new fragment_pager(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });






    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }


    public void onBackPressed() {

            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            talent_homescreen.this.finish();
                            System.exit(0);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        //super.onBackPressed();



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(getApplicationContext(), talent_edit.class);
               // intent.putExtra("mail",gmail1);
                startActivity(intent);
                break;
            case R.id.item2:
                SharedPreferences sp=getSharedPreferences("user",0);
                SharedPreferences.Editor ed=sp.edit();
                ed.clear();
                ed.commit();
                Intent intent1=new Intent(talent_homescreen.this,talent_box.class);
                startActivity(intent1);
                finish();
                break;

        }

        return true;

    }
}
