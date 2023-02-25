package com.brahimboulkriaat.psa.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDetailsScreen(mainViewModel: MainViewModel, navController: NavHostController) {
    Scaffold( topBar = { TopAppBar(title = { Text("Details") }) }) {
        Surface(modifier = Modifier.padding(it)) {

        }
    }
}