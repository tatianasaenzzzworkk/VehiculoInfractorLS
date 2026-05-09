package com.example.vehiculoinfractorls

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.vehiculoinfractorls.model.VehiculoInfractor

/**
 * ACTIVIDAD 3 – Pantalla de Detalle
 * Recupera datos del Intent y los muestra en la UI.
 */
class DetalleActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_PLACA = "extra_placa"
        const val EXTRA_TIPO_INFRACCION = "extra_tipo_infraccion"
        const val EXTRA_VELOCIDAD_REGISTRADA = "extra_velocidad_registrada"
        const val EXTRA_VELOCIDAD_PERMITIDA = "extra_velocidad_permitida"
        const val EXTRA_UBICACION = "extra_ubicacion"
        const val EXTRA_FECHA = "extra_fecha"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_detalle)

        // Recuperar datos del Intent
        val id = intent.getIntExtra(EXTRA_ID, -1)
        val placa = intent.getStringExtra(EXTRA_PLACA) ?: "N/A"
        val tipoInfraccion = intent.getStringExtra(EXTRA_TIPO_INFRACCION) ?: "N/A"
        val velocidadRegistrada = intent.getIntExtra(EXTRA_VELOCIDAD_REGISTRADA, 0)
        val velocidadPermitida = intent.getIntExtra(EXTRA_VELOCIDAD_PERMITIDA, 0)
        val ubicacion = intent.getStringExtra(EXTRA_UBICACION) ?: "N/A"
        val fecha = intent.getStringExtra(EXTRA_FECHA) ?: "N/A"

        // Reconstruir objeto para usar propiedades calculadas (excesoVelocidad, gravedad)
        val vehiculo = VehiculoInfractor(
            id = id,
            placa = placa,
            tipoInfraccion = tipoInfraccion,
            velocidadRegistrada = velocidadRegistrada,
            velocidadPermitida = velocidadPermitida,
            ubicacion = ubicacion,
            fecha = fecha
        )

        Log.d("SMT_Detalle", "Datos recibidos → Placa: $placa | Gravedad: ${vehiculo.gravedad}")

        // Poblar la UI
        findViewById<TextView>(R.id.tvDetallePlacaHeader).text = "Placa: $placa"
        findViewById<TextView>(R.id.tvDetallePlaca).text = placa
        findViewById<TextView>(R.id.tvDetalleTipoInfraccion).text = tipoInfraccion
        findViewById<TextView>(R.id.tvDetalleVelocidadReg).text = "$velocidadRegistrada km/h"
        findViewById<TextView>(R.id.tvDetalleVelocidadPerm).text = "$velocidadPermitida km/h"
        findViewById<TextView>(R.id.tvDetalleExceso).text = "${vehiculo.excesoVelocidad} km/h"
        findViewById<TextView>(R.id.tvDetalleUbicacion).text = "📍 $ubicacion"
        findViewById<TextView>(R.id.tvDetalleFecha).text = "📅 $fecha"

        // Color dinámico del badge de gravedad
        val tvGravedad = findViewById<TextView>(R.id.tvDetalleGravedad)
        tvGravedad.text = vehiculo.gravedad
        val colorFondo = when (vehiculo.gravedad) {
            "GRAVE" -> android.graphics.Color.parseColor("#D32F2F")
            "MODERADA" -> android.graphics.Color.parseColor("#F57C00")
            "LEVE" -> android.graphics.Color.parseColor("#FBC02D")
            else -> android.graphics.Color.parseColor("#388E3C")
        }
        tvGravedad.setBackgroundColor(colorFondo)

        // Botón volver
        findViewById<Button>(R.id.btnVolver).setOnClickListener {
            finish()
        }
    }
}