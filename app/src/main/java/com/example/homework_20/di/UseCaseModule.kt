package com.example.homework_20.di

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

}