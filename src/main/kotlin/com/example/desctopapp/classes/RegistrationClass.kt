package com.example.desctopapp.classes

import com.example.desctopapp.RetrofitHelper
import com.example.desctopapp.dataclasses.CreateUserDataClass
import com.example.desctopapp.dataclasses.UserDataClass
import com.example.desctopapp.interfaces.RegistrationInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.sql.DriverManager

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

    fun get(email: String, password: String): Flow<UserDataClass>
    {
        val responseApi = RetrofitHelper.getInstance().create(RegistrationInterface::class.java)
        return flow {
            try {
                val response = responseApi.getUserData(email, password)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        emit(body)
                    } else {
                        null // Если ответ от сервера пустой
                    }
                } else {
                    null // Если запрос не удался из-за ошибки на сервере (например, 404, 500 и т. д.)
                }
            } catch (e: Exception) {
                null // Если произошла ошибка во время выполнения запроса (например, отсутствует подключение к интернету)
            }
        }
    }

    fun send(email: String, password: String): Flow<Boolean>
    {
        val responseApi = RetrofitHelper.getInstance().create(RegistrationInterface::class.java)
        val userData = CreateUserDataClass(
            email = email,
            password = password,
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

    fun storeUser(userData: UserDataClass?): Boolean {
        if (userData != null) {
            val connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")
            try {
                // Формируем SQL-запрос INSERT
                val query = "INSERT INTO users " +
                        "(id, login, password, name, description, created_at, last_connection_at, phone, email, status, type, updated_at) " +
                        "VALUES " +
                        "('${userData.id}', '${userData.login}', '${userData.password}', '${userData.name}', " +
                        "'${userData.description ?: ""}', ${userData.created}, ${userData.lastConnection ?: "null"}, " +
                        "${userData.phone ?: "null"}, '${userData.email}', ${userData.status}, ${userData.type}, " +
                        "${userData.updated ?: "null"})"

                // Создаем объект Statement и выполняем запрос
                val statement = connection.createStatement()
                statement.executeUpdate(query)

                // Если запрос выполнен успешно, возвращаем true
                return true
            } catch (e: Exception) {
                e.printStackTrace()
                // Если произошла ошибка при выполнении запроса, возвращаем false
                return false
            } finally {
                // Закрываем ресурсы в блоке finally, чтобы убедиться, что они будут закрыты
                connection.close()
            }
        } else {
            // Если переданные данные пользователя равны null, возвращаем false
            return false
        }
    }

}
