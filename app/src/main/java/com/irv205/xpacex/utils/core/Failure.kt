package com.irv205.xpacex.utils.core

import com.irv205.xpacex.domain.ErrorResponse

sealed class Failure {
    object NetWorkConnection : Failure()
    class ServerError (val errorResponse: ErrorResponse): Failure()
    class DebugError (val stackTrace: String): Failure()
    class Unauthorized(val errorResponse: ErrorResponse? = null) : Failure()
}