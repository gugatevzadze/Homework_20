package com.example.homework_20.presentation.mapper.user

import com.example.homework_20.domain.model.user.User
import com.example.homework_20.presentation.model.user.UserModel

fun User.toPresentation(): UserModel {
    return UserModel(
        email = email,
        firstName = firstName,
        lastName = lastName,
        age = age,
    )
}

fun UserModel.toDomain(): User {
    return User(
        email = email,
        firstName = firstName,
        lastName = lastName,
        age = age,
    )
}