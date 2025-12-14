package com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource

import com.google.firebase.auth.FirebaseUser

interface AuthDataSource {
    suspend fun createUser(email: String, password: String): String
    suspend fun login(email: String, password: String): String
    fun signOut()
    fun getCurrentUserId(): String? // Retorna o ID do usuario
    suspend fun sendPasswordResetEmail(email: String)
    suspend fun updateEmailAddress(newEmail: String, password: String)
    suspend fun updatePassword(newPassword: String, currentPassword: String)
    suspend fun deleteUser()
}