package com.example.homework_20.di

import com.example.homework_20.domain.repository.user.UserRepository
import com.example.homework_20.domain.usecase.user.AddUserUseCase
import com.example.homework_20.domain.usecase.user.DeleteUserUseCase
import com.example.homework_20.domain.usecase.user.GetUserByEmailUseCase
import com.example.homework_20.domain.usecase.user.GetUsersUseCase
import com.example.homework_20.domain.usecase.user.UpdateUserUseCase
import com.example.homework_20.domain.usecase.validator.EmailValidatorUseCase
import com.example.homework_20.domain.usecase.validator.FieldsValidatorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideEmailValidatorUseCase(
    ): EmailValidatorUseCase {
        return EmailValidatorUseCase()
    }

    @Provides
    @Singleton
    fun provideFieldsValidatorUseCase(
    ): FieldsValidatorUseCase {
        return FieldsValidatorUseCase()
    }

    @Provides
    @Singleton
    fun provideAddUserUseCase(
        userRepository: UserRepository
    ): AddUserUseCase {
        return AddUserUseCase(
            userRepository = userRepository
        )
    }

    @Provides
    @Singleton
    fun provideDeleteUserUseCase(
        userRepository: UserRepository
    ): DeleteUserUseCase {
        return DeleteUserUseCase(
            userRepository = userRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetUserByEmailUseCase(
        userRepository: UserRepository
    ): GetUserByEmailUseCase {
        return GetUserByEmailUseCase(
            userRepository = userRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetUsersUseCase(
        userRepository: UserRepository
    ): GetUsersUseCase {
        return GetUsersUseCase(
            userRepository = userRepository
        )
    }

    @Provides
    @Singleton
    fun provideUpdateUserUseCase(
        userRepository: UserRepository
    ): UpdateUserUseCase {
        return UpdateUserUseCase(
            userRepository = userRepository
        )
    }
}