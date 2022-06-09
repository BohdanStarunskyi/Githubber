package com.example.testtask

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testtask.ui.main.UserDatabaseOperations
import com.example.testtask.utils.ACTION
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mHandler = MyBroadcast()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(mHandler, IntentFilter(ACTION))
    }

    class MyBroadcast: BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
            val userId = p1!!.getStringExtra("userId")!!
            val changesCount = p1.getIntExtra("changesCount", 0)
            UserDatabaseOperations().updateUserChanges(userId, changesCount)
        }

    }

}

