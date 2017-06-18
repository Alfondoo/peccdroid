package com.javialonso.peccdroid.presentation.view.activity

import android.os.Bundle
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.presentation.internal.HasComponent
import com.javialonso.peccdroid.presentation.internal.di.components.AuthorizationComponent
import com.javialonso.peccdroid.presentation.internal.di.components.DaggerAuthorizationComponent
import com.javialonso.peccdroid.presentation.view.fragment.LoginFragment
import com.javialonso.peccdroid.presentation.view.fragment.RegisterFragment

class MainActivity : BaseActivity(), HasComponent<AuthorizationComponent>, LoginFragment.LoginListener {

    override lateinit var component: AuthorizationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        initializeInjector()
        addFragment(R.id.fragmentContainer, LoginFragment())

    }

    private fun initializeInjector() {
        component = DaggerAuthorizationComponent.builder().applicationComponent(applicationComponent).activityModule(activityModule).build()
    }

    override fun onLoginComplete() {
        //toFragment(R.id.fragmentContainer, FeedFragment())
    }

    override fun toRegister() {
        toFragment(R.id.fragmentContainer, RegisterFragment())
    }
}
