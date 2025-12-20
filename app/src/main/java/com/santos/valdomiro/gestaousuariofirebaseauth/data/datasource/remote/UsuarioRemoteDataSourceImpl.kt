package com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.UsuarioRemoteDataSource
import com.santos.valdomiro.gestaousuariofirebaseauth.data.dto.UsuarioDocument
import com.santos.valdomiro.gestaousuariofirebaseauth.data.mapper.toModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsuarioRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : UsuarioRemoteDataSource {
    val usuarioCollection = "usuarios"

    override suspend fun insertUser(usuario: UsuarioDocument) {
        if (usuario.id.isBlank()) {
            throw IllegalArgumentException("Erro: Tentativa de salvar usuario sem UID do Firebase Auth")
        }

        firestore
            .collection(usuarioCollection)
            .document(usuario.id)
            .set(usuario)
            .await()

    }

    override suspend fun updateUser(id: String, usuario: UsuarioDocument) {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: String): UsuarioDocument? {
        val snapshot = firestore
            .collection(usuarioCollection)
            .document(id)
            .get()
            .await()

        return snapshot.toObject(UsuarioDocument::class.java)
    }

    override suspend fun deleteUser(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): List<UsuarioDocument> {
        TODO("Not yet implemented")
    }
}