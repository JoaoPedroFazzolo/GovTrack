package com.fiap.govtrack.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fiap.govtrack.R
import com.fiap.govtrack.model.Pagamentos

@Composable
fun PagamentoCard(pagamento: Pagamentos) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.white))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Nome Favorecido: ${pagamento.nomeFavorecido}", fontWeight = FontWeight.Bold)
            Text(text = "Valor: R$ ${pagamento.valor}")
            Text(text = "Código UG: ${pagamento.codigoUg}")
        }
    }
}