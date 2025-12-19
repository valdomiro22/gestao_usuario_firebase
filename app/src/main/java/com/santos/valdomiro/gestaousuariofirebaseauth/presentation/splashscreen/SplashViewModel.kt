package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.model.Usuario
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.usecase.GetCurrentUserUseCase
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Usuario?>>(UiState.Aguardando)
    val uiState = _uiState.asStateFlow()

    init {
        getCurrentUsuario()
    }

    fun getCurrentUsuario() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            delay(3000)
            val result = getCurrentUserUseCase()

            result.onSuccess { usuario ->
                if (usuario != null) {
                    _uiState.value = UiState.Success(usuario)
                } else {
                    _uiState.value = UiState.Error("Usuario não logado")
                }
            }.onFailure {
                _uiState.value = UiState.Error(it.message ?: "Erro desconhecido ao buscar usuario logado")
            }
        }
    }

}