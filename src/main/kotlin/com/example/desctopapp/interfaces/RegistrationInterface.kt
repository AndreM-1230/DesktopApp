package com.example.desctopapp.interfaces

import com.example.desctopapp.dataclasses.CreateUserDataClass
import com.example.desctopapp.dataclasses.RegistrationDataClass
import com.example.desctopapp.dataclasses.UserDataClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query



interface RegistrationInterface {
    @GET("/api/registration")
    suspend fun getHasUser(@Query("email, password") email: String, password: String) : Response<RegistrationDataClass>

    @GET("/api/registration/get-data")
    suspend fun getUserData(@Query("email, password") email: String, password: String) : Response<UserDataClass>

    @POST("/api/registration")
    suspend fun storeUser(@Body userData: CreateUserDataClass) : Response<RegistrationDataClass>
}