package com.javialonso.peccdroid.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.HistoriaEntity
import com.javialonso.peccdroid.presentation.internal.di.components.FeedComponent
import com.javialonso.peccdroid.presentation.presenter.HistoriasPresenter
import com.javialonso.peccdroid.presentation.view.HistoriasProfileAdapter
import javax.inject.Inject

class HistoriasFragment : BaseFragment(), HistoriasView {

    @BindView(R.id.rv_historias_view) @JvmField var historiasRecyclerView: RecyclerView? = null

    override fun showError(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    @Inject lateinit var historiasPresenter: HistoriasPresenter
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
        val fragmentView = inflater.inflate(R.layout.fragment_historia_view, container, false)
        butterknifeBinder = ButterKnife.bind(this, fragmentView)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.historiasPresenter.setView(this)
        this.historiasRecyclerView?.adapter = HistoriasProfileAdapter(items = ArrayList<HistoriaEntity>())
        this.historiasRecyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        retrieveHistorias()
    }

    private fun retrieveHistorias() {
        Log.e("HistoriasFragment", "retrieveHistorias")
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
        this.historiasRecyclerView?.adapter = HistoriasProfileAdapter(historias)
    }
}