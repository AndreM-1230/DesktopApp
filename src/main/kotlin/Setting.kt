
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import com.example.desctopapp.ThemeObject

@Composable
fun setting(title: String)
{
    var isThemeChanged = remember { mutableStateOf(false) }
    var themeChangedName = remember { mutableStateOf("") }
    var allThemes = remember { ThemeObject.list }
    Column {
        Text(title)
        for (i in 0 until allThemes.size) {
            Button(
                onClick = {
                    isThemeChanged.value = true
                    themeChangedName.value = allThemes[i].name
                    //ThemeObject.current = allThemes[i]
                }, // Упрощенная логика переключения состояний
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (allThemes[i].name == ThemeObject.current!!.name) Color(ThemeObject.current!!.btnColorActive.toLong(16)) else Color(ThemeObject.current!!.btnColor.toLong(16)),
                    contentColor = if (allThemes[i].name == ThemeObject.current!!.name) Color(ThemeObject.current!!.textColorActive.toLong(16)) else Color(ThemeObject.current!!.textColor.toLong(16))
                ),
                modifier = Modifier.padding(10.dp)
            ) {
                Image(
                    painter = painterResource("drawable/menu.svg"),
                    contentDescription = "image description",
                    contentScale = ContentScale.None
                )
                Text(allThemes[i].nameRu)
            }
        }
    }
    LaunchedEffect(key1 = isThemeChanged.value) {
        if (isThemeChanged.value) {
            ThemeObject.current = ThemeObject.list.find { it.name == themeChangedName.value }
            isThemeChanged.value = false
        }
    }
}

fun changeTheme()
{

}