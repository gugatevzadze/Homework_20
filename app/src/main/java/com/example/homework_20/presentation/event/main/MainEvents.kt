package com.example.homework_20.presentation.event.main

import com.example.homework_20.presentation.model.user.UserModel

sealed class MainEvents {
    data class AddUser(
        val user: UserModel
    ) : MainEvents()
    data class UpdateUser(
        val user: UserModel
    ) : MainEvents()
    data class DeleteUser(
        val user: UserModel
    ) : MainEvents()
    data class GetUserByEmail(
        val email: String
    ) : MainEvents()
    data object GetUsers : MainEvents()
    data object ResetErrorMessage : MainEvents()
    data object statusMessage : MainEvents()
}