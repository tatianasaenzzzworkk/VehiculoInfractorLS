package com.example.vehiculoinfractorls.data

import android.util.Log
import com.example.vehiculoinfractorls.model.VehiculoInfractor

object ColeccionesDemo {

    private const val TAG = "SMT_Colecciones"

    // Historial mock de infracciones

    private val historialInfracciones =
        listOf(

            VehiculoInfractor(
                id = 1,
                placa = "ABC-123",
                tipoInfraccion = "Exceso de velocidad",
                velocidadRegistrada = 95,
                velocidadPermitida = 60,
                ubicacion = "Av. Boyacá Km 3",
                fecha = "2025-05-01"
            ),

            VehiculoInfractor(
                id = 2,
                placa = "XYZ-789",
                tipoInfraccion = "Semáforo en rojo",
                velocidadRegistrada = 0,
                velocidadPermitida = 0,
                ubicacion = "Cra. 30 con Cll 72",
                fecha = "2025-05-02"
            ),

            VehiculoInfractor(
                id = 3,
                placa = "DEF-456",
                tipoInfraccion = "Exceso de velocidad",
                velocidadRegistrada = 130,
                velocidadPermitida = 80,
                ubicacion = "Troncal Norte Km 7",
                fecha = "2025-05-03"
            ),

            VehiculoInfractor(
                id = 4,
                placa = "GHI-321",
                tipoInfraccion = "Invasión de carril",
                velocidadRegistrada = 0,
                velocidadPermitida = 0,
                ubicacion = "Cll 100 con Cra 15",
                fecha = "2025-05-04"
            ),

            VehiculoInfractor(
                id = 5,
                placa = "JKL-654",
                tipoInfraccion = "Exceso de velocidad",
                velocidadRegistrada = 110,
                velocidadPermitida = 60,
                ubicacion = "Autopista Sur Km 2",
                fecha = "2025-05-05"
            ),

            VehiculoInfractor(
                id = 6,
                placa = "MNO-987",
                tipoInfraccion = "Uso indebido del carril exclusivo",
                velocidadRegistrada = 0,
                velocidadPermitida = 0,
                ubicacion = "Av. Caracas",
                fecha = "2025-05-06"
            ),

            VehiculoInfractor(
                id = 7,
                placa = "PQR-147",
                tipoInfraccion = "Exceso de velocidad",
                velocidadRegistrada = 150,
                velocidadPermitida = 80,
                ubicacion = "Ruta Nacional 55",
                fecha = "2025-05-07"
            ),

            VehiculoInfractor(
                id = 8,
                placa = "STU-258",
                tipoInfraccion = "Semáforo en rojo",
                velocidadRegistrada = 0,
                velocidadPermitida = 0,
                ubicacion = "Calle 26",
                fecha = "2025-05-08"
            ),

            VehiculoInfractor(
                id = 9,
                placa = "VWX-369",
                tipoInfraccion = "Exceso de velocidad",
                velocidadRegistrada = 88,
                velocidadPermitida = 50,
                ubicacion = "Autopista Norte",
                fecha = "2025-05-09"
            ),

            VehiculoInfractor(
                id = 10,
                placa = "YZA-741",
                tipoInfraccion = "Estacionamiento prohibido",
                velocidadRegistrada = 0,
                velocidadPermitida = 0,
                ubicacion = "Zona Centro",
                fecha = "2025-05-10"
            ),

            VehiculoInfractor(
                id = 11,
                placa = "BCD-852",
                tipoInfraccion = "Exceso de velocidad",
                velocidadRegistrada = 140,
                velocidadPermitida = 70,
                ubicacion = "Periférico Oriental",
                fecha = "2025-05-11"
            ),

            VehiculoInfractor(
                id = 12,
                placa = "EFG-963",
                tipoInfraccion = "Giro prohibido",
                velocidadRegistrada = 0,
                velocidadPermitida = 0,
                ubicacion = "Cra. 7 con Calle 45",
                fecha = "2025-05-12"
            )
        )

    fun ejecutarDemostraciones() {

        Log.d(
            TAG,
            "Total infracciones: ${historialInfracciones.size}"
        )

        historialInfracciones.forEach { vehiculo ->

            Log.d(
                TAG,
                "Placa: ${vehiculo.placa} | " +
                        "Infracción: ${vehiculo.tipoInfraccion} | " +
                        "Gravedad: ${vehiculo.gravedad}"
            )
        }

        val excesosVelocidad =
            historialInfracciones.filter {
                it.tipoInfraccion == "Exceso de velocidad"
            }

        Log.d(
            TAG,
            "Excesos de velocidad registrados: ${excesosVelocidad.size}"
        )
    }

    fun obtenerTodas():
            List<VehiculoInfractor> {

        return historialInfracciones
    }
}
