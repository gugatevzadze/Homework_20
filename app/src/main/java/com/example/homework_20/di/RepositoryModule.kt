package com.example.homework_20.di

import com.example.homework_20.data.local.dao.user.UserDao
import com.example.homework_20.data.local.repository.user.UserRepositoryImpl
import com.example.homework_20.domain.repository.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository {
        return UserRepositoryImpl(
            userDao = userDao
        )
    }
}