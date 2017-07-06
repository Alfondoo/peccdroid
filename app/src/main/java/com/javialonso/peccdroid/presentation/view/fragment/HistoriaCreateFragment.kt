package com.javialonso.peccdroid.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.ReglasAceptacion
import com.javialonso.peccdroid.data.entity.ReglasAportes
import com.javialonso.peccdroid.presentation.internal.di.components.FeedComponent
import com.javialonso.peccdroid.presentation.presenter.HistoriaCreatePresenter
import javax.inject.Inject


class HistoriaCreateFragment : BaseFragment(), HistoriaCreateView {

    @Inject lateinit var historiaCreatePresenter: HistoriaCreatePresenter
    var butterknifeBinder: Unbinder? = null
    @BindView(R.id.sp_reglas_aceptacion_historia_create) @JvmField var reglasAceptacion: Spinner? = null
    @BindView(R.id.sp_reglas_aportes_historia_create) @JvmField var reglasAportes: Spinner? = null
    @BindView(R.id.et_titulo_historia_create) @JvmField var titulo: TextInputEditText? = null
    @BindView(R.id.et_criterios_aporte_historia_create) @JvmField var criteriosAceptacion: TextInputEditText? = null
    @BindView(R.id.et_contenido_aporte_historia_create) @JvmField var contenido: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(FeedComponent::class.java).inject(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_historia_create_view, container, false)
        butterknifeBinder = ButterKnife.bind(this, fragmentView)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.historiaCreatePresenter.setView(this)
        loadSpinners()
    }


    override fun onResume() {
        super.onResume()
        this.historiaCreatePresenter.resume()
        this.historiaCreatePresenter.setView(this)
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onPause() {
        super.onPause()
        this.historiaCreatePresenter.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        butterknifeBinder?.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.historiaCreatePresenter.destroy()
    }

    private fun loadSpinners() {
        val reglasAceptacionVal = ReglasAceptacion.values().map { it.name }
        val reglasAceptacionAdapter = ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item, reglasAceptacionVal)
        reglasAceptacionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        reglasAceptacion?.adapter = reglasAceptacionAdapter

        val reglasAportesVal = ReglasAportes.values().map { it.name }
        val reglasAportesAdapter = ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item, reglasAportesVal)
        reglasAportesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        reglasAportes?.adapter = reglasAportesAdapter
    }

    override fun onDetach() {
        super.onDetach()
    }

    @OnClick(R.id.btn_historia_create) fun onButtonHistoriaCreateClicked() {
        val titulo = titulo?.text.toString()
        val contenido = contenido?.text.toString()
        val criteriosAceptacion = criteriosAceptacion?.text.toString()
        val reglasAportes = ReglasAportes.valueOf(reglasAportes?.selectedItem.toString())
        val reglasAceptacion = ReglasAceptacion.valueOf(reglasAceptacion?.selectedItem.toString())
        historiaCreatePresenter.historiaCreate(titulo = titulo, contenido = contenido, esBifurcable = true)
    }
}