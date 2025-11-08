package com.victorkirui.authentication.ui.emailSignIn

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

data class EmailSignInModel(
    val emailAddress: String = "",
    val password: String = "",
    val isVisible: Boolean = false,
    val visualTransformation: VisualTransformation = PasswordVisualTransformation(),
    val drawable: Int = com.google.android.material.R.drawable.design_ic_visibility_off
)
