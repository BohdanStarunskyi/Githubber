package com.example.testtask.domain.mappers

import com.example.testtask.data.network.dto.ReposResponse
import com.example.testtask.data.network.dto.UsersResponse
import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.entities.UserEntity

fun ArrayList<UsersResponse>?.toUserEntityArray(): ArrayList<UserEntity> {
    val array = arrayListOf<UserEntity>()
    this?.forEach {
        array.add(UserEntity(it.id, it.login, it.avatar_url))
    }
    return array
}

fun ArrayList<ReposResponse>?.toRepositoryEntityArray(): ArrayList<RepositoryEntity> {
    val array = arrayListOf<RepositoryEntity>()
    this?.forEach {
        array.add(
            RepositoryEntity(
                id = it.id,
                name = it.name,
                updatedAt = it.updated_at,
                stargazersCount = it.stargazers_count,
                language = it.language
            )
        )
    }
    return array
}