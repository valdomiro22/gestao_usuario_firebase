package com.santos.valdomiro.gestaousuariofirebaseauth.domain.usecase

import com.santos.valdomiro.gestaousuariofirebaseauth.domain.model.Usuario
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.AuthRepository
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.UsuarioFirestoreRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val usuarioFirestoreRepository: UsuarioFirestoreRepository
) {

    suspend operator fun invoke(): Result<Usuario?> {
        return try {
            val uid = authRepository.getCurrentUserId()

            if (uid != null) {
                usuarioFirestoreRepository.getUser(uid)
            } else {
                Result.success(null)
            }
        } catch (e: Exception) {
            Result.failure(Exception("Erro ao buscar usuario"))
        }
    }

}