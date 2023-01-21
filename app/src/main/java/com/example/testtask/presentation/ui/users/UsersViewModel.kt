package com.example.testtask.presentation.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.data.usecase.AppUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val useCase: AppUseCaseImpl) : ViewModel() {
    private val _usersState = MutableStateFlow<UsersStates?>(null)
    val usersState = _usersState.asStateFlow()

    fun getUsersFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _usersState.emit(UsersStates.Loading(true))
                useCase.getUsersFromDatabase()
            }.onSuccess {
                _usersState.emit(UsersStates.Success(it))
            }.onFailure {
                _usersState.emit(UsersStates.Error(it))
            }
        }
    }

    fun getUsersFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _usersState.emit(UsersStates.Loading(false))
                useCase.getUsersFromServer()
            }.onSuccess {
                _usersState.emit(UsersStates.Success(it))
            }.onFailure {
                _usersState.emit(UsersStates.Error(it))
            }
        }
    }
}