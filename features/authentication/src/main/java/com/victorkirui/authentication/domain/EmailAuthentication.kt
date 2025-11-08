package com.victorkirui.authentication.domain

import android.content.Context
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class EmailAuthentication {
    private var auth: FirebaseAuth = Firebase.auth

    private fun checkIfPasswordsMatch(password: String, confirmPassword: String): Boolean{
        return password == confirmPassword
    }

    fun signUpUser(emailAddress: String, password: String, confirmPassword: String, context: Context,
                   onSuccess:() -> Unit, onFail:() -> Unit, onStart:()-> Unit): String{
        var result = ""
        if (checkIfPasswordsMatch(password = password, confirmPassword = confirmPassword)){
            onStart()
            auth.createUserWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        onSuccess()
                    } else{
                        onFail()
                        Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            result = "Fail"
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
        }

        return result

    }

    fun signInUser(emailAddress: String, password: String, context: Context, onNavigateToHomeScreen:() -> Unit){
        auth.signInWithEmailAndPassword(emailAddress,password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    onNavigateToHomeScreen()
                }else{
                    Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}