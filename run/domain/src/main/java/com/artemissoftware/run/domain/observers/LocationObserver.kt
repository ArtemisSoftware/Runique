package com.artemissoftware.run.domain.observers

import com.artemissoftware.core.domain.models.location.LocationWithAltitude
import kotlinx.coroutines.flow.Flow

interface LocationObserver {
    fun observeLocation(interval: Long): Flow<LocationWithAltitude>
}