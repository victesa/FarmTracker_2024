package com.victorkirui.authentication.domain.usecases

import android.content.Context
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class ResetPasswordUseCase {
    private val auth = Firebase.auth

    fun sendPasswordResetEmail(email: String, context: Context){
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }
            }
    }
}