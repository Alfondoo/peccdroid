package com.javialonso.peccdroid.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.AporteEntity
import com.javialonso.peccdroid.data.entity.HistoriaEntity
import com.javialonso.peccdroid.data.entity.ProfileEntity
import com.javialonso.peccdroid.presentation.internal.di.components.FeedComponent
import com.javialonso.peccdroid.presentation.presenter.ProfilePresenter
import com.javialonso.peccdroid.presentation.view.adapter.AporteProfileAdapter
import com.javialonso.peccdroid.presentation.view.adapter.HistoriasProfileAdapter
import javax.inject.Inject

class ProfileFragment : BaseFragment(), ProfileView {
    @Inject lateinit var profilePresenter: ProfilePresenter
    private var butterknifeBinder: Unbinder? = null
    @BindView(R.id.tv_username_profile) @JvmField var username: TextView? = null
    @BindView(R.id.tv_aportes_num_profile) @JvmField var aportesNumber: TextView? = null
    @BindView(R.id.tv_media_aportes_profile) @JvmField var aportesAvg: TextView? = null
    @BindView(R.id.tv_historias_num_profile) @JvmField var historiasNumber: TextView? = null
    @BindView(R.id.tv_media_historias_profile) @JvmField var historiasAvg: TextView? = null
    @BindView(R.id.tv_aportes_profile_empty) @JvmField var aportesEmpty: TextView? = null
    @BindView(R.id.rv_aportes_profile) @JvmField var aportesRecyclerView: RecyclerView? = null
    @BindView(R.id.tv_historias_profile_empty) @JvmField var historiasEmpty: TextView? = null
    @BindView(R.id.rv_historias_profile) @JvmField var historiasRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(FeedComponent::class.java).inject(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_profile_view, container, false)
        butterknifeBinder = ButterKnife.bind(this, fragmentView)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.profilePresenter.setView(this)
        retrieveProfile()
    }

    override fun onResume() {
        super.onResume()
        this.profilePresenter.resume()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onPause() {
        super.onPause()
        this.profilePresenter.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        butterknifeBinder?.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.profilePresenter.destroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    fun retrieveProfile() {
        this.profilePresenter.retrieveProfile()
    }

    override fun updateProfileCard(profileEntity: ProfileEntity) {
        Log.e("H", profileEntity.historias.toString())
        Log.e("A", profileEntity.aportes.toString())
        this.username?.text = profileEntity.username
        this.historiasNumber?.text = profileEntity.historias.size.toString()
        this.aportesNumber?.text = profileEntity.aportes.size.toString()
        // TODO; Round puntuacion media.
        updateAportes(profileEntity.aportes)
        updateHistorias(profileEntity.historias)
    }

    private fun updateAportes(aportes: List<AporteEntity>) {
        if (!aportes.isEmpty()) {
            this.aportesRecyclerView?.adapter = AporteProfileAdapter(aportes)
            this.aportesRecyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            if (aportes.size > 1) {
                this.aportesRecyclerView?.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
            this.aportesEmpty?.visibility = View.GONE
            this.aportesRecyclerView?.visibility = View.VISIBLE
        }
    }

    private fun updateHistorias(historias: List<HistoriaEntity>) {
        if (!historias.isEmpty()) {
            this.historiasRecyclerView?.adapter = HistoriasProfileAdapter(historias)
            this.historiasRecyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            if (historias.size > 1) {
                this.historiasRecyclerView?.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
            this.historiasEmpty?.visibility = View.GONE
            this.historiasRecyclerView?.visibility = View.VISIBLE
        }
    }

}