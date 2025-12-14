package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.cadastrarusuario

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santos.valdomiro.gestaousuariofirebaseauth.R
import com.santos.valdomiro.gestaousuariofirebaseauth.ui.theme.Dimens
import com.santos.valdomiro.gestaousuariofirebaseauth.ui.theme.GestaoUsuarioFirebaseAuthTheme

@Composable
fun CadastrarUsuarioScreen(
    irParaLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }

    // Para mostrar/ocultar senhas
    var mostrarSenha by remember { mutableStateOf(false) }
    var mostrarConfirmarSenha by remember { mutableStateOf(false) }

    // Para ScrollView
    val scrollState = rememberScrollState()

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Habilita o scroll manual
            .imePadding() // Faz a tela "encolher" ou adicionar espaço quando o teclado sobe
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Cadastro",
            fontSize = 32.sp,
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Text(text = stringResource(R.string.label_nome), fontSize = 12.sp)
        OutlinedTextField(
            value = nome,
            onValueChange = { novoTexto -> nome = novoTexto },
            placeholder = { Text(stringResource(R.string.placeholder_nome)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Text(text = stringResource(R.string.label_sobrenome), fontSize = 12.sp)
        OutlinedTextField(
            value = sobrenome,
            onValueChange = { novoTexto -> sobrenome = novoTexto },
            placeholder = { Text(stringResource(R.string.placeholder_sobrenome)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Text(text = stringResource(R.string.label_email), fontSize = 12.sp)
        OutlinedTextField(
            value = email,
            onValueChange = { novoTexto -> email = novoTexto },
            placeholder = { Text(stringResource(R.string.placeholder_email)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Text(text = stringResource(R.string.laber_senha), fontSize = 12.sp)
        OutlinedTextField(
            value = senha,
            onValueChange = { novoTexto -> senha = novoTexto },
            placeholder = { Text(stringResource(R.string.placeholder_senha)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (mostrarSenha) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                IconButton(onClick = { mostrarSenha = !mostrarSenha }) {
                    Icon(
                        imageVector = if (mostrarSenha) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (mostrarSenha) "Esconder senha" else "Mostrar senha"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Text(text = stringResource(R.string.laber_confirmar_senha), fontSize = 12.sp)
        OutlinedTextField(
            value = confirmarSenha,
            onValueChange = { novoTexto -> confirmarSenha = novoTexto },
            placeholder = { Text(stringResource(R.string.placeholder_senha)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (mostrarConfirmarSenha) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                IconButton(onClick = { mostrarConfirmarSenha = !mostrarConfirmarSenha }) {
                    Icon(
                        imageVector = if (mostrarConfirmarSenha) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (mostrarConfirmarSenha) "Esconder senha" else "Mostrar senha"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Button(
            onClick = {
                Toast.makeText(context, "Cadastrar", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Cadastrar")
        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoMM))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.info_ja_tem_conta))

            TextButton(
                onClick = { irParaLogin() }
            ) {
                Text(text = stringResource(R.string.logar))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GestaoUsuarioFirebaseAuthTheme {
        CadastrarUsuarioScreen({})
    }
}