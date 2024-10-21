package com.example.examen_moviles.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen_moviles.data.model.service.AccountService
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class LoginViewModel(private val accountService: AccountService) : ViewModel() {

    // Obtener el usuario actual (si está autenticado)
    val currentUser: FirebaseUser?
        get() = accountService.currentUser

    // Función para iniciar sesión con una cuenta de Firebase
    fun login(email: String, password: String, onResult: (Result<FirebaseUser?>) -> Unit) {
        viewModelScope.launch {
            val result = accountService.login(email, password)
            onResult(result)
        }
    }

    // Función para cerrar sesión
    fun logout() {
        viewModelScope.launch {
            accountService.logout()
        }
    }

    // Función para enviar un correo de restablecimiento de contraseña
    fun sendPasswordReset(email: String, onResult: (Result<Void?>) -> Unit) {
        viewModelScope.launch {
            val result = accountService.sendPasswordReset(email)
            onResult(result)
        }
    }
}
