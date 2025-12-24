package com.santos.valdomiro.gestaousuariofirebaseauth.domain.usecase

import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.StorageRepository
import java.net.URL
import javax.inject.Inject

class DeletarFotoPerfilUseCase @Inject constructor(
    private val storageRepository: StorageRepository
) {

    suspend operator fun invoke(urlFoto: String?): Result<Unit> {
        return try {
            if (!urlFoto.isNullOrBlank()) {
                storageRepository.deleteFile(urlFoto)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}