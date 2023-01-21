package com.example.testtask.domain.repository

import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.entities.UserEntity

interface AppRepository {
    suspend fun getUsers(): ArrayList<UserEntity>
    suspend fun getUserRepos(username: String): ArrayList<RepositoryEntity>
}