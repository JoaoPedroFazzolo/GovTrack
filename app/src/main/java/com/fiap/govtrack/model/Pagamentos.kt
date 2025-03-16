package com.fiap.govtrack.model

import com.google.gson.annotations.SerializedName

data class Pagamentos(
    @SerializedName("data") val data: String = "",
    @SerializedName("documento") val documento: String = "",
    @SerializedName("documentoResumido") val documentoResumido: String = "",
    @SerializedName("observacao") val observacao: String = "",
    @SerializedName("funcao") val funcao: String = "",
    @SerializedName("subfuncao") val subfuncao: String = "",
    @SerializedName("programa") val programa: String = "",
    @SerializedName("acao") val acao: String = "",
    @SerializedName("subTitulo") val subTitulo: String = "",
    @SerializedName("localizadorGasto") val localizadorGasto: String = "",
    @SerializedName("fase") val fase: String = "",
    @SerializedName("especie") val especie: String = "",
    @SerializedName("favorecido") val favorecido: String = "",
    @SerializedName("codigoFavorecido") val codigoFavorecido: String = "",
    @SerializedName("nomeFavorecido") val nomeFavorecido: String = "",
    @SerializedName("ufFavorecido") val ufFavorecido: String = "",
    @SerializedName("valor") val valor: String = "",
    @SerializedName("codigoUg") val codigoUg: String = "",
    @SerializedName("ug") val ug: String = "",
    @SerializedName("codigoUo") val codigoUo: String = "",
    @SerializedName("uo") val uo: String = "",
    @SerializedName("codigoOrgao") val codigoOrgao: String = "",
    @SerializedName("orgao") val orgao: String = "",
    @SerializedName("codigoOrgaoSuperior") val codigoOrgaoSuperior: String = "",
    @SerializedName("orgaoSuperior") val orgaoSuperior: String = "",
    @SerializedName("categoria") val categoria: String = "",
    @SerializedName("grupo") val grupo: String = "",
    @SerializedName("elemento") val elemento: String = "",
    @SerializedName("modalidade") val modalidade: String = "",
    @SerializedName("numeroProcesso") val numeroProcesso: String = "",
    @SerializedName("planoOrcamentario") val planoOrcamentario: String = "",
    @SerializedName("autor") val autor: String = "",
    @SerializedName("favorecidoIntermediario") val favorecidoIntermediario: Boolean = false,
    @SerializedName("favorecidoListaFaturas") val favorecidoListaFaturas: Boolean = false
)


