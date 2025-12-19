package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.splashscreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.common.UiState
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.navigation.AppRoutes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    irParaDestino: (String) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state) {
        when (val s = state) {
            is UiState.Success -> {
                val rota = if (s.data != null) AppRoutes.HOME else AppRoutes.LOGIN
                irParaDestino(rota)
            }
            is UiState.Error -> {
                irParaDestino(AppRoutes.LOGIN)
            }
            else -> {}
        }
    }

    Box(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.Red), // Mesma cor que colocou no XML acima
    contentAlignment = Alignment.Center
    ) {
        // Sua Logo Personalizada
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.FlashOn,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = Color.Blue
            )
            Text(text = "Meu App Seguro", style = MaterialTheme.typography.headlineSmall)
        }
    }
}
