package com.javialonso.peccdroid.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.presentation.component.SharedPreferencesTokenStorage
import com.javialonso.peccdroid.presentation.internal.di.components.AuthorizationComponent
import com.javialonso.peccdroid.presentation.presenter.LoginPresenter
import javax.inject.Inject


class LoginFragment : BaseFragment(), LoginView {
    @Inject lateinit var loginPresenter: LoginPresenter
    internal var butterknifeBinder: Unbinder? = null
    @BindView(R.id.et_username_login) @JvmField var username: TextInputEditText? = null
    @BindView(R.id.et_password_login) @JvmField var password: TextInputEditText? = null
    @BindView(R.id.btn_login) @JvmField var btnLogin: Button? = null

    var loginListener: LoginListener? = null

    init {
        retainInstance = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(AuthorizationComponent::class.java).inject(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is LoginListener) {
            this.loginListener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_login_view, container, false)
        butterknifeBinder = ButterKnife.bind(this, fragmentView)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.loginPresenter.setView(this)
        this.loginPresenter.setTokenStorage(SharedPreferencesTokenStorage(activity))
    }

    override fun onResume() {
        super.onResume()
        this.loginPresenter.resume()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onPause() {
        super.onPause()
        this.loginPresenter.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        butterknifeBinder?.unbind()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.loginPresenter.destroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun navigateRegistro() {
        this.loginListener?.toRegister()
    }

    override fun loginComplete() {
        this.loginListener?.onLoginComplete()
    }

    override fun showError(message: String) {
        showToastMessage(message)
    }

    @OnClick(R.id.btn_login) internal fun onButtonLoginClick() {
        this.loginPresenter.login(username?.editableText.toString(), password?.editableText.toString())
    }

    @OnClick(R.id.btn_registro_login) internal fun onButtonRegistroClick() {
        this.loginPresenter.registro()
    }

    interface LoginListener {
        fun onLoginComplete()

        fun toRegister()
    }
}
