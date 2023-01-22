package com.example.testtask.domain.usecase

import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.entities.UserEntity

interface AppUseCase {
    suspend fun getUsersFromServer(): ArrayList<UserEntity>
    suspend fun getReposFromServer(username: String, ownerId: Int): ArrayList<RepositoryEntity>
    suspend fun getUsersFromDatabase(): ArrayList<UserEntity>
    suspend fun getReposFromDatabase(ownerId: Int): ArrayList<RepositoryEntity>
}