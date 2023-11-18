package com.example.testtask.data.repository

import com.example.testtask.data.database.DatabaseDao
import com.example.testtask.data.network.API
import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.entities.UserEntity
import com.example.testtask.domain.mappers.toRepositoryDO
import com.example.testtask.domain.mappers.toRepositoryEntity
import com.example.testtask.domain.mappers.toUserDo
import com.example.testtask.domain.mappers.toUserEntity
import com.example.testtask.domain.repository.AppRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AppRepositoryImpl(
    private val api: API,
    private val database: DatabaseDao,
    private val ioDispatcher: CoroutineDispatcher
) : AppRepository {
    override suspend fun getUsersFromServer(): List<UserEntity> = withContext(ioDispatcher) {
        api.getUsers().map { it.toUserEntity() }
    }

    override suspend fun getUserReposFromServer(username: String): List<RepositoryEntity> =
        withContext(ioDispatcher) {
            api.getUserRepos(username).map { it.toRepositoryEntity() }
        }

    override suspend fun saveUsersToDatabase(users: List<UserEntity>) {
        withContext(ioDispatcher) {
            database.saveUsers(users.map { it.toUserDo() })
        }
    }

    override suspend fun saveReposToDatabase(repos: List<RepositoryEntity>, ownerId: Int) {
        withContext(ioDispatcher) {
            database.saveUserRepos(repos.map { it.toRepositoryDO(ownerId) })
        }
    }

    override suspend fun getUsersFromDatabase(): List<UserEntity> = withContext(ioDispatcher) {
        database.getUsers().map { it.toUserEntity() }
    }

    override suspend fun getUserReposFromDatabase(ownerId: Int?): List<RepositoryEntity> =
        withContext(ioDispatcher) {
            database.getUserRepos(ownerId).map { it.toRepositoryEntity() }
        }
}