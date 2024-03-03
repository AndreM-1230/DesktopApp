package com.example.desctopapp.classes

import com.example.desctopapp.RetrofitHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.http.GET


interface QuotesApi {
    @GET("/api/users")
    suspend fun getQuotes() : Response<LaraServTest>
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

data class LaraServTest(
    val message: String
)


fun onCreate(): Flow<List<LaraServTest>> {
    val quotesApi = RetrofitHelper.getInstance().create(QuotesApi::class.java)

    return flow {
        emit(emptyList())

        val quoteResponse = try {
            quotesApi.getQuotes()
        } catch (e: Exception) {
            null
        }

        if (quoteResponse != null) {
            val quote = quoteResponse.body()
            if (quote != null) {
                emit(listOf(quote))
            }
        } else {
            // Обработка ошибки
        }
    }
}

