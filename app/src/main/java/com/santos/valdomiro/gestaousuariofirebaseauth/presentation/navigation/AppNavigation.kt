package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.cadastrarusuario.CadastrarUsuarioScreen
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.login.LoginScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = AppRoutes.CADASTRO
    ) {
        composable(AppRoutes.LOGIN) {
            LoginScreen(
                irParaCadastro = {
                    navController.navigate(AppRoutes.CADASTRO) {
                        popUpTo(AppRoutes.LOGIN) { inclusive = true }
                    }
                })
        }

        // 2. Rota de CADASTRO: Aqui dentro vai a tela de Cadastro
        composable(AppRoutes.CADASTRO) {
            CadastrarUsuarioScreen(
                irParaLogin = {
                    navController.navigate(AppRoutes.LOGIN) {
                        popUpTo(AppRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
    }
}