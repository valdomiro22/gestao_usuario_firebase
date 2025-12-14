package com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.repository

import com.google.firebase.firestore.FirebaseFirestoreException
import com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.UsuarioRemoteDataSource
import com.santos.valdomiro.gestaousuariofirebaseauth.data.mapper.toDocument
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.exceptions.AcessoNegadoException
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.exceptions.ErroBancoDadosDesconhecidoException
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.exceptions.NaoEncontradoException
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.exceptions.ServicoIndisponivelException
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.model.Usuario
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.UsuarioRepository
import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
    private val usuarioDataSource: UsuarioRemoteDataSource
) : UsuarioRepository {

    override suspend fun insertUser(usuario: Usuario) {
        try {
            usuarioDataSource.insertUser(usuario.toDocument())
        } catch (e: FirebaseFirestoreException) {
            throw when (e.code) {
                FirebaseFirestoreException.Code.PERMISSION_DENIED ->
                    AcessoNegadoException(e)

                FirebaseFirestoreException.Code.NOT_FOUND ->
                    NaoEncontradoException(e)

                FirebaseFirestoreException.Code.UNAVAILABLE ->
                    ServicoIndisponivelException(e)

                else ->
                    ErroBancoDadosDesconhecidoException(e)
            }
        } catch (e: Exception) {
            // Para qualquer outro erro genérico (ex: erro de conversão)
            throw ErroBancoDadosDesconhecidoException(e)
        }
    }

    override suspend fun updateUser(
        id: String,
        usuario: Usuario
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: String): Usuario {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): List<Usuario> {
        TODO("Not yet implemented")
    }
}