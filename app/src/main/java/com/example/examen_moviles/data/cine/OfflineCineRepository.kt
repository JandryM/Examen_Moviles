package com.example.examen_moviles.data.cine

import kotlinx.coroutines.flow.Flow

class OfflineCineRepository(private val cineDao: CineDao) : CineRepository {
    override fun getAllCineStream(): Flow<List<Cine>> = cineDao.getAllCine()

    override fun getCineStream(id: Int): Flow<Cine?> = cineDao.getCine(id)

    override suspend fun insertCine(cine: Cine) = cineDao.insert(cine)

    override suspend fun deleteCine(cine: Cine) = cineDao.delete(cine)
}