package com.example.vehiculoinfractorls.model

data class VehiculoInfractor(
    val id: Int,
    val placa: String,
    val tipoInfraccion: String,
    val velocidadRegistrada: Int,
    val velocidadPermitida: Int,
    val ubicacion: String,
    val fecha: String
) {

    //Calcula exceso de velocidad

    val excesoVelocidad: Int
        get() = (
                velocidadRegistrada -
                        velocidadPermitida
                ).coerceAtLeast(0)

    //Determina gravedad
    val gravedad: String
        get() = when {

            excesoVelocidad >= 40 ->
                "GRAVE"

            excesoVelocidad >= 20 ->
                "MODERADA"

            excesoVelocidad > 0 ->
                "LEVE"

            else ->
                "SIN EXCESO"
        }
}