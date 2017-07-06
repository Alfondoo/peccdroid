package com.javialonso.peccdroid.presentation.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.HistoriaEntity
import java.text.DecimalFormat

class HistoriasProfileAdapter(val items: List<HistoriaEntity>) : RecyclerView.Adapter<HistoriasProfileAdapter.HistoriaProfileViewHolder>() {

    override fun onBindViewHolder(holder: HistoriaProfileViewHolder, position: Int) {
        val item = items.get(position)
        holder.bindItem(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriaProfileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistoriaProfileViewHolder(layoutInflater.inflate(R.layout.historia_profile_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class HistoriaProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo = itemView.findViewById<TextView>(R.id.tv_titulo_historia_profile_vh)
        val puntuacion = itemView.findViewById<TextView>(R.id.tv_puntuacion_historia_profile_vh)

        fun bindItem(historia: HistoriaEntity) {
            titulo.text = historia.titulo
            puntuacion.text = DecimalFormat("#.##").format(historia.puntuacionMedia).toString()
        }
    }
}