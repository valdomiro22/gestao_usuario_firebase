package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.configuracoes

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.AuthRepository
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.usecase.UpdatePhotoProfileUseCase
import com.santos.valdomiro.gestaousuariofirebaseauth.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfiguracoesUsuarioViewModel @Inject constructor(
    private val updatePhotoProfileUseCase: UpdatePhotoProfileUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Unit>>(UiState.Aguardando)
    val uiState = _uiState.asStateFlow()

    fun atualizarFoto(fileUri: Uri) {
        val uid = authRepository.getCurrentUserId()

        if (uid == null) {
            _uiState.value = UiState.Error("Usuário não encontrado")
            return
        }

        viewModelScope.launch {
            _uiState.value = UiState.Loading

            val result = updatePhotoProfileUseCase(uid, fileUri)

            result.onSuccess { _uiState.value = UiState.Success(Unit) }
                .onFailure { _uiState.value = UiState.Error(it.message ?: "Erro ao atualizar foto") }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Aguardando
    }
}