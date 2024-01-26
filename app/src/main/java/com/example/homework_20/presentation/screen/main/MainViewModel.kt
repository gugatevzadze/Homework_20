package com.example.homework_20.presentation.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_20.domain.usecase.validator.EmailValidatorUseCase
import com.example.homework_20.domain.usecase.validator.FieldsValidatorUseCase
import com.example.homework_20.domain.usecase.wrapper.UserUseCase
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
    private val userUseCase: UserUseCase,
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
            is MainEvents.GetUserCount -> onGetUserCount()
            is MainEvents.ResetMessage -> onResetMessage()
        }
    }

    private fun onGetUserCount() {
        viewModelScope.launch {
            val userCount = userUseCase.getUserCountUseCase.invoke()
            _userState.update { currentState ->
                currentState.copy(userCount = userCount)
            }
        }
    }


    private fun onAddUser(user: UserModel) {
        viewModelScope.launch {
            if (validationChecker(user.email, user.firstName, user.lastName, user.age)) {
                val existingUser = userUseCase.getUserByEmailUseCase.invoke(user.email)
                if (existingUser != null) {
                    updateErrorMessage(message = "User already exists")
                } else {
                    userUseCase.addUserUseCase.invoke(user.toDomain())
                    updateStatusMessage(message = "User added successfully")
                }
            }
        }
    }

    private fun onDeleteUser(user: UserModel) {
        viewModelScope.launch {
            val existingUser = userUseCase.getUserByEmailUseCase.invoke(user.email)
            if (existingUser == null) {
                updateErrorMessage(message = "User does not exist")
            } else {
                userUseCase.deleteUserUseCase.invoke(user.toDomain())
                updateStatusMessage(message = "User deleted successfully")
            }
        }
    }


    private fun onUpdateUser(user: UserModel) {
        viewModelScope.launch {
            val existingUser = userUseCase.getUserByEmailUseCase.invoke(user.email)
            if (existingUser == null) {
                updateErrorMessage(message = "User not found")
            } else {
                userUseCase.updateUserUseCase.invoke(user.toDomain())
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