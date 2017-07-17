package com.javialonso.peccdroid.presentation.view.fragment

import android.widget.Toast
import com.javialonso.peccdroid.presentation.internal.HasComponent
import es.dmoral.toasty.Toasty


abstract class BaseFragment : android.support.v4.app.Fragment() {
    /**
     * Shows a [android.widget.Toast] message.

     * @param message An string representing a message to be shown.
     */
    protected fun showToastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    protected fun errorToast(message: String) {
        Toasty.error(activity, message, Toast.LENGTH_SHORT).show()
    }

    protected fun successToast(message: String) {
        Toasty.success(activity, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<C>).component)
    }
}