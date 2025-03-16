package com.fiap.govtrack.screens

import GradientBackground
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fiap.govtrack.R


@Composable
fun TelaPesquisaCNPJ(navController: NavController?) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        GradientBackground{
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
                    color = Color.White, fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaPesquisaPreview() {
    TelaPesquisaCNPJ(null)
}