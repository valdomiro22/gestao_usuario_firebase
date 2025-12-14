package com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.AuthDataSource
import com.santos.valdomiro.gestaousuariofirebaseauth.data.dto.UsuarioDocument
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : AuthDataSource {

    override suspend fun createUser(email: String, password: String): String {
        val authResult = auth.createUserWithEmailAndPassword(email, password).await()
        val user = authResult?.user ?: throw Exception("Erro no Firebase Auth: Usuario nulo apos criacao.")
        return user.uid;
    }

    override suspend fun login(email: String, password: String): String {
        TODO("Not yet implemented")
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

    override suspend fun updatePassword(newPassword: String, currentPassword: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }

}