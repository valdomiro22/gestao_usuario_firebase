package com.santos.valdomiro.gestaousuariofirebaseauth.domain.usecase

import com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.repository.AuthRepositoryImpl
import com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.repository.UsuarioRepositoryImpl
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.model.Usuario
import javax.inject.Inject

class CadastrarUsuarioUseCase @Inject constructor(
    private val authRepository: AuthRepositoryImpl,
    private val usuarioRepository: UsuarioRepositoryImpl
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