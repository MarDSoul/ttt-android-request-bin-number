package app.mardsoul.requestbin.utils

import java.text.SimpleDateFormat

fun Boolean.convertYesNoString(): String {
    return if (this) "Yes" else "No"
}

fun Long.getTime(): String {
    val timeFormat = SimpleDateFormat("HH:mm:ss")
    return timeFormat.format(this)
}

fun Long.getDate(): String {
    val timeFormat = SimpleDateFormat("dd MMM yyyy")
    return timeFormat.format(this)
}