package com.wrld.musicplayer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wrld.musicplayer.ui.screens.HomeScreen
import com.wrld.musicplayer.ui.screens.PlayerScreen
import com.wrld.musicplayer.ui.screens.LibraryScreen
import com.wrld.musicplayer.ui.screens.PlaylistsScreen
import com.wrld.musicplayer.ui.screens.SettingsScreen

seal class WrldScreen(val route: String) {
    object Home : WrldScreen("home")
    object Player : WrldScreen("player")
    object Library : WrldScreen("library")
    object Playlists : WrldScreen("playlists")
    object Settings : WrldScreen("settings")
}

@Composable
fun WrldNavGraph(
    navController: NavHostController,
    startDestination: String = WrldScreen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(WrldScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(WrldScreen.Player.route) {
            PlayerScreen(navController)
        }
        composable(WrldScreen.Library.route) {
            LibraryScreen(navController)
        }
        composable(WrldScreen.Playlists.route) {
            PlaylistsScreen(navController)
        }
        composable(WrldScreen.Settings.route) {
            SettingsScreen(navController)
        }
    }
}
