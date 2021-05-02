package com.example.medrecord.appointment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID = "CHANNEL_SAMPLE";

    @Override
    public void onReceive(Context context, Intent intent) {

        // Get id & message
        int notificationId = intent.getIntExtra("notificationId", 0);
        String name = intent.getStringExtra("message");
        String time = intent.getStringExtra("time");
        String location = intent.getStringExtra("location");
        String notes = intent.getStringExtra("notes");
        String reminder_message = "You have a " + name + " appointment at " + time + " at " +
                location + ". Do take note that " + notes;

        // Call MainActivity when notification is tapped.
        Intent mainIntent = new Intent(context, MakeAppointment.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

        Intent delIntent = new Intent(context, MakeAppointment.class);
        delIntent.putExtra("ID", notificationId > 0);
        if (notificationId > 0) {
            AppointmentDatabase sDB = new AppointmentDatabase(context);
            sDB.deleteNote(notificationId);
            Toast.makeText(context.getApplicationContext(), "Note Deleted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context.getApplicationContext(), "Note deletion unsuccessful", Toast.LENGTH_SHORT).show();
        }
        //


        // NotificationManager
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // For API 26 and above
            CharSequence channelName = "My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            notificationManager.createNotificationChannel(channel);
        }

        // Prepare Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("APPOINTMENT REMINDER")
                .setContentText(reminder_message)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        // Notify
        notificationManager.notify(notificationId, builder.build());
    }
}
