package com.example.vehiculoinfractorls.data

import android.util.Log
import com.example.vehiculoinfractorls.model.VehiculoInfractor

/**
 * ACTIVIDAD 1 – Demostración de colecciones de Kotlin
 * Cubre: List, MutableList y Map con operaciones básicas.
 */
object ColeccionesDemo {

    private const val TAG = "SMT_Colecciones"

    // 1. LIST (inmutable) — snapshot histórico
    val historialInfracciones: List<VehiculoInfractor> = listOf(
        VehiculoInfractor(1, "ABC-123", "Exceso de velocidad", 95,  60, "Av. Boyacá Km 3",   "2025-05-01"),
        VehiculoInfractor(2, "XYZ-789", "Semáforo en rojo",    0,   0,  "Cra. 30 con Cll 72","2025-05-02"),
        VehiculoInfractor(3, "DEF-456", "Exceso de velocidad", 130, 80, "Troncal Norte Km 7","2025-05-03"),
        VehiculoInfractor(4, "GHI-321", "Invasión de carril",  0,   0,  "Cll 100 con Cra 15","2025-05-04"),
        VehiculoInfractor(5, "JKL-654", "Exceso de velocidad", 110, 60, "Autopista Sur Km 2","2025-05-05")
    )

    // 2. MUTABLELIST — cola de infracciones activas
    val infraccionesActivas: MutableList<VehiculoInfractor> = mutableListOf(
        VehiculoInfractor(6, "MNO-987", "Exceso de velocidad", 75, 50, "Cll 26 con Cra 68", "2025-05-06"),
        VehiculoInfractor(7, "PQR-147", "Semáforo en rojo",    0,  0,  "Av. El Dorado",     "2025-05-07")
    )

    // 3. MAP — índice placa → infracción más reciente
    val indicePorPlaca: Map<String, VehiculoInfractor> = mapOf(
        "ABC-123" to historialInfracciones[0],
        "XYZ-789" to historialInfracciones[1],
        "DEF-456" to historialInfracciones[2],
        "GHI-321" to historialInfracciones[3],
        "JKL-654" to historialInfracciones[4]
    )

    /**
     * Ejecuta y registra todas las demostraciones de operaciones.
     */
    fun ejecutarDemostraciones() {

        // LIST: iterar y filtrar
        Log.d(TAG, "LIST: historial de infracciones")
        historialInfracciones.forEach { v ->
            Log.d(TAG, "  Placa: ${v.placa} | Infracción: ${v.tipoInfraccion} | Gravedad: ${v.gravedad}")
        }

        val soloExcesos = historialInfracciones.filter { it.tipoInfraccion == "Exceso de velocidad" }
        Log.d(TAG, "  → Filtrado (solo excesos de velocidad): ${soloExcesos.size} registros")

        val placas = historialInfracciones.map { it.placa }
        Log.d(TAG, "  → Placas en historial: $placas")

        // MUTABLELIST: agregar y eliminar
        Log.d(TAG, "MUTABLELIST: infracciones activas")
        Log.d(TAG, "  Tamaño inicial: ${infraccionesActivas.size}")

        val nueva = VehiculoInfractor(8, "STU-258", "Exceso de velocidad", 90, 60, "Cra. 50 Km 1", "2025-05-08")
        infraccionesActivas.add(nueva)
        Log.d(TAG, "  Tras agregar '${nueva.placa}': ${infraccionesActivas.size} elementos")

        infraccionesActivas.removeIf { it.placa == "MNO-987" }
        Log.d(TAG, "  Tras eliminar 'MNO-987': ${infraccionesActivas.size} elementos")

        infraccionesActivas.sortByDescending { it.velocidadRegistrada }
        Log.d(TAG, "  Ordenado por velocidad desc: ${infraccionesActivas.map { it.placa }}")

        // MAP: buscar por clave
        Log.d(TAG, "MAP: búsqueda por placa")
        val placaBuscada = "DEF-456"
        val encontrado = indicePorPlaca[placaBuscada]
        if (encontrado != null) {
            Log.d(TAG, "  Placa '$placaBuscada' encontrada → ${encontrado.ubicacion}, exceso: ${encontrado.excesoVelocidad} km/h")
        } else {
            Log.d(TAG, "  Placa '$placaBuscada' no encontrada")
        }

        Log.d(TAG, "  Todas las placas indexadas: ${indicePorPlaca.keys.toList()}")

        val totalExcesos = indicePorPlaca.values
            .filter { it.tipoInfraccion == "Exceso de velocidad" }
            .sumOf { it.excesoVelocidad }
        Log.d(TAG, "  Suma total de excesos en mapa: $totalExcesos km/h")
    }

    /**
     * Retorna la lista combinada (historial + activas) para poblar la UI.
     */
    fun obtenerTodas(): List<VehiculoInfractor> =
        historialInfracciones + infraccionesActivas
}