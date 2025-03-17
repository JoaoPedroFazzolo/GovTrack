package com.fiap.govtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fiap.govtrack.screens.TelaCadastro
import com.fiap.govtrack.screens.TelaLogin
import com.fiap.govtrack.screens.TelaPesquisaCNPJ
import com.fiap.govtrack.screens.TelasNavegacao
import com.fiap.govtrack.ui.theme.GovTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GovTrackTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "telasNavegacao",
                    ){
                    composable(route = "home") {  }
                    composable(route = "telasNavegacao") { TelasNavegacao(navController) }
                    composable(route = "cadastro") { TelaCadastro(navController) }
                    composable(route = "login") { TelaLogin(navController) }
                    composable(route = "resetSenha") {  }
                    composable(route = "pesquisaCnpj") { TelaPesquisaCNPJ(navController) }
                    composable(route = "graficos") {  }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GovTrackTheme {
        Greeting("Android")
    }
}