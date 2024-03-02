package com.example.desctopapp.interfaces

import com.example.desctopapp.dataclasses.RegistrationDataClass
import retrofit2.Response
import retrofit2.http.GET

interface RegistrationInterface {
    @GET("/api/registration")
    suspend fun getHasUser() : Response<RegistrationDataClass>

    //@POST("/api/registration")
    //suspend fun getStoreUser() : Response<LaraServTest>
}