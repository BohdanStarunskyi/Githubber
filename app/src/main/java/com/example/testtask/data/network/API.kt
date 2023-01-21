package com.example.testtask.data.network

import com.example.testtask.data.network.dto.ReposResponse
import com.example.testtask.data.network.dto.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("users")
    suspend fun getUsers(): Response<ArrayList<UsersResponse>>

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): Response<ArrayList<ReposResponse>>
}