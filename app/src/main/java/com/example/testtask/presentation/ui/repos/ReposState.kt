package com.example.testtask.presentation.ui.repos

import com.example.testtask.domain.entities.RepositoryEntity

data class ReposState(
    var isLoading: Boolean = false,
    val repos: List<RepositoryEntity>? = null,
    val error: String? = null
)
