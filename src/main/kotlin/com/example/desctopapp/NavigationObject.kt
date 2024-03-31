package com.example.desctopapp

import androidx.compose.runtime.mutableStateOf

object NavigationObject {
    val navigationButtons = mutableListOf("newGroup", "chats", "contacts", "savedMessages", "setting", "about")
    var current =  mutableStateOf("chats")
}