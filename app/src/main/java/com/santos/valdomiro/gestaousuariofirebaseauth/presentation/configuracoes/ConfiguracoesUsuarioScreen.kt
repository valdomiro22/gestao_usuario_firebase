package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.configuracoes

import android.Manifest
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.santos.valdomiro.gestaousuariofirebaseauth.R
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.components.CampoImagemAlteravel
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.components.CustomOutlinedTextField
import com.santos.valdomiro.gestaousuariofirebaseauth.ui.theme.Dimens
import com.santos.valdomiro.gestaousuariofirebaseauth.utils.Util
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ConfiguracoesUsuarioScreen(
    irParaHome: () -> Unit,
    irParaAlterarEmail: () -> Unit,
    viewModel: ConfiguracoesUsuarioViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    val cameraPermission = rememberPermissionState(Manifest.permission.CAMERA)

    var photoUri by remember { mutableStateOf<Uri?>(null) }

    val takePictureLaucher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            Log.d(Util.TAG, "Foto tirada com sucesso! URI: $photoUri")
            viewModel.atualizarFoto(photoUri!!)
        } else {
            Log.d(Util.TAG, "Foto cancelada ou falhou")
        }
    }

    fun createImageUri(): Uri {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFile = File(context.filesDir, "images/JPEG_${timestamp}.jpg")
        imageFile.parentFile?.mkdirs()

        return FileProvider.getUriForFile(
            context,
            "com.santos.valdomiro.gestaousuariofirebaseauth.provider",
//            "${context.packageName}.provider",
            imageFile
        )
    }

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

        CampoImagemAlteravel(
            photoUrl = "https://images.unsplash.com/photo-1761839258513-099c3121d72d?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            onClick = {
                if (cameraPermission.status.isGranted) {
                    val uri = createImageUri()
                    photoUri = uri
                    takePictureLaucher.launch(uri)
                } else {
                    cameraPermission.launchPermissionRequest()
                }
            }
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
                    irParaAlterarEmail()
//                    Toast.makeText(context, "Alterar Email", Toast.LENGTH_SHORT).show()
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


//@Preview(showBackground = true)
//@Composable
//fun ConfiguracoesUsuarioScreenPreview() {
//    MaterialTheme {
//        ConfiguracoesUsuarioScreen {}
//    }
//}