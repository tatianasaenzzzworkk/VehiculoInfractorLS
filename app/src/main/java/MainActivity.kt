package com.example.vehiculoinfractorls

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.vehiculoinfractorls.data.ColeccionesDemo
import com.example.vehiculoinfractorls.model.VehiculoInfractor

/**
 * ACTIVIDAD 3 – Lista Principal
 * Muestra las infracciones registradas y navega al detalle via Intent explícito.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        // Actividad 1: ejecutar demostraciones en Logcat
        ColeccionesDemo.ejecutarDemostraciones()

        // Actividad 3: obtener datos y mapear botones
        val vehiculos = ColeccionesDemo.obtenerTodas()

        // Botón 1 -> Vehículo con placa ABC-123
        findViewById<android.widget.Button>(R.id.btnDetalle1).setOnClickListener {
            navegarADetalle(vehiculos[0])
        }

        // Botón 2 -> Vehículo con placa DEF-456 (índice 2)
        findViewById<android.widget.Button>(R.id.btnDetalle2).setOnClickListener {
            navegarADetalle(vehiculos[2])
        }

        // Botón 3 -> Vehículo con placa XYZ-789 (índice 1)
        findViewById<android.widget.Button>(R.id.btnDetalle3).setOnClickListener {
            navegarADetalle(vehiculos[1])
        }

        Log.d("SMT_Main", "MainActivity iniciada. Total de vehículos cargados: ${vehiculos.size}")
    }

    /**
     * ACTIVIDAD 3 – Navegación con Intent explícito + extras.
     * Pasa todos los campos del modelo como extras individuales
     * para ser recuperados en DetalleActivity.
     */
    private fun navegarADetalle(vehiculo: VehiculoInfractor) {
        val intent = Intent(this, DetalleActivity::class.java).apply {
            putExtra(DetalleActivity.EXTRA_ID, vehiculo.id)
            putExtra(DetalleActivity.EXTRA_PLACA,vehiculo.placa)
            putExtra(DetalleActivity.EXTRA_TIPO_INFRACCION,vehiculo.tipoInfraccion)
            putExtra(DetalleActivity.EXTRA_VELOCIDAD_REGISTRADA,vehiculo.velocidadRegistrada)
            putExtra(DetalleActivity.EXTRA_VELOCIDAD_PERMITIDA,vehiculo.velocidadPermitida)
            putExtra(DetalleActivity.EXTRA_UBICACION,vehiculo.ubicacion)
            putExtra(DetalleActivity.EXTRA_FECHA,vehiculo.fecha)
        }
        startActivity(intent)
        Log.d("SMT_Main", "Navegando a detalle de placa: ${vehiculo.placa}")
    }
}