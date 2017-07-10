package com.javialonso.peccdroid.presentation.view

import android.graphics.*
import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.javialonso.peccdroid.R


class SwipeAcceptRefuseTouchHelper(val fragment: Fragment, swipeDirection: Int) : ItemTouchHelper.SimpleCallback(0, swipeDirection) {

    interface SwipeAcceptRefuse {
        fun onAccept(adapterPosition: Int)
        fun onRefuse(adapterPosition: Int)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val listener = fragment as SwipeAcceptRefuse

        if (direction == ItemTouchHelper.LEFT) {
            listener.onRefuse(position)
        } else {
            listener.onAccept(position)
        }
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

        var icon: Bitmap? = null
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

            val p = Paint()
            val itemView = viewHolder.itemView
            val height = itemView.bottom.toFloat() - itemView.top.toFloat()
            val width = height / 3

            if (dX > 0) {
                p.color = Color.parseColor("#43A047")
                val background = RectF(itemView.left.toFloat(), itemView.top.toFloat(), dX, itemView.bottom.toFloat())
                c.drawRect(background, p)
                val drawable = ResourcesCompat.getDrawable(fragment.resources, R.drawable.ic_check_white, null)
                drawable?.let { icon = drawableToBitmap(drawable) }
                val icon_dest = RectF(itemView.left.toFloat() + width, itemView.top.toFloat() + width, itemView.left.toFloat() + 2 * width, itemView.bottom.toFloat() - width)
                c.drawBitmap(icon, null, icon_dest, p)
            } else {
                p.color = Color.parseColor("#F44336")
                val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                c.drawRect(background, p)
                val drawable = ResourcesCompat.getDrawable(fragment.resources, R.drawable.ic_close_white, null)
                drawable?.let { icon = drawableToBitmap(drawable) }
                val icon_dest = RectF(itemView.right.toFloat() - 2 * width, itemView.top.toFloat() + width, itemView.right.toFloat() - width, itemView.bottom.toFloat() - width)
                c.drawBitmap(icon, null, icon_dest, p)
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}