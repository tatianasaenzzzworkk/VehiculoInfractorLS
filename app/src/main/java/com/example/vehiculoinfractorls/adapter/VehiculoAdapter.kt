package com.example.vehiculoinfractorls.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiculoinfractorls.R
import com.example.vehiculoinfractorls.model.VehiculoInfractor

class VehiculoAdapter(
    private val vehiculos: List<VehiculoInfractor>,
    private val onDetalleClick: (VehiculoInfractor) -> Unit
) : RecyclerView.Adapter<VehiculoAdapter.VehiculoViewHolder>() {

    inner class VehiculoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvPlaca: TextView =
            itemView.findViewById(R.id.tvPlaca)

        val tvGravedad: TextView =
            itemView.findViewById(R.id.tvGravedad)

        val tvInfraccion: TextView =
            itemView.findViewById(R.id.tvInfraccion)

        val tvUbicacion: TextView =
            itemView.findViewById(R.id.tvUbicacion)

        val btnDetalle: Button =
            itemView.findViewById(R.id.btnDetalle)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VehiculoViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_vehiculo,
                parent,
                false
            )

        return VehiculoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: VehiculoViewHolder,
        position: Int
    ) {

        val vehiculo = vehiculos[position]

        holder.tvPlaca.text =
            vehiculo.placa

        holder.tvGravedad.text =
            vehiculo.gravedad

        holder.tvInfraccion.text =
            vehiculo.tipoInfraccion

        holder.tvUbicacion.text =
            "📍 ${vehiculo.ubicacion}"

        configurarColorGravedad(
            holder.tvGravedad,
            vehiculo.gravedad
        )

        holder.btnDetalle.setOnClickListener {
            onDetalleClick(vehiculo)
        }
    }

    override fun getItemCount(): Int =
        vehiculos.size

    private fun configurarColorGravedad(
        textView: TextView,
        gravedad: String
    ) {

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

        textView.setBackgroundColor(color)
    }
}
