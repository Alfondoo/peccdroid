package com.javialonso.peccdroid.presentation.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.AporteEntity

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

        fun bindItem(aporte: AporteEntity) {
            contenido.text = aporte.contenido
            historia.text = aporte.historia.titulo
        }
    }
}