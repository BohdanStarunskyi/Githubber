package com.example.testtask.data.repository

import com.example.testtask.common.annotations.IoDispatcher
import com.example.testtask.data.database.DatabaseDao
import com.example.testtask.data.network.API
import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.entities.UserEntity
import com.example.testtask.domain.mappers.*
import com.example.testtask.domain.repository.AppRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: API,
    private val database: DatabaseDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AppRepository {
    override suspend fun getUsersFromServer() = withContext(ioDispatcher) {
        api.getUsers().body().toUserEntityArray()
    }

    override suspend fun getUserReposFromServer(username: String) = withContext(ioDispatcher) {
        api.getUserRepos(username).body().toRepositoryEntityArray()
    }

    override suspend fun saveUsersToDatabase(users: ArrayList<UserEntity>) {
        withContext(ioDispatcher) {
            database.saveUsers(users.toUserDoList())
        }
    }

    override suspend fun saveReposToDatabase(repos: ArrayList<RepositoryEntity>, ownerId: Int) {
        withContext(ioDispatcher) {
            database.saveUserRepos(repos.toRepositoryDoList(ownerId))
        }
    }

    override suspend fun getUsersFromDatabase() = withContext(ioDispatcher) {
        database.getUsers().toUserEntityArrayList()
    }

    override suspend fun getUserReposFromDatabase(ownerId: Int?) = withContext(ioDispatcher) {
        database.getUserRepos(ownerId).toRepositoryEntityArrayList()
    }

}