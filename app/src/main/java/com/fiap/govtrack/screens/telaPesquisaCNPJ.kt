package com.fiap.govtrack.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fiap.govtrack.R
import com.fiap.govtrack.components.PagamentoCard
import com.fiap.govtrack.components.buttonsComponent
import com.fiap.govtrack.components.userInput
import com.fiap.govtrack.ui.theme.GradientBackground
import com.fiap.govtrack.viewmodel.PagamentosViewModel

@Composable
fun TelaPesquisaCNPJ(navController: NavController?, viewModel: PagamentosViewModel = viewModel()) {
    val pagamentosList by viewModel.pagamentosList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var cnpj by remember { mutableStateOf("") }
    var ano by remember { mutableStateOf("") }
    var showTotalGeral by remember { mutableStateOf(false) }

    val pagamentosAgrupados = pagamentosList
        .filter { it.valor.replace(",", ".").toDoubleOrNull() ?: 0.0 != 0.0 }
        .groupBy { it.ug }
        .mapValues { entry ->
            entry.value.sumOf { pagamento ->
                pagamento.valor.replace(",", ".").toDoubleOrNull() ?: 0.0
            }
        }

    val totalGeral = pagamentosAgrupados.values.sum()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GradientBackground)
        ) {
            IconButton(
                onClick = { navController?.navigate("telaLogin") },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Logout",
                    tint = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp), // Espaço para o botão de logout
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
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
                        .fillMaxWidth()
                        .weight(1f),
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(R.color.white)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        userInput(
                            value = cnpj,
                            placeholder = stringResource(R.string.CNPJ),
                            keyboardType = KeyboardType.Number,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 22.dp, vertical = 8.dp),
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
                                .padding(horizontal = 22.dp, vertical = 8.dp),
                            onValueChange = { ano = it },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = stringResource(R.string.iconeDeConstrucao)
                                )
                            }
                        )
                        buttonsComponent(
                            texto = "Buscar",
                            onClick = {
                                viewModel.buscarPagamentos(cnpj, ano)
                                showTotalGeral = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 22.dp, vertical = 16.dp)
                        )

                        errorMessage?.let {
                            Text(
                                text = it,
                                color = Color.Red,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            )
                        }

                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(vertical = 16.dp)
                            )
                        } else if (pagamentosAgrupados.isNotEmpty()) {
                            if (showTotalGeral) {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp, horizontal = 22.dp)
                                        .border(
                                            width = 1.dp,
                                            color = Color.Gray,
                                            shape = RoundedCornerShape(8.dp)
                                        ),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White
                                    ),
                                    elevation = CardDefaults.cardElevation(4.dp)
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

                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(bottom = 8.dp)
                            ) {
                                items(pagamentosAgrupados.toList()) { (ug, valorTotal) ->
                                    PagamentoCard(
                                        ug = ug,
                                        valorTotal = String.format("R$ %.2f", valorTotal)
                                    )
                                    Divider(
                                        color = Color.LightGray,
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    )
                                }
                            }
                        } else if (showTotalGeral) {
                            Text(
                                text = "Nenhum resultado encontrado.",
                                color = Color.Gray,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(vertical = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PagamentoCard(ug: String, valorTotal: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "UG: $ug",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )
            Text(
                text = "Valor Total: $valorTotal",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaPesquisaPreview() {
    TelaPesquisaCNPJ(null)
}