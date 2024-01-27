package com.victorkirui.authentication.ui.emailSignIn

sealed class EmailSignInEvent{

    data class EnteredEmailAddress(val value: String): EmailSignInEvent()
    data class EnteredPassword(val value: String): EmailSignInEvent()
    data object ChangePasswordVisibility: EmailSignInEvent()
    data object SignInUser: EmailSignInEvent()

}
