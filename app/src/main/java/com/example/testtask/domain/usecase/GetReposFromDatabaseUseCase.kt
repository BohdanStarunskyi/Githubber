package com.example.testtask.domain.usecase

import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.repository.AppRepository

class GetReposFromDatabaseUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(ownerId: Int): List<RepositoryEntity> =
        repository.getUserReposFromDatabase(ownerId)
}