package com.inc.vr.corp.app.mobilelearning_krisisenergi.FB;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.inc.vr.corp.app.mobilelearning_krisisenergi.MainActivity;
import com.inc.vr.corp.app.mobilelearning_krisisenergi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.Channel;
import java.util.Map;

public class FirebaseMessageReceiver extends FirebaseMessagingService {
    String TAG = "FirebaseMessagingService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Log.d(TAG, "msg received: "+remoteMessage.getFrom());
        if(remoteMessage.getNotification() != null){
            //showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            String channelId = "Default";
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true)
                    .setContentIntent(pendingIntent);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "Default channel",
                        NotificationManager.IMPORTANCE_DEFAULT
                );
                manager.createNotificationChannel(channel);
            }
            manager.notify(0, builder.build());
        }
    }

    public void onNewToken(String token){
        Log.d("TAG", "Refreshed token: "+token);
    }
    private void showNotification(String title, String body){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT
        );
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());
    }
}
