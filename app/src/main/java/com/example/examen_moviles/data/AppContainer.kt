package com.example.examen_moviles.data

import android.content.Context
import com.example.examen_moviles.data.cine.CineDatabase
import com.example.examen_moviles.data.cine.CineRepository
import com.example.examen_moviles.data.cine.OfflineCineRepository
import com.example.examen_moviles.data.model.service.AccountService
import com.example.examen_moviles.data.model.service.impl.AccountServiceImpl

interface AppContainer {
    val cineRepository: CineRepository
    val accountService: AccountService // Agregar la interfaz AccountService
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val cineRepository: CineRepository by lazy {
        OfflineCineRepository(CineDatabase.getDatabase(context).cineDao())
    }

    override val accountService: AccountService by lazy {
        AccountServiceImpl() // Proveer la implementación de AccountService
    }
}
