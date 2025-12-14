package com.santos.valdomiro.gestaousuariofirebaseauth.domain.usecase

import com.santos.valdomiro.gestaousuariofirebaseauth.domain.model.Usuario
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.AuthRepository
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.UsuarioRepository
import javax.inject.Inject

class CadastrarUsuarioUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val usuarioRepository: UsuarioRepository
) {

    suspend operator fun invoke(usuario: Usuario, password: String, email: String) {
        val novoId = authRepository.createUser(email, password)
        val usuarioConId = usuario.copy(id = novoId)

        try {
            usuarioRepository.insertUser(usuarioConId)
        } catch (e: Exception) {
            throw e
        }
    }
}