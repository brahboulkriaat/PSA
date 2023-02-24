package com.brahimboulkriaat.psa.ui

import android.os.Bundle
import android.util.Log
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brahimboulkriaat.psa.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import com.brahimboulkriaat.psa.model.City as C

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                MainScreen(mainViewModel)
            }
        }

        mainViewModel.responseState.observe(this) {
            when (it) {
                is DataState.Success<Int> -> {
                    Log.d(TAG, "Success : ${it.data}")
                    // displayProgressBar(false)
                    // appendBlogTitles(it.result)
                }
                is DataState.Error -> {
                    Log.d(TAG, "Error : ${it.error.message}")
                    // displayProgressBar(false)
                    // displayError(it.error.message)
                }
                else -> {
                    Log.d(TAG, "Loading")
                    // displayProgressBar(true)
                }
            }
        }

        mainViewModel.launchRequest()
    }
}

@Composable
private fun MainScreen(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavGraph(navController, mainViewModel)
}

@Composable
private fun NavGraph(navController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = "home") {

        composable(route = "home") {
            HomeScreen(mainViewModel = mainViewModel, navController = navController)
        }

        composable(
            route = "details"/*,
            arguments = listOf(navArgument("id") {
                type = NavType.LongType
                // defaultValue = 0L
            }, navArgument("lon") {
                type = NavType.FloatType
                defaultValue = 0.0F
            }, navArgument("lat") {
                type = NavType.FloatType
                defaultValue = 0.0F
            })*/
        ) {
            DetailsScreen()
        }

        composable(route = "new") {
            NewScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen() {
    Scaffold( topBar = { TopAppBar(title = { Text("Details") }) }) {
        Surface(modifier = Modifier.padding(it)) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewScreen() {
    Scaffold( topBar = { TopAppBar(title = { Text("New") }) }) {
        Surface(modifier = Modifier.padding(it)) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(mainViewModel: MainViewModel, navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Cities") }) },
        floatingActionButton = { NewCityFab(mainViewModel) }
    ) {
        Surface(modifier = Modifier.padding(it)) {
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
                        {
                            //Toast.makeText(context, "New City", Toast.LENGTH_SHORT).show()
                            navController.navigate("new")
                        }
                })

            LaunchedEffect(
                key1 = Unit,
                block = {
                    mainViewModel.itemOnClick.value =
                        {
                            // Toast.makeText(context, "Details", Toast.LENGTH_SHORT).show()
                            navController.navigate("details")
                        }
                })

            CitiesList(listState, mainViewModel)
        }
    }
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
private fun CitiesList(
    state: LazyListState,
    mainViewModel: MainViewModel,
    cities: List<City> = data
) {
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