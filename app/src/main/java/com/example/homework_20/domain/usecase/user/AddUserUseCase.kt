package com.example.homework_20.domain.usecase.user

import com.example.homework_20.domain.model.user.User
import com.example.homework_20.domain.repository.user.UserRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) = userRepository.addUser(
        user
    )
}