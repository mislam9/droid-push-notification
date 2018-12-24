package com.mahbub.googlehomenotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.Toast;


/**
 * Created by ASUS on 3/4/2017.
 */

public class ManageNotification {
    private static final int MESSAGE_NOTIFICATION_ID_REGULAR = 702455;
    private static final int MESSAGE_NOTIFICATION_ID_SPECIAL = 702456;
    private static final String KEY_NOTIFICATION_CLICK = "notification";
    private static final String CHANNEL_ID = "googlehome_CH_001";
    private static final String CHANNEL_NAME = "push_notification";
    private static final String CHANNEL_DESC = "coming notification from server.";

    static void showNotification(Context context, CharSequence title, CharSequence description, Uri sound, boolean isSpecial, Intent openLinkIntent) {

        openLinkIntent.putExtra(KEY_NOTIFICATION_CLICK, true);
        openLinkIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        int iUniqueId = (int) (System.currentTimeMillis() & 0xfffffff);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, iUniqueId,
                openLinkIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        showNotificationUsingPendingIntent(context, title, description, sound, pendingIntent, isSpecial);
    }

    public static void gotoMarket(Context context, CharSequence title, CharSequence description, CharSequence url, CharSequence id, Uri sound) {
        Intent goToMarket = null;
        Uri uri = Uri.parse("market://details?id=" + url);

        try {
            goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context,
                    "Couldn't launch the market", Toast.LENGTH_LONG)
                    .show();
        }

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        goToMarket,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        showNotificationUsingPendingIntent(context, title, description, sound, resultPendingIntent, true);
    }

    private static void showNotificationUsingPendingIntent(Context context, CharSequence title, CharSequence description, Uri sound, PendingIntent resultPendingIntent, boolean isSpecial) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = null;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            mChannel.setDescription(CHANNEL_DESC);

            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);

            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(mChannel);
            }
        }

        Builder mBuilder;
        mBuilder =
                new Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setTicker(title)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                R.mipmap.ic_launcher))
                        .setContentTitle(title).setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(description)).setContentText(description).setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setWhen(System.currentTimeMillis()).setAutoCancel(true);
        if (sound != null) mBuilder.setSound(sound);
        mBuilder.setChannelId(CHANNEL_ID);
        mBuilder.setContentIntent(resultPendingIntent);
        try {
            if (mNotificationManager == null) return;
            if (isSpecial) {
                mNotificationManager.notify(MESSAGE_NOTIFICATION_ID_SPECIAL, mBuilder.build());
            } else mNotificationManager.notify(MESSAGE_NOTIFICATION_ID_SPECIAL, mBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancel(MESSAGE_NOTIFICATION_ID_REGULAR);
            notificationManager.cancel(MESSAGE_NOTIFICATION_ID_SPECIAL);
        }

    }
}
