package com.victorkirui.finance.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
internal fun FinanceOutlinedTextField(value: String,
                                      onValueChange:(String) -> Unit,
                                      placeholderText: String,
                                      keyboardType: KeyboardType,
                                      error: Boolean,
                                      widthPercentage: Float,
                                      minLines: Int,
                                      maxLines: Int){
    OutlinedTextField(value = value,
        onValueChange = {onValueChange(it)},
        placeholder = { Text(text = placeholderText)},
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        isError = error,
        modifier = Modifier.fillMaxWidth(widthPercentage),
        minLines = minLines,
        maxLines = maxLines
    )
}