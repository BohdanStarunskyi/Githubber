package com.example.testtask.presentation.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.common.Response
import com.example.testtask.domain.entities.UserEntity
import com.example.testtask.domain.usecase.GetUsersFromDatabaseUseCase
import com.example.testtask.domain.usecase.GetUsersFromServerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersFromDbUseCase: GetUsersFromDatabaseUseCase,
    private val getUsersFromServerUseCase: GetUsersFromServerUseCase
) : ViewModel() {
    private val _usersState = MutableStateFlow<Response<List<UserEntity>>>(Response.Loading())
    val usersState = _usersState.asStateFlow()

    init {
        getUsersFromDatabase()
        getUsersFromServer()
    }

    private fun getUsersFromDatabase() {
        viewModelScope.launch {
            runCatching {
                if (_usersState.value !is Response.Success) {
                    _usersState.update {
                        Response.Loading()
                    }
                }
                getUsersFromDbUseCase()
            }.onSuccess { users ->
                if (users.isNotEmpty())
                    _usersState.update { Response.Success(users) }
            }.onFailure { error ->
                if (_usersState.value !is Response.Success) {
                    _usersState.update {
                        Response.Error(error.message ?: "")
                    }
                }
            }
        }
    }

    private fun getUsersFromServer() {
        viewModelScope.launch {
            runCatching {
                if (_usersState.value !is Response.Success) {
                    _usersState.update {
                        Response.Loading()
                    }
                }
                getUsersFromServerUseCase()
            }.onSuccess { users ->
                if (users.isNotEmpty())
                    _usersState.update { Response.Success(users) }
            }.onFailure { error ->
                if (_usersState.value !is Response.Success) {
                    _usersState.update {
                        Response.Error(error.message ?: "")
                    }
                }
            }
        }
    }
}