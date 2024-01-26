package com.example.homework_20.data.local.mapper.user

import com.example.homework_20.data.local.model.user.UserEntity
import com.example.homework_20.domain.model.user.User

fun UserEntity.toDomain(): User {
    return User(
        email = email,
        firstName = firstName,
        lastName = lastName,
        age = age
    )
}

fun User.toData(): UserEntity {
    return UserEntity(
        email = email,
        firstName = firstName,
        lastName = lastName,
        age = age
    )
}