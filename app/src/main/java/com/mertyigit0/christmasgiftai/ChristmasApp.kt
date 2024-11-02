package com.mertyigit0.christmasgiftai

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun ChristmasApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "participantEntry") {
        composable("participantEntry") {
            ParticipantEntryScreen(navController = navController)
        }

        composable(
            route = "giftDraw/{participants}",
            arguments = listOf(navArgument("participants") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val participantsString = backStackEntry.arguments?.getString("participants")
            val participants = participantsString?.split(",") ?: emptyList()
           // GiftDrawScreen(participants = participants)
        }
    }
}
