package com.javialonso.peccdroid.presentation.presenter

import com.javialonso.peccdroid.domain.interactor.DefaultObserver
import com.javialonso.peccdroid.domain.interactor.LoginUseCase
import com.javialonso.peccdroid.domain.interactor.RegisterUseCase
import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.view.RegisterView
import javax.inject.Inject

@PerActivity
class RegisterPresenter @Inject
constructor(val registerUseCase: RegisterUseCase) : Presenter {

    private var registerView: RegisterView? = null

    fun setView(view: RegisterView) {
        this.registerView = view
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.registerUseCase.dispose()
        this.registerView = null
    }

    fun registro(user: String, email: String, password: String, passwordConfirm: String) {
        this.registerUseCase.execute(RegisterObserver(), RegisterUseCase.Params(user, email, password, passwordConfirm))
    }

    private fun error(message: String){
        this.registerView?.showError(message)
    }

    private inner class RegisterObserver : DefaultObserver<String>() {

        override fun onComplete() {
            print("Okay")
        }

        override fun onError(exception: Throwable) {
            error(exception.localizedMessage)
        }

        override fun onNext(t: String) {
            print("Perfe")
        }
    }
}

