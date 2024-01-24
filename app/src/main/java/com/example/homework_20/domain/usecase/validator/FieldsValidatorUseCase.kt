package com.example.homework_20.domain.usecase.validator

class FieldsValidatorUseCase {
    fun validateFields(vararg fields: String): Boolean {
        return fields.all { it.isNotEmpty() }
    }
}