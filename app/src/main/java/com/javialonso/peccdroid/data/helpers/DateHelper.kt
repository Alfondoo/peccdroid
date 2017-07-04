package com.javialonso.peccdroid.data.helpers

import java.util.*


class DateHelper {
    private val SECOND_MILLIS = 1000
    private val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private val DAY_MILLIS = 24 * HOUR_MILLIS

    fun getTimeAgo(time: Date): String? {

        val now = System.currentTimeMillis()
        /*val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        format.timeZone = TimeZone.getTimeZone("UTC")

        val date = format.parse(time)*/
        val ms = time.time
        if (ms > now || ms <= 0) {
            return null
        }

        val diff = now - ms
        if (diff < MINUTE_MILLIS) {
            return "ahora"
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "hace un minuto"
        } else if (diff < 50 * MINUTE_MILLIS) {
            return "hace " + diff / MINUTE_MILLIS + " minutos"
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "hace una hora"
        } else if (diff < 24 * HOUR_MILLIS) {
            return "hace " + diff / HOUR_MILLIS + " horas"
        } else if (diff < 48 * HOUR_MILLIS) {
            return "ayer"
        } else {
            return "hace " + diff / DAY_MILLIS + " dias"
        }

    }
}