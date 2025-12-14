package com.santos.valdomiro.gestaousuariofirebaseauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.cadastrarusuario.CadastrarUsuarioScreen
import com.santos.valdomiro.gestaousuariofirebaseauth.ui.theme.GestaoUsuarioFirebaseAuthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GestaoUsuarioFirebaseAuthTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CadastrarUsuarioScreen()
                }
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