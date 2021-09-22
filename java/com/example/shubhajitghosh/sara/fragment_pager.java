package com.example.shubhajitghosh.sara;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

//import com.example.shubhajitghosh.sara.talent.talent_address;
//import com.example.shubhajitghosh.sara.talent.talent_description;
//import com.example.shubhajitghosh.sara.talent.talent_profile;

public class fragment_pager extends FragmentStatePagerAdapter {


    int mNoOfTabs;
    public  fragment_pager(FragmentManager fm,int NoOfTabs){

        super(fm);//fm ko super
        this.mNoOfTabs = NoOfTabs;//setting global no. of tabs to local no. of tabs

    }


    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0 :
                talent_profile tab1 = new talent_profile();
                return  tab1;
            case 1:
                talent_description tab2 = new talent_description();
                return tab2;
            case 2:
                talent_address tab3 = new talent_address();
                return tab3;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
