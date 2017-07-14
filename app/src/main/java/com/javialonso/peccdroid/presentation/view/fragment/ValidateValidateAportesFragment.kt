package com.javialonso.peccdroid.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.AporteDetailEntity
import com.javialonso.peccdroid.presentation.internal.di.components.FeedComponent
import com.javialonso.peccdroid.presentation.presenter.ValidateAportesPresenter
import com.javialonso.peccdroid.presentation.view.SwipeAcceptRefuseTouchHelper
import com.javialonso.peccdroid.presentation.view.adapter.ValidateAportesAdapter
import com.javialonso.peccdroid.presentation.view.contract.ValidateAportesView
import javax.inject.Inject

class ValidateValidateAportesFragment : BaseFragment(), ValidateAportesView, SwipeAcceptRefuseTouchHelper.SwipeAcceptRefuse {
    @Inject lateinit var validateAportesPresenter: ValidateAportesPresenter
    @BindView(R.id.rv_aportes_pendientes) @JvmField var rvAportesPendientes: RecyclerView? = null
    var aportesPendientes: MutableList<AporteDetailEntity> = ArrayList()
    var butterknifeBinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(FeedComponent::class.java).inject(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_aporte_pendiente_view, container, false)
        butterknifeBinder = ButterKnife.bind(this, fragmentView)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.validateAportesPresenter.setView(this)
        this.arguments?.getInt("id")?.let { this.validateAportesPresenter.setHistoriaId(it) }
        this.validateAportesPresenter.aportesPendientes()
        this.rvAportesPendientes?.adapter = ValidateAportesAdapter(aportesPendientes)
        this.rvAportesPendientes?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        this.rvAportesPendientes?.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        val swipeAcceptRefuseTouchHelper = SwipeAcceptRefuseTouchHelper(this, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        val itemTouchHelper = ItemTouchHelper(swipeAcceptRefuseTouchHelper)
        itemTouchHelper.attachToRecyclerView(rvAportesPendientes)
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

    override fun loadAportes(aportes: List<AporteDetailEntity>) {
        aportesPendientes.clear()
        aportesPendientes.addAll(aportes)
        rvAportesPendientes?.adapter?.notifyDataSetChanged()
    }

    override fun onAccept(adapterPosition: Int) {
        this.validateAportesPresenter.aceptar(aportesPendientes[adapterPosition])
    }

    override fun onRefuse(adapterPosition: Int) {
        this.validateAportesPresenter.rechazar(aportesPendientes[adapterPosition])
    }

    override fun deleteAporte(aporte: AporteDetailEntity) {
        val aporte = aportesPendientes.find { it.id == aporte.id }
        aportesPendientes.remove(aporte)
        rvAportesPendientes?.adapter?.notifyDataSetChanged()
    }
}