package com.example.android_app_demo.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.android_app_demo.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class CreateNotification : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("msg received", "abcd")
        if (message.data.size > 0) {
            Log.d("Notification", "Message Data payload: " + message.data)
        }
        if (message.notification != null) {
            sendNotification(
                message.notification!!.body, message.notification!!.title
            )
        }
    }

    override fun onNewToken(token: String) {
        Log.d("Token", "Refreshed token: $token")
    }

    private fun sendNotification(messageBody: String?, title: String?) {
        val intent = Intent(this, NotificationData::class.java)
        intent.putExtra("title", title)
        intent.putExtra("body", messageBody)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_assistant_photo_24)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line...")
                )
                .setPriority(NotificationCompat.PRIORITY_HIGH)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager


        val channel = NotificationChannel(
            channelId,
            "My Notification",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(0, notificationBuilder.build())
    }
}