package com.example.desctopapp.classes

import com.example.desctopapp.RetrofitHelper
import com.example.desctopapp.dataclasses.UserDataClass
import com.example.desctopapp.interfaces.RegistrationInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jetbrains.skiko.currentNanoTime

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

    fun send(login: String, password: String, email: String): Flow<Boolean>
    {
        val responseApi = RetrofitHelper.getInstance().create(RegistrationInterface::class.java)
        val userData = UserDataClass(
            id = 0,
            login = login,
            password = password,
            name = login,
            description = null,
            dateCreated = currentNanoTime(),
            dateLastConnection = currentNanoTime(), // или любое другое значение
            phone = null, // или любое другое значение
            email = email,
            status = 1, // или любое другое значение
            type = 1 // или любое другое значение
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
