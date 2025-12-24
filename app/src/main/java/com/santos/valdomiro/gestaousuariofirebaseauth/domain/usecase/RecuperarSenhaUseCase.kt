package com.santos.valdomiro.gestaousuariofirebaseauth.domain.usecase

import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.AuthRepository
import javax.inject.Inject

class RecuperarSenhaUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(email: String): Result<Unit> {
        return try {
            authRepository.sendPasswordResetEmail(email)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}