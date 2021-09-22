package com.example.shubhajitghosh.sara;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static com.google.android.gms.wearable.DataMap.TAG;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    public  static final String REG_TOKEN= "REG_TOKEN";

   @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
     //   String refreshedToken = FirebaseInstanceId.getInstance().getToken();
    //    Log.d("myfirebaseid", "Refreshed token: " + refreshedToken);

     //   getApplicationContext().sendBroadcast(new Intent(TOKEN_BROADCAST));
     //   storeToken(refreshedToken);
       // sendRegistrationToServer(refreshedToken);


      // super.onTokenRefresh();
      // sendNewTokenToServer(FirebaseInstanceId.getInstance().getToken());

       // Get updated InstanceID token.
     //  String refreshedToken = FirebaseInstanceId.getInstance().getToken();
     //  Log.d(TAG, "Refreshed token: " + refreshedToken);
       // If you want to send messages to this application instance or
       // manage this apps subscriptions on the server side, send the
       // Instance ID token to your app server.
      // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       //preferences.edit().putString(SyncStateContract.Constants, refreshedToken).apply();

       String recent_token = FirebaseInstanceId.getInstance().getToken();  //we are generating token so as to send device token to firebase cloud messaging
       Log.e("12345678","value     :"+REG_TOKEN.toString());

    }

    private void sendNewTokenToServer(String token) {

       Log.d("fcm",token);
    }

    // private void storeToken(String refreshedToken){

   //    SharedPrefManager.getInstance(getApplicationContext()).storeToken(refreshedToken);
      //  SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(token);
 //   }

}
