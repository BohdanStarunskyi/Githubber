package com.example.testtask

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.ui.main.MainViewModel
import com.example.testtask.ui.main.database.UserDatabaseOperations
import com.example.testtask.utils.ACTION
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mHandler: MyBroadcast
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mHandler = MyBroadcast(mainViewModel)
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(mHandler, IntentFilter(ACTION))
    }

    class MyBroadcast(private val mainViewModel: MainViewModel) : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val userId = p1!!.getStringExtra("userId")!!
            val changesCount = p1.getIntExtra("changesCount", 0)
            UserDatabaseOperations().updateUserChanges(userId, changesCount)
            mainViewModel.requestUsersFromDatabase()
        }

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(mHandler)
    }

}

