package com.javialonso.peccdroid.presentation.view.fragment

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.presentation.internal.di.components.AuthorizationComponent
import com.javialonso.peccdroid.presentation.presenter.RegisterPresenter
import javax.inject.Inject


class RegisterFragment : BaseFragment(), RegisterView {

    @Inject lateinit var registerPresenter: RegisterPresenter
    private var butterknifeBinder: Unbinder? = null
    @BindView(R.id.et_username_register) @JvmField var username: TextInputEditText? = null
    @BindView(R.id.et_email_register) @JvmField var email: TextInputEditText? = null
    @BindView(R.id.et_password_register) @JvmField var password: TextInputEditText? = null
    @BindView(R.id.et_password_confirmation_register) @JvmField var passwordConfirm: TextInputEditText? = null
    @BindView(R.id.btn_register) @JvmField var btnRegister: Button? = null
    @BindView(R.id.pb_register) @JvmField var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.getComponent(AuthorizationComponent::class.java).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_register_view, container, false)
        butterknifeBinder = ButterKnife.bind(this, fragmentView)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.registerPresenter.setView(this)
    }

    @OnClick(R.id.btn_register) fun onRegistroButtonClicked() {
        Log.e("RegisterFragment", "Hola")
        registerPresenter.registro(username?.editableText.toString(), email?.editableText.toString(), password?.editableText.toString(), passwordConfirm?.editableText.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        registerPresenter.destroy()
        butterknifeBinder?.unbind()
    }

    override fun onResume() {
        super.onResume()
        this.registerPresenter.resume()
    }

    override fun onPause() {
        super.onPause()
        this.registerPresenter.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.registerPresenter.destroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun showProgressBar() {
        btnRegister?.visibility = View.INVISIBLE
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar?.visibility = View.GONE
        btnRegister?.visibility = View.VISIBLE
    }

    override fun showError(message: String) {
        showToastMessage(message)
    }

    interface RegisterListener {
        fun onRegisterComplete()
    }
}