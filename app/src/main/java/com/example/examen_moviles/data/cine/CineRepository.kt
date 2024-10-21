package com.example.examen_moviles.data.cine

import kotlinx.coroutines.flow.Flow

interface CineRepository {

    fun getAllCineStream(): Flow<List<Cine>>

    fun getCineStream(id: Int): Flow<Cine?>

    suspend fun insertCine(cine: Cine)

    suspend fun deleteCine(cine: Cine)
}
