package com.example.testtask.domain.entities

data class RepositoryEntity(
    var id: Int? = null,
    var name: String? = null,
    var updatedAt: String? = null,
    var stargazersCount: Int? = null,
    var language: String? = null
)
