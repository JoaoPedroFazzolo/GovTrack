package com.fiap.govtrack.screens

import GradientBackground
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
                        fontWeight = FontWeight.Bold
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
                            }
                        )
                        errorMessage?.let {
                            Text(
                                text = it,
                                color = Color.Red,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                        }

                        if (isLoading) {
                            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
                        } else {
                            LazyColumn {
                                items(pagamentosList) { pagamento ->
                                    PagamentoCard(pagamento)
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