package com.example.testtask.domain.usecase

import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.repository.AppRepository

class GetReposFromServerUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(username: String, ownerId: Int): List<RepositoryEntity> {
        val repos = repository.getUserReposFromServer(username)
        repository.saveReposToDatabase(repos, ownerId)
        return repos
    }

}