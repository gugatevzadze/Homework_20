package com.example.homework_20.data.local.mapper.user

import com.example.homework_20.data.local.model.user.UserEntity
import com.example.homework_20.domain.model.user.User

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        age = age
    )
}

fun User.toData(): UserEntity {
    return UserEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        age = age
    )
}