package com.victorkirui.farmtracker.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.victorkirui.addinfo.ui.navigation.AddInfoRoutes
import com.victorkirui.animals.ui.allTypesOfAnimalsList.AllAnimalTypesListViewModel
import com.victorkirui.animals.ui.specificAnimalType.SpecificAnimalTypeListViewModel
import com.victorkirui.authentication.navigation.AuthenticationRoutes
import com.victorkirui.farmtracker.navigation.AppNav
import com.victorkirui.farmtracker.navigation.TopLevelNavigationRoute
import com.victorkirui.home.ui.HomeCompact

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
    navController: NavHostController = rememberNavController(),
    appState: AppState = rememberAppState(windowSizeClass = windowSizeClass, navController = navController)
){

    if (appState.isSignedIn){
        Scaffold(
            bottomBar = {
                when (windowSizeClass.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> {
                        NavigationBar {

                            val bottomNavBarIcons = viewModel.bottomNavigationBarList.collectAsState()

                            var currentScreenNumber by remember {
                                mutableIntStateOf(0)
                            }

                            for ((number, i) in bottomNavBarIcons.value.withIndex()){
                                var navigateTo = TopLevelNavigationRoute.homeRoute

                                when(i.name){
                                    "Home" ->{
                                        navigateTo = TopLevelNavigationRoute.homeRoute
                                    }

                                    "Add" ->{
                                        navigateTo = AddInfoRoutes.addInfoRoutes
                                    }

                                }


                                NavigationBarItem(
                                    selected = i.isSelected,
                                    onClick = {
                                        viewModel.updateSelected(number, currentScreenNumber)
                                        navController.navigate(navigateTo)
                                        currentScreenNumber = number},
                                    icon = {
                                        if (i.isSelected){
                                            Icon(imageVector = i.selectedIcon, contentDescription = "")
                                            i.selectedIcon
                                        }else Icon(imageVector = i.unselectedIcon, contentDescription = "")
                                    },
                                    label = {
                                        Text(text = i.name)
                                    },
                                    alwaysShowLabel = false)

                            }


                        }
                    }
                }
            },
            topBar = {
                val currentScreen by remember {
                    mutableStateOf("")
                }

                if (currentScreen == "Home"){
                    Text(text = "Home")
                }
            }
        ) {
            Column(modifier = Modifier
                .padding(it)
                .padding(
                    start = if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium) {
                        80.dp
                    } else {
                        0.dp
                    }
                )) {
                AppNav(windowWidthSizeClass = windowSizeClass.widthSizeClass, TopLevelNavigationRoute.homeRoute,
                    navController = navController)
            }
            if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Medium){
                NavigationRail(
                    header = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Outlined.Menu, contentDescription = null)
                        }
                    }
                ) {
                    Column(modifier = Modifier.fillMaxHeight(),verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        viewModel.bottomNavigationBar.forEach {bottomNavigationBar ->
                            NavigationRailItem(selected = bottomNavigationBar.isSelected,
                                onClick = { /*TODO*/ },
                                icon = { if (bottomNavigationBar.isSelected){
                                    Icon(imageVector = bottomNavigationBar.selectedIcon, contentDescription = null)
                                }else{
                                    Icon(imageVector = bottomNavigationBar.unselectedIcon, contentDescription = null)
                                } },
                                label = { Text(text = bottomNavigationBar.name)})
                        }
                    }
                }
            }
        }
    }else{
        AppNav(windowWidthSizeClass = windowSizeClass.widthSizeClass,
            startScreenRoute = AuthenticationRoutes.authenticationGraph,
            navController = navController)
    }



}