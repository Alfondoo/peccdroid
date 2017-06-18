package com.javialonso.peccdroid.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.javialonso.peccdroid.R
import com.javialonso.peccdroid.presentation.internal.di.components.AuthorizationComponent
import com.javialonso.peccdroid.presentation.presenter.RegisterPresenter
import javax.inject.Inject


class RegisterFragment : BaseFragment() {

    @Inject lateinit var registerPresenter: RegisterPresenter
    private var butterknifeBinder: Unbinder? = null
    @BindView(R.id.et_username_register) @JvmField var username: EditText? = null
    @BindView(R.id.et_email_register) @JvmField var email: EditText? = null
    @BindView(R.id.et_password_register) @JvmField var password: EditText? = null
    @BindView(R.id.et_password_confirmation_register) @JvmField var passwordConfirm: EditText? = null

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
    }

    @OnClick(R.id.btn_register) fun onRegistroButtonClicked() {
        registerPresenter.registro(username?.editableText.toString(), email?.editableText.toString(), password?.editableText.toString(), passwordConfirm?.editableText.toString())
    }
}