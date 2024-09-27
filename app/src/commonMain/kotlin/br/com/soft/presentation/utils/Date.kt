package br.com.soft.presentation.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Long.formatDate(): String {
    return Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.UTC).date.toString()
}

fun Int.formatMinute(): String {
    return this.toString().padStart(2, '0')
}
fun Int.formatHour(): String {
    return this.toString().padStart(2, '0')
}
