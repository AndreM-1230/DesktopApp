package com.example.desctopapp.interfaces

import com.example.desctopapp.classes.LaraServTest
import retrofit2.Response
import retrofit2.http.GET

interface UserInterface {
    @GET("/api/users")
    suspend fun getQuotes() : Response<LaraServTest>
}