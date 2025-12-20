package com.santos.valdomiro.gestaousuariofirebaseauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.cadastrarusuario.CadastrarUsuarioScreen
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.navigation.AppNavigation
import com.santos.valdomiro.gestaousuariofirebaseauth.ui.theme.GestaoUsuarioFirebaseAuthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition {
            viewModel.isLoading.value
        }

        setContent {
            GestaoUsuarioFirebaseAuthTheme {
                val navController = rememberNavController()
                AppNavigation(
                    navController = navController,
                    destino = viewModel.startDestination
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GestaoUsuarioFirebaseAuthTheme {
    }
}