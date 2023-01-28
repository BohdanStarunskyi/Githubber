package com.example.testtask.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testtask.common.Constants
import com.example.testtask.domain.database_objects.RepositoryDO
import com.example.testtask.domain.database_objects.UserDO

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUsers(users: List<UserDO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserRepos(repos: List<RepositoryDO>)

    @Query("SELECT * FROM ${Constants.USERS_TABLE_NAME}")
    suspend fun getUsers(): List<UserDO>

    @Query("SELECT * FROM ${Constants.REPOSITORIES_TABLE_NAME} WHERE ownerId = :ownerId ORDER BY name")
    suspend fun getUserRepos(ownerId: Int?): List<RepositoryDO>
}