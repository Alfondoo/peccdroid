package com.javialonso.peccdroid.presentation.view.contract

interface LoginView : BaseView {
    fun navigateRegistro()

    fun loginComplete()
    fun showSuccess()

    fun showLoader()
    fun hideLoader()
}
