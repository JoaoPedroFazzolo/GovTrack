package com.fiap.govtrack.service

import com.fiap.govtrack.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val BASE_URL = "https://api.portaldatransparencia.gov.br/"
    val apiKey = BuildConfig.API_KEY
    private val client = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor(apiKey))
        .build()

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPagamentoService(): PagamentoService{
        return retrofitFactory.create(PagamentoService::class.java)
    }
}
