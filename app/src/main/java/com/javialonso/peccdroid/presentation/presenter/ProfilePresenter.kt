package com.javialonso.peccdroid.presentation.presenter

import android.support.annotation.NonNull
import com.javialonso.peccdroid.data.entity.ProfileEntity
import com.javialonso.peccdroid.domain.interactor.DefaultObserver
import com.javialonso.peccdroid.domain.interactor.ProfileUseCase
import com.javialonso.peccdroid.presentation.view.ProfileView
import javax.inject.Inject

class ProfilePresenter @Inject internal constructor(val profileUseCase: ProfileUseCase) : Presenter {

    private var profileView: ProfileView? = null

    fun setView(@NonNull view: ProfileView) {
        this.profileView = view
    }

    override fun pause() {

    }

    override fun destroy() {

    }

    override fun resume() {

    }

    fun retrieveProfile() {
        this.profileUseCase.execute(ProfileObserver(), Unit)
    }

    private inner class ProfileObserver : DefaultObserver<String>() {
        override fun onSuccess(t: String) {
            updateView(t)
        }

        override fun onComplete() {
            // no-op by default.
        }

        override fun onError(exception: Throwable) {
            // no-op by default.
        }
    }

    private fun updateView(s: String) {
        this.profileView?.updateProfileCard(s)
    }
}