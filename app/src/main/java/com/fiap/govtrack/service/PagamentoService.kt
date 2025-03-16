package com.fiap.govtrack.service

import com.fiap.govtrack.model.Resultado
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PagamentoService {

    @GET("api-de-dados/despesas/documentos-por-favorecido")
    fun getAllPagamentosFavorecido(
        @Query("codigoPessoa") cnpj: String,
        @Query("fase") fase: String = "3",  // valor padrão
        @Query("ano") ano: String,
        @Query("ordenacaoResultado") ordenacaoResultado: String = "4",  // valor padrão
        @Query("pagina") pagina: String = "1"
    ): Call<List<Resultado>>
}
