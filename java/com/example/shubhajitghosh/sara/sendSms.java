package com.example.shubhajitghosh.sara;

import android.util.Base64;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class sendSms {


    private static final String ACCOUNT_SID ="AC3c51f019a855712f8e6fcab8e195b87c" ;
    private static final String AUTH_TOKEN = "e2a0d1e7bee65d8964b7d4a71a138504";
    OkHttpClient client = new OkHttpClient();
    String url = "https://api.twilio.com/2010-04-01/Accounts/"+ACCOUNT_SID+"/SMS/Messages";
    String base64EncodedCredentials = "Basic " + Base64.encodeToString((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(), Base64.NO_WRAP);

    RequestBody body = new FormBody.Builder()
            .add("From", "8739874248")
            .add("To", "9694157137")
            .add("Body", "ADDON SERVICES")
            .build();

    Request request = new Request.Builder()
            .url(url)
            .post(body)
            .header("Authorization", base64EncodedCredentials)
            .build();


}
