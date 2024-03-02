package com.example.desctopapp.dataclasses

class ChatDataClass (
    val id: Int,
    val userId: Int,
    val repostChatId: Int?,
    val repostMessageId: Int?,
    val message: String,
    val fileId: Int?,
    val date: Long,
    val status: Int,
    val readUsersId: String?
)