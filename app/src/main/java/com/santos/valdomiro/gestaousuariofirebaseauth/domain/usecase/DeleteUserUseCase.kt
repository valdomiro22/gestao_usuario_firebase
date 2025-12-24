package com.santos.valdomiro.gestaousuariofirebaseauth.domain.usecase

import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.AuthRepository
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.UsuarioFirestoreRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val usuarioFirestoreRepository: UsuarioFirestoreRepository
) {

    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return try {
            val uid = authRepository.getCurrentUserId()

            if (uid != null) {
                authRepository.deleteUser(email = email, currentPassword = password)
                usuarioFirestoreRepository.deleteUser(uid)
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}