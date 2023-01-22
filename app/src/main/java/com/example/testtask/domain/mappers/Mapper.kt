package com.example.testtask.domain.mappers

import com.example.testtask.data.network.dto.ReposResponse
import com.example.testtask.data.network.dto.UsersResponse
import com.example.testtask.domain.database_objects.RepositoryDO
import com.example.testtask.domain.database_objects.UserDO
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
                language = it.language,
                repoUrl = it.url
            )
        )
    }
    return array
}

fun ArrayList<UserEntity>?.toUserDoList(): List<UserDO> {
    val array = arrayListOf<UserDO>()
    this?.forEach {
        array.add(
            UserDO(
                id = it.id ?: 0,
                username = it.username,
                avatarUrl = it.avatarUrl
            )
        )
    }
    return array
}

fun ArrayList<RepositoryEntity>?.toRepositoryDoList(ownerId: Int): List<RepositoryDO> {
    val array = arrayListOf<RepositoryDO>()
    this?.forEach {
        array.add(
            RepositoryDO(
                id = it.id ?: 0,
                ownerId = ownerId,
                name = it.name,
                updatedAt = it.updatedAt,
                stargazersCount = it.stargazersCount,
                language = it.language,
                repoUrl = it.repoUrl
            )
        )
    }
    return array
}

fun List<UserDO>?.toUserEntityArrayList(): ArrayList<UserEntity> {
    val array = arrayListOf<UserEntity>()
    this?.forEach {
        array.add(
            UserEntity(
                id = it.id,
                username = it.username,
                avatarUrl = it.avatarUrl
            )
        )
    }
    return array
}

fun List<RepositoryDO>?.toRepositoryEntityArrayList(): ArrayList<RepositoryEntity> {
    val array = arrayListOf<RepositoryEntity>()
    this?.forEach {
        array.add(
            RepositoryEntity(
                id = it.id,
                name = it.name,
                updatedAt = it.updatedAt,
                stargazersCount = it.stargazersCount,
                language = it.language,
                repoUrl = it.repoUrl
            )
        )
    }
    return array
}