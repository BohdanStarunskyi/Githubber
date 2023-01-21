package com.example.testtask.data.usecase

import com.example.testtask.data.repository.AppRepositoryImpl
import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.entities.UserEntity
import com.example.testtask.domain.usecase.AppUseCase
import javax.inject.Inject

class AppUseCaseImpl @Inject constructor(private val repository: AppRepositoryImpl) : AppUseCase {
    override suspend fun getUsersFromServer(): ArrayList<UserEntity> {
        return repository.getUsers()
    }

    override suspend fun getReposFromServer(username: String): ArrayList<RepositoryEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsersFromDatabase(): ArrayList<UserEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getReposFromDatabase(username: String): ArrayList<RepositoryEntity> {
        TODO("Not yet implemented")
    }
}