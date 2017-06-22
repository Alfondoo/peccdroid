package com.javialonso.peccdroid.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.data.entity.ProfileEntity
import com.javialonso.peccdroid.presentation.internal.di.components.FeedComponent
import com.javialonso.peccdroid.presentation.presenter.ProfilePresenter
import com.javialonso.peccdroid.presentation.view.ProfileView
import javax.inject.Inject

class ProfileFragment : BaseFragment(), ProfileView {
    @Inject lateinit var profilePresenter: ProfilePresenter
    private var butterknifeBinder: Unbinder? = null
    @BindView(R.id.tv_username_profile) @JvmField var username: TextView? = null
    @BindView(R.id.tv_aportes_num_profile) @JvmField var aportesNumber: TextView? = null
    @BindView(R.id.tv_media_aportes_profile) @JvmField var aportesAvg: TextView? = null
    @BindView(R.id.tv_historias_num_profile) @JvmField var historiasNumber: TextView? = null
    @BindView(R.id.tv_media_historias_profile) @JvmField var historiasAvg: TextView? = null

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
        this.username?.text = profileEntity.username
    }

}