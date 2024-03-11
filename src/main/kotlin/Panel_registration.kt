
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun panelRegistration(
    defaultEmail: String = "",
    defaultPassword: String = "",
    emailValidator: (String) -> Boolean = { it.isNotBlank() && isValidEmail(it) },
    passwordValidator: (String) -> Boolean = { it.length >= 6 }
) {
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
            var login by remember { mutableStateOf("") }
            var firstSuccess by remember { mutableStateOf(false)}
            Column(
                modifier = Modifier.fillMaxSize().padding(all = 16.dp)
            ) {
                if (!firstSuccess) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
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
                            firstSuccess = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Далее")
                    }
                } else {
                    TextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text("Login") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = { firstSuccess = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Зарегистрироваться")
                    }
                    Button(
                        onClick = { firstSuccess = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Назад")
                    }
                }
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return emailRegex.matches(email)
}
