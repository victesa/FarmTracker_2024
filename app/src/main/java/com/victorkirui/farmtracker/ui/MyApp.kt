package com.victorkirui.farmtracker.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorkirui.authentication.navigation.AuthenticationRoutes
import com.victorkirui.farmtracker.navigation.AppNav
import com.victorkirui.profile.navigation.ProfileRoutes

data class BottomNavigationBar(
    val name: String = "",
    var isSelected: Boolean = false,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val badgeNumber: Int? = null
)

@Composable
fun MyApp(
    windowSizeClass: WindowSizeClass,
    viewModel: MainViewModel = hiltViewModel(),
    appState: AppState = rememberAppState(windowSizeClass = windowSizeClass)
){
    Scaffold(
        bottomBar = {
            when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    NavigationBar {
                        viewModel.bottomNavigationBar.forEach {
                            NavigationBarItem(
                                selected = it.isSelected,
                                onClick = { it.isSelected = !it.isSelected},
                                icon = {
                                    if (it.isSelected){
                                        Icon(imageVector = it.selectedIcon, contentDescription = "")
                                        it.selectedIcon
                                    }else Icon(imageVector = it.unselectedIcon, contentDescription = "")
                                },
                                label = {
                                    Text(text = it.name)
                                })
                        }
                    }
                }
            }
        },
        topBar = {
            val currentScreen by remember {
                mutableStateOf("")
            }

            if (currentScreen == ProfileRoutes.profileGraphRoute){
                Text(text = "Home")
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            AppNav(windowWidthSizeClass = windowSizeClass.widthSizeClass, startScreenRoute = ProfileRoutes.profileGraphRoute)
        }
    }

}