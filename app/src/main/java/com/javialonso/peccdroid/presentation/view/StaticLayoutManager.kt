package com.javialonso.peccdroid.presentation.view

import android.support.v4.view.ViewCompat.canScrollVertically
import android.content.Context
import android.support.v7.widget.LinearLayoutManager


class StaticLayoutManager(context: Context, orientation: Int, reverseLayout: Boolean) : LinearLayoutManager(context, orientation, reverseLayout) {
    override fun canScrollVertically(): Boolean {
        return false
    }
}