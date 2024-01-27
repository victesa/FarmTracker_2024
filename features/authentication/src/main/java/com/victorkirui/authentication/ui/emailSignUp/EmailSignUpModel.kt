package com.victorkirui.authentication.ui.emailSignUp

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

data class EmailSignUpModel(
    val emailAddress: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val visualTransformationOne: VisualTransformation = PasswordVisualTransformation(),
    val visualTransformationTwo: VisualTransformation = PasswordVisualTransformation(),
    val drawableOne: Int = com.google.android.material.R.drawable.design_ic_visibility_off,
    val drawableTwo: Int = com.google.android.material.R.drawable.design_ic_visibility_off,
    val showProgressDialog: Boolean = false,
    val signUpComplete: Boolean = false
)
