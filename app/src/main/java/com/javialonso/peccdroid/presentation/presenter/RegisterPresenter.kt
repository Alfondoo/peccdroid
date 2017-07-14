package com.javialonso.peccdroid.presentation.presenter

import android.support.annotation.NonNull
import com.javialonso.peccdroid.domain.interactor.DefaultObserver
import com.javialonso.peccdroid.domain.interactor.RegisterUseCase
import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.view.contract.RegisterView
import javax.inject.Inject

@PerActivity
class RegisterPresenter @Inject
constructor(val registerUseCase: RegisterUseCase) : Presenter {

    private var registerView: RegisterView? = null

    fun setView(@NonNull view: RegisterView) {
        this.registerView = view
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.registerUseCase.dispose()
        this.registerView = null
    }

    fun registro(user: String, email: String, password: String, passwordConfirm: String) {
        this.showProgressBar()
        this.registerUseCase.execute(RegisterObserver(), RegisterUseCase.Params(user, email, password, passwordConfirm))
    }

    private fun error(message: String) {
        this.registerView?.showError(message)
    }

    private fun hideProgressBar() {
        this.registerView?.hideProgressBar()
    }

    private fun showProgressBar() {
        this.registerView?.showProgressBar()
    }

    private inner class RegisterObserver : DefaultObserver<String>() {

        override fun onComplete() {
            hideProgressBar()
        }

        override fun onError(exception: Throwable) {
            error(exception.localizedMessage)
            hideProgressBar()
        }

        override fun onSuccess(t: String) {
            hideProgressBar()
        }
    }
}

