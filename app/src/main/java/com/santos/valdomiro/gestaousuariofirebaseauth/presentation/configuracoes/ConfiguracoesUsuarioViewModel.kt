package com.santos.valdomiro.gestaousuariofirebaseauth.presentation.configuracoes

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.repository.AuthRepository
import com.santos.valdomiro.gestaousuariofirebaseauth.domain.usecase.DeleteUserUseCase
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
    private val deleteUserUseCase: DeleteUserUseCase,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _atualizarFotoState = MutableStateFlow<UiState<Unit>>(UiState.Aguardando)
    val atualizarFotoState = _atualizarFotoState.asStateFlow()

    private val _deletarContaState = MutableStateFlow<UiState<Unit>>(UiState.Aguardando)
    val deletarContaState = _deletarContaState.asStateFlow()

    fun atualizarFoto(fileUri: Uri) {
        val uid = authRepository.getCurrentUserId()

        if (uid == null) {
            _atualizarFotoState.value = UiState.Error("Usuário não encontrado")
            return
        }

        viewModelScope.launch {
            _atualizarFotoState.value = UiState.Loading

            val result = updatePhotoProfileUseCase(uid, fileUri)

            result.onSuccess { _atualizarFotoState.value = UiState.Success(Unit) }
                .onFailure { _atualizarFotoState.value = UiState.Error(it.message ?: "Erro ao atualizar foto") }
        }
    }

    fun deletarUsuario(email: String, password: String) {


        if (email.isBlank()) {
            _deletarContaState.value = UiState.Error("UID inválido")
            return
        } else if (password.isBlank()) {
            _deletarContaState.value = UiState.Error("Password inválido")
            return
        }

        viewModelScope.launch {
            _deletarContaState.value = UiState.Loading

            val result = deleteUserUseCase(email = email, password = password)

            result.onSuccess { _deletarContaState.value = UiState.Success(Unit) }
                .onFailure { _deletarContaState.value = UiState.Error(it.message ?: "Erro ao deletar usuario") }
        }
    }

    fun resetStates() {
        _atualizarFotoState.value = UiState.Aguardando
        _deletarContaState.value = UiState.Aguardando
    }
}