package com.example.desctopapp.dataclasses

data class UserDataClass (
    val id: Int,
    val login: String,
    val password: String,
    val name: String,
    val description: String?,
    val created: Long,
    val lastConnection: Long?,
    val phone: Int?,
    val email: String,
    val status: Int,
    val type: Int,
    val updated: Long?,
)