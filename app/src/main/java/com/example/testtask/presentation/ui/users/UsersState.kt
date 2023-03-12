package com.example.testtask.presentation.ui.users

import com.example.testtask.domain.entities.UserEntity

data class UsersState(
    var isLoading: Boolean = false,
    val users: List<UserEntity>? = null,
    val error: String? = null
)
