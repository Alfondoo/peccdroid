package com.javialonso.peccdroid.presentation.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager


class StaticLayoutManager(context: Context, orientation: Int, reverseLayout: Boolean) : LinearLayoutManager(context, orientation, reverseLayout) {
    override fun canScrollVertically(): Boolean {
        return false
    }
}