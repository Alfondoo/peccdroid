package com.javialonso.peccdroid.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
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
import com.javialonso.peccdroid.presentation.presenter.HistoriasListPresenter
import com.javialonso.peccdroid.presentation.view.adapter.HistoriasListAdapter
import com.javialonso.peccdroid.presentation.view.contract.HistoriasView
import javax.inject.Inject

class HistoriasListFragment : BaseFragment(), HistoriasView {
    @Inject lateinit var historiasListPresenter: HistoriasListPresenter
    @BindView(R.id.rv_historias_view) @JvmField var historiasRecyclerView: RecyclerView? = null
    var butterknifeBinder: Unbinder? = null

    var historiasListener: HistoriasListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(FeedComponent::class.java).inject(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is HistoriasListFragment.HistoriasListener) {
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
        this.historiasListPresenter.setView(this)
        retrieveHistorias()
        this.historiasRecyclerView?.adapter = HistoriasListAdapter(ArrayList<HistoriaEntity>())
        this.historiasRecyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        this.historiasRecyclerView?.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    private fun retrieveHistorias() {
        this.historiasListPresenter.historias()
    }

    override fun onResume() {
        super.onResume()
        this.historiasListPresenter.resume()
        this.historiasListPresenter.setView(this)
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onPause() {
        super.onPause()
        this.historiasListPresenter.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        butterknifeBinder?.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.historiasListPresenter.destroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun updateHistoriasList(historias: List<HistoriaEntity>) {
        val historiaAdapter = HistoriasListAdapter(historias)
        historiaAdapter.onItemClickListener = onItemClickListener
        this.historiasRecyclerView?.adapter = historiaAdapter
    }

    override fun showError(message: String) {
        showToastMessage(message)
    }

    private val onItemClickListener = object : HistoriasListAdapter.OnItemClickListener {
        override fun onHistoriaItemClicked(historia: HistoriaEntity) {
            if (this@HistoriasListFragment.historiasListPresenter != null && historia != null) {
                this@HistoriasListFragment.historiasListPresenter.onHistoriaClicked(historia)
            }
        }
    }

    @OnClick(R.id.fab_historias_view) fun onFabClicked() {
        historiasListPresenter.createHistoria()
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