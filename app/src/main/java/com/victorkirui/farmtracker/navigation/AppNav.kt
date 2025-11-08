package com.victorkirui.farmtracker.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.victorkirui.addinfo.ui.AddInfoUiRoute
import com.victorkirui.addinfo.ui.navigation.AddInfoRoutes
import com.victorkirui.animals.navigation.AnimalsRoute
import com.victorkirui.animals.ui.addingAnimals.AddAnimalRoute
import com.victorkirui.animals.ui.allTypesOfAnimalsList.AllAnimalTypeListRoute
import com.victorkirui.animals.ui.allTypesOfAnimalsList.AllAnimalTypesListViewModel
import com.victorkirui.animals.ui.specificAnimalType.SpecificAnimalTypeListRoute
import com.victorkirui.animals.ui.specificAnimalType.SpecificAnimalTypeListViewModel
import com.victorkirui.authentication.navigation.AuthenticationRoutes
import com.victorkirui.authentication.navigation.authenticationGraph
import com.victorkirui.authentication.navigation.navigateToEmailSignInScreen
import com.victorkirui.authentication.navigation.navigateToEmailSignUpScreen
import com.victorkirui.authentication.navigation.navigateToPhoneAuthenticationScreen
import com.victorkirui.authentication.navigation.navigateToSignUpOptionsScreen
import com.victorkirui.farmtracker.ui.MainViewModel
import com.victorkirui.farmtracker.ui.rememberAppState
import com.victorkirui.finance.domain.DataTypeForTopPart
import com.victorkirui.finance.navigation.FinanceRoutes
import com.victorkirui.finance.ui.AddFinanceRoute
import com.victorkirui.finance.ui.AllFinanceListRoute
import com.victorkirui.finance.ui.ExpensesUiRoute
import com.victorkirui.finance.ui.IncomeUiRoute
import com.victorkirui.home.ui.HomeRoute
import com.victorkirui.production.navigation.ProductionRoute
import com.victorkirui.production.ui.AddProductionUiRoute
import com.victorkirui.production.ui.AllProductionListRoute
import com.victorkirui.production.ui.ProductionUIRoute
import com.victorkirui.profile.navigation.navigateToProfileGraph
import com.victorkirui.profile.navigation.profileGraph


