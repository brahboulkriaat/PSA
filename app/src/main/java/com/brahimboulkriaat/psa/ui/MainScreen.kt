package com.brahimboulkriaat.psa.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.brahimboulkriaat.psa.ui.screen.CityDetailsScreen
import com.brahimboulkriaat.psa.ui.screen.HomeScreen
import com.brahimboulkriaat.psa.ui.screen.NewCityScreen

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {

        composable(route = "home") {
            HomeScreen(mainViewModel = mainViewModel, navController = navController)
        }

        composable(
            route = "details/{lon}/{lat}",
            arguments = listOf(navArgument("lon") {
                type = NavType.FloatType
                defaultValue = 0.0F
            }, navArgument("lat") {
                type = NavType.FloatType
                defaultValue = 0.0F
            })
        ) {
            val args = it.arguments
            val lon = args?.getFloat("lon")!!
            val lat = args.getFloat("lat")
            CityDetailsScreen(mainViewModel = mainViewModel, lon, lat)
        }

        composable(route = "new") {
            NewCityScreen(mainViewModel = mainViewModel, navController = navController)
        }
    }
}