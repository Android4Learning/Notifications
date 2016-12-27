package com.android4learning.notifications;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import static com.android4learning.notifications.MainActivity.mNotificationCount;

public class NotificationActivity extends AppCompatActivity {

    protected NotificationManager mNotificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        mNotificationCount = 1;

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(MainActivity.NOTIFICATION_HEADS_UP_ID);



        mNotificationManager.cancel(MainActivity.NOTIFICATION_DIRECT_REPLY_ID);
        Intent intent = this.getIntent();

        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);

        if (remoteInput != null) {
            TextView directReplyMessage = (TextView) findViewById(R.id.direct_reply_message);

            String inputString = remoteInput.getString("replyKey");

            directReplyMessage.setText("Direct Reply Message: " + inputString);
        }
    }
}
