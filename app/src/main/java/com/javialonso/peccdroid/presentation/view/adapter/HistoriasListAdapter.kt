package com.javialonso.peccdroid.presentation.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.HistoriaEntity
import java.text.DecimalFormat


class HistoriasListAdapter(val items: List<HistoriaEntity>) : RecyclerView.Adapter<HistoriasListAdapter.HistoriaViewViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener{
        fun onHistoriaItemClicked(historia: HistoriaEntity)
    }

    override fun onBindViewHolder(holder: HistoriaViewViewHolder, position: Int) {
        val item = items.get(position)
        holder.bindItem(item)
        holder.itemView.setOnClickListener {
            if (this@HistoriasListAdapter.onItemClickListener != null) {
                this@HistoriasListAdapter.onItemClickListener?.onHistoriaItemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoriaViewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistoriaViewViewHolder(layoutInflater.inflate(R.layout.historia_view_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class HistoriaViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo = itemView.findViewById<TextView>(R.id.tv_titulo_historia_view_vh)
        val puntuacion = itemView.findViewById<TextView>(R.id.tv_puntuacion_historia_view_vh)
        val creador = itemView.findViewById<TextView>(R.id.tv_creador_historia_view_vh)
        val reglaAuto = itemView.findViewById<ImageView>(R.id.iv_auto_historia_view_vh)
        val reglaAutor = itemView.findViewById<ImageView>(R.id.iv_autor_historia_view_vh)
        val reglasAporte = itemView.findViewById<TextView>(R.id.tv_aportes_reglas_valor_historia_view_vh)

        fun bindItem(historia: HistoriaEntity) {
            titulo.text = historia.titulo
            puntuacion.text = DecimalFormat("#.##").format(historia.puntuacionMedia).toString()
            creador.text = historia.creador
            if (historia.reglasAceptacion.regla == "A") {
                reglaAuto.visibility = View.GONE
                reglaAutor.visibility = View.VISIBLE
            }
            reglasAporte.text = historia.reglasAportes.name
        }
    }
}