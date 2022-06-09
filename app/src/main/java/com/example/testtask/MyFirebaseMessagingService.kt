package com.example.testtask

import android.content.Intent
import android.util.Log
import com.example.testtask.utils.ACTION
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
            val userId = message.data["userId"].toString()
            val changesCount = message.data["changesCount"]!!.toInt()
            val intent = Intent(ACTION)
            intent.putExtra("userId", userId)
            intent.putExtra("changesCount", changesCount)
            sendBroadcast(intent)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("DemoFirebaseService", "got new token: $token")
    }


}