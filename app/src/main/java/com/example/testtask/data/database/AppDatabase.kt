package com.example.testtask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testtask.domain.database_objects.RepositoryDO
import com.example.testtask.domain.database_objects.UserDO

@Database(
    entities = [UserDO::class, RepositoryDO::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
}