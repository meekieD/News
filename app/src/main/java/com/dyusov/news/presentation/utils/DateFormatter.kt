package com.dyusov.news.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dyusov.news.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

// pattern: day, month and year only (example: "12/13/25")
private val formatter = SimpleDateFormat.getDateInstance(DateFormat.SHORT)

@Composable
fun Long.formatDate(): String {
    val now = System.currentTimeMillis()
    val diff = now - this

    return when {
        diff < 0 -> "just now"
        diff < TimeUnit.MINUTES.toMillis(1) -> "just now"
        diff < TimeUnit.HOURS.toMillis(1) -> {
            val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
            stringResource(
                R.string.how_much_time_ago,
                minutes,
                if (minutes == 1L) {
                    stringResource(R.string.minute)
                } else {
                    stringResource(R.string.minutes)
                }
            )
        }

        diff < TimeUnit.DAYS.toMillis(1) -> {
            val hours = TimeUnit.MILLISECONDS.toHours(diff)
            stringResource(
                R.string.how_much_time_ago,
                hours,
                if (hours == 1L) {
                    stringResource(R.string.hour)
                } else {
                    stringResource(R.string.hours)
                }
            )
        }

        diff < TimeUnit.DAYS.toMillis(7) -> {
            val days = TimeUnit.MILLISECONDS.toDays(diff)
            stringResource(
                R.string.how_much_time_ago,
                days,
                if (days == 1L) {
                    stringResource(R.string.day)
                } else {
                    stringResource(R.string.days)
                }
            )
        }

        else -> {
            return formatter.format(this)
        }
    }
}