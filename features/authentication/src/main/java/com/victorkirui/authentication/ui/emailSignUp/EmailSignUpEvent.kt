package com.victorkirui.authentication.ui.emailSignUp

sealed class EmailSignUpEvent {
    data class EnteredEmailAddress(val value: String): EmailSignUpEvent()
    data class EnteredPassword(val value: String): EmailSignUpEvent()
    data class EnteredConfirmPassword(val value: String): EmailSignUpEvent()
    data object ChangePasswordVisibility: EmailSignUpEvent()
    data object ChangeConfirmPasswordVisibility: EmailSignUpEvent()
    data object signUpUser: EmailSignUpEvent()
}