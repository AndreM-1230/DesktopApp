
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
import com.example.desctopapp.ThemeObject
import com.example.desctopapp.classes.RegistrationClass
import com.example.desctopapp.dataclasses.ThemeDataClass
import com.google.gson.Gson
import java.io.File
import java.nio.charset.Charset
import java.sql.DriverManager

@Composable
@Preview
fun app(modifier: Modifier = Modifier) {
    //val currentThemeName by remember { mutableStateOf("Sea") }
    val charset = Charset.forName("UTF-8")
    val themesDir = File("src/main/resources/themes")
    themesDir.listFiles()?.forEach { file ->
        val json = file.readText(Charsets.UTF_8)
        val gson = Gson()
        val data = gson.fromJson(json, ThemeDataClass::class.java)
        ThemeObject.list.add(data)
    }
    //ThemeObject.current = ThemeObject.list.find { it.name == currentThemeName }

    var userLoggedIn by remember { mutableStateOf(true) }
    var displayLoginPanel by remember { mutableStateOf(false) }
    var displayLocalCredentialsPanel by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isButtonPressed = remember { mutableStateListOf(false, false, false, false) }
    val buttonTitles = remember { mutableStateListOf("Привет", "Вторая кнопка", "Ещё кнопка", "Настройки") }
    /*
    val registrationClass = RegistrationClass()
    LaunchedEffect(Unit) {
        // Проверяем, есть ли пользователь в локальной базе данных
        when (val localUserExists = registrationClass.hasLocalUser()) {
            // Если пользователя нет в локальной базе данных, отображаем панель входа
            null -> displayLoginPanel = true
            else -> {
                email = localUserExists.email
                password = localUserExists.password
                // Если пользователь есть в локальной базе данных, проверяем его наличие на сервере
                registrationClass.hasUser(email, password).collect { result: Boolean ->
                    if (result) {
                        // Если пользователь найден на сервере, устанавливаем флаг userLoggedIn в true
                        userLoggedIn = true
                    } else {
                        // Если пользователь не найден на сервере, устанавливаем флаг displayLocalCredentialsPanel в true
                        displayLocalCredentialsPanel = true
                    }
                }
            }
        }
    }
    */
    MaterialTheme {
        Box(modifier = Modifier.background(color = Color(ThemeObject.current!!.mainColor.toLong(16)))) {
            Row {
                // Отображаем соответствующие панели на основе результатов проверок
                if (userLoggedIn) {
                    // Пользователь найден на сервере, отображаем панель навигации и основное окно
                    //Панель навигации
                    panelNavigation(isButtonPressed, buttonTitles)
                    //Основное окно
                    panelMain(isButtonPressed, buttonTitles)
                } else if (displayLocalCredentialsPanel) {
                    // Пользователь не найден на сервере, но есть локальные учетные данные, отображаем панель входа с локальными учетными данными
                    userLoggedIn = panelRegistration(false, email, password)
                } else if (displayLoginPanel) {
                    // Пользователь не найден в локальной базе данных, отображаем стандартную панель входа
                    userLoggedIn = panelRegistration(false)
                }
            }
        }
    }
}

@Composable
@Preview
fun checkDBUser(): Boolean {
    var hasUser by remember { mutableStateOf(false) }
    val connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("SELECT * FROM users")
    val hasDBUser = resultSet.next()
    val registrationClass = RegistrationClass()
    if (hasDBUser) {
        val email = resultSet.getString("email")
        val password = resultSet.getString("password")
        LaunchedEffect(Unit) {
            registrationClass.hasUser(email, password).collect { result: Boolean ->
                hasUser = result
            }
        }
    }
    resultSet.close()
    statement.close()
    connection.close()
    return hasUser
}

@Composable
fun panelNavigation(
    isButtonPressed: SnapshotStateList<Boolean>,
    buttonTitles: SnapshotStateList<String>
) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(1080.dp)
            .background(color = Color(ThemeObject.current!!.subColor.toLong(16)))
    ) {
        Card(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .background(color = Color(ThemeObject.current!!.subColor.toLong(16)))
                .padding(top = 20.dp),
            elevation = 10.dp
        ) {
            Column (modifier = Modifier.background(color = Color(ThemeObject.current!!.subColor.toLong(16)))) {
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
            backgroundColor = if (isButtonPressed[i]) Color(ThemeObject.current!!.btnColorActive.toLong(16)) else Color(ThemeObject.current!!.btnColor.toLong(16)),
            contentColor = if (isButtonPressed[i]) Color(ThemeObject.current!!.textColorActive.toLong(16)) else Color(ThemeObject.current!!.textColor.toLong(16))
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
    val registrationClass = RegistrationClass()

    var hasUser by remember { mutableStateOf(false) }

    //LaunchedEffect(Unit) {
    //    registrationClass.hasUser("example@email.com","password").collect { result: Boolean ->
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

        if (hasUser) {
            Text("User exists")
        } else {
            Text("User does not exist")
        }
        //quotes.forEach { quote ->
        //    Row {
        //        Text(quote.message)
        //    }
        //}
    }
    connection.close()
}


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DesctopApp"

    ) {
        app(Modifier)
    }
}
