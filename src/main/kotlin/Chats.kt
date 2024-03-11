/*@Composable
fun ChatView(text: String) {
    val connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("SELECT * FROM users")
    //var quotes by remember { mutableStateOf<List<LaraServTest>>(emptyList()) }
    //LaunchedEffect(Unit) {
    //    onCreate().collect { result ->quotes = result }
    //}
    val registrationClass = RegistrationClass()

    var hasUser by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        registrationClass.index(login, password).collect { result: Boolean ->
            hasUser = result
        }
    }

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
*/