package com.brahimboulkriaat.psa.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.brahimboulkriaat.psa.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDetailsScreen(mainViewModel: MainViewModel, navController: NavHostController) {
    Scaffold( topBar = { TopAppBar(title = { Text("Details") }) }) {
        Surface(modifier = Modifier.padding(it)) {

        }
    }
}