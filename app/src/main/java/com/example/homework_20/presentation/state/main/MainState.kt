package com.example.homework_20.presentation.state.main

import com.example.homework_20.presentation.model.user.UserModel

data class MainState(
    val users: List<UserModel> = emptyList(),
    val userCount: Int? = null,
    val errorMessage: String? = null,
    val statusMessage: String? = null
)
