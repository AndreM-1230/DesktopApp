
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitHelper {

    private const val BaseUrl = "https://quotable.io/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

interface QuotesApi {
    @GET("/quotes")
    suspend fun getQuotes() : Response<QuoteList>
}

data class Result(
    val id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)

data class QuoteList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
)

fun onCreate(): Flow<List<Result>> {
    val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)

    return flow {
        emit(emptyList())

        val quoteList = try {
            quotesApi.getQuotes().body()
        } catch (e: Exception) {
            null
        }

        if (quoteList != null) {
            emit(quoteList.results)
        } else {
            //
        }
    }
}

