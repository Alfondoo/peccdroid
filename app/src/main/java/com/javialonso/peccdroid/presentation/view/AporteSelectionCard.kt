package com.javialonso.peccdroid.presentation.view

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.data.helpers.DateHelper


class AporteSelectionCard : CardView {

    private var contenido: TextView? = null
    private var autor: TextView? = null
    private var puntuacion: TextView? = null
    private var creacion: TextView? = null
    var idAporte: Int = -1

    constructor(context: Context) : super(context) {
        inflate()
        onFinishInflate()
    }

    constructor(context: Context, attributeSet: AttributeSet, parent: ViewGroup) : super(context, attributeSet) {
        inflate()
    }

    fun inflate() {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.aporte_selection, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.setCardBackgroundColor(Color.parseColor("#424242"))
        // Ripple setup
        this.isClickable = true
        val attrs = intArrayOf(R.attr.selectableItemBackground)
        val typedArray = context.obtainStyledAttributes(attrs)
        val selectedItemDrawable = typedArray.getDrawable(0)
        this.foreground = selectedItemDrawable

        contenido = this.findViewById(R.id.aporte_selection_content)
        autor = this.findViewById(R.id.aporte_selection_autor)
        puntuacion = this.findViewById(R.id.aporte_selection_puntuacion)
        creacion = this.findViewById(R.id.aporte_selection_creacion)
    }

    fun setData(aporte: AporteDetailEntity) {
        this.contenido?.text = aporte.contenido
        this.autor?.text = aporte.autor
        this.puntuacion?.text = aporte.puntuacionMedia.toString()
        this.creacion?.text = DateHelper().getTimeAgo(aporte.creacion)
        this.idAporte = aporte.id
    }
}