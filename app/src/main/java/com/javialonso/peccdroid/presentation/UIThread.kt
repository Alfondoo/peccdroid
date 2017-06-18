package com.javialonso.peccdroid.presentation

import com.javialonso.peccdroid.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UIThread @Inject
internal constructor() : PostExecutionThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
