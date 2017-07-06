package com.javialonso.peccdroid.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.HistoriaEntity
import com.javialonso.peccdroid.presentation.internal.di.components.FeedComponent
import com.javialonso.peccdroid.presentation.presenter.HistoriasPresenter
import com.javialonso.peccdroid.presentation.view.adapter.HistoriasViewAdapter
import javax.inject.Inject

class HistoriasFragment : BaseFragment(), HistoriasView {
    @Inject lateinit var historiasPresenter: HistoriasPresenter
    @BindView(R.id.rv_historias_view) @JvmField var historiasRecyclerView: RecyclerView? = null
    var butterknifeBinder: Unbinder? = null

    var historiasListener: HistoriasListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(FeedComponent::class.java).inject(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is HistoriasFragment.HistoriasListener) {
            this.historiasListener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_historia_view, container, false)
        butterknifeBinder = ButterKnife.bind(this, fragmentView)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.historiasPresenter.setView(this)
        retrieveHistorias()
        this.historiasRecyclerView?.adapter = HistoriasViewAdapter(ArrayList<HistoriaEntity>())
        this.historiasRecyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    private fun retrieveHistorias() {
        this.historiasPresenter.historias()
    }

    override fun onResume() {
        super.onResume()
        this.historiasPresenter.resume()
        this.historiasPresenter.setView(this)
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onPause() {
        super.onPause()
        this.historiasPresenter.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        butterknifeBinder?.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.historiasPresenter.destroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun updateHistoriasList(historias: List<HistoriaEntity>) {
        val historiaAdapter = HistoriasViewAdapter(historias)
        historiaAdapter.onItemClickListener = onItemClickListener
        this.historiasRecyclerView?.adapter = historiaAdapter
    }

    override fun showError(message: String) {
        showToastMessage(message)
    }

    private val onItemClickListener = object : HistoriasViewAdapter.OnItemClickListener {
        override fun onHistoriaItemClicked(historia: HistoriaEntity) {
            if (this@HistoriasFragment.historiasPresenter != null && historia != null) {
                this@HistoriasFragment.historiasPresenter.onHistoriaClicked(historia)
            }
        }
    }

    @OnClick(R.id.fab_historias_view) fun onFabClicked() {
        historiasPresenter.createHistoria()
    }

    override fun toDetailHistoria(historia: HistoriaEntity) {
        this.historiasListener?.viewHistoriaDetail(historia.id)
    }

    override fun toCreateHistoria() {
        this.historiasListener?.viewCreateHistoria()
    }


    interface HistoriasListener {
        fun viewHistoriaDetail(id: Int)

        fun viewCreateHistoria()
    }
}