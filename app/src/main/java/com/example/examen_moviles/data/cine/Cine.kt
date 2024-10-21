package com.example.examen_moviles.data.cine

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cine")
data class Cine(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val direccion: String,
    val imagen_cine: String,
    val numero_salas: Int,
)