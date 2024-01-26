package com.example.homework_20.domain.usecase.wrapper

import com.example.homework_20.domain.usecase.user.AddUserUseCase
import com.example.homework_20.domain.usecase.user.DeleteUserUseCase
import com.example.homework_20.domain.usecase.user.GetUserByEmailUseCase
import com.example.homework_20.domain.usecase.user.GetUserCountUseCase
import com.example.homework_20.domain.usecase.user.UpdateUserUseCase
import javax.inject.Inject

data class UserUseCase @Inject constructor(
    val addUserUseCase: AddUserUseCase,
    val deleteUserUseCase: DeleteUserUseCase,
    val updateUserUseCase: UpdateUserUseCase,
    val getUserByEmailUseCase: GetUserByEmailUseCase,
    val getUserCountUseCase: GetUserCountUseCase
)