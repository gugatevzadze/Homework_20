package com.example.homework_20.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_20.domain.usecase.user.AddUserUseCase
import com.example.homework_20.domain.usecase.user.DeleteUserUseCase
import com.example.homework_20.domain.usecase.user.GetUserByEmailUseCase
import com.example.homework_20.domain.usecase.user.GetUserCountUseCase
import com.example.homework_20.domain.usecase.user.GetUsersUseCase
import com.example.homework_20.domain.usecase.user.UpdateUserUseCase
import com.example.homework_20.domain.usecase.validator.EmailValidatorUseCase
import com.example.homework_20.domain.usecase.validator.FieldsValidatorUseCase
import com.example.homework_20.presentation.event.main.MainEvents
import com.example.homework_20.presentation.mapper.user.toDomain
import com.example.homework_20.presentation.model.user.UserModel
import com.example.homework_20.presentation.state.main.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase,
    private val getUsersUseCase: GetUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserByEmailUseCase: GetUserByEmailUseCase,
    private val getUserCountUseCase: GetUserCountUseCase,
    private val emailValidatorUseCase: EmailValidatorUseCase,
    private val fieldsValidatorUseCase: FieldsValidatorUseCase,
) : ViewModel() {

    private val _userState = MutableStateFlow(MainState())
    val userState: StateFlow<MainState> get() = _userState

    fun onEvent(event: MainEvents) {
        when (event) {
            is MainEvents.AddUser -> onAddUser(event.user)
            is MainEvents.DeleteUser -> onDeleteUser(event.user)
            is MainEvents.UpdateUser -> onUpdateUser(event.user)
            is MainEvents.GetUsers -> onGetUsers()
            is MainEvents.GetUserCount -> onGetUserCount()
            is MainEvents.ResetMessage -> onResetMessage()
        }
    }

    private fun onGetUsers() {
        viewModelScope.launch {
            val userCount = getUserCountUseCase.invoke()
            _userState.update { currentState ->
                currentState.copy(userCount = userCount)
            }
        }
    }

    private fun onGetUserCount() {
        viewModelScope.launch {
            getUserCountUseCase.invoke()
        }
    }


    private fun onAddUser(user: UserModel) {
        viewModelScope.launch {
            if (validationChecker(user.email, user.firstName, user.lastName, user.age)) {
                val existingUser = getUserByEmailUseCase.invoke(user.email)
                if (existingUser != null) {
                    updateErrorMessage(message = "User already exists")
                } else {
                    addUserUseCase.invoke(user.toDomain())
                    updateStatusMessage(message = "User added successfully")
                }
            }
        }
    }

    private fun onDeleteUser(user: UserModel) {
        viewModelScope.launch {
            val existingUser = getUserByEmailUseCase.invoke(user.email)
            if (existingUser == null) {
                updateErrorMessage(message = "User does not exist")
            } else {
                deleteUserUseCase.invoke(user.toDomain())
                updateStatusMessage(message = "User deleted successfully")
            }
        }
    }


    private fun onUpdateUser(user: UserModel) {
        viewModelScope.launch {
            val existingUser = getUserByEmailUseCase.invoke(user.email)
            if (existingUser == null) {
                updateErrorMessage(message = "User not found")
            } else {
                updateUserUseCase.invoke(user.toDomain())
                updateStatusMessage(message = "User updated successfully")
            }
        }
    }

    private fun validationChecker(
        email: String,
        firstName: String,
        lastName: String,
        age: String
    ): Boolean {
        return when {
            !fieldsValidatorUseCase.validateFields(email, firstName, lastName, age) -> {
                updateErrorMessage(message = "Please fill in all fields")
                false
            }

            !emailValidatorUseCase.isEmailValid(email) -> {
                updateErrorMessage(message = "Please enter a valid email address")
                false
            }

            else -> true
        }
    }

    private fun updateErrorMessage(message: String?) {
        _userState.update { currentState ->
            currentState.copy(
                errorMessage = message
            )
        }
    }

    private fun updateStatusMessage(message: String?) {
        _userState.update { currentState ->
            currentState.copy(
                statusMessage = message
            )
        }
    }

    private fun onResetMessage() {
        _userState.update { currentState ->
            currentState.copy(
                errorMessage = null,
                statusMessage = null
            )
        }
    }
}