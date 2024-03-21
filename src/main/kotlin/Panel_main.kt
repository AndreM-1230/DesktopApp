
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.desctopapp.dataclasses.ThemeDataClass

@Composable
fun panelMain(
    currentTheme: ThemeDataClass,
    isButtonPressed: SnapshotStateList<Boolean>,
    buttonTitles: SnapshotStateList<String>
) {
    Box(modifier = Modifier.fillMaxSize())
    {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(40.dp)) {
                Box(modifier = Modifier.align(Alignment.Center)){
                    isButtonPressed.forEachIndexed { index, isPressed ->if (isPressed) Text(text = buttonTitles[index], color = Color(currentTheme.textColorActive.toLong(16)))}
                }
            }
            Box ( modifier = Modifier.padding(10.dp)) {
                when (isButtonPressed.indexOf(true)) {
                    0 -> firstPage("Чаты")
                    1 -> Text("2")
                    2 -> Text("3")
                    3 ->setting("Настройки", currentTheme)
                }
            }
        }
    }
}