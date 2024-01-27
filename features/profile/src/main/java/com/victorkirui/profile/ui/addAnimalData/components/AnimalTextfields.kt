package com.victorkirui.profile.ui.addAnimalData.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun AnimalDataTextFields(value: String, onValueChange:(String) -> Unit, placeholder: String){
    OutlinedTextField(value = value, onValueChange = onValueChange, modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White),
        placeholder = {
            Text(text = placeholder)
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next)
    )
}