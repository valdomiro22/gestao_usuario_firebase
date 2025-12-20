package com.santos.valdomiro.gestaousuariofirebaseauth.di

import com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.repository.AuthRepositoryImpl
import com.santos.valdomiro.gestaousuariofirebaseauth.data.datasource.repository.UsuarioFirestoreFirestoreRepositoryImpl
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.AuthRepository
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.UsuarioFirestoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepository: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindUsuarioRepository(
        usuarioFirestoreRepositoryImpl: UsuarioFirestoreFirestoreRepositoryImpl
    ): UsuarioFirestoreRepository
}