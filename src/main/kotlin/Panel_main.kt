
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.desctopapp.NavigationObject
import com.example.desctopapp.ThemeObject

@Composable
fun panelMain(
    isButtonPressed: SnapshotStateList<Boolean>,
    buttonTitles: SnapshotStateList<String>
) {
    var currentTheme = remember { mutableStateOf(ThemeObject.current) }
    LaunchedEffect(key1 = ThemeObject.current) {
        currentTheme.value = ThemeObject.current
    }
    Box(modifier = Modifier.fillMaxSize())
    {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(40.dp)) {
                Box(modifier = Modifier.align(Alignment.Center)){
                    isButtonPressed.forEachIndexed { index, isPressed ->if (isPressed) Text(text = buttonTitles[index], color = Color(
                        currentTheme.value!!.textColorActive.toLong(16)))}
                }
            }
            Box ( modifier = Modifier.padding(10.dp)) {
                when (NavigationObject.current.value) {
                    "profile" -> Text("Profile")
                    "newGroup" -> Text("newGroup")
                    "chats" -> panel_chats("Чаты")
                    "contacts" -> Text("contacts")
                    "savedMessages" -> Text("3")
                    "setting"-> setting("Настройки")
                    "about" -> Text("about")
                }
            }
        }
    }
}