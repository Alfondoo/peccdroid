package com.javialonso.peccdroid.presentation.view.fragment

import android.app.Fragment
import android.support.v7.view.SupportActionModeWrapper
import android.widget.Toast
import com.javialonso.peccdroid.presentation.internal.HasComponent


abstract class BaseFragment : android.support.v4.app.Fragment() {
    /**
     * Shows a [android.widget.Toast] message.

     * @param message An string representing a message to be shown.
     */
    protected fun showToastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<C>).component)
    }
}