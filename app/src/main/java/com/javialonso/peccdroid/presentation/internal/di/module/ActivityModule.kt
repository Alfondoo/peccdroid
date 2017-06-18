package com.javialonso.peccdroid.presentation.internal.di.module

import android.app.Activity
import com.javialonso.peccdroid.presentation.internal.PerActivity
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val activity: Activity) {

    @Provides @PerActivity internal fun activity(): Activity {
        return this.activity
    }
}
