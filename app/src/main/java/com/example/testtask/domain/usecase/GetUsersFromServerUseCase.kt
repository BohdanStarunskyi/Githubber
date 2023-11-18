package com.example.testtask.domain.usecase

import com.example.testtask.domain.entities.UserEntity
import com.example.testtask.domain.repository.AppRepository

class GetUsersFromServerUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<UserEntity> {
        val users = repository.getUsersFromServer()
        repository.saveUsersToDatabase(users)
        return users
    }
}