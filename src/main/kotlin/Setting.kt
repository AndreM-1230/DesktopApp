
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.desctopapp.ThemeObject

@Composable
fun setting(title: String)
{
    Column {
        Text(title)
        if (ThemeObject.list.isNotEmpty()) {
            ThemeObject.list.forEach { theme ->
                Button(
                    onClick = {
                        ThemeObject.current = theme
                    }, // Упрощенная логика переключения состояний
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (theme.name == ThemeObject.current!!.name) Color(ThemeObject.current!!.btnColorActive.toLong(16)) else Color(ThemeObject.current!!.btnColor.toLong(16)),
                        contentColor = if (theme.name == ThemeObject.current!!.name) Color(ThemeObject.current!!.textColorActive.toLong(16)) else Color(ThemeObject.current!!.textColor.toLong(16))
                    ),
                    modifier = Modifier.padding(10.dp)
                ) {
                    Image(
                        painter = painterResource("drawable/menu.svg"),
                        contentDescription = "image description",
                        contentScale = ContentScale.None
                    )
                    Text(theme.nameRu)
                }
            }
        }
    }
}