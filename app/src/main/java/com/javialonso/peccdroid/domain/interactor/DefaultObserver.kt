package com.javialonso.peccdroid.domain.interactor

import io.reactivex.observers.DisposableMaybeObserver


open class DefaultObserver<T> : DisposableMaybeObserver<T>() {
    override fun onSuccess(t: T) {
    }

    override fun onComplete() {
        // no-op by default.
    }

    override fun onError(exception: Throwable) {
        // no-op by default.
    }
}