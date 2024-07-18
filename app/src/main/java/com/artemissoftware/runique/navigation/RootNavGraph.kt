package com.artemissoftware.runique.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.artemissoftware.auth.presentation.intro.IntroScreen
import com.artemissoftware.auth.presentation.login.LoginScreen
import com.artemissoftware.auth.presentation.register.RegisterScreen
import com.artemissoftware.run.presentation.activerun.ActiveRunScreen
import com.artemissoftware.run.presentation.activerun.service.ActiveRunService
import com.artemissoftware.run.presentation.overview.OverviewScreen
import com.artemissoftware.runique.MainActivity

@Composable
fun RootNavGraph(
    navController: NavHostController,
    isLoggedIn: Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = if(isLoggedIn) "run" else "auth"
    ) {
        authGraph(navController)
        runGraph(navController)
    }
}

private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = "intro",
        route = "auth"
    ) {
        composable(route = "intro") {
            IntroScreen(
                onSignUpClick = {
                    navController.navigate("register")
                },
                onSignInClick = {
                    navController.navigate("login")
                }
            )
        }
        composable(route = "register") {
            RegisterScreen(
                onSignInClick = {
                    navController.navigate("login") {
                        popUpTo("register") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate("login")
                }
            )
        }
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("run") {
                        popUpTo("auth") {
                            inclusive = true
                        }
                    }
                },
                onSignUpClick = {
                    navController.navigate("register") {
                        popUpTo("login") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                }
            )
        }
    }
}

private fun NavGraphBuilder.runGraph(navController: NavHostController) {
    navigation(
        startDestination = "run_overview",
        route = "run"
    ) {
        composable("run_overview") {
            OverviewScreen(
                onStartRunClick = {
                    navController.navigate("active_run")
                }
            )
        }

        composable(
            route = "active_run",
            deepLinks = listOf(
                navDeepLink { uriPattern = "runique://active_run" }
            )
        ) {
            val context = LocalContext.current
            ActiveRunScreen(
                onServiceToggle = { shouldServiceRun ->
                    if(shouldServiceRun) {
                        context.startService(
                            ActiveRunService.createStartIntent(
                                context = context,
                                activityClass = MainActivity::class.java
                            )
                        )
                    } else {
                        context.startService(
                            ActiveRunService.createStopIntent(context = context)
                        )
                    }
                }
            )
        }
    }
}