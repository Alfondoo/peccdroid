package com.javialonso.peccdroid.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.data.entity.HistoriaDetailEntity
import com.javialonso.peccdroid.presentation.internal.di.components.FeedComponent
import com.javialonso.peccdroid.presentation.presenter.HistoriaDetailPresenter
import com.javialonso.peccdroid.presentation.view.AporteDetailAdapter
import javax.inject.Inject

class HistoriaDetailFragment : BaseFragment(), HistoriaDetailView {

    @Inject lateinit var historiaDetailPresenter: HistoriaDetailPresenter
    @BindView(R.id.tv_titulo_historia_detail) @JvmField var titulo: TextView? = null
    @BindView(R.id.tv_creador_historia_detail) @JvmField var creador: TextView? = null
    @BindView(R.id.tv_puntuacion_historia_detail) @JvmField var puntuacion: TextView? = null
    @BindView(R.id.tv_reglas_aceptacion_valor_historia_detail) @JvmField var reglasAceptacion: TextView? = null
    @BindView(R.id.tv_reglas_aportes_valor_historia_detail) @JvmField var reglasAportes: TextView? = null
    @BindView(R.id.tv_criterios_aceptacion_historia_detail) @JvmField var criteriosAceptacionLabel: TextView? = null
    @BindView(R.id.tv_criterios_aceptacion_valor_historia_detail) @JvmField var criteriosAceptacion: TextView? = null
    @BindView(R.id.rv_aportes_historia_detail) @JvmField var aportes: RecyclerView? = null
    @BindView(R.id.ll_btn_container_historia_detail) @JvmField var navigationAportesContainer: LinearLayout? = null
    var butterknifeBinder: Unbinder? = null
    private var aportesVisibles: MutableList<AporteDetailEntity> = ArrayList()
    private var aportesExistentes: List<AporteDetailEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(FeedComponent::class.java).inject(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_historia_detail_view, container, false)
        butterknifeBinder = ButterKnife.bind(this, fragmentView)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.historiaDetailPresenter.setView(this)
        aportes?.adapter = AporteDetailAdapter(aportesVisibles)
        aportes?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        this.arguments?.getInt("id")?.let { this.historiaDetailPresenter.historiaDetail(it) }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        butterknifeBinder?.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun updateView(historia: HistoriaDetailEntity) {
        titulo?.text = historia.titulo
        creador?.text = historia.creador
        puntuacion?.text = historia.puntuacionMedia.toString()
        reglasAceptacion?.text = historia.reglasAceptacion.name
        reglasAportes?.text = historia.reglasAportes.name
        historia.criteriosAceptacion?.let { criteriosAceptacion?.text = it }

        aportesExistentes = historia.aportes
        val aporteInicial = aportesExistentes.filter { it.aportePadre == null }.first()
        addAporte(aporteInicial)
        showPathButtonsForAporte(aporteInicial)
    }

    private fun showPathButtonsForAporte(aporte: AporteDetailEntity) {
        val aportesHijos = aportesExistentes.filter { it.aportePadre == aporte.id }
        for (aporte in aportesHijos) {
            val button = Button(activity)
            button.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            button.text = aporte.id.toString()
            button.setOnClickListener { v ->
                addAporte(aportesExistentes.filter {
                    it.id == (v as Button).text.toString().toInt() && it.aportePadre == aportesVisibles.last().id
                }.first())
            }
            navigationAportesContainer?.addView(button)
        }
    }

    private fun addAporte(aporte: AporteDetailEntity) {
        if (aportesVisibles.isNotEmpty()) {
            val aportesExistentes = aportesVisibles.subList(0, aportesVisibles.size - 1)
            aportesExistentes.add(aporte)
            aportesVisibles.clear()
            aportesVisibles.addAll(aportesExistentes)
        } else {
            aportesVisibles.clear()
            aportesVisibles.add(aporte)
        }
        aportes?.adapter?.notifyDataSetChanged()
    }
}