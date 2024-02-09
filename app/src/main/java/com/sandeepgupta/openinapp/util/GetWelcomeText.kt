package com.sandeepgupta.openinapp.util

import java.time.LocalTime

fun getWelcomeText(): String {
    val currentTime = LocalTime.now()

    return when {
        currentTime.isAfter(LocalTime.of(5, 0)) && currentTime.isBefore(LocalTime.of(12, 0)) -> "Good Morning"
        currentTime.isAfter(LocalTime.of(12, 0)) && currentTime.isBefore(LocalTime.of(17, 0)) -> "Good Afternoon"
        currentTime.isAfter(LocalTime.of(17, 0)) && currentTime.isBefore(LocalTime.of(20, 0)) -> "Good Evening"
        else -> "Good Night"
    }
}