package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.usecase.LogarUsuarioUseCase
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.santos.valdomiro.gestaousuariofirebaseauth.utils.Util
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logarUsuarioUseCase: LogarUsuarioUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Aguardando)
    val uiState = _uiState.asStateFlow()

    fun logar(email: String, senha: String) {
        Log.d(Util.TAG, "Sucesso no Firebase! UID")
        viewModelScope.launch {
            Log.d(Util.TAG, "Estado carregando")
            _uiState.value = UiState.Loading
            val result = logarUsuarioUseCase(email, senha)
            Log.d(Util.TAG, "Metodo executado")
            result.onSuccess {
                Log.d(Util.TAG, "Sucesso")
                _uiState.value = UiState.Success(it)
            }
                .onFailure {
                    Log.d(Util.TAG, "Erro")
                    _uiState.value = UiState.Error(it)
                }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Aguardando
    }

}