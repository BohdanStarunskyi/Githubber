package com.example.testtask

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random


private const val CHANNEL_ID = "my_channel"

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        val from = message.from
        val data: Map<*, *> = message.data
        Log.e("Message", "Could not parse malformed JSON: \"$data\"")

        showNotification(message)
    }

    override fun onNewToken(token: String) {

        Toast.makeText(applicationContext, "Token $token", Toast.LENGTH_LONG).show()
    }



    //    override fun onMessageReceived(message: RemoteMessage) {
//        if(message.data.isNotEmpty()) {
//            val userId = message.data["userId"].toString()
//            val changesCount = message.data["changesCount"]!!.toInt()
//            val intent = Intent(ACTION)
//            intent.putExtra("userId", userId)
//            intent.putExtra("changesCount", changesCount)
//            sendBroadcast(intent)
//        }
//        showNotification(message)
//    }
//
    private fun showNotification(message: RemoteMessage){
        val intent = Intent(this, MainActivity::class.java)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = Random.nextInt()

        createNotificationChannel(notificationManager)

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_star)
            .setContentTitle(message.data["userId"].toString())
            .setContentText(message.data["changesCount"]!!.toString())
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(notificationId, notification)
    }

    private fun createNotificationChannel(notificationManager: NotificationManager){
        val channelName = "channelName"
        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
            description = "My channel description"
            enableLights(true)
        }
        notificationManager.createNotificationChannel(channel)
    }

}

