import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.desctopapp.ThemeObject
import com.example.desctopapp.classes.RegistrationClass
import com.example.desctopapp.templates.ChatsListTemplate
import java.sql.DriverManager

@Composable
fun panel_chats(text: String) {
    val connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")
    val statement = connection.createStatement()
    val statements2 = connection.createStatement()
    val resultSet = statement.executeQuery("SELECT * FROM chats_users")
    var currentTheme = remember { mutableStateOf(ThemeObject.current) }
    val chatsList = ChatsListTemplate()
    val registrationClass = RegistrationClass()
    var chats by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = ThemeObject.current) {
        currentTheme.value = ThemeObject.current
    }
    Column {
        chatsList.chatListTemplate(
            "Написать",
            "",
            Color(currentTheme.value!!.subColor.toLong(16))
        )
        while (resultSet.next()) {
            val lastMessage = statements2.executeQuery("SELECT * FROM chat_"+resultSet.getString("chatId")+" ORDER BY id DESC LIMIT 1")
            chatsList.chatListTemplate(
                lastMessage.getString("message"),
                resultSet.getString("title"),
                Color(currentTheme.value!!.subColor.toLong(16))
            )
        }
    }
    connection.close()
}