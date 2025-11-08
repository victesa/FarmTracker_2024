package com.victorkirui.authentication.ui.emailSignIn

import android.app.Application
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.ViewModel
import com.victorkirui.authentication.domain.EmailAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EmailSignInViewModel @Inject constructor(
    private val emailAuthentication: EmailAuthentication,
    private val applicationContext: Application
): ViewModel() {
    private val _uiState = MutableStateFlow(EmailSignInModel())
    val uiState: StateFlow<EmailSignInModel> = _uiState.asStateFlow()


    fun onEvent(event: EmailSignInEvent){
        when(event){
            is EmailSignInEvent.EnteredEmailAddress -> {
                _uiState.update {
                    it.copy(emailAddress = event.value)
                }
            }

            is EmailSignInEvent.EnteredPassword ->{
                _uiState.update {
                    it.copy(password = event.value)
                }
            }

            is EmailSignInEvent.ChangePasswordVisibility -> {
                if (!uiState.value.isVisible){
                    _uiState.update {
                        it.copy(
                            drawable = com.google.android.material.R.drawable.design_ic_visibility,
                            isVisible = true,
                            visualTransformation = VisualTransformation.None
                        )
                    }
                }else{
                    _uiState.update {
                        it.copy(
                            drawable = com.google.android.material.R.drawable.design_ic_visibility_off,
                            isVisible = false,
                            visualTransformation = PasswordVisualTransformation()
                        )
                    }
                }
            }

            is EmailSignInEvent.SignInUser ->{
                emailAuthentication.signInUser(uiState.value.emailAddress, uiState.value.password, event.context, event.onNavigateToHomeScreen)
            }
        }
    }
}