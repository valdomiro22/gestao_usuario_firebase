package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
//import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lint.kotlin.metadata.Visibility

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginClick: (email: String, senha: String) -> Unit = { _, _ -> },
    onRecuperarSenhaClick: () -> Unit = {},
    onCadastrarClick: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mostrarSenha by remember { mutableStateOf(false) }

    // Estados de erro
    var emailError by remember { mutableStateOf<String?>(null) }
    var senhaError by remember { mutableStateOf<String?>(null) }

    // Validação simples
    val isFormValid = email.isNotBlank() &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            senha.isNotBlank() &&
            senha.length >= 6

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "Bem-vindo!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Campo Email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = when {
                    it.isBlank() -> "Email é obrigatório"
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches() -> "Email inválido"
                    else -> null
                }
            },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isError = emailError != null,
            supportingText = { emailError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Senha
        OutlinedTextField(
            value = senha,
            onValueChange = {
                senha = it
                senhaError = if (it.length < 6) "Senha deve ter pelo menos 6 caracteres" else null
            },
            label = { Text("Senha") },
            visualTransformation = if (mostrarSenha) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { mostrarSenha = !mostrarSenha }) {
                    Icon(
                        imageVector = if (mostrarSenha) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (mostrarSenha) "Esconder senha" else "Mostrar senha"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            isError = senhaError != null,
            supportingText = { senhaError?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth()
        )

        // Recuperar senha
        Text(
            text = "Esqueceu a senha?",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 8.dp)
                .clickable { onRecuperarSenhaClick() }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botão Logar
        Button(
            onClick = { onLoginClick(email, senha) },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logar")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Cadastro
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Não tem conta? ")
            Text(
                text = "Cadastre-se",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onCadastrarClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen()
    }
}