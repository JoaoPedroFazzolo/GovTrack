package com.fiap.govtrack.service

import com.fiap.govtrack.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val newRequest: Request = originalRequest.newBuilder()
            .addHeader("chave-api-dados", apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}