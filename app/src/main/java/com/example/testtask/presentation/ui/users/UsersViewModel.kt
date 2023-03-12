package com.example.testtask.presentation.ui.users

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.data.usecase.AppUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val useCase: AppUseCaseImpl) : ViewModel() {
    private val _usersState = mutableStateOf(UsersState())
    val usersState: State<UsersState> = _usersState

    fun getUsersFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _usersState.value = _usersState.value.copy(isLoading = true)
                useCase.getUsersFromDatabase()
            }.onSuccess {
                if (it.isNotEmpty())
                    _usersState.value = UsersState(users = it)
            }.onFailure {
                _usersState.value = _usersState.value.copy(error = it.message, isLoading = false)
            }
        }
    }

    fun getUsersFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _usersState.value = _usersState.value.copy(isLoading = true)
                useCase.getUsersFromServer()
            }.onSuccess {
                if (it.isNotEmpty())
                    _usersState.value = UsersState(users = it)
            }.onFailure {
                _usersState.value = _usersState.value.copy(error = it.message, isLoading = false)
            }
        }
    }
}