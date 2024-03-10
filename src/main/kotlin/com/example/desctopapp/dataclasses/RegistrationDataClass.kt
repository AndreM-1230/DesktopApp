package com.example.desctopapp.dataclasses

data class RegistrationDataClass(
    val hasUser: Boolean,
)

data class CreateUserDataClass(
    val login: String,
    val password: String,
    val name: String,
    val email: String,
)
