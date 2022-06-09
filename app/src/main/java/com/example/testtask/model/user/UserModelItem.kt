package com.example.testtask.model.user

data class UserModelItem(
    var changesCount: Int = 0,
    val avatar_url: String,
    val id: Int,
    val login: String
)