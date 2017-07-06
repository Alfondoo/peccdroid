package com.javialonso.peccdroid.presentation.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import butterknife.BindView
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.presentation.internal.HasComponent
import com.javialonso.peccdroid.presentation.internal.di.components.DaggerFeedComponent
import com.javialonso.peccdroid.presentation.internal.di.components.FeedComponent
import com.javialonso.peccdroid.presentation.presenter.FeedPresenter
import com.javialonso.peccdroid.presentation.view.fragment.HistoriaCreateFragment
import com.javialonso.peccdroid.presentation.view.fragment.HistoriaDetailFragment
import com.javialonso.peccdroid.presentation.view.fragment.HistoriasFragment
import com.javialonso.peccdroid.presentation.view.fragment.ProfileFragment
import javax.inject.Inject

class FeedActivity : BaseActivity(), HasComponent<FeedComponent>, HistoriasFragment.HistoriasListener {


    override lateinit var component: FeedComponent
    @Inject lateinit var feedPresenter: FeedPresenter
    @BindView(R.id.bnav_feed_activity) @JvmField var bottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        bottomNavigation = findViewById(R.id.bnav_feed_activity) as BottomNavigationView?
        initializeInjector()
        // TODO: Bottom Navigation
        bottomNavigation?.setOnNavigationItemSelectedListener(
                { item ->
                    when (item.itemId) {
                        R.id.it_profile_bnav -> toFragment(R.id.fragmentFeedContainer, ProfileFragment(), false)
                        R.id.it_historias_bnav -> toFragment(R.id.fragmentFeedContainer, HistoriasFragment(), false)
                    }
                    true
                })
        addFragment(R.id.fragmentFeedContainer, ProfileFragment())
    }

    private fun initializeInjector() {
        component = DaggerFeedComponent.builder().applicationComponent(applicationComponent).activityModule(activityModule).build()
    }

    override fun viewHistoriaDetail(id: Int) {
        val fragment = HistoriaDetailFragment()
        val bundleData = Bundle()
        bundleData.putInt("id", id)
        fragment.arguments = bundleData
        this.toFragment(R.id.fragmentFeedContainer, fragment, true)
    }

    override fun viewCreateHistoria() {
        this.toFragment(R.id.fragmentFeedContainer, HistoriaCreateFragment(), true)
    }
}
