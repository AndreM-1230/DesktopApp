
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.desctopapp.dataclasses.ThemeDataClass
import com.google.gson.Gson
import java.io.File

@Composable
fun setting(title: String, theme: ThemeDataClass)
{

    val themesDir = File("src/main/resources/themes")
    val themesData = mutableListOf<ThemeDataClass>()
    Column {
        Text(title)
        themesDir.listFiles()?.forEach { file ->
            val json = file.readText()
            val gson = Gson()
            val data = gson.fromJson(json, ThemeDataClass::class.java)
            themesData.add(data)
            Text(data.nameRu ?: "-")
        }
    }


}