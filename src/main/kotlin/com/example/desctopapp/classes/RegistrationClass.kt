package com.example.desctopapp.classes

import com.example.desctopapp.interfaces.RegistrationInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegistrationClass {

    fun index(): Flow<Boolean>
    {
        val responseApi = RetrofitHelper.getInstance().create(RegistrationInterface::class.java)

        return flow {
            emit(false)

            val response = try {
                responseApi.getHasUser()
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
