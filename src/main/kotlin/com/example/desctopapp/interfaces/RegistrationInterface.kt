package com.example.desctopapp.interfaces

import com.example.desctopapp.dataclasses.CreateUserDataClass
import com.example.desctopapp.dataclasses.HasUser
import com.example.desctopapp.dataclasses.UserDataClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface RegistrationInterface {
    @GET("registration/has-user")
    suspend fun getHasUser(@Query("email") email: String, @Query("password") password: String) : Response<HasUser>

    @GET("registration/get-data")
    suspend fun getUserData(@Query("email") email: String, @Query("password") password: String) : Response<UserDataClass>

    @POST("registration/create-user")
    suspend fun storeUser(@Body userData: CreateUserDataClass) : Response<UserDataClass>
}