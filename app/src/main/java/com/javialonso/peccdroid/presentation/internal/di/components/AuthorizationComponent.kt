package com.javialonso.peccdroid.presentation.internal.di.components


import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.internal.di.module.ActivityModule
import com.javialonso.peccdroid.presentation.internal.di.module.AuthorizationModule
import com.javialonso.peccdroid.presentation.view.fragment.LoginFragment
import com.javialonso.peccdroid.presentation.view.fragment.RegisterFragment
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class, AuthorizationModule::class))
interface AuthorizationComponent : ActivityComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(registerFragment: RegisterFragment)
}
