package com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository

import com.santos.valdomiro.gestaousuariofirebaseauth.domain.model.Usuario

interface UsuarioRepository {
    suspend fun insertUser(usuario: Usuario)
    suspend fun updateUser(id: String, usuario: Usuario)
    suspend fun getUser(id: String): Usuario
    suspend fun deleteUser(id: String)
    suspend fun getAllUsers(): List<Usuario>
}