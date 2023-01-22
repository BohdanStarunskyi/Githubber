package com.example.testtask.domain.repository

import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.entities.UserEntity

interface AppRepository {
    suspend fun getUsersFromServer(): ArrayList<UserEntity>
    suspend fun getUserReposFromServer(username: String): ArrayList<RepositoryEntity>
    suspend fun saveUsersToDatabase(users: ArrayList<UserEntity>)
    suspend fun saveReposToDatabase(repos: ArrayList<RepositoryEntity>, ownerId: Int)
    suspend fun getUsersFromDatabase():ArrayList<UserEntity>
    suspend fun getUserReposFromDatabase(ownerId: Int?):ArrayList<RepositoryEntity>
}