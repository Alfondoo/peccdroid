package com.javialonso.peccdroid.domain.interactor

import android.support.v4.util.Preconditions
import com.javialonso.peccdroid.domain.executor.PostExecutionThread
import com.javialonso.peccdroid.domain.executor.ThreadExecutor
import com.javialonso.peccdroid.presentation.presenter.LoginPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, Params>(val threadExecutor: ThreadExecutor, val postExecutionThread: PostExecutionThread, val disposables: CompositeDisposable = CompositeDisposable()) {

    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Params) {
        Preconditions.checkNotNull(observer)
        val observable = this.buildUseCaseObservable(params).subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecutionThread.getScheduler())
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose(){
        if(!disposables.isDisposed){
            disposables.dispose()
        }
    }

    private fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(disposables)
        disposables.add(disposable)
    }
}