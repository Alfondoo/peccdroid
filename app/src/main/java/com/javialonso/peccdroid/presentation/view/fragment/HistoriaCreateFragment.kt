package com.javialonso.peccdroid.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.ReglasAceptacion
import com.javialonso.peccdroid.data.entity.ReglasAportes
import com.javialonso.peccdroid.presentation.internal.di.components.FeedComponent
import com.javialonso.peccdroid.presentation.presenter.HistoriaCreatePresenter
import com.javialonso.peccdroid.presentation.view.contract.HistoriaCreateView
import com.roughike.swipeselector.SwipeItem
import com.roughike.swipeselector.SwipeSelector
import javax.inject.Inject


class HistoriaCreateFragment : BaseFragment(), HistoriaCreateView {

    @Inject lateinit var historiaCreatePresenter: HistoriaCreatePresenter
    var butterknifeBinder: Unbinder? = null
    @BindView(R.id.sp_reglas_aceptacion_historia_create) @JvmField var reglasAceptacion: Spinner? = null
    @BindView(R.id.sp_reglas_aportes_historia_create) @JvmField var reglasAportes: Spinner? = null
    @BindView(R.id.ss_reglas_aceptacion_historia_create) @JvmField var ssReglasAceptacion: SwipeSelector? = null
    @BindView(R.id.ss_reglas_aportes_historia_create) @JvmField var ssReglasAportes: SwipeSelector? = null
    @BindView(R.id.et_titulo_historia_create) @JvmField var titulo: TextInputEditText? = null
    @BindView(R.id.et_criterios_aporte_historia_create) @JvmField var criteriosAceptacion: TextInputEditText? = null
    @BindView(R.id.et_contenido_aporte_historia_create) @JvmField var contenido: TextInputEditText? = null
    @BindView(R.id.cb_historia_create) @JvmField var esBifurcable: CheckBox? = null
    @BindView(R.id.ll_caracteres_container_historia_create) @JvmField var llCaracteres: LinearLayout? = null
    @BindView(R.id.cl_historia_create) @JvmField var clRoot: ConstraintLayout? = null
    @BindView(R.id.et_caracteres_historia_create) @JvmField var caracteres: EditText? = null

    var historiaCreateListener: HistoriaCreateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(FeedComponent::class.java).inject(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is HistoriaCreateListener) {
            this.historiaCreateListener = context
        }
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

        //

        ssReglasAceptacion?.setItems(
                SwipeItem(ReglasAceptacion.Autor, "Autor", "El autor de la historia aceptará o rechazará manualmente los aportes."),
                SwipeItem(ReglasAceptacion.Automatico, "Automático", "Los aportes serán aceptados en el momento de su creación.")
        )

        ssReglasAportes?.setItems(
                SwipeItem(ReglasAportes.SinReglas, "Sin reglas", "No existe ningún tipo de limitación en los aportes."),
                SwipeItem(ReglasAportes.MinCaracteres, "Mínimo de caracteres", "Los aportes deberán contener al menos los caracteres especificados."),
                SwipeItem(ReglasAportes.MaxCaracteres, "Máximo de caracteres", "Los aportes no podrán superar los caracteres especificados.")
        )

        ssReglasAportes?.setOnItemSelectedListener {
            when (it.value) {
                ReglasAportes.MinCaracteres -> {
                    addMinCaracteres()
                }
                ReglasAportes.MaxCaracteres -> {
                    addMaxCaracteres()
                }
                else -> {
                    limpiarInputCaracteres()
                }
            }
        }
    }

    fun addMaxCaracteres() {
        llCaracteres?.visibility = View.VISIBLE
    }

    fun addMinCaracteres() {
        llCaracteres?.visibility = View.VISIBLE
    }

    fun limpiarInputCaracteres() {
        llCaracteres?.visibility = View.GONE
    }

    override fun onDetach() {
        super.onDetach()
    }

    @OnClick(R.id.btn_historia_create) fun onButtonHistoriaCreateClicked() {
        val titulo = titulo?.text.toString()
        val contenido = contenido?.text.toString()
        val criteriosAceptacion = criteriosAceptacion?.text.toString()
        // val reglasAportes = ReglasAportes.valueOf(reglasAportes?.selectedItem.toString())
        val reglasAportes = ssReglasAportes?.selectedItem?.value as ReglasAportes
        val reglasAceptacion = ssReglasAceptacion?.selectedItem?.value as ReglasAceptacion
        var esBifurcable = false
        this.esBifurcable?.let { esBifurcable = it.isChecked }
        when (ssReglasAportes?.selectedItem?.value) {
            ReglasAportes.MaxCaracteres -> {
                val maxCaracteres = caracteres?.text.toString().toInt()
                historiaCreatePresenter.historiaCreate(titulo = titulo, contenido = contenido,
                        esBifurcable = esBifurcable, reglasAportes = reglasAportes,
                        reglasAceptacion = reglasAceptacion, criteriosAceptacion = criteriosAceptacion,
                        maxCaracteres = maxCaracteres)
            }
            ReglasAportes.MinCaracteres -> {
                val minCaracteres = caracteres?.text.toString().toInt()
                historiaCreatePresenter.historiaCreate(titulo = titulo, contenido = contenido,
                        esBifurcable = esBifurcable, reglasAportes = reglasAportes,
                        reglasAceptacion = reglasAceptacion, criteriosAceptacion = criteriosAceptacion,
                        minCaracteres = minCaracteres)
            }
            else -> historiaCreatePresenter.historiaCreate(titulo = titulo, contenido = contenido,
                    esBifurcable = esBifurcable, reglasAportes = reglasAportes,
                    reglasAceptacion = reglasAceptacion, criteriosAceptacion = criteriosAceptacion)
        }

    }

    override fun toHistoriasList() {
        this.historiaCreateListener?.navigateToHistoriasList()
    }


    override fun showError() {
        this.errorToast("Oops! Revisa los elementos del formulario.")
    }

    interface HistoriaCreateListener {
        fun navigateToHistoriasList()
    }
}