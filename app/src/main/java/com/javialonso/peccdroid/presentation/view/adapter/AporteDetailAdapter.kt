package com.javialonso.peccdroid.presentation.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.data.helpers.DateHelper
import java.text.DecimalFormat

class AporteDetailAdapter(val items: List<AporteDetailEntity>) : RecyclerView.Adapter<AporteDetailAdapter.AporteDetailViewHolder>() {

    override fun onBindViewHolder(holder: AporteDetailViewHolder, position: Int) {
        val item = items.get(position)
        holder.bindItem(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AporteDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AporteDetailViewHolder(layoutInflater.inflate(R.layout.aporte_detail_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class AporteDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contenido = itemView.findViewById<TextView>(R.id.tv_contenido_aporte_detail_vh)
        val puntuacionMedia = itemView.findViewById<TextView>(R.id.tv_puntuacion_aporte_detail_vh)
        val esBifurcable = itemView.findViewById<ImageView>(R.id.iv_es_bifurcable_aporte_detail_vh)
        val noEsBifurcable = itemView.findViewById<ImageView>(R.id.iv_no_bifurcable_aporte_detail_vh)
        val creacion = itemView.findViewById<TextView>(R.id.tv_creacion_aporte_detail_vh)
        val autor = itemView.findViewById<TextView>(R.id.tv_creador_aporte_detail_vh)

        fun bindItem(aporte: AporteDetailEntity) {
            contenido.text = aporte.contenido
            if (!aporte.esBifurcable) {
                esBifurcable.visibility = View.GONE
                noEsBifurcable.visibility = View.VISIBLE
            }
            puntuacionMedia.text = DecimalFormat("#.##").format(aporte.puntuacionMedia).toString()
            creacion.text = DateHelper().getTimeAgo(aporte.creacion)
            autor.text = aporte.autor
        }
    }
}