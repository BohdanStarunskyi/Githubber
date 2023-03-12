package com.example.testtask.presentation.ui.repos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.data.usecase.AppUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(private val useCase: AppUseCaseImpl) : ViewModel() {
    private val _reposState = mutableStateOf(ReposState())
    val reposState: State<ReposState> = _reposState

    fun getReposFromDatabase(ownerId: Int) {
        viewModelScope.launch {
            runCatching {
                _reposState.value = _reposState.value.copy(isLoading = true)
                useCase.getReposFromDatabase(ownerId)
            }.onSuccess {
                if (it.isNotEmpty())
                    _reposState.value = ReposState(repos = it)
            }.onFailure {
                _reposState.value = _reposState.value.copy(error = it.message, isLoading = false)
            }
        }
    }

    fun getReposFromServer(username: String, ownerId: Int) {
        viewModelScope.launch {
            runCatching {
                _reposState.value = _reposState.value.copy(isLoading = true)
                useCase.getReposFromServer(username, ownerId)
            }.onSuccess {
                if (it.isNotEmpty())
                    _reposState.value = ReposState(repos = it)
            }.onFailure {
                _reposState.value = _reposState.value.copy(error = it.message, isLoading = false)
            }
        }
    }
}