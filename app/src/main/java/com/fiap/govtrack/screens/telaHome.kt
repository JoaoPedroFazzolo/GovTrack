package com.fiap.govtrack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fiap.govtrack.R
import com.fiap.govtrack.ui.theme.GradientBackground

@Composable
fun TelaHome(navController: NavController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientBackground)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.govtrack_logo),
            contentDescription = "Logo Principal",
            modifier = Modifier
                .padding(top = 150.dp)
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .padding(start = 22.dp, end = 22.dp)
        ) {
            Text(
                text = "GovTrack,",
                color = Color.White.copy(alpha = 0.4f),
                fontSize = 40.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "Seu direito de saber.\nNosso compromisso com a transparência",
                color = Color.White,
                fontSize = 36.sp,
                lineHeight = 45.sp
            )
        }

        Image(
            painter = painterResource(id = R.drawable.shape),
            contentDescription = "Shape",
            modifier = Modifier
                .size(200.dp)
                .offset(x = (-80).dp)
                .align(Alignment.Start)
        )

        Button(
            onClick = { navController?.navigate("telaLogin") },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .padding(bottom = 32.dp, start = 24.dp)
                .size(48.dp)
                .align(Alignment.Start)
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "Arrow",
                modifier = Modifier
                    .size(32.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TelaHomePreview() {
    MaterialTheme {
        TelaHome(navController = null)
    }
}