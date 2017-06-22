package com.javialonso.peccdroid.presentation.view.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import butterknife.BindView
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.presentation.internal.HasComponent
import com.javialonso.peccdroid.presentation.internal.di.components.DaggerFeedComponent
import com.javialonso.peccdroid.presentation.internal.di.components.FeedComponent
import com.javialonso.peccdroid.presentation.presenter.FeedPresenter
import com.javialonso.peccdroid.presentation.view.fragment.ProfileFragment
import javax.inject.Inject

class FeedActivity : BaseActivity(), HasComponent<FeedComponent> {

    override lateinit var component: FeedComponent
    @Inject lateinit var feedPresenter: FeedPresenter
    @BindView(R.id.bnav_feed_activity) @JvmField var bottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        initializeInjector()
        // TODO: Bottom Navigation
        addFragment(R.id.fragmentFeedContainer, ProfileFragment())
    }

    private fun initializeInjector() {
        component = DaggerFeedComponent.builder().applicationComponent(applicationComponent).activityModule(activityModule).build()
    }

    private inner class BottomNavigationListener : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
            // R.id.it_profile_bnav -> toFragment(R.id.fragmentFeedContainer, ProfileFragment())
            // R.id.it_historias_bnav -> toFragment(R.id.fragmentFeedContainer, HistoriasFragment())
            // R.id.it_puntuaciones_bnav -> toFragment(R.id.fragmentFeedContainer, PuntuacionesFragment())
            }
            return true
        }
    }
}
