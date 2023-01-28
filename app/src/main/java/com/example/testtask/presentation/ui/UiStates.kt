package com.example.testtask.presentation.ui

sealed class UiStates {
    object Loading : UiStates()
    object Success : UiStates()
    data class Error(var error: Throwable) : UiStates()
}
