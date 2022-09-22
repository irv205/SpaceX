package com.irv205.xpacex.core

import android.util.Log
import com.google.gson.JsonParseException
import com.irv205.xpacex.core.utils.ErrorResponse
import org.json.JSONObject
import retrofit2.Call

interface ApiRequest {

    fun <T, R> makeRequest(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> {
                    try {
                        val errorBody
                        = JSONObject(response.errorBody()?.string()?:"{\"message\":\"Server Error\"}, \"errors\":[\"Server Error\"]")

                        when(response.code()){
                            401 -> Either.Left(
                                Failure.Unauthorized(
                                    (ErrorResponse(
                                        response.code(),
                                        errorBody.getString("message")
                                    ))
                                )
                            )
                            else -> Either.Left(
                                Failure.ServerError(
                                    ErrorResponse(
                                        response.code(),
                                        errorBody.getString("message")
                                    )
                                )
                            )
                        }
                    }
                    catch (e: JsonParseException){
                        Either.Left(
                            Failure.ServerError(
                                ErrorResponse(
                                    response.code(),
                                    "Error de servidor"
                                )
                            )
                        )
                    }
                }
            }
        } catch (exception: Throwable) {
            Log.e("ApiRequest", exception.message?:"message empty", exception)
            Either.Left(
                Failure.DebugError(
                    "message = {\n${exception.message ?: "null"}\n}\n" +
                            "stackTrace = {\n${exception.stackTrace?.contentToString() ?: "null"}\n}"
                )
            )
        }
    }
}