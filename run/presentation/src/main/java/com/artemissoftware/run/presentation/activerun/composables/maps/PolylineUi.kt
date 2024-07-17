package com.artemissoftware.run.presentation.activerun.composables.maps

import androidx.compose.ui.graphics.Color
import com.artemissoftware.core.domain.models.location.Location

data class PolylineUi(
    val location1: Location,
    val location2: Location,
    val color: Color
)