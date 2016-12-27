package com.android4learning.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String  GROUP_KEY = "myBundledNotificationsKey";
    public static final int     NOTIFICATION_STANDARD_ID = 10;
    public static final int     NOTIFICATION_VIBRATION_SOUND_LED_ID = 11;
    public static final int     NOTIFICATION_HEADS_UP_ID = 12;
    public static final int     NOTIFICATION_INBOX_STYLE_ID = 13;
    public static final int     NOTIFICATION_CUSTOM_LAYOUT_ID = 14;
    public static final int     NOTIFICATION_DIRECT_REPLY_ID = 15;
    public static final int     NOTIFICATION_MESSAGING_STYLE_ID = 16;

    public static final int     REQUEST_CODE_STANDARD_NOTIFICATION = 10001;

    protected static int        mNotificationCount;

    protected NotificationManager mNotificationManager;

    protected Button mShowStandardNotificationButton;
    protected Button mCancelStandardNotificationButton;
    protected Button mVibrationSoundLEDNotificationButton;
    protected Button mHeadsUpNotificationButton;
    protected Button mInboxStyleNotificationButton;
    protected Button mCustomLayoutNotificationButton;
    protected Button mBundledNotificationButton;
    protected Button mDirectReplyNotificationButton;
    protected Button mMessagingStyleNotificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set counter initial value
        mNotificationCount = 1;

        //Define notification manager object from system service
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mShowStandardNotificationButton = (Button) findViewById(R.id.notification_show);
        mShowStandardNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showStandardNotification();
            }
        });

        mCancelStandardNotificationButton = (Button) findViewById(R.id.notification_standard_cancel);
        mCancelStandardNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mNotificationManager.cancel(NOTIFICATION_STANDARD_ID);
                //Reset counter when notification is canceled
                mNotificationCount = 1;
            }
        });


        mVibrationSoundLEDNotificationButton = (Button) findViewById(R.id.notification_vibration_sound);
        mVibrationSoundLEDNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showVibrationsSoundLEDNotification();
            }
        });

        mHeadsUpNotificationButton = (Button) findViewById(R.id.notification_heads_up);
        mHeadsUpNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showHeadsUpNotification();
            }
        });

        mInboxStyleNotificationButton = (Button) findViewById(R.id.notification_inbox_style);
        mInboxStyleNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showNotificationWithInboxStyle();
            }
        });

        mCustomLayoutNotificationButton = (Button) findViewById(R.id.notification_custom_layout);
        mCustomLayoutNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showNotificationWithCustomLayout();
            }
        });

        mBundledNotificationButton = (Button) findViewById(R.id.notification_bundled);
        mBundledNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This Notification feature is available on Android version N and higher
                //Check the Android version
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    showBundledNotifications();
                }
                else{
                    Toast.makeText(MainActivity.this,
                            "Sorry.. You need Android version N or higher!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        mDirectReplyNotificationButton = (Button) findViewById(R.id.notification_direct_reply);
        mDirectReplyNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This Notification feature is available on Android version N and higher
                //Check the Android version
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    showDirectReplyNotification();
                }
                else{
                    Toast.makeText(MainActivity.this,
                            "Sorry.. You need Android version N or higher!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        mMessagingStyleNotificationButton = (Button) findViewById(R.id.notification_messaging_style);
        mMessagingStyleNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This Notification feature is available on Android version N and higher
                //Check the Android version
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    showMessagingStyleNotification();
                }
                else{
                    Toast.makeText(MainActivity.this,
                            "Sorry.. You need Android version N or higher!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void showStandardNotification() {

        //Create notification builder object
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(MainActivity.this);

        //Create explicit Intent for the Activity
        Intent mIntent = new Intent(MainActivity.this, NotificationActivity.class);

        //Create TaskStackBuilder object
        TaskStackBuilder mTaskStackBuilder = TaskStackBuilder.create(MainActivity.this);
        //Add the back stack for the Intent of your Activity
        mTaskStackBuilder.addParentStack(MainActivity.class);
        //Add the Intent that will start your Activity at the top of the stack
        mTaskStackBuilder.addNextIntent(mIntent);

        //Create PendingIntent object from the TaskStackBuilder object
        PendingIntent mPendingIntent = mTaskStackBuilder
                .getPendingIntent(REQUEST_CODE_STANDARD_NOTIFICATION, PendingIntent.FLAG_UPDATE_CURRENT);

        //Encode the image into Bitmap
        Bitmap androidLargeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.uaic_logo);

        //Set the notification options
        mNotificationBuilder
                //Set Notification ticker
                .setTicker("Notification from SDBIS")
                //Set Notification title
                .setContentTitle("New notification from SDBIS")
                //Set Notification content
                .setContentText("Software Development and Business Information Systems!")
                //Set Notification small icon
                .setSmallIcon(R.drawable.notification_icon)
                //Set Notification large icon (bitmap format)
                .setLargeIcon(androidLargeIcon)
                //Set Notification PendingIntent
                .setContentIntent(mPendingIntent)
                //Set Notification auto cancel on tap action
                .setAutoCancel(true)
                //Set Notification visibility of information on lock screen
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                //Set number to right side of the Notification
                .setNumber(mNotificationCount++);

        //Create the notification from the builder
        Notification mNotification = mNotificationBuilder.build();

        //Raise the notification
        mNotificationManager.notify(NOTIFICATION_STANDARD_ID, mNotification);
    }

    private void showVibrationsSoundLEDNotification(){

        long[] vibrate = new long[] { 1000, 500, 1000, 500, 1000 };

        //Set default system ringtone for notifications
        Uri mRingtoneURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Notification initialization
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(this);
        mNotificationBuilder.setSmallIcon(R.drawable.notification_icon);
        mNotificationBuilder.setContentTitle("Notification from SDBIS");
        mNotificationBuilder.setContentText("Notification with Vibration, Sound and LED");
        //Set vibration pattern
        mNotificationBuilder.setVibrate(vibrate);
        //Set ringtone option to the notification builder
        mNotificationBuilder.setSound(mRingtoneURI);
        //Set LED indicator
        mNotificationBuilder.setLights(Color.RED, 500, 100);

        //Create notification from builder
        Notification mNotification = mNotificationBuilder.build();

        //Raise the notification
        mNotificationManager.notify(NOTIFICATION_VIBRATION_SOUND_LED_ID, mNotification);
    }

    private void showHeadsUpNotification() {

        //Create explicit Intent for the activity
        Intent mIntent = new Intent(this, NotificationActivity.class);

        //Set the intent to the pending intent
        PendingIntent mPendingIntent = PendingIntent.
                getActivity(this, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        //Create Action that will open your Activity
        NotificationCompat.Action mAction = new NotificationCompat
                .Action.Builder(R.drawable.door_icon, "ACCEPT", mPendingIntent).build();

        Bitmap androidLargeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.uaic_logo);

        //Notification initialization
        NotificationCompat.Builder myNotificationBuilder = new NotificationCompat.Builder(this);
        myNotificationBuilder.setSmallIcon(R.drawable.notification_icon);
        myNotificationBuilder.setLargeIcon(androidLargeIcon);
        myNotificationBuilder.setContentTitle("Urgent Notification from SDBIS");
        myNotificationBuilder.setContentText("You have a meeting today at 15:00!");
        //Set system default setting such as ringtone, vibration
        myNotificationBuilder.setDefaults(Notification.DEFAULT_ALL);
        //Set the priority High(or Max) to get heads-up notification
        myNotificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        //Notification will be erased by tapping on it
        myNotificationBuilder.setAutoCancel(true);
        //Set the action
        myNotificationBuilder.addAction(mAction);

        //Create notification from builder
        Notification mNotification = myNotificationBuilder.build();

        //Raise the notification
        mNotificationManager.notify(NOTIFICATION_HEADS_UP_ID, mNotification);
    }

    private void showNotificationWithInboxStyle() {

        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(MainActivity.this);

        Intent mIntent = new Intent(MainActivity.this, NotificationActivity.class);

        TaskStackBuilder mTaskStackBuilder = TaskStackBuilder.create(MainActivity.this);
        mTaskStackBuilder.addParentStack(MainActivity.class);
        mTaskStackBuilder.addNextIntent(mIntent);

        PendingIntent mPendingIntent = mTaskStackBuilder
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap androidLargeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.uaic_logo);

        //Create the InboxStyle object and setup the properties
        NotificationCompat.InboxStyle mInboxStyle = new NotificationCompat.InboxStyle();
        //Set the title
        mInboxStyle.setBigContentTitle("SDBIS courses schedule updated!");
        //Add lines
        mInboxStyle.addLine("Android Development | 10:00 | Mon");
        mInboxStyle.addLine("Java Programming | 12:00 | Mon");
        mInboxStyle.addLine("Design Patterns | 14:00 | Tue");
        mInboxStyle.addLine("DataBase Administration | 16:00 | Wen");
        mInboxStyle.addLine("Business Intelligence | 18:00 | Wen");
        //Set notification summary
        mInboxStyle.setSummaryText("Total: 5 updated courses");

        mNotificationBuilder
                .setTicker("SDBIS")
                .setContentTitle("SDBIS courses schedule updated!")
                .setContentText("Check new courses from SDBIS!")
                .setSmallIcon(R.drawable.notification_icon)
                .setLargeIcon(androidLargeIcon)
                .setContentIntent(mPendingIntent)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                //Set the InboxStyle object with applied options to the notification builder
                .setStyle(mInboxStyle);

        Notification mNotification = mNotificationBuilder.build();

        mNotificationManager.notify(NOTIFICATION_INBOX_STYLE_ID, mNotification);
    }

    private void showNotificationWithCustomLayout() {

        Intent mIntent = new Intent(this, NotificationActivity.class);

        PendingIntent mPendingIntent = PendingIntent.getActivity(this, 0, mIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder mNotificationBuilder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.bell_icon)
                .setTicker("SDBIS")
                .setAutoCancel(true)
                .setContentIntent(mPendingIntent);


        //RemoteViews binds custom layouts to Notification
        RemoteViews mRemoteViews = new RemoteViews(getPackageName(),
                R.layout.notification_custom_layout);

        // Set RemoteViews into Notification, Android system below N version
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mNotificationBuilder.setContent(mRemoteViews);
        }

        // Set RemoteViews into Notification, Android system N version and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mNotificationBuilder.setCustomContentView(mRemoteViews);
            //Set decoration (header) to custom layout
            mNotificationBuilder.setStyle(new Notification.DecoratedCustomViewStyle());
        }

        //Set resource to ImageView element from custom layout
        mRemoteViews.setImageViewResource(R.id.img_uaic_left, R.drawable.uaic_logo);
        mRemoteViews.setImageViewResource(R.id.img_uaic_right, R.drawable.uaic_logo);

        //Set text to TextView element from custom layout
        mRemoteViews.setTextViewText(R.id.uaic_title, "Software Development\nand\nBusiness Information Systems");

        //Raise the notification with Notification Manager
        mNotificationManager.notify(NOTIFICATION_CUSTOM_LAYOUT_ID, mNotificationBuilder.build());
    }

    private void showBundledNotifications() {

        Bitmap UAIClogo = BitmapFactory.decodeResource(getResources(), R.drawable.uaic_logo);

        //Build notification summary (it will be display like header of notification)
        NotificationCompat.Builder mNotificationSummaryBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bell_icon)
                //Set group summary option
                .setGroupSummary(true)
                //Set group identification key
                .setGroup(GROUP_KEY);

        //Create first notification
        NotificationCompat.Builder mNotificationOneBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bell_icon)
                .setContentTitle("Android Development")
                .setContentText("Test 1 score: 10")
                .setLargeIcon(UAIClogo)
                .setGroup(GROUP_KEY);

        //Create second notification
        NotificationCompat.Builder mNotificationTwoBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bell_icon)
                .setContentTitle("Java Programming")
                .setContentText("Project 2 score: 10")
                .setLargeIcon(UAIClogo)
                .setGroup(GROUP_KEY);

        //Create third notification
        NotificationCompat.Builder mNotificationThreeBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bell_icon)
                .setContentTitle("Design Patterns")
                .setContentText("Presentation 1 score: 10")
                .setLargeIcon(UAIClogo)
                .setGroup(GROUP_KEY);

        //Raise notifications
        mNotificationManager.notify(0, mNotificationSummaryBuilder.build());
        mNotificationManager.notify(1, mNotificationOneBuilder.build());
        mNotificationManager.notify(2, mNotificationTwoBuilder.build());
        mNotificationManager.notify(3, mNotificationThreeBuilder.build());
    }

    public void showDirectReplyNotification() {

        //Create remote input object
        RemoteInput mRemoteInput = new RemoteInput.Builder("replyKey")
                //Set input placeholder
                .setLabel("YOUR MESSAGE")
                .build();

        //Create explicit intent for the Activity
        Intent mReplyIntent = new Intent(this, NotificationActivity.class)
                .addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);


        PendingIntent replyPendingIntent = PendingIntent.getActivity(this,
                0,
                mReplyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //Create action and add the remote input
        NotificationCompat.Action replyAction = new NotificationCompat.Action
                                                  //Direct reply text when input field in not focused
                        .Builder(R.drawable.bell_icon, "WRITE YOUR MESSAGE HERE", replyPendingIntent)
                        .addRemoteInput(mRemoteInput)
                        .build();

        NotificationCompat.Builder replyNotificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bell_icon)
                .setContentTitle("SDBIS support assistant")
                .setContentText("We are always ready to help you!")
                .addAction(replyAction)
                .setAutoCancel(true);

        mNotificationManager.notify(NOTIFICATION_DIRECT_REPLY_ID, replyNotificationBuilder.build());
    }

    public void showMessagingStyleNotification(){

        //Create notification builder object
        NotificationCompat.Builder mNotificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.chat_icon)
                //Create notification messaging style object.   Set your chat name
                .setStyle(new NotificationCompat.MessagingStyle("Lilian Tofan")
                        //Set title of the conversation
                        .setConversationTitle("Android4Learning Team")
                        //Set message:          text                  number   user
                        .addMessage("This app is awesome, isn't it?",       1, null) //set null for device's user
                        .addMessage("Yes, I like it very much!",            2, "Octavian Dospinescu")
                        .addMessage("Yes, Notifications are amazing!",      3, "Marian Perca"));

        //Raise notification
        mNotificationManager.notify(NOTIFICATION_MESSAGING_STYLE_ID, mNotificationBuilder.build());
    }

}
