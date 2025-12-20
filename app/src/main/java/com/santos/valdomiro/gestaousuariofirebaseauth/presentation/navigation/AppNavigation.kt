package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.cadastrarusuario.CadastrarUsuarioScreen
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.configuracoes.ConfiguracoesUsuarioScreen
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.home.HomeScreen
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.login.LoginScreen

@Composable
fun AppNavigation(navController: NavHostController, destino: String) {

    NavHost(
        navController = navController,
        startDestination = destino
    ) {
        composable(AppRoutes.LOGIN) {
            LoginScreen(
                irParaCadastro = {
                    navController.navigate(AppRoutes.CADASTRO)
                },

                irParaHome = {
                    navController.navigate(AppRoutes.HOME) {
                        popUpTo(AppRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoutes.CADASTRO) {
            CadastrarUsuarioScreen(
                irParaLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.HOME) {
            HomeScreen(
                irParaLogin = {
                    navController.navigate(AppRoutes.LOGIN) {
                        popUpTo(AppRoutes.HOME) { inclusive = true }
                    }
                },

                irParaConfiguracoes = {
                    navController.navigate(AppRoutes.CONFIGURACOES)
                }
            )
        }

        composable(AppRoutes.CONFIGURACOES) {
            ConfiguracoesUsuarioScreen(
                irParaHome = {
                    navController.popBackStack()
                }
            )
        }
    }
}