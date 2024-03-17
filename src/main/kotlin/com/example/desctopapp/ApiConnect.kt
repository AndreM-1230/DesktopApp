package com.example.desctopapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BaseUrl = "http://127.0.0.1:8000/api/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}