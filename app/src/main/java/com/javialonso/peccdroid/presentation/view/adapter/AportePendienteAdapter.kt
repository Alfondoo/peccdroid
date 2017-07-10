package com.javialonso.peccdroid.presentation.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.data.helpers.DateHelper


class AportePendienteAdapter(val items: List<AporteDetailEntity>) : RecyclerView.Adapter<AportePendienteAdapter.AportePendienteViewHolder>() {
    override fun onBindViewHolder(holder: AportePendienteViewHolder, position: Int) {
        val item = items[position]
        holder.bindItem(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AportePendienteAdapter.AportePendienteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AportePendienteAdapter.AportePendienteViewHolder(layoutInflater.inflate(R.layout.aporte_pendiente_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class AportePendienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val contenido = itemView.findViewById<TextView>(R.id.tv_contenido_aporte_pendiente_vh)
        //        val esBifurcable = itemView.findViewById<ImageView>(R.id.iv_es_bifurcable_aporte_detail_vh)
//        val noEsBifurcable = itemView.findViewById<ImageView>(R.id.iv_no_bifurcable_aporte_detail_vh)
        val creacion = itemView.findViewById<TextView>(R.id.tv_creacion_aporte_pendiente_vh)
        val autor = itemView.findViewById<TextView>(R.id.tv_autor_aporte_pendiente_vh)

        fun bindItem(aporte: AporteDetailEntity) {
            contenido.text = aporte.contenido
//            if (!aporte.esBifurcable) {
//                esBifurcable.visibility = View.GONE
//                noEsBifurcable.visibility = View.VISIBLE
//            }
            creacion.text = DateHelper().getTimeAgo(aporte.creacion)
            autor.text = aporte.autor
        }
    }
}