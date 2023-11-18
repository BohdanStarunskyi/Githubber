package com.example.testtask.di

import com.example.testtask.domain.repository.AppRepository
import com.example.testtask.domain.usecase.GetReposFromDatabaseUseCase
import com.example.testtask.domain.usecase.GetReposFromServerUseCase
import com.example.testtask.domain.usecase.GetUsersFromDatabaseUseCase
import com.example.testtask.domain.usecase.GetUsersFromServerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideReposFromDatabaseUseCase(
        repository: AppRepository
    ) = GetReposFromDatabaseUseCase(repository)

    @Provides
    @Singleton
    fun provideReposFromServerUseCase(
        repository: AppRepository
    ) = GetReposFromServerUseCase(repository)

    @Provides
    @Singleton
    fun provideUsersFromDatabaseUseCase(
        repository: AppRepository
    ) = GetUsersFromDatabaseUseCase(repository)

    @Provides
    @Singleton
    fun provideUsersFromServerUseCase(
        repository: AppRepository
    ) = GetUsersFromServerUseCase(repository)
}