package com.example.testtask.data.repository

import com.example.testtask.data.database.DatabaseDao
import com.example.testtask.data.network.API
import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.entities.UserEntity
import com.example.testtask.domain.mappers.*
import com.example.testtask.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: API,
    private val database: DatabaseDao
) : AppRepository {
    override suspend fun getUsersFromServer() = api.getUsers().body().toUserEntityArray()

    override suspend fun getUserReposFromServer(username: String) =
        api.getUserRepos(username).body().toRepositoryEntityArray()

    override suspend fun saveUsersToDatabase(users: ArrayList<UserEntity>) {
        database.saveUsers(users.toUserDoList())
    }

    override suspend fun saveReposToDatabase(repos: ArrayList<RepositoryEntity>, ownerId: Int) {
        database.saveUserRepos(repos.toRepositoryDoList(ownerId))
    }

    override suspend fun getUsersFromDatabase() = database.getUsers().toUserEntityArrayList()


    override suspend fun getUserReposFromDatabase(ownerId: Int?) =
        database.getUserRepos(ownerId).toRepositoryEntityArrayList()

}