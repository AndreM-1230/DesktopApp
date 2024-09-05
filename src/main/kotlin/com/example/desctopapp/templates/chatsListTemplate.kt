package com.example.desctopapp.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class ChatsListTemplate {
    @Composable
    fun chatListTemplate(message: String, title: String, color: Color) {
        Box(modifier = Modifier.fillMaxWidth().height(60.dp).padding(bottom = 10.dp)
            .background(color = color)
            ) {
            Row {
                Box(modifier = Modifier.width(60.dp).padding(start = 5.dp, end = 5.dp)) {

                }
                Box(modifier = Modifier.width(120.dp).padding(start = 5.dp, end = 5.dp)) {
                    Text(title)
                }
                Box(modifier = Modifier.fillMaxWidth().padding(start = 5.dp, end = 5.dp)) {
                    Text(message)
                }
            }
        }
    }
}