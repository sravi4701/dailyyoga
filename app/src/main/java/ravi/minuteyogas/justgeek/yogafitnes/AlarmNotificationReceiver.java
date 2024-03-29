package ravi.minuteyogas.justgeek.yogafitnes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import ravi.minuteyogas.justgeek.yogafitnes.R;

/**
 * Created by Ravi on 05-10-2017.
 */

public class AlarmNotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Intent trainingIntent = new Intent(context.getApplicationContext(), Training.class);
        PendingIntent trainingPending = PendingIntent.getActivity(context.getApplicationContext(), 0, trainingIntent, 0);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("10 Minute Yoga")
                .setContentIntent(trainingPending)
                .setContentText("Hey It's time to do yoga, Let's start")
                .setContentInfo("Info");

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}