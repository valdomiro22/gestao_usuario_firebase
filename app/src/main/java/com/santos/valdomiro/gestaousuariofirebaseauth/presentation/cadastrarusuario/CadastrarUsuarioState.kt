package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.cadastrarusuario

import com.santos.valdomiro.gestaousuariofirebaseauth.domain.model.Usuario

// O Estado da UI
data class CadastrarUsuarioState(
    val loading: Boolean = false,
    val sucesso: Boolean = false, // Mudei de 'dados' para 'sucesso' (flag de navegação)
    val erro: String? = null
)

sealed class MeuEvento {
    data class Cadastrar(
        val email: String,
        val pass: String,
        val usuario: Usuario
    ) : MeuEvento()

    data object LimparErro : MeuEvento()
}