package com.example.vehiculoinfractorls

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiculoinfractorls.adapter.VehiculoAdapter
import com.example.vehiculoinfractorls.data.ColeccionesDemo
import com.example.vehiculoinfractorls.model.VehiculoInfractor

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerVehiculos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        ColeccionesDemo.ejecutarDemostraciones()

        val vehiculos = ColeccionesDemo.obtenerTodas()

        Log.d(
            "SMT_Main",
            "Vehículos cargados en RecyclerView: ${vehiculos.size}"
        )

        initRecyclerView(vehiculos)
    }

    private fun initRecyclerView(vehiculos: List<VehiculoInfractor>) {

        recyclerVehiculos = findViewById(R.id.recyclerVehiculos)

        recyclerVehiculos.layoutManager = LinearLayoutManager(this)

        recyclerVehiculos.adapter = VehiculoAdapter(vehiculos) { vehiculo ->
            navegarADetalle(vehiculo)
        }
    }

    private fun navegarADetalle(vehiculo: VehiculoInfractor) {
        val intent = Intent(this, DetalleActivity::class.java).apply {
            putExtra(DetalleActivity.EXTRA_ID, vehiculo.id)
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
            "SMT_Main", "Detalle abierto para placa: ${vehiculo.placa}"
        )
    }
}