@Composable
fun AppNav(windowWidthSizeClass: WindowWidthSizeClass,
           startScreenRoute: String,
           navController: NavHostController,
           viewModel: MainViewModel = hiltViewModel()){




    NavHost(navController = navController, startDestination = startScreenRoute ){

        authenticationGraph(windowWidthSizeClass,
            onSignUpClicked = navController::navigateToSignUpOptionsScreen,
            onSignInClicked = navController::navigateToEmailSignInScreen,
            navigateToEmailSignInScreen = navController::navigateToEmailSignInScreen,
            navigateToEmailSignUpScreen = navController::navigateToEmailSignUpScreen,
            navigateToPhoneAuthenticationScreen = navController::navigateToPhoneAuthenticationScreen,
            navigateToProfileGraph = navController::navigateToProfileGraph,
            navigateToHomeScreen = {
                navController.navigate(TopLevelNavigationRoute.homeRoute)
            navController.clearBackStack(AuthenticationRoutes.authenticationGraph)}

        )

        composable(route = TopLevelNavigationRoute.homeRoute){
            HomeRoute(windowWidthSizeClass,
                navigateToSpecificAnimalScreen = {
                    navController.navigate(AnimalsRoute.specificAnimalTypeList + "/$it")
                },
                navigateToAllTypeOfAnimalsList = {
                    navController.navigate(AnimalsRoute.allTypesOfAnimalsList)
                })
        }

        profileGraph(windowWidthSizeClass, navigateToHomeScreen = {
            navController.navigate(TopLevelNavigationRoute.homeRoute){
                popUpTo(TopLevelNavigationRoute.homeRoute)
            }
        })

        composable(route = AnimalsRoute.allTypesOfAnimalsList,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }){
            AllAnimalTypeListRoute(windowWidthSizeClass = windowWidthSizeClass,
                navigateToSpecificAnimal = {
                    navController.navigate(AnimalsRoute.specificAnimalTypeList + "/$it") })
        }

        composable(route = AnimalsRoute.specificAnimalTypeList + "/{currentAnimals}",
            arguments = listOf(navArgument("currentAnimals"){type = NavType.StringType})
        ){backStackEntry->
            val currentAnimal = backStackEntry.arguments?.getString("currentAnimals")
            SpecificAnimalTypeListRoute(windowWidthSizeClass = windowWidthSizeClass,
                currentAnimal = currentAnimal!!,
                navigateToAddAnimals = {
                    navController.navigate(AnimalsRoute.addAnimals + "/$it")
                })
        }
        
        composable(route = AnimalsRoute.addAnimals + "/{currentAnimal}",
            arguments = listOf(navArgument("currentAnimal"){type = NavType.StringType})
        ){
            val currentAnimal = it.arguments?.getString("currentAnimal")
            AddAnimalRoute(windowWidthSizeClass = windowWidthSizeClass,
                navigateToPreviousScreen = {
                    navController.popBackStack()
                },
                currentAnimal = currentAnimal!!)
        }

        //FinanceNavigation

        composable(route = FinanceRoutes.incomeRoute){
            IncomeUiRoute(
                windowWidthSizeClass = windowWidthSizeClass,
                navigateToAllListScreen = {
                    navController.navigate("${FinanceRoutes.allFinanceRoute}/${it}") },
                navigateToAddIncomeScreen ={
                    navController.navigate("${FinanceRoutes.addFinanceRoute}/${it}")
                }
            )
        }

        composable(route = FinanceRoutes.expensesRoute){
            ExpensesUiRoute(
                windowWidthSizeClass = windowWidthSizeClass,
                navigateToAllListScreen = {
                    navController.navigate("${FinanceRoutes.allFinanceRoute}/${it}")
                },
                navigateToAddIncomeScreen = {
                    navController.navigate("${FinanceRoutes.addFinanceRoute}/${it}")
                }
            )
        }

        composable(route = "${FinanceRoutes.allFinanceRoute}/{financeType}",
            arguments = listOf(navArgument("financeType"){type = NavType.StringType})
        ){navBackStackEntry->
            AllFinanceListRoute(
                windowWidthSizeClass = windowWidthSizeClass,
                navigateToAddFinance = {
                    navController.navigate("${FinanceRoutes.addFinanceRoute}/${it}")
                },
                financeType = navBackStackEntry.arguments?.getString("financeType")!!
            )
        }

        composable(route = "${FinanceRoutes.addFinanceRoute}/{financeType}",
            arguments = listOf(navArgument("financeType"){type = NavType.StringType}
            )){navBackStackEntry->
                AddFinanceRoute(widthSizeClass = windowWidthSizeClass,
                    financeType = navBackStackEntry.arguments?.getString("financeType")!!,
                    navigateToPreviousScreen = {
                        navController.popBackStack()
                    })
        }


        composable(route = AddInfoRoutes.addInfoRoutes){
            AddInfoUiRoute(
                navigateToAddAnimalScreen = {
                    navController.navigate(AnimalsRoute.allTypesOfAnimalsList)
                },
                navigateToAddExpensesScreen = {
                    navController.navigate(FinanceRoutes.expensesRoute)
                },
                navigateToAddProfitScreen = {
                    navController.navigate(FinanceRoutes.incomeRoute)
                },
                navigateToAddProductionScreen = {
                    navController.navigate(ProductionRoute.productionUi)
                },
                windowWidthSizeClass = windowWidthSizeClass
            )


        }
        
        
        //Production
        composable(route = ProductionRoute.addProductionUiRoute){
            AddProductionUiRoute(windowWidthSizeClass = windowWidthSizeClass)
        }
        
        composable(route = ProductionRoute.productionUi){
            ProductionUIRoute(windowWidthSizeClass = windowWidthSizeClass)
        }
        
        composable(route = ProductionRoute.allProductionList){
            AllProductionListRoute(windowWidthSizeClass = windowWidthSizeClass) {
                
            }
        }


    }
}