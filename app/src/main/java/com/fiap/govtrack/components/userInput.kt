package com.fiap.govtrack.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fiap.govtrack.R

@Composable
fun userInput(
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier,
        placeholder = {
            Text(text = placeholder)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = trailingIcon,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = colorResource(R.color.inputBackgroudColor) ,
            unfocusedContainerColor = colorResource(R.color.inputBackgroudColor),
            disabledContainerColor = colorResource(R.color.inputBackgroudColor)
        ),
        shape = RoundedCornerShape(16.dp)
    )
}

@Preview
@Composable
private fun userInputPreview() {
    userInput(
        value = "",
        placeholder = "CNPJPLACHOLDER",
        keyboardType = KeyboardType.Number,
        onValueChange = {},
        trailingIcon = {
            Icon(imageVector = Icons.Filled.Home, contentDescription = stringResource(R.string.iconeDeConstrucao))
        }
    )
}