package com.example.desctopapp.dataclasses

data class RegistrationDataClass(
    val hasUser: Boolean,
)

data class CreateUserDataClass(
    val email: String,
    val password: String,
)
