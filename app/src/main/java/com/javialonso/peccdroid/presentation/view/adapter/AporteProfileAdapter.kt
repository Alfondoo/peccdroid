package com.javialonso.peccdroid.presentation.view.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.AporteEntity
import com.javialonso.peccdroid.data.helpers.DateHelper
import java.text.DecimalFormat

class AporteProfileAdapter(val items: List<AporteEntity>) : RecyclerView.Adapter<AporteProfileAdapter.AporteProfileViewHolder>() {

    override fun onBindViewHolder(holder: AporteProfileViewHolder, position: Int) {
        val item = items.get(position)
        holder.bindItem(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AporteProfileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AporteProfileViewHolder(layoutInflater.inflate(R.layout.aporte_profile_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class AporteProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contenido = itemView.findViewById<TextView>(R.id.tv_contenido_aporte_profile_vh)
        val historia = itemView.findViewById<TextView>(R.id.tv_historia_aporte_profile_vh)
        val puntuacionMedia = itemView.findViewById<TextView>(R.id.tv_puntuacion_aporte_profile_vh)
        val esAceptado = itemView.findViewById<ImageView>(R.id.iv_es_aceptado_true_aporte_profile_vh)
        val esRechazado = itemView.findViewById<ImageView>(R.id.iv_es_aceptado_false_aporte_profile_vh)
        val esBifurcable = itemView.findViewById<ImageView>(R.id.iv_es_bifurcable_aporte_profile_vh)
        val noEsBifurcable = itemView.findViewById<ImageView>(R.id.iv_no_bifurcable_aporte_profile_vh)
        val creacion = itemView.findViewById<TextView>(R.id.tv_creacion_aporte_profile_vh)

        init {
            esRechazado.setColorFilter(ContextCompat.getColor(itemView.context, R.color.materialRed))
            esAceptado.setColorFilter(ContextCompat.getColor(itemView.context, R.color.materialGreen))
        }

        fun bindItem(aporte: AporteEntity) {
            contenido.text = aporte.contenido
            historia.text = aporte.historia.titulo
            if (!aporte.esAceptado) {
                esAceptado.visibility = View.GONE
                esRechazado.visibility = View.VISIBLE
            }
            if (!aporte.esBifurcable) {
                esBifurcable.visibility = View.GONE
                noEsBifurcable.visibility = View.VISIBLE
            }
            puntuacionMedia.text = DecimalFormat("#.##").format(aporte.puntuacionMedia).toString()
            creacion.text = DateHelper().getTimeAgo(aporte.creacion)
        }
    }
}