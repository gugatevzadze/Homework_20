package com.example.homework_20.domain.repository.user

import com.example.homework_20.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers(): Flow<List<User>>

    suspend fun addUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun getUserByEmail(email: String): User?

    suspend fun deleteUser(user: User)
}