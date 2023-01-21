package com.example.testtask.data.repository

import com.example.testtask.data.network.API
import com.example.testtask.domain.mappers.toRepositoryEntityArray
import com.example.testtask.domain.mappers.toUserEntityArray
import com.example.testtask.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val api: API) : AppRepository {
    override suspend fun getUsers() = api.getUsers().body().toUserEntityArray()

    override suspend fun getUserRepos(username: String) =
        api.getUserRepos(username).body().toRepositoryEntityArray()

}