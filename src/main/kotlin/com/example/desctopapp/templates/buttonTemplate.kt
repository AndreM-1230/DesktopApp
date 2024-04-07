package com.example.desctopapp.templates

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desctopapp.NavigationObject
import com.example.desctopapp.ThemeObject
import com.example.desctopapp.TitlesObject

class ButtonTemplate {

    @Composable
    fun mainButton(i: Int) {
        var currentTheme = remember { mutableStateOf(ThemeObject.current) }
        LaunchedEffect(key1 = ThemeObject.current) {
            currentTheme.value = ThemeObject.current
        }
        val buttonText = TitlesObject.current?.let {
            val fieldName = NavigationObject.navigationButtons[i]
            val field = it::class.java.getDeclaredField(fieldName)
            field.isAccessible = true
            field.get(it) as? String ?: ""
        } ?: ""
        Button(
            onClick = { NavigationObject.current.value = NavigationObject.navigationButtons[i] }, // Упрощенная логика переключения состояний
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (NavigationObject.current.value == NavigationObject.navigationButtons[i]) Color(currentTheme.value!!.btnColorActive.toLong(16)) else Color(currentTheme.value!!.btnColor.toLong(16)),
                contentColor = if (NavigationObject.current.value == NavigationObject.navigationButtons[i]) Color(currentTheme.value!!.textColorActive.toLong(16)) else Color(currentTheme.value!!.textColor.toLong(16))
            ),
            modifier = Modifier.padding(10.dp).width(150.dp)
        ) {
            Image(
                painter = painterResource("drawable/menu.svg"),
                contentDescription = "image description",
                contentScale = ContentScale.None,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(buttonText, fontSize = 11.sp)
        }
    }

}
