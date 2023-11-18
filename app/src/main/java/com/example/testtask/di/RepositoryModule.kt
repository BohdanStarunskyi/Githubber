package com.example.testtask.di

import com.example.testtask.common.annotations.IoDispatcher
import com.example.testtask.data.database.DatabaseDao
import com.example.testtask.data.network.API
import com.example.testtask.data.repository.AppRepositoryImpl
import com.example.testtask.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAppRepository(
        api: API,
        database: DatabaseDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): AppRepository = AppRepositoryImpl(api, database, ioDispatcher)
}