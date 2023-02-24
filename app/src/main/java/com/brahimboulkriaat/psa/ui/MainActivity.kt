package com.brahimboulkriaat.psa.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                /*CitiesList(cities = cities)

                ExtendedFloatingActionButton(
                    modifier = Modifier.padding(all = 8.dp),
                    text = { Text(text = "New City") },
                    icon = { Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Navigate FAB"

                    ) },
                    expanded = mainViewModel.isFabExpanded.value,
                    onClick = { mainViewModel.fabOnClick.value.invoke()  },
                    // containerColor = MaterialTheme.colorScheme.secondary
                )*/
                MainScreen(mainViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(mainViewModel: MainViewModel) {

    val context = LocalContext.current
    val listState = rememberLazyListState()
    val expandedFabState = remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }

    LaunchedEffect(
        key1 = expandedFabState.value,
        block = {
            mainViewModel.isFabExpanded.value = expandedFabState.value
        })

    LaunchedEffect(
        key1 = Unit,
        block = {
            mainViewModel.fabOnClick.value =
                { Toast.makeText(context, "New City", Toast.LENGTH_SHORT).show() }
        })

    LaunchedEffect(
        key1 = Unit,
        block = {
            mainViewModel.itemOnClick.value =
                { Toast.makeText(context, "Details", Toast.LENGTH_SHORT).show() }
        })

    CitiesList(listState, mainViewModel)

    NewCityFab(mainViewModel)
}

@Composable
fun NewCityFab(mainViewModel: MainViewModel) {
    ExtendedFloatingActionButton(
        modifier = Modifier.padding(all = 8.dp),
        text = { Text(text = "New City") },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Navigate FAB"

            )
        },
        expanded = mainViewModel.isFabExpanded.value,
        onClick = { mainViewModel.fabOnClick.value.invoke() },
        // containerColor = MaterialTheme.colorScheme.secondary
    )
}

@Composable
private fun CitiesList(state: LazyListState, mainViewModel: MainViewModel, cities: List<City> = data) {
    LazyColumn(state = state, modifier = Modifier.fillMaxSize()) {
        items(cities) { city ->
            CityItem(city, mainViewModel)
        }
    }
}

@Composable
private fun CityItem(city: City, mainViewModel: MainViewModel) {
    ElevatedCard(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .clickable { mainViewModel.itemOnClick.value.invoke() }
    ) {
        Surface(modifier = Modifier.padding(all = 8.dp)) {
            Text(text = city.name)
        }
    }
}

private data class City(val id: Long, val name: String, val lat: Double, val lon: Double)

private val data = listOf<City>(
    City(id = 1, name = "Paris", lat = 0.0, lon = 0.0),
    City(id = 2, name = "London", lat = 0.0, lon = 0.0),
    City(id = 3, name = "New York", lat = 0.0, lon = 0.0),
    City(id = 4, name = "Lisbon", lat = 0.0, lon = 0.0),
    City(id = 5, name = "Madrid", lat = 0.0, lon = 0.0),
    City(id = 6, name = "Budapest", lat = 0.0, lon = 0.0),
    City(id = 7, name = "Tunis", lat = 0.0, lon = 0.0),
    City(id = 8, name = "Tokyo", lat = 0.0, lon = 0.0),
    City(id = 9, name = "Seoul", lat = 0.0, lon = 0.0),
    City(id = 10, name = "Cairo", lat = 0.0, lon = 0.0),
    City(id = 11, name = "Dubai", lat = 0.0, lon = 0.0),
    City(id = 12, name = "California", lat = 0.0, lon = 0.0),
    City(id = 13, name = "Berlin", lat = 0.0, lon = 0.0),
    City(id = 14, name = "Istanbul", lat = 0.0, lon = 0.0)
)