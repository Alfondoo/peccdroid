package com.javialonso.peccdroid.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread{
    fun getScheduler(): Scheduler
}