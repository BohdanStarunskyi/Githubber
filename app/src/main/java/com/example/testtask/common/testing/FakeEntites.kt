package com.example.testtask.common.testing

import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.entities.UserEntity
import kotlin.random.Random

fun getFakeUsers(count: Int): List<UserEntity> {
    val users = arrayListOf<UserEntity>()
    for (i in 0..count) {
        users.add(UserEntity(id = Random.nextInt(), username = "John", avatarUrl = null))
    }
    return users
}

fun getFakeRepos(count: Int): List<RepositoryEntity> {
    val repos = arrayListOf<RepositoryEntity>()
    for (i in 0..count) {
        repos.add(
            RepositoryEntity(
                id = Random.nextInt(),
                name = "random repo",
                updatedAt = "12.12.2023",
                stargazersCount = i,
                language = "Kotlin",
                repoUrl = null
            )
        )
    }
    return repos
}

