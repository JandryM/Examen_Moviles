package com.example.examen_moviles

import android.app.Application
import com.example.examen_moviles.data.AppContainer
import com.example.examen_moviles.data.AppDataContainer

class CineApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        // Eliminar la base de datos antes de crearla nuevamente (para propósitos de depuración)
        this.deleteDatabase("cine_database")

        container = AppDataContainer(this)
    }
}