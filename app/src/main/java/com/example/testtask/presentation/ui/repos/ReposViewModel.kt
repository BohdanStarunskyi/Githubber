package com.example.testtask.presentation.ui.repos

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.data.usecase.AppUseCaseImpl
import com.example.testtask.domain.entities.RepositoryEntity
import com.example.testtask.presentation.ui.UiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(private val useCase: AppUseCaseImpl) : ViewModel() {
    private val _reposState = MutableStateFlow<UiStates?>(null)
    val reposState = _reposState.asStateFlow()

    private val _reposList = mutableStateListOf<RepositoryEntity>()
    val reposList: List<RepositoryEntity> = _reposList

    fun getReposFromDatabase(ownerId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _reposState.emit(UiStates.Loading)
                useCase.getReposFromDatabase(ownerId)
            }.onSuccess {
                _reposState.emit(UiStates.Success)
                if (it.isNotEmpty()) {
                    _reposList.clear()
                    _reposList.addAll(it)
                }
            }.onFailure {
                _reposState.emit(UiStates.Error(it))
            }
        }
    }

    fun getReposFromServer(username: String, ownerId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _reposState.emit(UiStates.Loading)
                useCase.getReposFromServer(username, ownerId)
            }.onSuccess { repos ->
                _reposState.emit(UiStates.Success)
                if (repos.isNotEmpty()) {
                    _reposList.clear()
                    _reposList.addAll(repos.sortedBy { it.name })
                }
            }.onFailure {
                _reposState.emit(UiStates.Error(it))
            }
        }
    }
}