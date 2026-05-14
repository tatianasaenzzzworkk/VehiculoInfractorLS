package com.example.vehiculoinfractorls

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiculoinfractorls.adapter.VehiculoAdapter
import com.example.vehiculoinfractorls.data.ColeccionesDemo
import com.example.vehiculoinfractorls.model.VehiculoInfractor

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SMT_Main"
    }

    private lateinit var recyclerVehiculos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(
            window,
            false
        )

        setContentView(R.layout.activity_main)

        ocultarSystemBars()

        initRecyclerView()

        cargarVehiculos()
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

    private fun initRecyclerView() {

        recyclerVehiculos =
            findViewById(R.id.recyclerVehiculos)

        recyclerVehiculos.apply {

            layoutManager =
                LinearLayoutManager(this@MainActivity)

            setHasFixedSize(true)
        }
    }

    private fun cargarVehiculos() {

        ColeccionesDemo.ejecutarDemostraciones()

        val vehiculos =
            ColeccionesDemo.obtenerTodas()

        Log.d(
            TAG,
            "Vehículos cargados: ${vehiculos.size}"
        )

        recyclerVehiculos.adapter =
            VehiculoAdapter(
                vehiculos = vehiculos,
                onDetalleClick = ::navegarADetalle
            )
    }

    private fun navegarADetalle(
        vehiculo: VehiculoInfractor
    ) {

        val intent =
            Intent(
                this,
                DetalleActivity::class.java
            ).apply {

                putExtra(
                    DetalleActivity.EXTRA_ID,
                    vehiculo.id
                )

                putExtra(
                    DetalleActivity.EXTRA_PLACA,
                    vehiculo.placa
                )

                putExtra(
                    DetalleActivity.EXTRA_TIPO_INFRACCION,
                    vehiculo.tipoInfraccion
                )

                putExtra(
                    DetalleActivity.EXTRA_VELOCIDAD_REGISTRADA,
                    vehiculo.velocidadRegistrada
                )

                putExtra(
                    DetalleActivity.EXTRA_VELOCIDAD_PERMITIDA,
                    vehiculo.velocidadPermitida
                )

                putExtra(
                    DetalleActivity.EXTRA_UBICACION,
                    vehiculo.ubicacion
                )

                putExtra(
                    DetalleActivity.EXTRA_FECHA,
                    vehiculo.fecha
                )
            }

        startActivity(intent)

        Log.d(
            TAG,
            "Navegando → ${vehiculo.placa}"
        )
    }
}
