package com.fiap.govtrack.service

import com.fiap.govtrack.model.Pagamentos
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PagamentoService {
    @GET("api-de-dados/despesas/documentos-por-favorecido")
    suspend fun getAllPagamentosFavorecido(
        @Header("chave-api-dados") chaveApi: String,
        @Query("codigoPessoa") cnpj: String,
        @Query("fase") fase: String,
        @Query("ano") ano: String,
        @Query("ordenacaoResultado") ordenacaoResultado: String,
        @Query("pagina") pagina: String
    ): List<Pagamentos>
}