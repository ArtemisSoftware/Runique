package com.artemissoftware.run.mappers

import android.location.Location
import com.artemissoftware.core.domain.models.location.LocationWithAltitude

fun Location.toLocationWithAltitude(): LocationWithAltitude {
    return LocationWithAltitude(
        location = com.artemissoftware.core.domain.models.location.Location(
            lat = latitude,
            long = longitude
        ),
        altitude = altitude
    )
}