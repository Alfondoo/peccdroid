package com.javialonso.peccdroid.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.data.entity.HistoriaDetailEntity
import com.javialonso.peccdroid.data.entity.ReglasAportes
import com.javialonso.peccdroid.presentation.internal.di.components.FeedComponent
import com.javialonso.peccdroid.presentation.presenter.HistoriaAporteCreatePresenter
import com.javialonso.peccdroid.presentation.view.contract.HistoriaAporteCreateView
import javax.inject.Inject


class HistoriaAporteCreateFragment : BaseFragment(), HistoriaAporteCreateView {
    @Inject lateinit var historiaAporteCreatePresenter: HistoriaAporteCreatePresenter
    @BindView(R.id.btn_create_historia_aporte_create) @JvmField var crearAporte: Button? = null
    @BindView(R.id.et_contenido_aporte_historia_aporte_create) @JvmField var contenido: TextInputEditText? = null
    @BindView(R.id.cb_bifurcable_historia_aporte) @JvmField var esBifurcable: CheckBox? = null
    @BindView(R.id.tv_reglas_aporte_historia_aporte_create_view) @JvmField var reglasAporte: TextView? = null
    @BindView(R.id.tv_reglas_aporte_valor_historia_aporte_create_view) @JvmField var reglasAporteValor: TextView? = null
    var butterknifeBinder: Unbinder? = null
    private var historiaAporteCreateListener: HistoriaAporteCreateListener? = null

    interface HistoriaAporteCreateListener {
        fun viewBackwardsHistoriaDetail()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(FeedComponent::class.java).inject(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is HistoriaAporteCreateFragment.HistoriaAporteCreateListener) {
            this.historiaAporteCreateListener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_historia_aporte_create_view, container, false)
        butterknifeBinder = ButterKnife.bind(this, fragmentView)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.historiaAporteCreatePresenter.setView(this)
        this.arguments?.getSerializable("aporte")?.let { this.historiaAporteCreatePresenter.setAportePadre(it as AporteDetailEntity) }
        this.arguments?.getSerializable("historia")?.let { this.historiaAporteCreatePresenter.setHistoria(it as HistoriaDetailEntity) }
        initializeGUI()
    }

    private fun initializeGUI() {
        this.historiaAporteCreatePresenter.initializeGUI()
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

    @OnClick(R.id.btn_create_historia_aporte_create) fun onClickCreateAporte() {
        var esBifurcable = false
        this.esBifurcable?.let { esBifurcable = it.isChecked }
        this.historiaAporteCreatePresenter.historiaAporteCreate(contenido?.text.toString(), esBifurcable)
    }

    override fun toHistoriaDetail() {
        this.historiaAporteCreateListener?.viewBackwardsHistoriaDetail()
    }

    override fun updateReglas(reglasAportes: ReglasAportes, caracteres: Int) {
        this.reglasAporte?.text = reglasAportes.name
        this.reglasAporteValor?.text = "($caracteres)"
        if (reglasAportes == ReglasAportes.MaxCaracteres) {
            crearAporte?.isEnabled = false
            this.contenido?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    crearAporte?.isEnabled = s.length <= caracteres
                }

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })
        } else if (reglasAportes == ReglasAportes.MinCaracteres) {
            this.contenido?.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    crearAporte?.isEnabled = s.length >= caracteres
                }

                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })
        }
    }

    override fun updateReglas(reglasAportes: ReglasAportes) {
        this.reglasAporte?.text = reglasAportes.name

    }
}
