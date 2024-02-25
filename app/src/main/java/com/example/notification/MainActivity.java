package com.example.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 100;
    private static final int REQUEST_CODE = 10;
    private static final String CHANNEL_ID = "MyNotificationChannel";
    private Notification noty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.aiml, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap large = bitmapDrawable.getBitmap();

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//        Intent notifyIntent = new Intent(getApplicationContext(), MainActivity.class);
//        notifyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Notification.BigPictureStyle  bigstyle = new Notification.BigPictureStyle()
                .bigPicture(((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.download, null)).getBitmap())
                .bigLargeIcon(large)
                .setBigContentTitle("Image by raman ")
                .setSummaryText("funny ");


        try
        {
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                noty = new Notification.Builder(this)
                        .setContentText("New Message hai kya")
                        .setContentTitle("mja aaya ")
                        .setLargeIcon(large)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setStyle(bigstyle)
                        .setAutoCancel(false)
                        .setChannelId(CHANNEL_ID)
                        .build();

                nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "NEW CHANNEL", NotificationManager.IMPORTANCE_HIGH));

            }
            else
            {
                noty = new Notification.Builder(this)
                        .setContentText("New Message hai kya")
                        .setContentTitle("mja aaya ")
                        .setAutoCancel(false)
                        .setLargeIcon(large)
//                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setChannelId(CHANNEL_ID)
                        .setStyle(bigstyle)
                        .build();
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Failed to create PendingIntent", Toast.LENGTH_SHORT).show();
        }

        if (noty != null) {
            nm.notify(NOTIFICATION_ID, noty);
        }
    }
}
