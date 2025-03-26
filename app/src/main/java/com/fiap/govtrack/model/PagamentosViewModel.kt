package com.fiap.govtrack.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiap.govtrack.BuildConfig
import com.fiap.govtrack.service.RetrofitFactory
import com.fiap.govtrack.model.Pagamentos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class PagamentosViewModel : ViewModel() {
    private val apiKey = BuildConfig.API_KEY
    private val pagamentoService = RetrofitFactory().getPagamentoService()

    // Estado para a lista de pagamentos
    private val _pagamentosList = MutableStateFlow<List<Pagamentos>>(emptyList())
    val pagamentosList: StateFlow<List<Pagamentos>> get() = _pagamentosList

    // Estado para indicar carregamento
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    // Estado para mensagens de erro
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    // Estado para armazenar os valores totais por UG
    private val _pagamentosPorUG = MutableStateFlow<Map<String, Double>>(emptyMap())
    val pagamentosPorUG: StateFlow<Map<String, Double>> get() = _pagamentosPorUG

    fun buscarPagamentos(cnpj: String, ano: String, paginaInicial: String = "1") {
        if (cnpj.isBlank() || ano.isBlank() || paginaInicial.isBlank()) {
            _errorMessage.value = "Por favor, preencha todos os campos"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            _pagamentosList.value = emptyList()

            var currentPage = paginaInicial.toIntOrNull() ?: 1
            var hasMoreData = true
            val listaAtualizada = mutableListOf<Pagamentos>()

            try {
                while (hasMoreData) {
                    val response = pagamentoService.getAllPagamentosFavorecido(
                        chaveApi = apiKey,
                        cnpj = cnpj,
                        fase = "3",
                        ano = ano,
                        ordenacaoResultado = "4",
                        pagina = currentPage.toString()
                    )

                    if (response.isEmpty()) {
                        hasMoreData = false
                    } else {
                        listaAtualizada.addAll(response)
                        currentPage++
                    }
                }

                _pagamentosList.value = listaAtualizada
                calcularPagamentosPorUG(listaAtualizada)

            } catch (e: IOException) {
                _errorMessage.value = "Erro de conexão com a internet"
            } catch (e: HttpException) {
                _errorMessage.value = when (e.code()) {
                    400 -> "Requisição inválida. Verifique os parâmetros."
                    401 -> "Chave de API inválida ou não autorizada."
                    404 -> "Nenhum dado encontrado para os parâmetros informados."
                    else -> "Erro na resposta do servidor: ${e.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Erro inesperado: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }


    private fun calcularPagamentosPorUG(lista: List<Pagamentos>) {
        val pagamentosPorUG = lista
            .filter { it.ug != null && it.valor != null } // Garante que os dados estão corretos
            .groupBy { it.ug ?: "UG Desconhecida" } // Evita erros em valores nulos
            .mapValues { (_, pagamentos) ->
                pagamentos.sumOf { pagamento ->
                    pagamento.valor
                        .replace(".", "")  // Remove os pontos de milhar
                        .replace(",", ".") // Substitui a vírgula por ponto
                        .toDoubleOrNull()
                        .also {
                            if (it == null) Log.e("PagamentosViewModel", "Valor inválido encontrado: ${pagamento.valor}")
                        } ?: 0.0
                }
            }

        _pagamentosPorUG.value = pagamentosPorUG
        Log.d("PagamentosViewModel", "Pagamentos por UG: $pagamentosPorUG")
    }


    fun clearError() {
        _errorMessage.value = null
    }
}