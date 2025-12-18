package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
//import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.santos.valdomiro.gestaousuariofirebaseauth.R
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.model.Usuario
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.cadastrarusuario.MeuEvento
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.common.UiState
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.widgets.CustomOutlinedTextField
import com.santos.valdomiro.gestaousuariofirebaseauth.ui.theme.Dimens
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    irParaCadastro: () -> Unit,
    irParaHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
//    onLoginClick: (email: String, senha: String) -> Unit = { _, _ -> },
//    onRecuperarSenhaClick: () -> Unit = {},
//    onCadastrarClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mostrarSenha by remember { mutableStateOf(false) }

    val state by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    val isFormValid = email.isNotBlank() &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            senha.isNotBlank() &&
            senha.length >= 6

    LaunchedEffect(key1 = state) {
        when (val currentState = state) {
            is UiState.Success -> {
                Toast.makeText(context, "Logado", Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            is UiState.Error -> {
                Toast.makeText(context, "Erro ao fazer login", Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            else -> {
                Toast.makeText(context, "Outra condição", Toast.LENGTH_SHORT).show()
                viewModel.resetState()

            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.EspacamentoG),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.logar),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(top = 50.dp, bottom = Dimens.EspacamentoXG)
                .align(Alignment.Start)
        )

        CustomOutlinedTextField(
            value = email,
            onValueChange = {email = it},
            placeHolder = stringResource(R.string.placeholder_email),
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        CustomOutlinedTextField(
            value = senha,
            onValueChange = {senha = it},
            placeHolder = stringResource(R.string.placeholder_senha),
            keyboardType = KeyboardType.Password,
            visualTransformation = if (mostrarSenha) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon =  {
                IconButton(onClick = { mostrarSenha = !mostrarSenha }) {
                    Icon(
                        imageVector = if (mostrarSenha) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (mostrarSenha) "Esconder senha" else "Mostrar senha"
                    )
                }
            }
        )

        Text(
            text = "Esqueceu a senha?",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 8.dp)
                .clickable { }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.logar(email, senha)
                irParaHome()
                Toast.makeText(context, "Usuario logado", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Logar")
        }

//        if (state.loading) {
//            Spacer(modifier = Modifier.height(Dimens.EspacamentoG))
//            CircularProgressIndicator(
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            )
//        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.info_ja_tem_conta))

            TextButton(
                onClick = { irParaCadastro() }
            ) {
                Text(text = stringResource(R.string.cadastrar))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen({}, {})
    }
}