package com.fiap.govtrack.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiap.govtrack.components.buttonsComponent

@Composable
fun TelasNavegacao(navController: NavController?) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.background(Color.Cyan).padding(top = 64.dp, start = 32.dp)
        ) {
            buttonsComponent("home", onClick = {navController?.navigate("home")})
            buttonsComponent("cadastro", onClick = {navController?.navigate("cadastro")})
            buttonsComponent("login", onClick = {navController?.navigate("login")})
            buttonsComponent("resetSenha", onClick = {navController?.navigate("resetSenha")})
            buttonsComponent("pesquisaCnpj", onClick = {navController?.navigate("pesquisaCnpj")})
            buttonsComponent("graficos", onClick = {navController?.navigate("graficos")})
        }

    }

}

@Preview
@Composable
private fun TelasNavegacaoPreview() {
    TelasNavegacao(null)
}