package com.artemissoftware.run.domain

import com.artemissoftware.core.domain.models.location.LocationTimestamp
import com.artemissoftware.run.domain.models.RunData
import com.artemissoftware.run.domain.util.LocationDataCalculator
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.seconds

fun LocationTimestamp.toRunData(currentLocations: List<List<LocationTimestamp>>): RunData {

    val lastLocationsList = if(currentLocations.isNotEmpty()) {
        currentLocations.last() + this
    } else listOf(this)
    val newLocationsList = currentLocations.replaceLast(lastLocationsList)

    val distanceMeters = LocationDataCalculator.getTotalDistanceMeters(
        locations = newLocationsList
    )
    val distanceKm = distanceMeters / 1000.0
    val currentDuration = this.durationTimestamp

    val avgSecondsPerKm = if(distanceKm == 0.0) {
        0
    } else {
        (currentDuration.inWholeSeconds / distanceKm).roundToInt()
    }

    return RunData(
        distanceMeters = distanceMeters,
        pace = avgSecondsPerKm.seconds,
        locations = newLocationsList
    )
}

private fun <T> List<List<T>>.replaceLast(replacement: List<T>): List<List<T>> {
    if(this.isEmpty()) {
        return listOf(replacement)
    }
    return this.dropLast(1) + listOf(replacement)
}