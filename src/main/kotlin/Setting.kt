
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
import com.example.desctopapp.TitlesObject
import java.sql.DriverManager

@Composable
fun setting(title: String)
{
    var isThemeChanged = remember { mutableStateOf(false) }
    var themeChangedName = remember { mutableStateOf("") }
    var isTitlesChanged = remember { mutableStateOf(false) }
    var titlesChangedName = remember { mutableStateOf("") }
    var allThemes = remember { ThemeObject.list }
        Column {
            Text("Темы")
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
            Text("Язык")
            for (i in 0 until TitlesObject.list.size) {
                Button(
                    onClick = {
                        isTitlesChanged.value = true
                        titlesChangedName.value = TitlesObject.list[i].language
                        //ThemeObject.current = allThemes[i]
                    }, // Упрощенная логика переключения состояний
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (TitlesObject.list[i].language == TitlesObject.current!!.language) Color(ThemeObject.current!!.btnColorActive.toLong(16)) else Color(ThemeObject.current!!.btnColor.toLong(16)),
                        contentColor = if (TitlesObject.list[i].language == TitlesObject.current!!.language) Color(ThemeObject.current!!.textColorActive.toLong(16)) else Color(ThemeObject.current!!.textColor.toLong(16))
                    ),
                    modifier = Modifier.padding(10.dp)
                ) {
                    Image(
                        painter = painterResource("drawable/menu.svg"),
                        contentDescription = "image description",
                        contentScale = ContentScale.None
                    )
                    Text(TitlesObject.list[i].language)
                }
            }
        }
    if (isThemeChanged.value) {
        LaunchedEffect(key1 = isThemeChanged.value) {
            val connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")
            val preparedStatement = connection.prepareStatement("UPDATE setting SET theme_name = ? WHERE theme_name = ?")
            preparedStatement.setString(1, themeChangedName.value)
            preparedStatement.setString(2, ThemeObject.current?.name)
            preparedStatement.executeUpdate()
            connection.close()
            ThemeObject.current = ThemeObject.list.find { it.name == themeChangedName.value }
            isThemeChanged.value = false
        }
    }
    if (isTitlesChanged.value) {
        LaunchedEffect(key1 = isTitlesChanged.value) {
            val connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")
            val preparedStatement = connection.prepareStatement("UPDATE setting SET titles_name = ? WHERE titles_name = ?")
            preparedStatement.setString(1, titlesChangedName.value)
            preparedStatement.setString(2, TitlesObject.current!!.language)
            preparedStatement.executeUpdate()
            connection.close()
            TitlesObject.current = TitlesObject.list.find { it.language == titlesChangedName.value }
            isTitlesChanged.value = false
        }
    }
}
