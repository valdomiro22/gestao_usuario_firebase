package com.santos.valdomiro.gestaousuariofirebaseauth.domain.usecase

import androidx.compose.ui.geometry.Rect
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.AuthRepository
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.UsuarioFirestoreRepository
import javax.inject.Inject

class UpdateEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(newEmail: String, currentPassword: String): Result<Unit> {
        return try {
            // Apenas tenta enviar o email de verificação
            // Não toca no Firestore aqui!
            val result = authRepository.updateEmailAddress(newEmail, currentPassword)

            if (result.isSuccess) {
                Result.success(Unit) // Sucesso: email de verificação enviado
            } else {
                Result.failure(result.exceptionOrNull()!!)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}