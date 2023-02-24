package com.brahimboulkriaat.psa.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                CitiesList(cities = cities)
            }
        }
    }
}


@Composable
private fun CitiesList(cities: List<City>) {
    LazyColumn {
        items(cities) { city ->
            CityItem(city)
        }
    }
}

@Composable
private fun CityItem(city: City) {
    ElevatedCard(modifier = Modifier.padding(all = 16.dp).fillMaxWidth()) {
        Surface(modifier = Modifier.padding(all = 8.dp)) {
            Text(text = city.name)
        }
    }
}

private data class City(val id: Long, val name: String, val lat: Double, val lon: Double)

private val cities = listOf<City>(
    City(id = 1, name = "Paris", lat = 0.0, lon = 0.0),
    City(id = 2, name = "London", lat = 0.0, lon = 0.0),
    City(id = 3, name = "New York", lat = 0.0, lon = 0.0),
    City(id = 4, name = "Lisbon", lat = 0.0, lon = 0.0),
    City(id = 5, name = "Madrid", lat = 0.0, lon = 0.0),
    City(id = 6, name = "Budapest", lat = 0.0, lon = 0.0),
    City(id = 7, name = "Tunis", lat = 0.0, lon = 0.0),
    City(id = 8, name = "Tokyo", lat = 0.0, lon = 0.0),
    City(id = 9, name = "Seoul", lat = 0.0, lon = 0.0)
)