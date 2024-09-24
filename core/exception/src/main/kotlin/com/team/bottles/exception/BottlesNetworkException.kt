package com.team.bottles.exception

import com.team.bottles.exception.BottlesNetworkException.Companion.CONNECTION_EXCEPTION_CODE
import com.team.bottles.exception.BottlesNetworkException.Companion.CONNECTION_EXCEPTION_MESSAGE
import com.team.bottles.exception.BottlesNetworkException.Companion.NO_ROUTE_TO_HOST_EXCEPTION_CODE
import com.team.bottles.exception.BottlesNetworkException.Companion.NO_ROUTE_TO_HOST_EXCEPTION_MESSAGE
import com.team.bottles.exception.BottlesNetworkException.Companion.SOCKET_EXCEPTION_CODE
import com.team.bottles.exception.BottlesNetworkException.Companion.SOCKET_EXCEPTION_MESSAGE
import com.team.bottles.exception.BottlesNetworkException.Companion.SOCKET_TIMEOUT_EXCEPTION_CODE
import com.team.bottles.exception.BottlesNetworkException.Companion.SOCKET_TIMEOUT_EXCEPTION_MESSAGE
import com.team.bottles.exception.BottlesNetworkException.Companion.UNKNOWN_HOST_EXCEPTION_CODE
import com.team.bottles.exception.BottlesNetworkException.Companion.UNKNOWN_HOST_EXCEPTION_MESSAGE
import java.io.IOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BottlesNetworkException(
    val code: Int,
    override val message: String?,
) : IOException(message) {

    companion object {
        const val SOCKET_TIMEOUT_EXCEPTION_CODE = 1001
        const val CONNECTION_EXCEPTION_CODE = 1002
        const val NO_ROUTE_TO_HOST_EXCEPTION_CODE = 1003
        const val SOCKET_EXCEPTION_CODE = 1004
        const val UNKNOWN_HOST_EXCEPTION_CODE = 1005

        const val SOCKET_TIMEOUT_EXCEPTION_MESSAGE = "서버 응답이 지연되고 있습니다. 잠시 후 다시 시도해주세요."
        const val CONNECTION_EXCEPTION_MESSAGE = "서버에 연결할 수 없습니다. 인터넷 연결을 확인해주세요."
        const val NO_ROUTE_TO_HOST_EXCEPTION_MESSAGE = "잘못된 접근 입니다."
        const val SOCKET_EXCEPTION_MESSAGE = "인터넷 연결이 끊겼습니다. 연결을 확인하고 다시 시도해주세요."
        const val UNKNOWN_HOST_EXCEPTION_MESSAGE = "인터넷 연결이 끊겼습니다. 연결을 확인하고 다시 시도해주세요."
    }
}

fun Exception.parseWithNetworkError(): Exception {
    val exceptionMapping = mapOf(
        SocketTimeoutException::class to BottlesNetworkException(
            code = SOCKET_TIMEOUT_EXCEPTION_CODE,
            message = SOCKET_TIMEOUT_EXCEPTION_MESSAGE
        ),
        ConnectException::class to BottlesNetworkException(
            code = CONNECTION_EXCEPTION_CODE,
            message = CONNECTION_EXCEPTION_MESSAGE
        ),
        NoRouteToHostException::class to BottlesNetworkException(
            code = NO_ROUTE_TO_HOST_EXCEPTION_CODE,
            message = NO_ROUTE_TO_HOST_EXCEPTION_MESSAGE
        ),
        SocketException::class to BottlesNetworkException(
            code = SOCKET_EXCEPTION_CODE,
            message = SOCKET_EXCEPTION_MESSAGE
        ),
        UnknownHostException::class to BottlesNetworkException(
            code = UNKNOWN_HOST_EXCEPTION_CODE,
            message = UNKNOWN_HOST_EXCEPTION_MESSAGE
        )
    )

    val mappedException = exceptionMapping[this::class] ?: this

    return mappedException
}