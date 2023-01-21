package com.example.testtask.presentation.ui.users

import com.example.testtask.domain.entities.UserEntity

sealed class UsersStates {
    data class Loading(var shouldLoaderBeVisible: Boolean) : UsersStates()
    data class Success(var users: ArrayList<UserEntity>) : UsersStates()
    data class Error(var error: Throwable) : UsersStates()
}
