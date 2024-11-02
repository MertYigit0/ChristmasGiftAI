package com.mertyigit0.christmasgiftai

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person


import androidx.compose.ui.graphics.vector.ImageVector

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

val topLevelRoutes = listOf(
    TopLevelRoute("Katılımcı Girişi", "participantEntry", Icons.Filled.Person),
    TopLevelRoute("Baking", "baking", Icons.Filled.DateRange) // Updated route name
)
