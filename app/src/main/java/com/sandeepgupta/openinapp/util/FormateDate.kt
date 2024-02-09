package com.sandeepgupta.openinapp.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.Locale

fun formatDate(date:String,format:String): String {
    val inputFormatter = DateTimeFormatter.ISO_INSTANT
    val outputFormatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH)

    val instant = Instant.from(inputFormatter.parse(date))
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

    return outputFormatter.format(localDateTime)
}

fun formatDate(date:String): String {

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val temporalAccessor: TemporalAccessor = formatter.parse(date)

    return DateTimeFormatter.ofPattern("dd MMM").format(temporalAccessor)
}
