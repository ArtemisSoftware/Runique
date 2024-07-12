package com.artemissoftware.core.presentation.util.extensions

import kotlin.math.pow
import kotlin.math.round

fun Double.toFormattedKm(): String {
    return "${this.roundToDecimals(1)} km"
}

private fun Double.roundToDecimals(decimalCount: Int): Double {
    val factor = 10f.pow(decimalCount)
    return round(this * factor) / factor
}