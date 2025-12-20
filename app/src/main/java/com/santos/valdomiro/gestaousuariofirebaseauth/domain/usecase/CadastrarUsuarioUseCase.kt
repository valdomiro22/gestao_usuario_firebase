package com.santos.valdomiro.gestaousuariofirebaseauth.domain.usecase

import com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.repository.AuthRepositoryImpl
import com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.repository.UsuarioFirestoreFirestoreRepositoryImpl
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.model.Usuario
import javax.inject.Inject

class CadastrarUsuarioUseCase @Inject constructor(
    private val authRepository: AuthRepositoryImpl,
    private val usuarioRepository: UsuarioFirestoreFirestoreRepositoryImpl
) {

    suspend operator fun invoke(usuario: Usuario, password: String, email: String) : Result<String> {
        return runCatching {
            val novoId = authRepository.createUser(email, password).getOrThrow()
            val usuarioConId = usuario.copy(id = novoId)
            usuarioRepository.insertUser(usuarioConId)
            novoId
        }
    }
}