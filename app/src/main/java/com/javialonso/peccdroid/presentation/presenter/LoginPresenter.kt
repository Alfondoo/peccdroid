package com.javialonso.peccdroid.presentation.presenter

import android.support.annotation.NonNull
import com.javialonso.peccdroid.data.entity.TokenEntity
import com.javialonso.peccdroid.data.storage.TokenStorage
import com.javialonso.peccdroid.domain.interactor.DefaultObserver
import com.javialonso.peccdroid.domain.interactor.LoginUseCase
import com.javialonso.peccdroid.presentation.internal.PerActivity
import com.javialonso.peccdroid.presentation.view.fragment.LoginView
import javax.inject.Inject


@PerActivity
class LoginPresenter @Inject
constructor(@NonNull val loginUseCase: LoginUseCase) : Presenter {

    private var loginView: LoginView? = null
    private var tokenStorage: TokenStorage? = null

    fun setView(view: LoginView) {
        this.loginView = view
    }

    fun setTokenStorage(tokenStorage: TokenStorage) {
        this.tokenStorage = tokenStorage
    }

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        this.loginUseCase.dispose()
        this.loginView = null
    }

    fun login(user: String, password: String) {
        this.loginUseCase.execute(LoginObserver(), LoginUseCase.Params(user, password))
    }

    private fun error(message: String) {
        this.loginView?.showError(message)
    }

    fun registro() {
        this.loginView?.navigateRegistro()
    }

    fun toFeed() {
        this.loginView?.loginComplete()
    }

    private inner class LoginObserver : DefaultObserver<TokenEntity>() {

        override fun onComplete() {
            print("Okay")
        }

        override fun onError(exception: Throwable) {
            error(exception.localizedMessage)
            //error((exception as HttpException).response())
        }

        override fun onSuccess(t: TokenEntity) {
            print("Perfe")
            this@LoginPresenter.error(t.toString())
            this@LoginPresenter.tokenStorage?.saveData(t)
            toFeed()
        }
    }
}
