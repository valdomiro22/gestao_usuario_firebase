package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.configuracoes

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.santos.valdomiro.gestaousuariofirebaseauth.R
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.widgets.CustomOutlinedTextField
import com.santos.valdomiro.gestaousuariofirebaseauth.ui.theme.Dimens

@Composable
fun ConfiguracoesScreen(
    irParaHome: () -> Unit,
) {

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(Dimens.EspacamentoG)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            "Configurações",
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Image(
//            painter = painterResource(id = android.R.drawable.ic_menu_camera),
            painter = rememberVectorPainter(Icons.Default.Person),
            contentDescription = "Ícone",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Text(
            "Nome Usuario",
            fontSize = Dimens.TextoG,
        )

        Text(
            "Email Usuario",
//            fontSize = Dimens.TextoMM
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        CustomOutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            placeHolder = stringResource(R.string.label_nome),
            keyboardType = KeyboardType.Text,
            icone = {
                Icon(Icons.Default.Person, contentDescription = null)
            }
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoM))

        CustomOutlinedTextField(
            value = sobrenome,
            onValueChange = { sobrenome = it },
            placeHolder = stringResource(R.string.label_sobrenome),
            keyboardType = KeyboardType.Text,
            icone = {
                Icon(Icons.Default.Person, contentDescription = null)
            }
        )

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Button(
            onClick = {
                Toast.makeText(context, "Salvar", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Salvar")
        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Alterar Email", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(stringResource(R.string.alterar_email))
            }

            Button(
                onClick = {
                    Toast.makeText(context, "Alterar Senha", Toast.LENGTH_SHORT).show()
                }
            ) {
                Text(stringResource(R.string.alterar_senha))
            }
        }

        Spacer(modifier = Modifier.height(Dimens.EspacamentoG))

        Button(
            onClick = {
                Toast.makeText(context, "Excluir conta", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Excluir Conta")
        }
    }
}

@Composable
fun AsyncImage(
    model: String,
    contentDescription: String,
    modifier: Modifier,
    contentScale: ContentScale
) {
    TODO("Not yet implemented")
}


@Preview(showBackground = true)
@Composable
fun ConfiguracoesScreenPreview() {
    MaterialTheme {
        ConfiguracoesScreen {}
    }
}