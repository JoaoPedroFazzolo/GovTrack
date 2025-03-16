package com.fiap.govtrack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiap.govtrack.service.RetrofitFactory
import com.fiap.govtrack.model.Pagamentos
import com.fiap.govtrack.model.Resultado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PagamentosViewModel : ViewModel() {
    private val _pagamentosList = MutableStateFlow<List<Pagamentos>>(emptyList())
    val pagamentosList: StateFlow<List<Pagamentos>> get() = _pagamentosList

    private val pagamentoService = RetrofitFactory().getPagamentoService()

    fun buscarPagamentos(cnpj: String, ano: String, pagina: String) {
        pagamentoService.getAllPagamentosFavorecido(cnpj, ano, pagina)
            .enqueue(object : Callback<List<Resultado>> {
                override fun onResponse(call: Call<List<Resultado>>, response: Response<List<Resultado>>) {
                    if (response.isSuccessful) {
                        _pagamentosList.value = response.body() as? List<Pagamentos> ?: emptyList()
                    }
                }
                override fun onFailure(call: Call<List<Resultado>>, t: Throwable) {
                    // Handle failure
                }
            })
    }
}
