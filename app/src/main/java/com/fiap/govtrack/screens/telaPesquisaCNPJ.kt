package com.fiap.govtrack.screens

import GradientBackground
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fiap.govtrack.R
import com.fiap.govtrack.components.PagamentoCard
import com.fiap.govtrack.components.buttonsComponent
import com.fiap.govtrack.components.userInput
import com.fiap.govtrack.viewmodel.PagamentosViewModel
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun TelaPesquisaCNPJ(navController: NavController?, viewModel: PagamentosViewModel = viewModel()) {
    // Conectar os estados do ViewModel à UI
    val pagamentosList by viewModel.pagamentosList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // Estados locais para os campos de entrada
    var cnpj by remember { mutableStateOf("") }
    var ano by remember { mutableStateOf("") }

    // Estado para controlar a exibição do Total Geral
    var showTotalGeral by remember { mutableStateOf(false) }

    // Função para agrupar e somar os valores por UG, ignorando valores zero
    val pagamentosAgrupados = pagamentosList
        .filter { it.valor.replace(",", ".").toDoubleOrNull() ?: 0.0 != 0.0 } // Filtra valores diferentes de zero
        .groupBy { it.ug } // Agrupa pelo nome da UG
        .mapValues { entry ->
            entry.value.sumOf { pagamento ->
                pagamento.valor.replace(",", ".").toDoubleOrNull() ?: 0.0 // Soma os valores
            }
        }

    // Calcula o total geral de todos os valores agrupados
    val totalGeral = pagamentosAgrupados.values.sum()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        GradientBackground {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.iconeDePesquisa),
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = stringResource(R.string.facaSuaPesquisa),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(R.color.white)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        userInput(
                            value = cnpj,
                            placeholder = stringResource(R.string.CNPJ),
                            keyboardType = KeyboardType.Number,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 22.dp, start = 22.dp, end = 22.dp),
                            onValueChange = { cnpj = it },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.BusinessCenter,
                                    contentDescription = stringResource(R.string.iconeDeConstrucao)
                                )
                            }
                        )
                        userInput(
                            value = ano,
                            placeholder = stringResource(R.string.ano),
                            keyboardType = KeyboardType.Number,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 22.dp, start = 22.dp, end = 22.dp),
                            onValueChange = { ano = it },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.DateRange,
                                    contentDescription = stringResource(R.string.iconeDeConstrucao)
                                )
                            }
                        )
                        buttonsComponent(
                            texto = "Buscar",
                            onClick = {
                                viewModel.buscarPagamentos(cnpj, ano)
                                showTotalGeral = true // Exibe o Total Geral após o clique
                            }
                        )
                        errorMessage?.let {
                            Text(
                                text = it,
                                color = Color.Red,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        }

                        // Exibe o Total Geral apenas se showTotalGeral for true
                        if (showTotalGeral) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp)
                                    .border(
                                        width = 1.dp,
                                        color = Color.Black,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                shape = RoundedCornerShape(8.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.Transparent
                                )
                            ) {
                                Text(
                                    text = "Total Geral: ${String.format("R$ %.2f", totalGeral)}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
                            }
                        }

                        if (isLoading) {
                            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
                        } else {
                            LazyColumn {
                                // Exibe os resultados agrupados por UG, ignorando valores zero
                                items(pagamentosAgrupados.toList()) { (ug, valorTotal) ->
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        Text(
                                            text = "UG: $ug",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 18.sp
                                        )
                                        Text(
                                            text = "Valor Total: ${String.format("R$ %.2f", valorTotal)}",
                                            fontSize = 16.sp
                                        )
                                    }
                                    Divider() // Adiciona uma linha divisória entre os itens
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaPesquisaPreview() {
    TelaPesquisaCNPJ(null)
}