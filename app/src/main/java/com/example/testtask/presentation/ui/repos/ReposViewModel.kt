package com.example.testtask.presentation.ui.repos

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
class ReposViewModel @Inject constructor(private val useCase: AppUseCaseImpl) : ViewModel() {
    private val _reposState = MutableStateFlow<ReposStates?>(null)
    val reposState = _reposState.asStateFlow()

    fun getReposFromDatabase(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _reposState.emit(ReposStates.Loading(true))
                useCase.getReposFromDatabase(username)
            }.onSuccess {
                _reposState.emit(ReposStates.Success(it))
            }.onFailure {
                _reposState.emit(ReposStates.Error(it))
            }
        }
    }

    fun getReposFromServer(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _reposState.emit(ReposStates.Loading(false))
                useCase.getReposFromServer(username)
            }.onSuccess {
                _reposState.emit(ReposStates.Success(it))
            }.onFailure {
                _reposState.emit(ReposStates.Error(it))
            }
        }
    }
}