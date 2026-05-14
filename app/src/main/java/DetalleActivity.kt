package com.example.vehiculoinfractorls

import android.graphics.Color
import android.view.View
import androidx.core.view.WindowCompat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.vehiculoinfractorls.model.VehiculoInfractor

class DetalleActivity : AppCompatActivity() {

    companion object {

        private const val TAG = "SMT_Detalle"

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
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_detalle)
        ocultarSystemBars()

        val vehiculo = obtenerVehiculoDesdeIntent()

        poblarUI(vehiculo)

        configurarBotonVolver()

        Log.d(
            TAG,
            "Detalle cargado → ${vehiculo.placa}"
        )
    }

    private fun obtenerVehiculoDesdeIntent(): VehiculoInfractor {

        return VehiculoInfractor(
            id = intent.getIntExtra(EXTRA_ID, -1),
            placa = intent.getStringExtra(EXTRA_PLACA) ?: "N/A",
            tipoInfraccion = intent.getStringExtra(EXTRA_TIPO_INFRACCION) ?: "N/A",
            velocidadRegistrada = intent.getIntExtra(EXTRA_VELOCIDAD_REGISTRADA, 0),
            velocidadPermitida = intent.getIntExtra(EXTRA_VELOCIDAD_PERMITIDA, 0),
            ubicacion = intent.getStringExtra(EXTRA_UBICACION) ?: "N/A",
            fecha = intent.getStringExtra(EXTRA_FECHA) ?: "N/A"
        )
    }

     //Renderiza información en pantalla

    private fun poblarUI(vehiculo: VehiculoInfractor) {

        findViewById<TextView>(R.id.tvDetallePlacaHeader).text =
            "Placa: ${vehiculo.placa}"

        findViewById<TextView>(R.id.tvDetallePlaca).text =
            vehiculo.placa

        findViewById<TextView>(R.id.tvDetalleTipoInfraccion).text =
            vehiculo.tipoInfraccion

        findViewById<TextView>(R.id.tvDetalleVelocidadReg).text =
            "${vehiculo.velocidadRegistrada} km/h"

        findViewById<TextView>(R.id.tvDetalleVelocidadPerm).text =
            "${vehiculo.velocidadPermitida} km/h"

        findViewById<TextView>(R.id.tvDetalleExceso).text =
            "${vehiculo.excesoVelocidad} km/h"

        findViewById<TextView>(R.id.tvDetalleUbicacion).text =
            "📍 ${vehiculo.ubicacion}"

        findViewById<TextView>(R.id.tvDetalleFecha).text =
            "📅 ${vehiculo.fecha}"

        configurarBadgeGravedad(vehiculo.gravedad)
    }


    // Configura badge dinámico de gravedad
    private fun configurarBadgeGravedad(gravedad: String) {

        val tvGravedad =
            findViewById<TextView>(R.id.tvDetalleGravedad)

        tvGravedad.text = gravedad

        val color = when (gravedad) {

            "GRAVE" ->
                Color.parseColor("#D32F2F")

            "MODERADA" ->
                Color.parseColor("#F57C00")

            "LEVE" ->
                Color.parseColor("#FBC02D")

            else ->
                Color.parseColor("#388E3C")
        }

        tvGravedad.setBackgroundColor(color)
    }

    private fun ocultarSystemBars() {

        window.decorView.systemUiVisibility =
            (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    )
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (hasFocus) {
            ocultarSystemBars()
        }
    }

    private fun configurarBotonVolver() {

        findViewById<Button>(R.id.btnVolver)
            .setOnClickListener {
                finish()
            }
    }
}