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
import com.fiap.govtrack.screens.TelaHome
import com.fiap.govtrack.screens.TelaLogin
import com.fiap.govtrack.screens.TelaPesquisaCNPJ
import com.fiap.govtrack.screens.TelaRecuperacaoSenha
import com.fiap.govtrack.ui.theme.GovTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GovTrackTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "telaHome"
                ) {
                    composable(route = "telaHome") { TelaHome(navController) }
                    composable(route = "telaCadastro") { TelaCadastro(navController) }
                    composable(route = "telaLogin") { TelaLogin(navController) }
                    composable(route = "telaRecuperacaoSenha") { TelaRecuperacaoSenha(navController) }
                    composable(route = "telaPesquisaCNPJ") { TelaPesquisaCNPJ(navController) }
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