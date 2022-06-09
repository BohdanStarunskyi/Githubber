package com.example.testtask

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotification: FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        val title = message.notification!!.title
        val text = message.notification!!.body
        val channelID = "MESSAGE"
        val channel = NotificationChannel(channelID, "Message notification", NotificationManager.IMPORTANCE_HIGH)
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification = Notification.Builder(this, channelID)
            .setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
        NotificationManagerCompat.from(this).notify(1, notification.build())
        super.onMessageReceived(message)
    }
}