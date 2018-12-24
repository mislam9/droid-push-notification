package com.mahbub.googlehomenotification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    public static final String TOPIC_ALL_DEVICES = "alldevices";

    @Override
    public void onTokenRefresh() {

        //For registration of token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(getClass().getSimpleName(), refreshedToken);
        //To displaying token on logcat
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_ALL_DEVICES);
        SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(refreshedToken);
        //iTokenGenerate.onTokenGenerate(refreshedToken);
    }

    public void setTokenGenerate(ITokenGenerate iTokenGenerate){
        this.iTokenGenerate = iTokenGenerate;
    }

    private ITokenGenerate iTokenGenerate;

}