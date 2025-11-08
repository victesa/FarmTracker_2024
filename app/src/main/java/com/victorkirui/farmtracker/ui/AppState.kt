package com.victorkirui.farmtracker.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.util.trace
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

@Composable
fun rememberAppState(
    windowSizeClass: WindowSizeClass,
    firebaseAuth: FirebaseAuth = Firebase.auth,
    navController: NavHostController
): AppState {

    var isSignedIn by remember { mutableStateOf(firebaseAuth.currentUser) }

    firebaseAuth.addAuthStateListener {
        val currentUser = it.currentUser
        if (currentUser != null && currentUser.uid != isSignedIn?.uid) {
            isSignedIn = currentUser
        } else if (currentUser == null && isSignedIn != null) {
            isSignedIn = null
        }
    }

    return remember(windowSizeClass) {
        AppState(navController,windowSizeClass, isSignedIn)
    }
}

class AppState(
    private val navController: NavHostController,
    private val windowSizeClass: WindowSizeClass,
    private val currentUser: FirebaseUser?
) {
    val currentScreenSize: WindowWidthSizeClass
        get() = windowSizeClass.widthSizeClass

    val permissionList = listOf(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    fun navigateToNextScreen(route: String){
        trace("NavHost"){

        }
    }

    val permissionDeniedList = listOf<String>()

    val isSignedIn: Boolean
        get() = currentUser != null

    val userName: String?
        get() = currentUser?.displayName

    val userProfilePhoto: String?
        get() = currentUser?.photoUrl?.toString()
}
