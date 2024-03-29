package com.example.shubhajitghosh.sara;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARE_PREF_NAME = "fcmsharedprefdemo";
    private static final String KEY_ACCESS_TOKEN = "token";


   private static Context mCtx;
   private static SharedPrefManager mInstance;

   private SharedPrefManager(Context context){

       mCtx = context;


   }

   public static synchronized SharedPrefManager getInstance(Context context){


       if(mInstance == null)
           mInstance = new SharedPrefManager(context);
       return mInstance;

   }

   public boolean storeToken(String token){


       SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF_NAME,Context.MODE_PRIVATE);
       SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.putString(KEY_ACCESS_TOKEN,token);
       editor.apply();
       return true;

   }
   public String getToken(){


       SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARE_PREF_NAME,Context.MODE_PRIVATE);
       return sharedPreferences.getString(KEY_ACCESS_TOKEN,null);



   }
}
