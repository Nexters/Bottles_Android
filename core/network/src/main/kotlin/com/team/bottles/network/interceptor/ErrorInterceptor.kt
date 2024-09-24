package com.team.bottles.network.interceptor

import com.team.bottles.exception.BottlesException
import com.team.bottles.exception.parseWithNetworkError
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import javax.inject.Inject

class ErrorInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val response = chain.proceed(chain.request())
            val responseBody = response.body

            if (response.isSuccessful) {
                return response
            }

            responseBody?.let { body ->
                val json = JSONObject(body.string())
                val code = response.code
                val message = json.optString("message", "고객 센터에 문의하여 주세요.")

                if (code == 400 || code == 500) {
                    throw BottlesException(
                        code = code,
                        message = message
                    )
                }
            }

            return response
        } catch (e: BottlesException) {
            throw e
        } catch (e: Exception) {
            throw e.parseWithNetworkError()
        }
    }

}