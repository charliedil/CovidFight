package com.example.covidfight;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

public class Reminder extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    NotificationManager notificationManager = (NotificationManager)
        context.getSystemService(Context.NOTIFICATION_SERVICE);
    Intent repeatingIntent = new Intent(context, MainActivity.class);
    repeatingIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent pendingIntent = PendingIntent.getActivity(context,
        100, repeatingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "maskNotify")
        .setContentIntent(pendingIntent)
        .setSmallIcon(R.drawable.logo)
        .setContentTitle("Daily Mask Reminder")
        .setContentText("Don't forget to grab your mask!")
        //.setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true);

    // NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

    notificationManager.notify(100, builder.build());
  }
}
