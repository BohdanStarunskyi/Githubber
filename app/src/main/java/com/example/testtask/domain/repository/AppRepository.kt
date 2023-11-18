package com.example.testtask.domain.repository

import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.entities.UserEntity

interface AppRepository {
    suspend fun getUsersFromServer(): List<UserEntity>
    suspend fun getUserReposFromServer(username: String): List<RepositoryEntity>
    suspend fun saveUsersToDatabase(users: List<UserEntity>)
    suspend fun saveReposToDatabase(repos: List<RepositoryEntity>, ownerId: Int)
    suspend fun getUsersFromDatabase(): List<UserEntity>
    suspend fun getUserReposFromDatabase(ownerId: Int?): List<RepositoryEntity>
}