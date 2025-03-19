package com.fiap.govtrack.viewmodel

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
    val apiKey = BuildConfig.API_KEY
    // Estado para a lista de pagamentos
    private val _pagamentosList = MutableStateFlow<List<Pagamentos>>(emptyList())
    val pagamentosList: StateFlow<List<Pagamentos>> get() = _pagamentosList

    // Estado para indicar carregamento
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    // Estado para mensagens de erro
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val pagamentoService = RetrofitFactory().getPagamentoService()

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
                        _pagamentosList.value = _pagamentosList.value + response
                        currentPage++
                    }
                }
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

    fun clearError() {
        _errorMessage.value = null
    }
}