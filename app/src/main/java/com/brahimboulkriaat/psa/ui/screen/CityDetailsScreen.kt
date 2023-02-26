package com.brahimboulkriaat.psa.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.brahimboulkriaat.psa.model.Weather
import com.brahimboulkriaat.psa.ui.MainViewModel
import com.brahimboulkriaat.psa.ui.component.ErrorComponent
import com.brahimboulkriaat.psa.ui.component.LoaderComponent
import com.brahimboulkriaat.psa.ui.component.WeatherComponent
import com.brahimboulkriaat.psa.util.DataState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDetailsScreen(
    mainViewModel: MainViewModel,
    lon: Float,
    lat: Float
) {
    Scaffold(topBar = { TopAppBar(title = { Text("Details") }) }) {
        Surface(modifier = Modifier.padding(it)) {
            mainViewModel.run {
                getWeather(lon, lat)
                val weatherState = weatherState.collectAsStateWithLifecycle(DataState.Loading)
                when (weatherState.value) {
                    is DataState.Loading -> LoaderComponent()
                    is DataState.Error -> ErrorComponent(
                        onRetryClick = {
                            setWeatherStateToLoading()
                            getWeather(lat, lon)
                        }
                    )
                    is DataState.Success<Weather> -> {
                        WeatherComponent(
                            weather = (weatherState.value as DataState.Success<Weather>).data
                        )
                    }
                }
            }
        }
    }
}