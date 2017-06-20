package com.javialonso.peccdroid.presentation.internal.di.components


import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.internal.di.module.ActivityModule
import com.javialonso.peccdroid.presentation.internal.di.module.FeedModule
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class, FeedModule::class))
interface FeedComponent : ActivityComponent {
    //fun inject(profileFragment: LoginFragment)
    //fun inject(historiasFragment: RegisterFragment)
}
