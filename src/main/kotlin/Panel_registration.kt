
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.desctopapp.classes.RegistrationClass
import com.example.desctopapp.dataclasses.UserDataClass

@Composable
@Preview
fun panelRegistration(
    defaultHasUser: Boolean = false,
    defaultEmail: String = "",
    defaultPassword: String = "",
    emailValidator: (String) -> Boolean = { it.isNotBlank() && isValidEmail(it) },
    passwordValidator: (String) -> Boolean = { it.length >= 6 }
){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(350.dp)
                .height(250.dp)
                .background(color = Color(0xFFC4C4C4))
                .align(Alignment.Center)
        ) {
            var email by remember { mutableStateOf(defaultEmail) }
            var password by remember { mutableStateOf(defaultPassword) }
            var hasUser by remember { mutableStateOf(defaultHasUser)}
            var isButtonLoading by remember { mutableStateOf(false) }
            val registrationClass = RegistrationClass()
            Column(
                modifier = Modifier.fillMaxSize().padding(all = 16.dp)
            ) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Почта") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Пароль") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick =
                        {
                            if (emailValidator(email) && passwordValidator(password)) {
                                isButtonLoading = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Yellow,
                            contentColor = Color.Black
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Войти/Зарегистрироваться")
                    }
            }
            if (isButtonLoading) {
                LaunchedEffect(Unit) {
                    registrationClass.index(email, password).collect { result: Boolean ->
                        hasUser = result
                    }
                }
                hasUser = if (hasUser) {
                    getApiUserData(email, password)
                } else {
                    createUser(email, password)
                }
                isButtonLoading = false
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return emailRegex.matches(email)
}

@Composable
fun getApiUserData(email: String, password: String): Boolean {
    val registrationClass = RegistrationClass()
    var hasUser by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        registrationClass.get(email, password).collect { result: UserDataClass ->
            hasUser = registrationClass.storeUser(result)
        }
    }
    return hasUser
}

@Composable
fun createUser(email: String, password: String): Boolean {
    var hasUser by remember { mutableStateOf(false) }
    val registrationClass = RegistrationClass()
    var hasApiUser by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        registrationClass.send(email, password).collect { result: Boolean ->
            hasApiUser = result
        }
    }
    if (hasApiUser) {
        hasUser = getApiUserData(email, password)
    }
    return hasUser
}

