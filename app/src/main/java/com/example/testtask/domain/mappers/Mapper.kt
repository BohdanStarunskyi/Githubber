package com.example.testtask.domain.mappers

import com.example.testtask.data.network.dto.ReposResponse
import com.example.testtask.data.network.dto.UsersResponse
import com.example.testtask.domain.database_objects.RepositoryDO
import com.example.testtask.domain.database_objects.UserDO
import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.entities.UserEntity

fun UsersResponse.toUserEntity(): UserEntity = UserEntity(this.id, this.login, this.avatarUrl)
fun ReposResponse.toRepositoryEntity(): RepositoryEntity = RepositoryEntity(
    id = this.id,
    name = this.name,
    updatedAt = this.updatedAt,
    stargazersCount = this.stargazersCount,
    language = this.language,
    repoUrl = this.htmlUrl
)

fun UserEntity.toUserDo(): UserDO = UserDO(
    id = this.id ?: 0,
    username = this.username,
    avatarUrl = this.avatarUrl
)

fun RepositoryEntity.toRepositoryDO(ownerId: Int): RepositoryDO = RepositoryDO(
    id = this.id ?: 0,
    ownerId = ownerId,
    name = this.name,
    updatedAt = this.updatedAt,
    stargazersCount = this.stargazersCount,
    language = this.language,
    repoUrl = this.repoUrl
)

fun UserDO.toUserEntity(): UserEntity = UserEntity(
    id = this.id,
    username = this.username,
    avatarUrl = this.avatarUrl
)

fun RepositoryDO.toRepositoryEntity(): RepositoryEntity = RepositoryEntity(
    id = this.id,
    name = this.name,
    updatedAt = this.updatedAt,
    stargazersCount = this.stargazersCount,
    language = this.language,
    repoUrl = this.repoUrl
)