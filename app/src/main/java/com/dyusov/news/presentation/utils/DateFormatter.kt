package com.dyusov.news.presentation.utils

import java.text.DateFormat
import java.text.SimpleDateFormat

// pattern: day, month and year only (example: "12/13/25")
private val formatter = SimpleDateFormat.getDateInstance(DateFormat.SHORT)

fun Long.formatDate(): String {
    return formatter.format(this)
}