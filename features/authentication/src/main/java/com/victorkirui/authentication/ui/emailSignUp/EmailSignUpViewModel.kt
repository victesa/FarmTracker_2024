package com.victorkirui.authentication.ui.emailSignUp

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
class EmailSignUpViewModel @Inject constructor(
    private val emailAuthentication: EmailAuthentication,
    private val applicationContext: Application
): ViewModel() {

    private val _uiState = MutableStateFlow(EmailSignUpModel())
    val uiState: StateFlow<EmailSignUpModel> = _uiState.asStateFlow()

    fun onEvent(event: EmailSignUpEvent){
        when(event){
            is EmailSignUpEvent.EnteredEmailAddress -> {
                _uiState.update {
                    it.copy(emailAddress = event.value)
                }
            }

            is EmailSignUpEvent.EnteredPassword ->{
                _uiState.update {
                    it.copy(password = event.value)
                }
            }

            is EmailSignUpEvent.EnteredConfirmPassword ->{
                _uiState.update {
                    it.copy(confirmPassword = event.value)
                }
            }

            is EmailSignUpEvent.ChangePasswordVisibility -> {
                if (uiState.value.visualTransformationOne == PasswordVisualTransformation()){
                    _uiState.update {
                        it.copy(
                            drawableOne = com.google.android.material.R.drawable.design_ic_visibility,
                            visualTransformationOne = VisualTransformation.None
                        )
                    }
                }else{
                    _uiState.update {
                        it.copy(
                            drawableOne = com.google.android.material.R.drawable.design_ic_visibility_off,
                            visualTransformationOne = PasswordVisualTransformation()
                        )
                    }
                }
            }

            is EmailSignUpEvent.ChangeConfirmPasswordVisibility -> {
                if (uiState.value.visualTransformationTwo == PasswordVisualTransformation()){
                    _uiState.update {
                        it.copy(
                            drawableTwo = com.google.android.material.R.drawable.design_ic_visibility,
                            visualTransformationTwo = VisualTransformation.None
                        )
                    }
                }else{
                    _uiState.update {
                        it.copy(
                            drawableTwo = com.google.android.material.R.drawable.design_ic_visibility_off,
                            visualTransformationTwo = PasswordVisualTransformation()
                        )
                    }
                }
            }

            is EmailSignUpEvent.signUpUser ->{
                emailAuthentication.signUpUser(emailAddress = uiState.value.emailAddress,
                    password = uiState.value.password, confirmPassword = uiState.value.confirmPassword, context = applicationContext,
                    onSuccess = {
                        _uiState.update {
                            it.copy(
                                signUpComplete = true
                            )
                        }
                    }, onFail = {
                        _uiState.update {
                            it.copy(
                                showProgressDialog = false
                            )
                        }
                    },
                    onStart = {
                        _uiState.update {
                            it.copy(
                                showProgressDialog = true
                            )
                        }

                    })
            }
        }
    }


    fun showProgressDialogFalse(){
        _uiState.update {
            it.copy(
                showProgressDialog = false
            )
        }
    }



}