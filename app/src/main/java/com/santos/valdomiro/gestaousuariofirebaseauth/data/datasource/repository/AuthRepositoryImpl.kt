package com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.repository

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.AuthDataSource
import com.santos.valdomiro.gestaousuariofirebaseauth.data.dto.UsuarioDocument
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.exceptions.AuthException
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun createUser(email: String, password: String): Result<String> {
        return try {
            val uid = authDataSource.createUser(email, password)
            Result.success(uid)
        } catch (e: FirebaseAuthUserCollisionException) {
            Result.failure(AuthException.EmailEmUso)
        } catch (e: FirebaseAuthWeakPasswordException) {
            Result.failure(AuthException.SenhaFraca)
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            Result.failure(AuthException.EmailInvalido)
        } catch (e: FirebaseNetworkException) {
            Result.failure(AuthException.ErroDeRede)
        } catch (e: Exception) {
            Result.failure(
                AuthException.Desconhecido(
                    AuthException.Desconhecido(
                        Exception("Erro descohecido ao criar usuario: ${e.message}")
                    )
                )
            )
        }
    }

    override suspend fun login(email: String, password: String): Result<String> {
        try {
            return authDataSource.login(email, password)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    override fun signOut(): Result<Unit> {
        return runCatching {
            authDataSource.signOut()
        }
    }

    override fun getCurrentUserId(): String? {
        return try {
            authDataSource.getCurrentUserId()
        } catch (e: Exception) {
            throw Exception("Erro ao recuperar usuario logado")
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updateEmailAddress(newEmail: String, password: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePassword(newPassword: String, currentPassword: String): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(): Result<Unit> {
        TODO("Not yet implemented")
    }
}