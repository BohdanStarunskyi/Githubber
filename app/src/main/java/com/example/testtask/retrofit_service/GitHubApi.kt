package com.example.testtask.retrofit_service

import com.example.testtask.model.repository.RepositoryModel
import com.example.testtask.model.user.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users")
    fun getUsers(): Call<UserModel>
    @GET("users/{username}/repos")
    fun getUsersRepos(@Path("username") username: String): Call<RepositoryModel>
}