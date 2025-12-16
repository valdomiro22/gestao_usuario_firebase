package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.cadastrarusuario

import com.santos.valdomiro.gestaousuariofirebaseauth.domain.model.Usuario

data class CadastrarUsuarioState(
    val loading: Boolean = false,
    val sucesso: Boolean = false,
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