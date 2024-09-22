package com.team.bottles.exception

import com.team.bottles.exception.BottlesNetworkException.Companion.INTERNAL_SERVER_ERROR_CODE
import com.team.bottles.exception.BottlesNetworkException.Companion.INTERNAL_SERVER_ERROR_MESSAGE
import com.team.bottles.exception.BottlesNetworkException.Companion.UNKNOWN_ERROR
import com.team.bottles.exception.BottlesNetworkException.Companion.UNKNOWN_ERROR_MESSAGE
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class BottlesNetworkException(
    code: Int,
    override val message: String?,
) : BottlesException(code, message) {
    companion object {
        const val INTERNAL_SERVER_ERROR_CODE = 500
        const val INTERNAL_SERVER_ERROR_MESSAGE = "네트워크 연결을 확인할 수 없습니다. 잠시 후 다시 시도해주세요."

        const val UNKNOWN_ERROR = -9999
        const val UNKNOWN_ERROR_MESSAGE = "알수 없는 에러가 발생하였습니다."
    }
}

fun Exception.parseWithNetworkError(): BottlesNetworkException {
    return when (this) {
        is UnknownHostException,
        is SocketException,
        is SocketTimeoutException,
        -> {
            BottlesNetworkException(
                code = INTERNAL_SERVER_ERROR_CODE,
                message = INTERNAL_SERVER_ERROR_MESSAGE,
            )
        }

        else -> {
            BottlesNetworkException(
                code = UNKNOWN_ERROR,
                message = UNKNOWN_ERROR_MESSAGE,
            )
        }
    }
}