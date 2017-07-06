package com.javialonso.peccdroid.presentation.internal.di.components


import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.internal.di.module.ActivityModule
import com.javialonso.peccdroid.presentation.internal.di.module.FeedModule
import com.javialonso.peccdroid.presentation.presenter.HistoriaCreatePresenter
import com.javialonso.peccdroid.presentation.view.fragment.HistoriaCreateFragment
import com.javialonso.peccdroid.presentation.view.fragment.HistoriaDetailFragment
import com.javialonso.peccdroid.presentation.view.fragment.HistoriasFragment
import com.javialonso.peccdroid.presentation.view.fragment.ProfileFragment
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class, FeedModule::class))
interface FeedComponent : ActivityComponent {
    fun inject(profileFragment: ProfileFragment)
    fun inject(historiasFragment: HistoriasFragment)
    fun inject(historiasDetailFragment: HistoriaDetailFragment)
    fun inject(historiaCreateFragment: HistoriaCreateFragment)
}
