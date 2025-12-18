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

    override suspend fun createUser(email: String, password: String): String {
        return try {
            authDataSource.createUser(email, password)
        } catch (e: FirebaseAuthUserCollisionException) {
            throw AuthException.EmailEmUso
        } catch (e: FirebaseAuthWeakPasswordException) {
            throw AuthException.SenhaFraca
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            throw AuthException.EmailInvalido
        } catch (e: FirebaseNetworkException) {
            throw AuthException.ErroDeRede
        } catch (e: Exception) {
            throw AuthException.Desconhecido(
                Exception("Erro descohecido ao criar usuario: $e")
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

    override suspend fun sendPasswordResetEmail(email: String) {
        TODO("Not yet implemented")
    }

    override fun getCurrentUserId(): String? {
        TODO("Not yet implemented")
    }

    override fun signOut() {
        TODO("Not yet implemented")
    }

    override suspend fun updateEmailAddress(newEmail: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePassword(
        newPassword: String,
        currentPassword: String
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }
}