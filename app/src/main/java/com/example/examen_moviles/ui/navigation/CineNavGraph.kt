package com.example.examen_moviles.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.examen_moviles.ui.screen.CineEntryScreen
import com.example.examen_moviles.ui.screen.LoginScreen

@Composable
fun CineNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = "login",  // Establecemos LoginScreen como la primera pantalla
        modifier = modifier
    ) {
        composable(route = "login") {
            LoginScreen(
                onLoginSuccess = {
                    // Navegar a CineEntryScreen después de un login exitoso
                    navController.navigate("cine_entry") {
                        popUpTo("login") { inclusive = true }  // Eliminar login del backstack
                    }
                },
                onForgotPassword = {
                    // Si implementas la pantalla de restablecer contraseña
                    navController.navigate("forgot_password")
                }
            )
        }

        composable(route = "cine_entry") {
            CineEntryScreen()
        }

        // Opción futura para agregar una pantalla de restablecer contraseña
        composable(route = "forgot_password") {
            // ForgotPasswordScreen() // Si decides implementarla
        }
    }
}
