package com.example.admin.materialui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class NotificationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

    }

    public void smallNotification(View view) {
        Intent resultIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
        Notification notification = mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Notify")
                .setWhen(0)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle("Notfication")
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setContentText("Hi smal notification")
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }

    public void bigNotification(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.BigPictureStyle mBig = new NotificationCompat.BigPictureStyle();
        mBig.setBigContentTitle("Title");
        mBig.setSummaryText("Hello Big STYLE DESCRIPTION");
        mBig.bigPicture(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher));
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        Notification notification;
        notification = mBuilder.setSmallIcon(R.mipmap.ic_launcher).setTicker("hi").setWhen(0)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle("hi")
                .setStyle(mBig)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setContentText("hello big pic")
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);

    }

    public void customNotification(View view) {
        Intent resultIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
        Notification notification = mBuilder.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Notify")
                .setWhen(0)
                .setAutoCancel(true)
                .setContent(getRemoteViews())
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

    private RemoteViews getRemoteViews() {
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.custom_notif);
        remoteViews.setImageViewResource(R.id.imagenotileft, R.mipmap.ic_launcher);
        remoteViews.setTextViewText(R.id.title, "title");
        remoteViews.setTextViewText(R.id.text, "hai hello from cstom title");
        return remoteViews;
    }

}
