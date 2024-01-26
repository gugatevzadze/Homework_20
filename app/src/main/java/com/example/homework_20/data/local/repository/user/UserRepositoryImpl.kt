package com.example.homework_20.data.local.repository.user

import com.example.homework_20.data.local.dao.user.UserDao
import com.example.homework_20.data.local.mapper.user.toData
import com.example.homework_20.data.local.mapper.user.toDomain
import com.example.homework_20.domain.model.user.User
import com.example.homework_20.domain.repository.user.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor (
    private val userDao: UserDao
)
: UserRepository {

    override suspend fun getUserCount(): Int {
        return userDao.getUserCount()
    }


    override suspend fun addUser(user: User) {
        return userDao.addUser(user.toData())
    }

    override suspend fun updateUser(user: User) {
        return userDao.updateUser(user.toData())
    }

    override suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)?.toDomain()
    }

    override suspend fun deleteUser(user: User) {
        return userDao.deleteUser(user.toData())
    }
}