package com.brahimboulkriaat.psa.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.brahimboulkriaat.psa.model.Weather

@Composable
fun WeatherComponent(weather: Weather) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Coordinates : ${weather.lon}, ${weather.lat}")
        Text(text = "Timezone : ${weather.timeZone}")
    }
}