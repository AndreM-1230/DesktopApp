package com.example.desctopapp.classes

import com.example.desctopapp.RetrofitHelper
import com.example.desctopapp.dataclasses.CreateUserDataClass
import com.example.desctopapp.interfaces.RegistrationInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegistrationClass {

    fun index(login: String, password: String): Flow<Boolean>
    {
        val responseApi = RetrofitHelper.getInstance().create(RegistrationInterface::class.java)

        return flow {
            emit(false)

            val response = try {
                responseApi.getHasUser(login, password)
            } catch (e: Exception) {
                null
            }

            if (response != null) {
                val body = response.body()
                if (body != null) {
                    emit(body.hasUser)
                }
            } else {
                // Обработка ошибки
            }
        }
    }

    fun send(login: String, password: String, name: String, email: String): Flow<Boolean>
    {
        val responseApi = RetrofitHelper.getInstance().create(RegistrationInterface::class.java)
        val userData = CreateUserDataClass(
            login = login,
            password = password,
            name = name,
            email = email,
        )
        return flow {
            emit(false)

            val response = try {
                responseApi.storeUser(userData)
            } catch (e: Exception) {
                null
            }

            if (response != null) {
                val body = response.body()
                if (body != null) {
                    emit(body.hasUser)
                }
            } else {
                // Обработка ошибки
            }
        }
    }
}
