package com.example.examen_moviles.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.examen_moviles.R
import com.example.examen_moviles.data.cine.Cine
import com.example.examen_moviles.ui.AppViewModelProvider

object CineEntryDestination {
    val route = "cine_entry"
    val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CineEntryScreen(viewModel: CineEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val cines by viewModel._cine.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .background(Color(0xFFF5F5F5))
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "CINES",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        if (cines.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No hay cines disponibles", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn {
                items(cines) { cine ->
                    CineItem(cine)
                }
            }
        }
    }
}

@Composable
fun CineItem(cine: Cine) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            if (cine.imagen_cine.isNotEmpty()) {
                Log.d("CineItem", "Cargando imagen desde: ${cine.imagen_cine}")
                Image(
                    painter = rememberImagePainter(data = cine.imagen_cine),
                    contentDescription = "Imagen del cine ${cine.nombre}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Log.d("CineItem", "No se encontró la imagen para el cine: ${cine.nombre}")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Nombre: ${cine.nombre}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Ubicación: ${cine.direccion}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Número de Salas: ${cine.numero_salas}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
