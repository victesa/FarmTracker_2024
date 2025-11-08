package com.victorkirui.production.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AddProductionOutlinedTextField(placeholderText: String,
                                   value: String,
                                   onValueChange:(String) -> Unit,
                                   keyboardType: KeyboardType,
                                   errorBoolean: Boolean,
                                   widthPercentage: Float){
    OutlinedTextField(value = value,
        onValueChange = {onValueChange(it)},
        modifier = Modifier.fillMaxWidth(widthPercentage),
        placeholder = {
            Text(text = placeholderText)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        isError = errorBoolean
    )
}