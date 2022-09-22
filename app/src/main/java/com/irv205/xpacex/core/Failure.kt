package com.irv205.xpacex.core

import com.irv205.xpacex.core.utils.ErrorResponse

sealed class Failure {
    object NetWorkConnection : Failure()
    class ServerError (val errorResponse: ErrorResponse): Failure()
    class DebugError (val stackTrace: String): Failure()
    class Unauthorized(val errorResponse: ErrorResponse? = null) : Failure()
}