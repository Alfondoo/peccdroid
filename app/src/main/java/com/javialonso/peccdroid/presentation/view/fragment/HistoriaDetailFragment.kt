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
import com.javialonso.peccdroid.presentation.component.SharedPreferencesTokenStorage
import com.javialonso.peccdroid.presentation.internal.di.components.FeedComponent
import com.javialonso.peccdroid.presentation.presenter.HistoriaDetailPresenter
import com.javialonso.peccdroid.presentation.view.AporteSelectionCard
import com.javialonso.peccdroid.presentation.view.StaticLayoutManager
import com.javialonso.peccdroid.presentation.view.adapter.AporteDetailAdapter
import java.text.DecimalFormat
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
    @BindView(R.id.btn_admin_historia_detail) @JvmField var adminAportesHistoria: Button? = null

    var butterknifeBinder: Unbinder? = null
    private var aportesVisibles: MutableList<AporteDetailEntity> = ArrayList()
    private var aportesExistentes: List<AporteDetailEntity> = ArrayList()
    private var historiaDetailListener: HistoriaDetailListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(FeedComponent::class.java).inject(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is HistoriaDetailFragment.HistoriaDetailListener) {
            this.historiaDetailListener = context
        }
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
        this.historiaDetailPresenter.setTokenStorage(SharedPreferencesTokenStorage(activity))
        aportes?.adapter = AporteDetailAdapter(aportesVisibles)
        aportes?.layoutManager = StaticLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
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
        puntuacion?.text = DecimalFormat("#.##").format(historia.puntuacionMedia).toString()
        reglasAceptacion?.text = historia.reglasAceptacion.name
        reglasAportes?.text = historia.reglasAportes.name
        historia.criteriosAceptacion?.let { criteriosAceptacion?.text = it }

        aportesExistentes = historia.aportes
        val aporteInicial = aportesExistentes.filter { it.aportePadre == null }.first()
        addAporte(aporteInicial)
        showPathButtonsForAporte(aporteInicial)
    }

    private fun showPathButtonsForAporte(aporte: AporteDetailEntity) {
        navigationAportesContainer?.removeAllViews()
        val aportesHijos = aportesExistentes.filter { it.aportePadre == aporte.id && it.esAceptado }
        for (aporte in aportesHijos) {
            val aporteCard = AporteSelectionCard(activity)
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(8, 8, 8, 8)
            aporteCard.setData(aporte)
            aporteCard.layoutParams = layoutParams
            aporteCard.setOnClickListener { v ->
                val aporte = aportesExistentes.find { it.id == (v as AporteSelectionCard).idAporte }
                aporte?.let {
                    addAporte(aporte)
                    showPathButtonsForAporte(aporte)
                }
            }
            navigationAportesContainer?.addView(aporteCard)
        }
        if (aporte.esBifurcable || aportesHijos.isEmpty()) {
            val addButton = Button(activity)
            addButton.text = "Escribir nuevo aporte"
            addButton.setBackgroundResource(R.drawable.abc_btn_borderless_material)
            addButton.setOnClickListener {
                this@HistoriaDetailFragment.historiaDetailPresenter.toCreateNuevoAporte(aporte)
            }
            val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.setMargins(8, 8, 8, 8)
            navigationAportesContainer?.addView(addButton)
        }
    }

    private fun addAporte(aporte: AporteDetailEntity) {
        if (aportesVisibles.isNotEmpty()) {
            // Sublist generate a view of an array, not a new array, this lead to a ConcurrentModificationException in list.addAll()
            val aportesExistentes = ArrayList(aportesVisibles.subList(0, aportesVisibles.size))
            aportesExistentes.add(aporte)
            aportesVisibles.clear()
            aportesVisibles.addAll(aportesExistentes)
        } else {
            aportesVisibles.clear()
            aportesVisibles.add(aporte)
        }
        aportes?.adapter?.notifyDataSetChanged()
    }

    override fun showCreatorControls() {
        adminAportesHistoria?.visibility = View.VISIBLE
    }

    override fun toCreateNuevoAporte(aporte: AporteDetailEntity, historia: HistoriaDetailEntity) {
        this.historiaDetailListener?.viewCreateNuevoAporte(aporte, historia)
    }

    interface HistoriaDetailListener {
        fun viewCreateNuevoAporte(aporte: AporteDetailEntity, historia: HistoriaDetailEntity)
    }
}