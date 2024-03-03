package com.example.desctopapp.interfaces

import com.example.desctopapp.dataclasses.RegistrationDataClass
import com.example.desctopapp.dataclasses.UserDataClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RegistrationInterface {
    @GET("/api/registration")
    suspend fun getHasUser(@Query("login, password") login: String, password: String) : Response<RegistrationDataClass>

    @POST("/api/registration")
    suspend fun storeUser(@Body userData: UserDataClass) : Response<RegistrationDataClass>
}