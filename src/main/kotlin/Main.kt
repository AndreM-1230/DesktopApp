
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.desctopapp.classes.RegistrationClass
import java.sql.DriverManager

@Composable
@Preview
fun app(modifier: Modifier = Modifier) {
    val hasUser = checkDBUser()
    val isButtonPressed = remember { mutableStateListOf(false, false, false) }
    val buttonTitles = remember { mutableStateListOf("Привет", "Вторая кнопка", "Ещё кнопка") }
    MaterialTheme {
        Box(modifier = Modifier.background(color = Color(0xFFEFEFEF))) {
            Row {
                if (hasUser) {
                    //Панель навигации
                    panelNavigation(isButtonPressed, buttonTitles)
                    //Основное окно
                    panelMain(isButtonPressed, buttonTitles)
                }
            }
        }
    }
}

@Composable
@Preview
fun checkDBUser(): Boolean {
    val connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("SELECT * FROM users")
    var hasDBUser = resultSet.next()
    resultSet.close()
    statement.close()
    connection.close()
    var hasApiUser = false
    if (hasDBUser) {
        hasApiUser = checkApiUser(resultSet.getString("login"), resultSet.getString("password"))
    } else {
        hasDBUser = createUser()
    }
    return hasDBUser
}

@Composable
@Preview
fun checkApiUser(login: String, password: String): Boolean {
    val registrationClass = RegistrationClass()
    var hasUser by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        registrationClass.index(login, password).collect { result: Boolean ->
            hasUser = result
        }
    }
    return hasUser
}

@Composable
@Preview
fun createUser(): Boolean {
    val login = "root"
    val password = "password"
    val name = "exampleUsername"
    val email = "example@gmail.com"
    val registrationClass = RegistrationClass()
    var hasApiUser by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        registrationClass.send(login, password, name, email).collect { result: Boolean ->
            hasApiUser = result
        }
    }
    if (hasApiUser) {
        Text("hasApiUser")
        val connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")
        // Формируем SQL-запрос INSERT
        val query = "INSERT INTO users (login, password,name,status,type) VALUES ('$login', '$password', '$name',1,1)"
        // Создаем объект Statement и выполняем запрос
        val statement = connection.createStatement()
        statement.executeUpdate(query)
        // Закрываем ресурсы
        statement.close()
        connection.close()
    } else {
        Text("nohasApiUser")
    }
    return hasApiUser
}

@Composable
fun panelNavigation(isButtonPressed: SnapshotStateList<Boolean>, buttonTitles: SnapshotStateList<String>) {
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
    val connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("SELECT * FROM users")
    //var quotes by remember { mutableStateOf<List<LaraServTest>>(emptyList()) }
    //LaunchedEffect(Unit) {
    //    onCreate().collect { result ->quotes = result }
    //}
    //val registrationClass = RegistrationClass()

    //var hasUser by remember { mutableStateOf(false) }

    //LaunchedEffect(Unit) {
    //    registrationClass.index(login, password).collect { result: Boolean ->
    //        hasUser = result
    //    }
    //}

    Column {
        while (resultSet.next()) {
            Row {
                //Text(resultSet.getString("name"))
                //Text(resultSet.getString("date"))
            }
        }

        //if (hasUser) {
        //    Text("User exists")
        //} else {
        //    Text("User does not exist")
        //}
        //quotes.forEach { quote ->
        //    Row {
        //        Text(quote.message)
        //    }
        //}
    }
    connection.close()
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        app(Modifier)
    }
}
