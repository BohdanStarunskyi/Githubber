package com.example.testtask

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application(){
    override fun onCreate() {
        super.onCreate()
//        Realm.init(this)
//        val configuration = RealmConfiguration.Builder()
//            .deleteRealmIfMigrationNeeded()
//            .schemaVersion(1)
//            .build()
//
//        Realm.setDefaultConfiguration(configuration)
    }
}