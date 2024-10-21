package com.example.examen_moviles.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.examen_moviles.CineApplication
import com.example.examen_moviles.ui.screen.CineEntryViewModel
import com.example.examen_moviles.ui.screen.LoginViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Inicializar CineEntryViewModel
        initializer {
            val application = cineApplication()
            CineEntryViewModel(
                cineRepository = application.container.cineRepository,
                context = application.applicationContext
            )
        }

        // Inicializar LoginViewModel
        initializer {
            val application = cineApplication() // Obtenemos la instancia de la aplicación
            LoginViewModel(
                accountService = application.container.accountService // Obtenemos el servicio desde el contenedor
            )
        }
    }
}

// Helper para obtener la instancia de CineApplication desde CreationExtras
fun CreationExtras.cineApplication(): CineApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as CineApplication)
