package com.example.desctopapp.dataclasses

data class HasUser(
    val hasUser: Boolean,
)

data class IsResponse(
    var hasUserResponse: Boolean = false,
    val getResponse: Boolean = false,
    val sendResponse: Boolean = false,
    var hasUser: Boolean = false,
    val get: Boolean = false,
    val send: Boolean = false,
    var hasLocalUser: Boolean = false,
)

data class CreateUserDataClass(
    val email: String,
    val password: String,
)
