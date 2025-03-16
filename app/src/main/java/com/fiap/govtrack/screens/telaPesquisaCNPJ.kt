package com.fiap.govtrack.screens

import GradientBackground
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.fiap.govtrack.components.userInput


@Composable
fun TelaPesquisaCNPJ(navController: NavController?) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        GradientBackground{
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
                        .fillMaxSize().padding(top = 20.dp),
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(R.color.white)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        userInput(
                            value = "",
                            placeholder = stringResource(R.string.CNPJ),
                            keyboardType = KeyboardType.Number,
                            modifier = Modifier.fillMaxWidth().padding(top = 22.dp, start = 22.dp, end = 22.dp),
                            onValueChange = { },
                            trailingIcon = {
                                Icon(imageVector = Icons.Default.BusinessCenter, contentDescription = stringResource(R.string.iconeDeConstrucao))
                            }
                        )
                        userInput(
                            value = "",
                            placeholder = stringResource(R.string.ano),
                            keyboardType = KeyboardType.Number,
                            modifier = Modifier.fillMaxWidth().padding(top = 22.dp, start = 22.dp, end = 22.dp),
                            onValueChange = { },
                            trailingIcon = {
                                Icon(imageVector = Icons.Filled.DateRange, contentDescription = stringResource(R.string.iconeDeConstrucao))
                            }
                        )
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