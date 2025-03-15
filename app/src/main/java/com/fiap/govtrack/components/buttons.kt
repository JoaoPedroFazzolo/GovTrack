package com.fiap.govtrack.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun buttonsComponent(
    texto: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        modifier = Modifier.padding(all = 20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF0C0C0C),
            contentColor = Color(0xFFFFFFFF),
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.LightGray
            ),
        onClick = onClick,
        enabled = enabled,
        shape = ButtonDefaults.shape,
        elevation = ButtonDefaults.buttonElevation(10.dp),
        border = null,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Text(
            text = texto,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun buttonsComponentPreview() {
    buttonsComponent("Cadastre-se", onClick = {})
}