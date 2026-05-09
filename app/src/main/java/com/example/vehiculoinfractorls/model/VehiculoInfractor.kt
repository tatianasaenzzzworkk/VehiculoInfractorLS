package com.example.vehiculoinfractorls.model

/**
 * Modelo de dominio: representa un vehículo con infracción de tráfico
 * registrada por el Sistema de Monitoreo de Tráfico (SMT).
 */
data class VehiculoInfractor(
    val id: Int,
    val placa: String,
    val tipoInfraccion: String,
    val velocidadRegistrada: Int,
    val velocidadPermitida: Int,
    val ubicacion: String,
    val fecha: String
) {
    /**
     * Exceso de velocidad calculado dinámicamente
     */
    val excesoVelocidad: Int
        get() = (velocidadRegistrada - velocidadPermitida).coerceAtLeast(0)

    /**
     * Clasificación de gravedad según el exceso
     */
    val gravedad: String
        get() = when {
            excesoVelocidad >= 40 -> "GRAVE"
            excesoVelocidad >= 20 -> "MODERADA"
            excesoVelocidad > 0  -> "LEVE"
            else -> "SIN EXCESO"
        }
}