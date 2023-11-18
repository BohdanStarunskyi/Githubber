package com.example.testtask.presentation.ui.repos

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.common.Response
import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.domain.usecase.GetReposFromDatabaseUseCase
import com.example.testtask.domain.usecase.GetReposFromServerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val getReposFromDatabaseUseCase: GetReposFromDatabaseUseCase,
    private val getReposFromServerUseCase: GetReposFromServerUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _reposState = MutableStateFlow<Response<List<RepositoryEntity>>>(Response.Loading())
    val reposState = _reposState.asStateFlow()

    init {
        val username = savedStateHandle.get<String>("username")
        val ownerId = savedStateHandle.get<Int>("ownerId")
        if (username != null && ownerId != null) {
            getReposFromDatabase(ownerId)
            getReposFromServer(username, ownerId)
        }
    }

    fun getReposFromDatabase(ownerId: Int) {
        viewModelScope.launch {
            runCatching {
                if (_reposState.value !is Response.Success) {
                    _reposState.update {
                        Response.Loading()
                    }
                }
                getReposFromDatabaseUseCase.invoke(ownerId)
            }.onSuccess { repos ->
                if (repos.isNotEmpty())
                    _reposState.update { Response.Success(repos) }
            }.onFailure { error ->
                if (_reposState.value !is Response.Success) {
                    _reposState.update {
                        Response.Error(error.message ?: "")
                    }
                }
            }
        }
    }

    fun getReposFromServer(username: String, ownerId: Int) {
        viewModelScope.launch {
            runCatching {
                if (_reposState.value !is Response.Success) {
                    _reposState.update {
                        Response.Loading()
                    }
                }
                getReposFromServerUseCase.invoke(username, ownerId)
            }.onSuccess { repos ->
                if (repos.isNotEmpty())
                    _reposState.update { Response.Success(repos) }
            }.onFailure { error ->
                if (_reposState.value !is Response.Success) {
                    _reposState.update {
                        Response.Error(error.message ?: "")
                    }
                }
            }
        }
    }
}