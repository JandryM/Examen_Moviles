package com.example.examen_moviles.ui.screen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen_moviles.data.cine.Cine
import com.example.examen_moviles.data.cine.CineRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class CineEntryViewModel(
    private val cineRepository: CineRepository,
    private val context: Context  // Para manejar el almacenamiento interno
) : ViewModel() {

    val _cine: StateFlow<List<Cine>> =
        cineRepository.getAllCineStream()
            .map { it }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    // Inicializa los cines de ejemplo solo si no existen
    init {
        viewModelScope.launch {
            initializeSampleCines()
        }
    }

    // Método para inicializar cines de ejemplo solo si no existen
    private suspend fun initializeSampleCines() = withContext(Dispatchers.IO) {
        // Verificar si ya existen cines
        val existingCines = cineRepository.getAllCineStream().first()
        if (existingCines.isEmpty()) {
            Log.d("CineEntryViewModel", "No hay cines existentes, insertando cines de ejemplo...")
            insertSampleCines()
        } else {
            Log.d("CineEntryViewModel", "Cines ya existen, no es necesario insertar ejemplos ni copiar imágenes.")
        }
    }

    // Método privado para insertar cines de ejemplo
    private suspend fun insertSampleCines() = withContext(Dispatchers.IO) {
        val sampleCines = listOf(
            Cine(
                nombre = "Cineplex XYZ",
                direccion = "Avenida Principal 123, Ciudad",
                imagen_cine = copiarImagenDeAssetsAlAlmacenamientoInterno(context, "cine1.jpg"),
                numero_salas = 5
            ),
            Cine(
                nombre = "Cinemanía ABC",
                direccion = "Calle Secundaria 456, Ciudad",
                imagen_cine = copiarImagenDeAssetsAlAlmacenamientoInterno(context, "cine2.jpg"),
                numero_salas = 7
            ),
            Cine(
                nombre = "Multicines 123",
                direccion = "Boulevard Central 789, Ciudad",
                imagen_cine = copiarImagenDeAssetsAlAlmacenamientoInterno(context, "cine3.jpg"),
                numero_salas = 10
            )
        )

        sampleCines.forEach { cine ->
            cineRepository.insertCine(cine)
            Log.d("CineEntryViewModel", "Cine insertado: ${cine.nombre}, Imagen: ${cine.imagen_cine}")
        }
    }

    // Método para copiar la imagen desde assets al almacenamiento interno
    private suspend fun copiarImagenDeAssetsAlAlmacenamientoInterno(context: Context, nombreArchivo: String): String =
        withContext(Dispatchers.IO) {
            try {
                Log.d("CineEntryViewModel", "Intentando copiar la imagen $nombreArchivo desde assets...")

                // Abrir el archivo desde assets
                val inputStream = context.assets.open(nombreArchivo)

                // Crear un archivo en el almacenamiento interno
                val archivoImagen = File(context.filesDir, nombreArchivo)
                val fos = FileOutputStream(archivoImagen)

                // Copiar los datos de la imagen desde assets al almacenamiento interno
                val buffer = ByteArray(1024)
                var length: Int
                while (inputStream.read(buffer).also { length = it } > 0) {
                    fos.write(buffer, 0, length)
                }

                fos.close()
                inputStream.close()

                Log.d("CineEntryViewModel", "Imagen $nombreArchivo copiada correctamente a ${archivoImagen.absolutePath}")

                // Devolver la ruta absoluta del archivo guardado
                archivoImagen.absolutePath
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("CineEntryViewModel", "Error al copiar la imagen $nombreArchivo: ${e.message}")
                ""
            }
        }
}
