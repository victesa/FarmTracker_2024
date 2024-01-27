package com.victorkirui.authentication.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.constraintlayout.widget.Placeholder

@Composable
fun PasswordTextField(value: String,
                      onValueChange: (String) -> Unit,
                      visualTransformation: VisualTransformation,
                      icon: Int, onIconClicked:() -> Unit, placeholderText: String){
    OutlinedTextField(value = value, onValueChange = onValueChange, modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White, focusedContainerColor = Color.White),
        placeholder = {
            Text(text = placeholderText)
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next)
        , visualTransformation = visualTransformation, trailingIcon = {
            Icon(painter = painterResource(id = icon),
                contentDescription = "Show Password", modifier = Modifier.clickable { onIconClicked() })
        }
    )
}