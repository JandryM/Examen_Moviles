package com.example.examen_moviles.data.model.service.impl

import com.example.examen_moviles.data.model.service.AccountService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AccountServiceImpl : AccountService {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Obtiene el usuario actual si está autenticado
    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    // Inicia sesión con el email y la contraseña
    override suspend fun login(email: String, password: String): Result<FirebaseUser?> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            Result.success(result.user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Cierra la sesión del usuario actual
    override suspend fun logout() {
        auth.signOut()
    }

    // Elimina la función de restablecimiento de contraseña si no es necesaria
    override suspend fun sendPasswordReset(email: String): Result<Void?> {
        return try {
            val result = auth.sendPasswordResetEmail(email).await()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
