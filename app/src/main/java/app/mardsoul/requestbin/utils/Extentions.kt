package app.mardsoul.requestbin.utils

fun Boolean.convertYesNoString(): String {
    return if (this) "Yes" else "No"
}