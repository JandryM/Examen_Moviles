package com.example.examen_moviles.data.cine

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CineDao {
    @Query("SELECT * from cine ORDER BY nombre ASC")
    fun getAllCine(): Flow<List<Cine>>

    @Query("SELECT * from cine WHERE id = :id")
    fun getCine(id: Int): Flow<Cine>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cine: Cine)

    @Delete
    suspend fun delete(cine: Cine)
}
