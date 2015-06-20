package se.shapeapp.notifylater;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by nille on 20/06/15.
 */

public class AlarmReciever extends BroadcastReceiver {

    @SuppressWarnings("null")
    public void onReceive(Context context, Intent intent) {
        context.startService(intent);

        PendingIntent Sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        NotificationManager manager = (NotificationManager) context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(context)
                .setContentTitle("Notification recieved.")
                .setContentText("Hello world!").setSmallIcon(R.drawable.logga)
                .setContentIntent(Sender)
                .addAction(R.drawable.logga, "Call", Sender)
                .addAction(R.drawable.logga, "More", Sender)
                .addAction(R.drawable.logga, "And more", Sender).build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }

}
