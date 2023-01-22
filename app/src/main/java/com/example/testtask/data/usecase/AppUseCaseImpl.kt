package com.example.testtask.data.usecase

import com.example.testtask.data.repository.AppRepositoryImpl
import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.entities.UserEntity
import com.example.testtask.domain.usecase.AppUseCase
import javax.inject.Inject

class AppUseCaseImpl @Inject constructor(private val repository: AppRepositoryImpl) : AppUseCase {
    override suspend fun getUsersFromServer(): ArrayList<UserEntity> {
        val users = repository.getUsersFromServer()
        if(users.isNotEmpty())
            repository.saveUsersToDatabase(users)
        return users
    }

    override suspend fun getReposFromServer(
        username: String,
        ownerId: Int
    ): ArrayList<RepositoryEntity> {
        val repos = repository.getUserReposFromServer(username)
        if(repos.isNotEmpty())
            repository.saveReposToDatabase(repos, ownerId)
        return repos
    }

    override suspend fun getUsersFromDatabase() = repository.getUsersFromDatabase()

    override suspend fun getReposFromDatabase(ownerId: Int) =
        repository.getUserReposFromDatabase(ownerId)
}