package com.mahbub.googlehomenotification;

import android.content.Intent;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class NotificationsService extends FirebaseMessagingService {
    public static final String TAG_KEY = "data";
    Logger mLogger = new Logger(this);
    public NotificationsService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage == null) {
            return;
        }
        mLogger.debug("Message Received: " + remoteMessage.toString());
        Map<String, String> dataMap = remoteMessage.getData();

        if(dataMap!=null){
            String subTitle = dataMap.get("subtitle");
            String title = dataMap.get("title");
            String message = dataMap.get("message");
            handleNotification(title, message);
        }

        else if (remoteMessage.getNotification() != null) {
            handleNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
    }

    public void handleNotification(String title, String message) {
        ManageNotification.showNotification(this, title, message, null, false, new Intent(this, MainActivity.class));
    }

}