package com.mahbub.googlehomenotification;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import static com.mahbub.googlehomenotification.MyFirebaseInstanceIDService.TOPIC_ALL_DEVICES;
import static com.mahbub.googlehomenotification.NotificationsService.TAG_KEY;

public class MainActivity extends AppCompatActivity implements ITokenGenerate {
    Logger mLogger = new Logger(this);

    TextView tokenTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyFirebaseInstanceIDService instanceIDService = new MyFirebaseInstanceIDService();

        instanceIDService.setTokenGenerate(this);

        tokenTxt = findViewById(R.id.token);
        tokenTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(tokenTxt.getText());
                Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        try {
            tokenTxt.setText(SharedPrefManager.getInstance(this).getDeviceToken());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mLogger.debug("bundle: " + bundle.toString());
            if (bundle.getString(TAG_KEY) != null) {

                String packageName = bundle.getString(TAG_KEY);
                mLogger.debug("package: " + packageName);
                //ManageNotification.showNotification(this, title, message, null, false, new Intent(this, MainActivity.class));
                //bundle must contain all info sent in "data" field of the notification
            }
        }

    }

    @Override
    public void onTokenGenerate(String token) {
        tokenTxt.setText(token);
    }
}
