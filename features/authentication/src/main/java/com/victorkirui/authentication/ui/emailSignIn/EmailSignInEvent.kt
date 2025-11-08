package com.victorkirui.authentication.ui.emailSignIn

import android.content.Context

sealed class EmailSignInEvent{

    data class EnteredEmailAddress(val value: String): EmailSignInEvent()
    data class EnteredPassword(val value: String): EmailSignInEvent()
    data object ChangePasswordVisibility: EmailSignInEvent()
    data class SignInUser(val context: Context, val onNavigateToHomeScreen:() -> Unit): EmailSignInEvent()

}
