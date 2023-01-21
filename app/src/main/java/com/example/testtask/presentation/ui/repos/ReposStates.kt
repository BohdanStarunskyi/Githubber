package com.example.testtask.presentation.ui.repos

import com.example.testtask.domain.entities.RepositoryEntity

sealed class ReposStates {
    data class Loading(var shouldLoaderBeVisible: Boolean): ReposStates()
    data class Success(var repos: ArrayList<RepositoryEntity>): ReposStates()
    data class Error(var error: Throwable): ReposStates()
}
