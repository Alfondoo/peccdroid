package com.javialonso.peccdroid.presentation.internal.di.components

import android.app.Activity
import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.internal.di.module.ActivityModule
import dagger.Component


@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class)) interface ActivityComponent {
    //Exposed to sub-graphs.
    fun activity(): Activity
}