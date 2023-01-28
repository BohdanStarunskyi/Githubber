package com.example.testtask.presentation.ui.users

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.data.usecase.AppUseCaseImpl
import com.example.testtask.domain.entities.UserEntity
import com.example.testtask.presentation.ui.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val useCase: AppUseCaseImpl) : ViewModel() {
    private val _usersState = MutableStateFlow<UiStates?>(null)
    val usersState = _usersState.asStateFlow()

    private val _userList = mutableStateListOf<UserEntity>()
    val userList: List<UserEntity> = _userList

    fun getUsersFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _usersState.emit(UiStates.Loading)
                useCase.getUsersFromDatabase()
            }.onSuccess {
                _usersState.emit(UiStates.Success)
                if (it.isNotEmpty()) {
                    _userList.clear()
                    _userList.addAll(it)
                }
            }.onFailure {
                _usersState.emit(UiStates.Error(it))
            }
        }
    }

    fun getUsersFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _usersState.emit(UiStates.Loading)
                useCase.getUsersFromServer()
            }.onSuccess {
                _usersState.emit(UiStates.Success)
                if (it.isNotEmpty()) {
                    _userList.clear()
                    _userList.addAll(it)
                }
            }.onFailure {
                _usersState.emit(UiStates.Error(it))
            }
        }
    }
}