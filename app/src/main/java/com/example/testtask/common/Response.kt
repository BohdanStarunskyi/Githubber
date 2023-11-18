package com.example.testtask.common

sealed class Response<T> {
    class Loading<T> : Response<T>()
    class Success<T>(val data: T) : Response<T>()
    class Error<T>(val message: String) : Response<T>()
}