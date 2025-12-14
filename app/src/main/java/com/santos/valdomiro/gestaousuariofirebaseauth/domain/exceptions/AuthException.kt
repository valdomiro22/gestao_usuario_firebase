package com.santos.valdomiro.gestaousuariofirebaseauth.domain.exceptions

sealed class AuthException : Exception() {
    object EmailEmUso : AuthException()
    object SenhaFraca : AuthException()
    object EmailInvalido : AuthException()
    object ErroDeRede : AuthException()

    data class Desconhecido(val erroOriginal: Throwable?) : AuthException()
}