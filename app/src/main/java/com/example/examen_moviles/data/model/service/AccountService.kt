package com.example.examen_moviles.data.model.service

import com.example.examen_moviles.data.model.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUser: FirebaseUser?

    suspend fun login(email: String, password: String): Result<FirebaseUser?>

    suspend fun logout()

    suspend fun sendPasswordReset(email: String): Result<Void?>
}