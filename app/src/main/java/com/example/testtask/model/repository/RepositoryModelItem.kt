package com.example.testtask.model.repository

data class RepositoryModelItem(
    val html_url: String,
    val language: String?,
    val name: String,
    val stargazers_count: Int,
    val username: String
)