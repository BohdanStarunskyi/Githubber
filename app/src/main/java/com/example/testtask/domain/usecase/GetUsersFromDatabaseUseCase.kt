package com.example.testtask.domain.usecase

import com.example.testtask.domain.entities.UserEntity
import com.example.testtask.domain.repository.AppRepository

class GetUsersFromDatabaseUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<UserEntity> = repository.getUsersFromDatabase()
}