import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.material.TextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
@Preview
fun App(modifier: Modifier = Modifier) {
    val isButtonPressed = remember { mutableStateListOf(false, false, false) }
    val buttonTitles = remember { mutableStateListOf("Привет", "Вторая кнопка", "Ещё кнопка") }
    MaterialTheme {
        Box(modifier = Modifier.background(color = Color(0xFFEFEFEF))) {
            Row {
                //Панель навигации
                panel_navigation(isButtonPressed, buttonTitles)
                //Основное окно
                panel_main(isButtonPressed, buttonTitles)
            }
        }
    }
}

@Composable
fun panel_navigation(isButtonPressed: SnapshotStateList<Boolean>, buttonTitles: SnapshotStateList<String>) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(1080.dp)
            .background(color = Color(0xFFC4C4C4))
    ) {
        Card(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .background(color = Color(0xFFC4C4C4))
                .padding(top = 20.dp),
            elevation = 10.dp
        ) {
            Column (modifier = Modifier.background(color = Color(0xFFC4C4C4))) {
                for (i in 0 until buttonTitles.size) {
                    mainButton(i, isButtonPressed, buttonTitles)
                }
            }
        }
    }
}

@Composable
fun mainButton(i: Int, isButtonPressed: MutableList<Boolean>, buttonTitles: List<String>) {
    Button(
        onClick = { for (j in 0 until isButtonPressed.size) {
            isButtonPressed[j] = false
            if (j == i) isButtonPressed[i] = true
        } }, // Упрощенная логика переключения состояний
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isButtonPressed[i]) Color.DarkGray else Color.Yellow,
            contentColor = if (isButtonPressed[i]) Color.White else Color.Black
        ),
        modifier = Modifier.padding(10.dp)
    ) {
        Image(
            painter = painterResource("drawable/menu.svg"),
            contentDescription = "image description",
            contentScale = ContentScale.None
        )
        Text(buttonTitles[i])
    }
}

@Composable
fun firstPage(text: String) {
    Text(text)
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App(Modifier)
    }
}
